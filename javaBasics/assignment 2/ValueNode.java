//The ValueNode File is used dimensionally

class ValueNode {// Specific computed Variable
    private int value;// The Act
    private int rowNum;// These are the trace list of each value
    private int columnNum;
	private ValueNode nextColValNode;
	private ValueNode nextRowValNode;

	public ValueNode() {// Constructor for parent
		value = 0;
		nextColValNode = null;
		nextRowValNode = null;
	}

	public void displayValueNode() {
		System.out.print(value + " ");
	}

	// Setters
	public void setValue(int d) {
		value = d;
    }
    public void setRowNum(int rowID){
        rowNum=rowID;
    }
    public void setColNum(int columnID){
        columnNum=columnID;
    }
    
	public void setNextColValNode(ValueNode nextNumber) {
		nextColValNode = nextNumber;
	}

	public void setNextRowValNode(ValueNode underThis) {
		nextRowValNode = underThis;
	}

    // Getters
    public int getRowNum(){// linked list
        return rowNum;
    }
    public int getColumnNum(){// linked list
        return columnNum;
    }
	public int getValue() {// what is it
		return value;
	}
	public ValueNode getNext() {
		return nextColValNode;
	}
	public ValueNode getDown() {
		return nextRowValNode;
	}
}