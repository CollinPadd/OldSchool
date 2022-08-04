//Collin Paddock lab5 Node Class
public class Node{// node class
    private Integer value=null;
    Node left, right;// this is left right
    public Node(int value){//comment for constructor, left right null at start
        left=null;//
        right=null;
        insert(value);
    }
    public Node getLeftChild(){// new 
        return left;
    }
    public Node getRightChild(){//new
        return right;
    }
    public Integer getValue(){// pull specific node value ( is private)
        return this.value;

    }
    public void insert(int value){//insert this node's value
       this.value=value;//3pts for comments isnt a lot
    }
}
