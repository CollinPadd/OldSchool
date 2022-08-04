// public class MatrixColumn {
// 	private int size;
// 	private ValueNode firstValNode;
// 	private MatrixColumn nextColumn;

// 	public MatrixColumn() {
// 		size = 0;
// 		firstValNode = null;
// 	}

// 	public boolean isEmpty() {
// 		return (firstValNode == null);
// 	}

// 	public void insert(int s) {
// 		size = s;
// 		for (int i = 1; i <= size; i++) {
//             ValueNode newValueNode = new ValueNode();
//             newValueNode.setColNum(i);
// 			if (isEmpty()) {
// 				firstValNode = newValueNode;
// 				//firstValNode.setNextColValNode(null);
// 			} else if (firstValNode.getNext() == null) {
// 				firstValNode.setNextColValNode(newValueNode);
// 				newValueNode.setNextColValNode(firstValNode);
// 				firstValNode = newValueNode;
// 			} else {
// 				newValueNode.setNextColValNode(firstValNode.getNext());
// 				firstValNode.setNextColValNode(newValueNode);
// 				firstValNode = newValueNode;
// 			}
// 		}
// 		if (!(firstValNode.getNext() == null)) {
// 			getNextColumn(); // To point firstValNode back to first ValueNode
// 		}

// 	}
    
      
    
// 	public void display() {
// 		//System.out.println("firstValNode --> End");
// 		for (int i = 1; i <= getSize(); i++ ) {
// 			firstValNode.displayValueNode();
// 			if (!(firstValNode.getNext() == null)) {
// 				getNextColumn();
// 			}
// 		}
// 		System.out.println();
// 	}

// 	public void getNextColumn() {
// 		firstValNode = firstValNode.getNext();
// 	}

// 	public ValueNode getfirstValNode() {
// 		return firstValNode;
// 	}

// 	public int getSize() {
// 		return size;
// 	}
// 	public MatrixColumn getNextMatrixColumn() { 
// 		return nextColumn;  }  // set the next node
// 	public void setNextMatrixColumn(MatrixColumn n) {
// 		this.nextColumn = n;  }

// }
public class MatrixColumn {
	private int anyValue;
	private ValueNode firstValNode;
	private MatrixColumn nextColumn;

	public MatrixColumn() {// constructor
		anyValue = 0;
		firstValNode = null;
	}
	public MatrixColumn getNextMatrixColumn() { // used to call upon next linked column
		return nextColumn;  }  // set the next node
	public void setNextMatrixColumn(MatrixColumn n) {
		this.nextColumn = n;  }// n represemnds the node attached 

	public boolean isEmpty() {
		return (firstValNode == null);
	}

	public void insert(int s) {
		anyValue = s;
		for (int i = 1; i <= anyValue; i++) {
            ValueNode newValueNode = new ValueNode();
            newValueNode.setRowNum(i);
			if (isEmpty()) {
				firstValNode = newValueNode;
			}
			 else if (firstValNode.getNext() == null) {
				firstValNode.setNextRowValNode(newValueNode);
				newValueNode.setNextRowValNode(firstValNode);
				firstValNode = newValueNode;
			} 
			else {
				newValueNode.setNextRowValNode(firstValNode.getDown());// The valut in the next row
				firstValNode.setNextRowValNode(newValueNode);
				firstValNode = newValueNode;
			}
		}
		if (!(firstValNode.getNext() == null)) {
			getNextColumn(); // To point firstValNode back to first ValueNode
		}

	}
	public void display() {
		for (int i = 1; i <= getanyValue(); i++ ) {
			firstValNode.displayValueNode();
			if (!(firstValNode.getNext() == null)) {//When the last Node is called call upon the linked column
				getNextColumn();
			}
		}
		System.out.println();
	}
	public void getNextColumn() {//singly nodes
		firstValNode = firstValNode.getNext();//
	}
	public ValueNode getfirstValNode() {// The Column holds the very First of the LINKED list values
		return firstValNode;
	}

	public int getanyValue() {//The entirety of the file summed up as an integer
		return anyValue;
	}
	
}