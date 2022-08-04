public class SearchTree<E extends Comparable<E>> {
    private SearchTreeNode overallRoot;
    public SearchTree(){
        overallRoot = null;
    }
    public void add(E value){
        overallRoot = add(overallRoot,value);
    }
    private SearchTreeNode<E> add(SearchTreeNode<E> root, E value){
        
        if (root==null){
            root=new SearchTreeNode<E>(value);
        } else if (value.compareTo(root.data)<=0){
            root.left = add(root.left, value);
        } else {
            root.right = add(root.right, value);
        }
        return root;
    }
    public boolean contains(E value){
        return contains(overallRoot, value);
    }
    private boolean contains(SearchTreeNode<E> root, E value){
        if (root==null){
            return false;
        }  
        else{
            int compare = value.compareTo(root.data);
             if (compare == 0){
            return true;
        } 
        else if (compare < 0){
            return contains(root.left, value);
        }
        else{
            return contains(root.right, value);
        }
    }
    }
    public void print(){
        printInOrder(overallRoot);
        System.out.println();
    }
    private void printInOrder(SearchTreeNode root){
        if (root!= null){
            printInOrder(root.left);
            System.out.print(root.data + " ");
            printInOrder(root.right);
        }
    }
}