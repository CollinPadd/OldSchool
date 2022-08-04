import java.util.Arrays; 
public class quicksortex{
    public static void main(String[] args) {
    int[] array = { 3, 4, 2, 3 ,70};
    System.out.println("Original Array:");
                System.out.println(Arrays.toString(array));
                quicksortex ob = new quicksortex();
                ob.quickSort(array);
                System.out.println("New Array:");
                System.out.println(Arrays.toString(array));
    }
    static void quickSort(int[] array){
        myQuickSort(array, 0, array.length - 1);
    }

    public static void myQuickSort(int arr[], int begin, int end) {
    if (begin < end) {
        int partitionIndex = partition(arr, begin, end);

            myQuickSort(arr, begin, partitionIndex - 1);
            myQuickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(int arr[], int begin, int end) {
    int pivot = arr[end];
    int i = (begin-1);
 
    for (int j = begin; j < end; j++) {
        if (arr[j] <= pivot) {
            i++;
 
            int swapTemp = arr[i];
            arr[i] = arr[j];
            arr[j] = swapTemp;
        }
    }
 
    int swapTemp = arr[i+1];
    arr[i+1] = arr[end];
    arr[end] = swapTemp;
 
    return i+1;
}
}