public class MinHeap { 
    private int[] Heap; 
    private int size; 
    private int maxsize; 
  
    // Constructor to initialize an 
    // empty max heap with given maximum 
	// capacity. 
	public int getSize(){
		return size;
	}
    public MinHeap(int maxsize) 
    { 
        this.maxsize = maxsize; 
        this.size = 0; 
        Heap = new int[this.maxsize + 1]; 
        Heap[0] = Integer.MIN_VALUE; 
    } 
  
    // Returns position of parent 
    private int parent(int pos) 
    { 
        return pos / 2; 
    } 
  
    // Below two functions return left and 
    // right children. 
    private int leftChild(int pos) 
    { 
        return (2 * pos); 
    } 
    private int rightChild(int pos) 
    { 
        return ((2 * pos) + 1); 
    } 
  
    // Returns true of given node is leaf 
    private boolean isLeaf(int pos) 
    { 
        if (pos > (size / 2) && pos <= size+1) { 
            return true; 
        } 
        return false; 
    } 
    public int[] arrayHeap(){
        return Heap;
    }
    private void swap(int fpos, int spos) 
    { 
        int tmp; 
        tmp = Heap[fpos]; 
        Heap[fpos] = Heap[spos]; 
        Heap[spos] = tmp; 
    } 
  
    // A recursive function to max heapify the given 
    // subtree. This function assumes that the left and 
    // right subtrees are already heapified, we only need 
    // to fix the root. 
    public void minHeapify(int pos) 
    { 
        if (isLeaf(pos)) 
            return; 
  
        if (Heap[pos] > Heap[leftChild(pos)] ||  
            Heap[pos] > Heap[rightChild(pos)]) { 
  
            if (Heap[leftChild(pos)] < Heap[rightChild(pos)]) { 
                swap(pos, leftChild(pos)); 
                minHeapify(leftChild(pos)); 
            } 
            else { 
                swap(pos, rightChild(pos)); 
                minHeapify(rightChild(pos)); 
            } 
        } 
    } 
  
    // Inserts a new element to max heap 
    public void insert(int element) 
    { 
        size = size+1;
        Heap[size] = element; 
  
        // Traverse up and fix violated property 
        int current = size; 
        while (Heap[current] < Heap[parent(current)]) { 
            swap(current, parent(current)); 
            current = parent(current); 
        } 
    } 
    public boolean contains(int value){
        for(int i=0;i<size;i++){
            if (Heap[i]==value){
                return true;
            }
        }
        return false;

    }
    
    public void print() 
    { 
        for(int i = 1; i < size +2; i++){
            if(isLeaf(i) == true){//for the even amount of nodes there will only be a left child. maybe use size for that
                System.out.println("Heap Node: " + Heap[i] + ", Left Child: NA, Right Child: NA");
            }else if(size%2 ==0 && (Heap[(2*i) + 1] == 0)){
                System.out.println("Heap Node: " + Heap[i] + ", Left Child: " + Heap[i*2] + ", Right Child: NA");
            }else if(isLeaf(i) == false){
                System.out.println("Heap Node: " + Heap[i] + ", Left Child: " + Heap[i*2] + ", Right Child: " + Heap[(i*2)+1]);
            }
        }
    } 
  
    // Remove an element from max heap 
    public int extractMin() 
    { 
        int popped = Heap[1]; 
        Heap[1] = Heap[size--]; 
        //size = size-1;
        minHeapify(1); 
        return popped; 
    } 
    public void minHeap() 
    { 
        for (int pos = (size / 2); pos >= 1; pos--) { 
            //maxHeapify(pos); 
        } 
    } 
  
    public static void main(String[] arg) 
    { 
        System.out.println("The Max Heap is "); 
        MinHeap maxHeap = new MinHeap(15); 
        maxHeap.insert(234); 
        maxHeap.insert(11); 
        maxHeap.insert(33); 
        maxHeap.insert(249); 
        maxHeap.insert(250); 
        maxHeap.insert(13); 
        maxHeap.insert(12);
      
        
  
        maxHeap.print(); 
        //System.out.println("The max val is " + maxHeap.extractMax()); 
        int num;
        for (int i=0; i< 7;i++){
        num=maxHeap.extractMin();
        System.out.println(num);

        }
       
        

    }
} 
