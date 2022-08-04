import java.util.Scanner;
class Assignment2 {//

// Group 2 
// Collin P Alec H Darren M
    public static void main(String[] args) {
	Scanner doWhat = new Scanner(System.in);
		int numRow;//first Input by user
		int numCol;//second Input
		System.out.println("Please Enter # of Rows");
		Scanner doWhat2 = doWhat;
		numRow=doWhat2.nextInt();
		System.out.println("Please Enter # of Columns");
        numCol=doWhat2.nextInt();
        SparseMatrix firstMatrix= new SparseMatrix();// Not calling Constructor
		firstMatrix.SparseMatrix(numRow, numCol);//
		int bugFix=0;//Scanner Buffer 
		/////////////////////////////Beginning of Loop////////////////////////
		while(true) {//
			System.out.println("Select Option");
			System.out.println("Input Desired 'insert' 'quit' 'show' 'transpose' ' product'"); 
			String userDo =doWhat.next();
			doWhat.nextLine();// 
			boolean transposedMatrix=false;// 
			switch (userDo) {// please do one of these things
				case "insert":
				// put a number somewhere
					System.out.println("row number: ");
					int rowWhat = doWhat.nextInt();
					System.out.println("column number: ");
					int colWhat = doWhat.nextInt();
					System.out.println("Row number " + rowWhat + " and  Column number " + colWhat + " :");
					int rcValue = doWhat.nextInt();
					firstMatrix.insert(rowWhat,colWhat,rcValue);
					break;
				case "show":
					//see the Matrix transposed as a matrix
					if (transposedMatrix=true){//show first Matrix Pristine and worthy of an A
						System.out.println("Your Matrix ");
						firstMatrix.displayMatrix();// give the user this
					}
					else{//Show user one line Matrix using linked lists
						firstMatrix.displayAll(firstMatrix.getAll());// every value
					}
					break;
				case "quit"://end it
					System.out.println("Bye");
					System.exit(0);
					break;
				case "transpose":// make a new matrix 
					System.out.println("Please Enter # of Rows");//new matrix row amt
					int newRow=doWhat.nextInt();
					System.out.println("Please Enter # of Columns");//new matrix column amt
					int newCol=doWhat.nextInt();
					firstMatrix=firstMatrix.transposeMatrix(firstMatrix.getAll(), newRow, newCol);
					firstMatrix.displayAll(firstMatrix.getAll());
					transposedMatrix=true;//used to show user the flat matrix to make it easier
					break;
				case "product":
					System.out.println("Please Enter new matrix Data");
					SparseMatrix secondMatrix=new SparseMatrix();// creating an additional matrix to take inputs
					secondMatrix.SparseMatrix(numRow, numCol);
					SparseMatrix returnThisMatrix= new SparseMatrix();
				while (transposedMatrix==false) {//this is overly complicated for An Array A
					System.out.println("Input Desired 'insert' 'compute' 'quit' 'back After Computing'");// All of these work
					String userDoTwo = doWhat.next();//next user input
					doWhat.nextLine()
					switch (userDoTwo) {
						// keep it shortHand
					case "insert":// to be assigned an ID
						System.out.println("Requesting Row Number: ");
						int rowWhatTwo = doWhat.nextInt();
						System.out.println("Requesting Column number: ");
						int colWhatTwo = doWhat.nextInt();
						System.out.println("Row number " + rowWhatTwo + " and Column number " + colWhatTwo + ":");
						int rcValueTwo = doWhat.nextInt();
						secondMatrix.insert(rowWhatTwo, colWhatTwo, rcValueTwo);// quick way of displaying
						break;

					case "compute":// runs the file as processed
						returnThisMatrix = returnThisMatrix.product(firstMatrix, secondMatrix);// Shorthand Calculation
						// computed.displayAll(computed.getAll());
						break;

					case "quit":// i used this a thousand times
						System.out.println("thank you");
						System.exit(0);// This cuts it off
						break;

					case "back":// take the user back to previous matrix
						transposedMatrix=true;
						break;


					default:// I am sorry
						System.out.println("Something went Wrong");
					}
				}
					case "see":
					firstMatrix.displayAll(firstMatrix.getAll());
					break;
				default:// Used to make file process
						System.out.println("Here are my inputs");// this makes the file more intricate
					break;
			} //End of Switch Statement
		}//End of Assignment2(need A)   
    }//end of Main
}//End Thank you