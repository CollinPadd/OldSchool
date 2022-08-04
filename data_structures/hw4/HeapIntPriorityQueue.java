public class HeapIntPriorityQueue{
    private int[] elementData;
    private int size;
    public HealIntPriorityQueue(){
        elementData= new int[10];
        size=0;
    }
    private int parent(int index){
        return index/2;
    }
    private int leftChild(int index){
        return index*2;
    }
    private int rightChild(int index){
        return index *2 +1;
    }
    private boolean hasParent(int index){
        return index>1;
    }
    private boolean hasLeftChild(int index){
        return leftChild(index) <= size;
    }
    private boolean hasRightChild(int index){
        return rightChild(index)<= size;
    }
    private void swap(int[] a, int index1, int index2){
        int temp = a[index1];
        a[index1]=a[index2];
        a[index2]=temp;
    }
    //adds the given element to this queue
    public void add(int value){
        //resize if necessary
        if(size+1 >= elementData.length){
            elementData= Arrays.copyOf(elementData,elementData.length*2);
        }
        elementData[size +1]=value;//add as rightmost leaf

        //bubble up as necessary to fix ordering
        int index = size +1;
        boolean found =false;
        while (!found && hasParent(index)){
            int parent = parent(index);
            if (elementData[index]< elementData[parent]){
                swap(elementData, index, parent(index));
                index = parent(index);

            } else {
                found= true; //found proper location; stop the loop
            }
        }
        size++;
    }
    // removes and returns the minimum value in the queue
    public int remove(){
        // move rightmost leaf to become root
        int result = peak();
        elementData[1] = elementData[size];
        size--;

        //bubble down as necessary to fix ordering
        int index = 1;
        boolean found = false;
        while(!found && hasLeftChild(index)){
            int left = leftChild(index);
            int right = rightChild(index);
            int child = left;
            if(hasRightChild(index)&&elementData[right]<elementData[left]){
                child=right;
            }

            if (elementData[index] > elementData[child]){
                swap(elementData, index, child);
                index= child;
            } else{
                found = true; // found proper location; stop the loop
            }
        }
        return result;
    }
    public static void heapSort(int[] a){
        Queue<Integer> pq = new PriorityQueue<Integer>();
        for (int n:a){
            pq.add(n);
        }
        for (int i=0;i<a.length; i++){
            a[i]=pq.remove();
        }
    }
}