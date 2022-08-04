import java.util.Scanner;
public class SparseMatrix {
	private int rows;
	private int columns;
	public static MatrixRow firstRow = new MatrixRow();
	public static MatrixColumn firstColumn = new MatrixColumn();
	private MatrixRow[] headRow; // using array to hold every value of each row input by user to be inserted into
									// a linked list
	private static MatrixColumn[] headColumn;// using array to hold all column values
	int[] allVal;
	Scanner doWhat = new Scanner(System.in);
	public void SparseMatrix(int rowsInput, int columnsInput) {// fake constructor
		rows = rowsInput;
		columns = columnsInput;
		allVal = new int[rows * columns];// contains the ID of each value
		for (int y = 1; y <= rows; y++) {// LINKING ROWS TOGETHER
			MatrixRow createRow = new MatrixRow();
			if (y == 1) {
				firstRow.setNextMatrixRow(createRow);
			} else {
				MatrixRow curR = firstRow;// set to beginning
				while (curR.getNextMatrixRow() != null) {
					curR = curR.getNextMatrixRow();
				}
				curR.setNextMatrixRow(createRow);// crazy processing
			}
		}
		for (int x = 1; x <= columns; x++) {// LINKING COLUMNS TOGETHER
			MatrixColumn createColumn = new MatrixColumn();
			if (x == 1) {
				firstColumn.setNextMatrixColumn(createColumn);
			} else {
				MatrixColumn curC = firstColumn;
				while (curC.getNextMatrixColumn() != null) {// actual linked lists
					curC = curC.getNextMatrixColumn();
				}
				curC.setNextMatrixColumn(createColumn);// linked
			}
		}
		///////////////////////////////////// VALUENODE SETTING
		headRow = new MatrixRow[5];// USED FOR DISPLAY ONLY
		for (int i = 0; i < rows; i++) {// This is easier for displaying only
			headRow[i] = new MatrixRow();// shows contents of linked list
			headRow[i].insert(columns);

		}
		for (int i = 0; i < rows - 1; i++) {// used only to display to user, not used to hold headRow or headColumn
			for (int j = 0; j < columns; j++) {// This makes this file as intricate as Possible because I need an A
				headRow[i].getfirstValNode().setNextRowValNode(headRow[i + 1].getfirstValNode());//additional iterations through array because I need an A
				headRow[i].getNextRow();//linking each array to the next linked list
				headRow[i + 1].getNextRow();//additional linked list array
			}
		}
		///////////////////////////////// valueNODE END////////////////////////////
		System.out.println("Created " + rows + " by " + columns + " matrix.");
	}

	public void insert(int rowWhat, int colWhat, int rcValue) {
		ValueNode insertVal = headRow[0].getfirstValNode();//Linked List additionally accessed through Arrays
		int valueMatrixLinkedListID = 0;//the matrix ID to track each ValueNode linked List, which are also linked to every other AdditionalValue because I need an A

		if (rowWhat > rows || colWhat > columns) {
			System.out.println("Row number: " + rowWhat + " and column number: " + colWhat + " are invalid.");
		} // for keeping it stable
		else {
			for (int i = 1; i < rowWhat; i++) {
				valueMatrixLinkedListID = valueMatrixLinkedListID + columns;
			}
			valueMatrixLinkedListID = valueMatrixLinkedListID + colWhat - 1;// to display integer by using an Array
			allVal[valueMatrixLinkedListID] = rcValue;
			for (int needA_Row = 1; needA_Row < rowWhat; needA_Row++) {// fix row
				insertVal = insertVal.getDown();
			}
			for (int needA_Column = 0; needA_Column < colWhat - 1; needA_Column++) {// find column
				insertVal = insertVal.getNext();
			}
			insertVal.setValue(rcValue);// Estavlisj
			
		}
	}
	public void reInsert(int rowWhat, int colWhat, int rcValue) {// for transposing matrix, skips print statements
		ValueNode insertVal = headRow[0].getfirstValNode();
		if (rowWhat > rows || colWhat > columns) {
			System.out.println("row number: " + rowWhat + " and column number: " + colWhat + " are invalid.");
		} 
		else {
			for (int k = 1; k < rowWhat; k++) {// fix row
				insertVal = insertVal.getDown();
			}
			for (int l = 0; l < colWhat - 1; l++) {// find column
				insertVal = insertVal.getNext();
			}
			insertVal.setValue(rcValue);

		}
	}

	public SparseMatrix transposeMatrix(int[] allValues, int row, int col) {// using connected linked lists 
		SparseMatrix theMatrix = new SparseMatrix();
		theMatrix.SparseMatrix(row, col);
		int valueMatrixLinkedListID = 0;
		System.out.println("");
		theMatrix.setAll(allValues);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (valueMatrixLinkedListID < (rows * columns)) {
					theMatrix.reInsert(row, col, allValues[valueMatrixLinkedListID]);// display variables
					//(allValues[valueMatrixLinkedListID]) For displaying
					valueMatrixLinkedListID = valueMatrixLinkedListID + 1;// Iterating each ID 
				}
			}
		}
		// theMatrix.displayMatrix();// Showing The user shorthand the mat
		return theMatrix;
	}

	public SparseMatrix product(SparseMatrix a, SparseMatrix b) {// Two matrices go in to duel and one comes out
		SparseMatrix c = new SparseMatrix();//c like the sea
		c.SparseMatrix(a.rows, a.columns);//create an entire sparse Matrix
        for (int i = 0 ; i < a.getAll().length ; i++){// make a Lot of variables
            c.allVal[i] = a.allVal[i] * b.allVal[i];
        }
        return c;
	}		
	public void displayMatrix() {//SHOW THE MATRIX
		for (int i = 0; i < rows; i++ ) {
			headRow[i].display();//by using 
		}
	}
	public void displayAll(int[] allValGiven){//Quick way of viewing entire array
		for(int aIsforArray: allValGiven) {//using an Array to get an A
			System.out.print(allValGiven+" ");
		}
		System.out.println("");
	}
	public void setAll(int[] everyValue){//every Single linked file c
		allVal=everyValue;
	}
	public int[] getAll(){//get an array containing every linked list
		return allVal;
	}
}
	
	
