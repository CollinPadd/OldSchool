// Name: Collin Paddock
// Description: HeapMax
// Date: March 12, 2020
// HeapMax class 
    public class HeapMax {
    // class variables private int[] Heap; private int size; private int maxSize;
    // Constructor public HeapMax(int maxSize){ }
    private int[] Heap;
    private int maxSize;
    private int size; 
    public HeapMax(int maxSize){
        this.maxSize = maxSize; 
            this.size = 0; 
            Heap = new int[this.maxSize + 1]; 
            Heap[0] = Integer.MAX_VALUE; 
    }
    // Returns position of parent private int getParent(int pos){ }
    private int getParent(int index){
        return index/2;
    }
    // Calculate the index positions of the left and right children private int getLeftChild(int pos){ } private int getRightChild(int pos){ }
    private int getLeftChild(int index){
        return index*2;
    }
    private int getRightChild(int index){
        return index*2 +1;
    }
    // Boolean if node is a leaf private boolean isLeaf(int pos) { }
    private boolean isLeaf(int pos) 
        { 
            if (pos > (size / 2) && pos <= size) { 
                return true; 
            } 
            return false; 
        } 
    // standard function to swap two values private void swap(int index1, int index2) { }
    private void swap(int index1, int index2){
        int temp = Heap[index1];
        Heap[index1]=Heap[index2];
        Heap[index2]=temp;
    }
    // Inserts a new element into the heap public void insert(int element){ }
    public void insert(int value){
        //resize if necessary
        Heap[++size] = value; 
        // Traverse up and fix violated property 
        int current = size; 
        while (Heap[current] > Heap[getParent(current)]) { 
            swap(current, getParent(current)); 
            current = getParent(current); 
        } 
    }
    // print the contents of the heap public void printMaxHeap(){ }
    public void printMaxHeap() // print the children of every node
    { 
        for (int i = 1; i <= size ; i++) { 
            if(!isLeaf(i)){// not for leafs
                System.out.print(" Heap Node : " + Heap[i] );
                if (Heap[2 * i]==0){// turn 0 into n/a
                    System.out.print(" left child : N/A");
                }
                else{// for non leaf
                    System.out.print(" left child : " + Heap[2 * i]);
                }
                if(Heap[2 * i + 1]==0){
                    System.out.print(" right child : N/A");
                }
                else{
                    System.out.print(" right child :" + Heap[2 * i + 1]); 
                }
                System.out.println(); 
            }
            else{// when children are both 0
            System.out.print(" Heap Node : " + Heap[i]  + " left child : N/A"  + " right child : N/A"); 
            System.out.println(); //
            }
        } 
    } 
}
