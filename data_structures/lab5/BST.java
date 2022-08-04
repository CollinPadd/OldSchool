//Collin Paddock
//Lab 5 BST
//new game plus
import java.io.IOException;// allowed imports
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class BST{
    private static Node overallRoot;// for print reference
    private static String inOrderTraverseStr="";
    public BST(){ // will be called myBST
        overallRoot=null;// construct with no root
    }
    public static void updateRoot(Node rootNode){//new
        overallRoot=rootNode;
    }
    public static Node getRoot(){
        return overallRoot;// for print statements
    }
    private static void treeTest_1(){//straightforward test case with results the same as in lab
        BST treeTest = new BST();// NEW BST
        treeTest.insert(4);
        treeTest.insert(2);
        treeTest.insert(1);
        treeTest.insert(3);
        treeTest.insert(9);
        treeTest.insert(8);
        inOrderTraverseStr = "";
        System.out.print("Inorder Traversal : ");
        treeTest.printInOrder(treeTest.getRoot());//lab sample 
        System.out.println("");
        inOrderTraverse(treeTest.getRoot());
        System.out.println("nodeChildrenTrav: " + inOrderTraverseStr);
        if (inOrderTraverseStr.equals("n1n123n3n249n8n89n")){ 
            System.out.print("treeTest_1 passed\n");
        }
        else{ 
            System.out.println("treeTest_1 FAILED\n");
        }
    }
    public static void inOrderTraverse(Node node){
        if (node!=null){
            inOrderTraverse(node.left); // recursive call to left child
            Node lNode=node.getLeftChild();// Get the "value" of the left child using the getLeftChild function
            if(lNode==null){//  If the "value" of the left child is null, append "n" to inOrderTraverseStr
                inOrderTraverseStr=inOrderTraverseStr+"n";
            }
            else{ // else append the left node’s value.
                inOrderTraverseStr=inOrderTraverseStr+lNode.getValue();
            }
            inOrderTraverseStr=inOrderTraverseStr+node.getValue();// Get the "value" of node that was passed as an argument, and append the value to inOrderTraverseStr.
            Node rNode=node.getRightChild();// Get the "value" of the right child using the getRightChild function in Node.
            if(rNode==null){// If the "value" of the right child is null, append "n" to inOrderTraverseStr
                inOrderTraverseStr=inOrderTraverseStr+"n";
            }
            else{ //else append the right node’s // value.
                inOrderTraverseStr=inOrderTraverseStr+rNode.getValue();
            }
            inOrderTraverse(node.right); // recursive call to the right child
        }
    }
    private static void rightRotateTest_1(){
        BST rotateTest = new BST();// NEW BST
        rotateTest.insert(4);//add specified elements
        rotateTest.insert(2);
        rotateTest.insert(1);
        rotateTest.insert(3);
        rotateTest.insert(9);
        rotateTest.insert(8);
        System.out.print("In Order traversal : ");
        rotateTest.printInOrder(rotateTest.getRoot());
        inOrderTraverseStr = "";// lab sample
        inOrderTraverse(rotateTest.getRoot());
        System.out.println("");
        System.out.println("PreRotation: " + inOrderTraverseStr);
        rotateTest.updateRoot(rightRotate(rotateTest.getRoot()));//perform rotation and root update
        inOrderTraverseStr = "";
        inOrderTraverse(rotateTest.getRoot());
        System.out.println("PostRotation: " + inOrderTraverseStr);
        if (inOrderTraverseStr.equals("n1n124n3n349n8n89n")){ //different than last test
            System.out.print("rightRotateTest_1 passed\n"); 
        }
        else{ System.out.println("rightRotateTest_1 FAILED\n");
        }
    }
    public static Node rightRotate(Node pivot){
         Node aNode = pivot.left;// new root
         pivot.left = aNode.right;//reassign old left (now root) to old right
         aNode.right=pivot;// root right
         return aNode;//return value that was lifted
    }
    public static void insert(int value){// adding value,
        Node newNode = new Node(value);
		if (overallRoot==null){//first node
			overallRoot = newNode;
			return;
        }
        Node from = null; //will be parent
		Node thisNode = overallRoot;
		while(true){// return after this
			from = thisNode;
			if(value<thisNode.getValue()){// sort to left because less than 
				thisNode = thisNode.left;
				if(null==thisNode){
                    from.left = newNode;// set left
					return;
				}
            }
            else{// sort to right
				thisNode = thisNode.right;
				if(thisNode==null){
					from.right = newNode;
					return;
				}
			}
		}
    }
    public static void printInOrder(Node Node){// left print right
        if (Node!=null){
            printInOrder(Node.left);
            System.out.print(Node.getValue()+" ");
            printInOrder(Node.right);
        }
    }
    public static void printPreOrder(Node Node){ 
        if(Node !=  null) {// print left right
               System.out.print(" " +Node.getValue());
               printPreOrder(Node.left);
               printPreOrder(Node.right);
        }
    }
    public static void printPostOrder(Node Node){ 
        if(Node !=  null) {//left right print
            printPostOrder(Node.left);
            printPostOrder(Node.right);
            System.out.print(" " +Node.getValue());
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader doWhat = new BufferedReader(new InputStreamReader(System.in));// bufferedreader
        System.out.println("Type integers, press return after each one.");// same as lab
        System.out.println("Type End when Done. ");
        String addNode="";
        boolean loop=true;
        //just type end
        while (loop) {// for base case "end" just y
            addNode=(doWhat.readLine());
            if(addNode.equalsIgnoreCase("end")){//break
                loop=false;
            }
            else{
                BST.insert(Integer.valueOf(addNode));// inserting node parsing value
            }
        }
        // requested responses
        System.out.print("In Order Traversal : ");
        BST.printInOrder(BST.getRoot());
        System.out.println("");
        System.out.println("======================");//flair
        System.out.println("treeTest_1");
        System.out.println("======================");
        treeTest_1();//perform test
        System.out.println("======================");
        System.out.println("rightRotateTest_1");
        System.out.println("======================");
        rightRotateTest_1();//perform test
        // 50pts thx
    }
}
