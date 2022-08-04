// Program Behavior, User’s perspective
// Your program must :
//• Prompt the user for the size of the array, n, and create an array with n integer entries, chosen randomly from [−n,n].
//• Prompt the user to specify if merge sort, quick sort, insertion sort, radix sort, or all of those 4 sorts, should be employed to sort the entries of the array of size n. The options should be one letter designated, m, q, i and r, else all.
//• If all sorts are specified, the input to each sort must be identical.
//• If n < 20, the pre-sorted and sorted array’s contents are printed for each sort invoked. 
//• If n ≥ 20, the pre-sorted and sorted array’s contents are NOT printed for each sort invoked. 
//• The count of comparisons performed is/are output.

import java.util.Arrays;  
import java.util.Scanner;
public class SortCompare{
public static void main(String[] args){
    Integer inputSize; 
    final Scanner doWhat = new Scanner(System.in);
    System.out.println("Operation Input Array Size(Number)");//Greeting
    inputSize = doWhat.nextInt();
    /////////////create array///////////
    // int[] array = new int[inputSize];
    int[] array = {13,12,11,10,9,8,7,6,5,4,3,2,1};
    String inputCommand; 
    System.out.println("Operation Input Sort Style(Letter)");
    while(true){
        
        inputCommand= doWhat.nextLine();
        if (inputCommand.equalsIgnoreCase("m")){
                hw1 ob = new hw1();
                System.out.println("Original Array:");
        System.out.println(Arrays.toString(array));
            ob.mergeSort(array);
            System.out.println("Sorted Array:");
        System.out.println(Arrays.toString(array));
            }
            if (inputCommand.equalsIgnoreCase("q")) {
                //// quicksort
            }
            if (inputCommand.equalsIgnoreCase("i")) {
                //// index sort
            }
            if (inputCommand.equalsIgnoreCase("r")) {
                //// radix sort
            }


        }

    }

    static void myMerge(int array[], int arrayStart[], int arrayEnd[], int split, int backEnd) {
        int indexStart = 0;
        int indexEnd = 0;
        int indexMain = 0;
        while (indexStart<split && indexEnd<backEnd){
            if (arrayEnd[indexEnd] >= arrayStart[indexStart]){
                array[indexMain++]=arrayStart[indexStart++];
            }
            else{
                array[indexMain++] = arrayEnd[indexEnd++];
            }
        }
        while (indexStart<split){
            array[indexMain++]=arrayStart[indexMain++];
        }
        while (indexEnd<backEnd){
            array[indexMain++]=arrayEnd[indexEnd++];
        }
    }

    public static int[] arraySplitStart(int array[], int start, int end) {
        int split = (int) (Math.floor(start + end) / 2);
        int arrayStart[] = new int[split];
        for (int i = 0; i < split; i++) {
            arrayStart[i] = array[i];
        }
        return arrayStart;

    }

    public static int[] arraySplitEnd(int array[], int start, int end) {
        int split = (int) (Math.floor(start + end) / 2);
        int arrayEnd[] = new int[array.length -split];
        for (int i = split; i < end; i++) {
            arrayEnd[i-split] = array[i];
        }
        return arrayEnd;

    }

    public void mergeSort(int[] array) {
        int start = 0;
        int end = array.length - 1;
        int split = (int) (Math.floor(start + end) / 2);
        if (start < end) {
            int arrayStart[] = arraySplitStart(array, start, end);
            int arrayEnd[] = arraySplitEnd(array, start, end);
            mergeSort(arrayStart);
            mergeSort(arrayEnd);
            // merge function which takes in the input and both the sub-arrays and the
            // starting and end indices of both the sub arrays.
            myMerge(array, arrayStart, arrayEnd, split, end - split);
            
            
            
            
        }
        
      }  
    

    // quickSort(int[] array){

    // }

    // insertionSort(int[] array){

    // }

    // radixSort(int[] array){

    // }
}