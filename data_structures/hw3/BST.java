import java.util.ArrayList;
public class BST {
    private  Node overallRoot;
    int nodeCount=0;//start 0 nodes
    public Node one= new Node("none" ,0);// for checking top 3
    public Node two=new Node("none" ,0);//for checking top 3
    public Node three=new Node("none" ,0);
    public BST() { 
        overallRoot = null;// construct with no root
    }
    public Node getRoot() {
        return overallRoot;// for print statements
    }
    public void printInOrder(Node Node) {// left print right
        if (Node != null) {
            printInOrder(Node.left);
            System.out.print(Node.getValue() + " ");
            printInOrder(Node.right);
        }
    }
    public void analyzeBST(Node Node) {// left print right
        if (Node != null) {
            //System.out.print(one.al.size());
            analyzeBST(Node.left);
            if (Node.al.size()>=2){
                if (Node.al.size()>one.al.size()){
                        one=Node;
                } 
                else if (Node.al.size()>two.al.size()){
                    two=Node;
                } 
                else if (Node.al.size()>three.al.size()){
                    three=Node;
                }
            }
            analyzeBST(Node.right);
        }
    }
    public void printAnalysis(){
        System.out.print( one.getValue()+":"+ one.al.size()+":");
        for(int i=0; i <  one.al.size(); i++){
            System.out.print(one.al.get(i));
            if(!(i+1 == one.al.size())){
                System.out.print(",");
            }
            else{
                System.out.print(" ");
            }
        }
        System.out.print(" "+ two.getValue()+":"+  two.al.size()+":");
        for(int i=0; i < two.al.size(); i++){
            System.out.print(two.al.get(i));
            if(!(i+1 == two.al.size())){
                System.out.print(",");
            }
            else{
                System.out.print(" ");
            }
            }
        System.out.print(" "+three.getValue()+":"+three.al.size()+":");
        for(int i=0; i < three.al.size(); i++){
            System.out.print(three.al.get(i));
            if(!(i+1 ==three.al.size())){
                System.out.print(",");
            }else{
                System.out.print(" ");
            }
        }
    }
    public void insert(String addNode){
        nodeCount++;// increment amount of nodes  for each input
        overallRoot = insert(overallRoot,addNode);
    }
    private Node insert(Node root, String value){//overclocking function
        if (root==null){
            root=new Node(value,nodeCount);
        }
        else if (duplicateExists(root, value)){
            return root;
        }
        else if (value.compareTo(root.getValue())<=0){
            root.left = insert(root.left, value);
        } 
        else{
            root.right = insert(root.right, value);    
        }
        return root;
    }
    public boolean duplicateExists(String value){//used to check if duplicateExists, but we need nodecount from this function found in chapter 17
        return duplicateExists(overallRoot, value);// so we use a helper to differentiate between duplicates
        // this ensures nodecount goes into the right arraylist
    }
    private boolean duplicateExists(Node root, String thisString){// check if node is duplicate
        if (root==null){//non value root
            return false;
        } 
        else{
            int checkThis = thisString.compareTo(root.getValue());
            if (checkThis < 0){
                return duplicateExists(root.left, thisString);}// recursively check left size
            else if (checkThis == 0){
            //System.out.println("duplicate " +nodeCount);
                root.al.add(nodeCount);// add to node's Al
                return true;// found duplicate
            }
            else{
                return duplicateExists(root.right, thisString);//recursively check right side
            }
        }
    }
    public void parseArgs(String value){
        if(doesNotContain(value)){
            System.out.print(value+":0 ");
        }
        else{
            checkThisArg(value, overallRoot);
        }
    }
    public void checkThisArg(String value,Node Node) {// left print right
        if (Node != null) {
            checkThisArg(value,Node.left);
            if(Node.getValue().equalsIgnoreCase(value)){
                System.out.print(value+":"+Node.al.size()+":");
                for(int i=0; i < Node.al.size(); i++){
                    System.out.print(Node.al.get(i));
                    if(!(i+1 == Node.al.size())){
                        System.out.print(",");
                    }
                    else{
                        System.out.print(" ");
                    }
                }
            }
            checkThisArg(value,Node.right);
        }
    }
    public boolean doesNotContain(String value){
        return doesNotContain(overallRoot,value);
    }
    private boolean doesNotContain(Node root, String thisString){// check if node is duplicate
        if (root==null){//non value root
            return true;
        }  
        else{
            int checkThis = thisString.compareTo(root.getValue());
            if (checkThis < 0){
                return doesNotContain(root.left, thisString);
            }// recursively check left size
            else if (checkThis == 0){
                return false;// found duplicate
            }
            else{
                return doesNotContain(root.right, thisString);//recursively check right side
            }
        }
    }
}