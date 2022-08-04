//Collin Paddock
//Lab 4 BST

import java.io.IOException;// allowed imports
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BST{
    
    private static Node overallRoot;// for print reference
    public BST(){ // will be called myBST
        BST.overallRoot=null;// construct with no root
    }
    public static Node getRoot(){
        return overallRoot;// for print statements
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
        BST myBST = new BST();// my BST also error?
        BufferedReader doWhat = new BufferedReader(new InputStreamReader(System.in));// bufferedreader
        System.out.println("Type integers, press return after each one.");// same as lab
        System.out.println("Type End when Done. ");
        String addNode="";
        boolean loop=true;
        while(loop){// for base case "end"
            addNode=(doWhat.readLine());
            if(addNode.equalsIgnoreCase("end")){//break
                loop=false;
            }
            else{
                BST.insert(Integer.valueOf(addNode));// inserting node parsing value
            }
        }
        // requested responses
        // System.out.print("In Order Traversal : ");
        // BST.printInOrder(BST.overallRoot);
        // System.out.println("");
        // System.out.print("Pre Order Traversal :");
        // BST.printPreOrder(BST.overallRoot);
        // System.out.println("");
        System.out.print("Post Order Traversal :");
        printPostOrder(BST.overallRoot);
       
        
        // 55pts thx
    }
}
