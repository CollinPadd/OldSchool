//Collin Paddock lab4 Node Class
public class Node{// node class
    private int value;
    Node left, right;// this is left right
    public Node(int value){//comment for constructor, left right null at start
        left=null;//
        right=null;
        insert(value);
    }
    public int getValue(){// pull specific node value ( is private)
        return this.value;
    }
    public void insert(int value){//insert this node's value
       this.value=value;//3pts for comments isnt a lot
    }
}
