public class MatrixRow {
	private int size;
	private ValueNode firstValNode;
	private MatrixRow nextRow;

	public MatrixRow() {// constructor
		size = 0;
		firstValNode = null;
	}

	public boolean isEmpty() {
		return (firstValNode == null);
	}

	public void insert(int s) {
		size = s;
		for (int i = 1; i <= size; i++) {
            ValueNode newValueNode = new ValueNode();
            newValueNode.setColNum(i);
			if (isEmpty()) {
				firstValNode = newValueNode;
				//firstValNode.setNextColValNode(null);
			} else if (firstValNode.getNext() == null) {
				firstValNode.setNextColValNode(newValueNode);
				newValueNode.setNextColValNode(firstValNode);
				firstValNode = newValueNode;
			} else {
				newValueNode.setNextColValNode(firstValNode.getNext());
				firstValNode.setNextColValNode(newValueNode);
				firstValNode = newValueNode;
			}
		}
		if (!(firstValNode.getNext() == null)) {
			getNextRow(); // To point firstValNode back to first ValueNode
		}

	}
	public void display() {//each one shows the row
		//System.out.println("firstValNode --> End");
		for (int i = 1; i <= getSize(); i++ ) {
			firstValNode.displayValueNode();
			if (!(firstValNode.getNext() == null)) {
				getNextRow();
			}
		}
		System.out.println();
	}
	public void getNextRow() {
		firstValNode = firstValNode.getNext();
	}
	public ValueNode getfirstValNode() {
		return firstValNode;
	}

	public int getSize() {
		return size;
	}
	public MatrixRow getNextMatrixRow() { 
		return nextRow;  }  // set the next node
	public void setNextMatrixRow(MatrixRow n) {
		this.nextRow = n;  }

}