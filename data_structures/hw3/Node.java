import java.util.ArrayList;
public class Node{// node class
    private String value;
    Node left, right;// this is left right
    ArrayList<Integer> al = new ArrayList<Integer>();// contains "nodecount" values
    public Node(String value,int nodeCount){//comment for constructor, left right null at start
        left=null;//
        right=null;
        insert(value,nodeCount);// insert the specific nodecount number, IE the nth node is the nodecount
    }
    public String getValue(){// pull specific node value ( is private)
        return this.value;
    }
    public void insert(String value, int nodeCount){//insert this node's value
       this.value=value;
       al.add(nodeCount);// add nodecount as described
    }
}
