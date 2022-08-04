// Collin Paddock
// Cs 241 HomeWork 1
// SortCompare.java
// Below is description of objective


// Program Behavior, User’s perspective
// Your program must :
//• Prompt the user for the size of the array, n, and create an array with n integer entries, chosen randomly from [−n,n].
//• Prompt the user to specify if merge sort, quick sort, insertion sort, radix sort, or all of those 4 sorts, should be employed to sort the entries of the array of size n. The options should be one letter designated, m, q, i and r, else all.
//• If all sorts are specified, the input to each sort must be identical.
//• If n < 20, the pre-sorted and sorted array’s contents are printed for each sort invoked. 
//• If n ≥ 20, the pre-sorted and sorted array’s contents are NOT printed for each sort invoked. 
//• The count of comparisons performed is/are output.
 

// using array util to parse string values
// (I completed this program during Snow days, before we learned about it in class)
// resources used for basis : Baeldung, Geeksforgeeks, Cs.Prinston
import java.util.Arrays;  
import java.util.Scanner;
public class SortCompare{
    static int inSortTally = 0;
    static int quSortTally = 0;
    static int merSortTally = 0;

    public static void main(String[] args) {
        int inputSize;// takes user array size
        int negInput;// reverses it to create array from -n,n
        final Scanner doWhat = new Scanner(System.in);
        // System.out.println("Operation Input Array Size(Number)");// Greeting
        // inputSize = doWhat.nextInt();
        
        ///////////// create Deep copy of array///////////
        // for(int j=0;j<inputSize;j++){ //create new array
        //     arrayMer[j]=deepArray[j];
        // }
        int[] deepArray = {1,2,3,5,4,6,7};
        inputSize = deepArray.length;
        negInput = -deepArray.length;
        

        String inputCommand=" ";// user input
        System.out.println("Operation Input Sort Style(Letter)");
        SortCompare ob = new SortCompare();
        while (!inputCommand.equalsIgnoreCase("quit")) {
            // end program at any time

            inputCommand = doWhat.nextLine();
            if (inputCommand.equalsIgnoreCase("m")||inputCommand.equalsIgnoreCase("all")) {// illicits merge function
                System.out.println("+++Merge Sort+++");
                int[] arrayMer = new int[inputSize];
                for(int j=0;j<inputSize;j++){ //create new array
                    arrayMer[j]=deepArray[j];
                }
                if (inputSize <20) {// in the rubric
                    System.out.println("Original Array:");
                    System.out.println(Arrays.toString(arrayMer));//using array utility to make string for user
                    ob.mergeSort(arrayMer);
                    System.out.println("Sorted Array:");
                    System.out.println(Arrays.toString(arrayMer));
                }
                else{
                    ob.mergeSort(arrayMer);
                }
                System.out.println("Tally total: " +merSortTally);
                System.out.println("---Merge Sort End---");
                

            }
            if (inputCommand.equalsIgnoreCase("q")||inputCommand.equalsIgnoreCase("all")) {// quick sort fx
                System.out.println("+++Quick Sort+++");
                int[] arrayQu = new int[inputSize];
                for(int j=0;j<inputSize;j++){//create new ARRAY
                    arrayQu[j]=deepArray[j];
                }
                if (inputSize< 20){
                    System.out.println("Original Array:");
                    System.out.println(Arrays.toString(arrayQu));
                    SortCompare.quickSort(arrayQu);
                    System.out.println("Sorted Array:");
                    System.out.println(Arrays.toString(arrayQu));
                } 
                else{
                    SortCompare.quickSort(arrayQu);
                }
                System.out.println("Tally total: " +quSortTally);
                System.out.println("---Quick Sort End---");
            }

            if (inputCommand.equalsIgnoreCase("i")||inputCommand.equalsIgnoreCase("all")) {
                System.out.println("+++Insertion Sort+++");
                int[] arrayIns = new int[inputSize];
                for(int j=0;j<inputSize;j++){
                    arrayIns[j]=deepArray[j];
                }
                if (inputSize< 20){ // saw this last second
                    System.out.println("Original Array:");
                    System.out.println(Arrays.toString(arrayIns));
                    ob.insertionSort(arrayIns);
                    System.out.println("Sorted Array:");
                    System.out.println(Arrays.toString(arrayIns));
                }
                else{
                    ob.insertionSort(arrayIns);
                }
                System.out.println("Tally total: " +inSortTally);
                System.out.println("---Insertion Sort End---");

            }
            if (inputCommand.equalsIgnoreCase("r")||inputCommand.equalsIgnoreCase("all")) {
                System.out.println("+++Radix Sort+++");
                int[] arrayRadix = new int[inputSize];
                for(int j=0;j<inputSize;j++){
                    arrayRadix[j]=deepArray[j];
                }
                if (inputSize>20){
                System.out.println("Original Array:");
                System.out.println(Arrays.toString(arrayRadix));
                SortCompare.radixSort(arrayRadix);
                System.out.println("Sorted Array:");
                System.out.println(Arrays.toString(arrayRadix));
                System.out.println("---Radix Sort End---");
                }
                //// radix sort
            }
        }
        //     if (inputCommand.equalsIgnoreCase("all")) {
        //         ////////////////////////
                
        // }

    }

    public static void myMerge(int[] array, int[] arrayStart, int[] arrayEnd, int split, int backEnd) {
        int indexStart = 0;//iterators
        int indexEnd = 0;
        int indexMain = 0;

        while (indexStart < split && indexEnd < backEnd) {
            if (arrayEnd[indexEnd] >= arrayStart[indexStart]) {
                array[indexMain++] = arrayStart[indexStart++];
            } else {
                array[indexMain++] = arrayEnd[indexEnd++];
            }
        }
        while (split > indexStart) {// pivot
            array[indexMain++] = arrayStart[indexStart++];
        }
        while (indexEnd < backEnd) {//backend pivot
            array[indexMain++] = arrayEnd[indexEnd++];
        }
    }

    public static int[] arraySplitStart(int array[], int start, int split) {// splits array into two parts, the first part, up to the median(pivot)

        int arrayStart[] = new int[split];
        for (int i = 0; i < split; i++) {
            arrayStart[i] = array[i];

        }
        // System.out.println("Sorted Array:");
        // System.out.println(Arrays.toString(arrayStart));
        return arrayStart;
    }

    public static int[] arraySplitEnd(int array[], int split, int end) {// splits array into two parts

        int arrayEnd[] = new int[end - split];
        for (int i = split; i < end; i++) {
            arrayEnd[i - split] = array[i];
        }
        // System.out.println("Sorted Array:");
        // System.out.println(Arrays.toString(arrayEnd));
        return arrayEnd;
    }

    public void mergeSort(int[] array) {// merge sort
        int start = 0;
        int end = array.length;
        if (end < 2) {// if not an array
            return;
        }
        int split = (int) (Math.floor(start + end) / 2);
        if (start < end) {
            int arrayStart[] = arraySplitStart(array, start, split);// reusing this function for other sorts
            int arrayEnd[] = arraySplitEnd(array, split, end);//as well as this one
            mergeSort(arrayStart);
            mergeSort(arrayEnd);
            // merge function which take both the sub-arrays
            // 
            myMerge(array, arrayStart, arrayEnd, split, end - split);
            merSortTally++;
        }

    }

    static void quickSort(int[] array) {
        mySortQuicker(array, 0, array.length - 1);//just calls the array sorter that has two inputs
    }

    public static void mySortQuicker(int arr[], int begin, int end) { // philip told me to make a function call another with more parameters
        if (begin < end) {
            int indexSort = swapFest(arr, begin, end);//quick sort partitioner

            mySortQuicker(arr, begin, indexSort - 1);
            mySortQuicker(arr, indexSort + 1, end);
        }
    }

    public static int swapFest(int array[], int begin, int end) {// partitioner
        int split = array[end];// pivot point
        int swapThis = (begin - 1); //swaps this point

        for (int j = begin; j < end; j++) {
            if (array[j] <= split) {
                swapThis++;

                int swapTemp = array[swapThis];// temp placeholder
                array[swapThis] = array[j];
                array[j] = swapTemp;
                quSortTally++;
            }
        }
        int swapTemp = array[swapThis + 1];//other case
        array[swapThis + 1] = array[end];
        array[end] = swapTemp;
        return swapThis + 1;
    }

    public void insertionSort(int[] array) {// simple sorting
        for (int i = 1; i < array.length; i++) {
            int takeThis = array[i];// var names explain
            int compareBefore = i - 1;
            while (compareBefore >= 0 && array[compareBefore] > takeThis) {//insertion logic 
                array[1 + compareBefore] = array[compareBefore];
                compareBefore--;
                inSortTally++;
               
                    System.out.println(takeThis)
                    ;System.out.println(inSortTally);
                
            }
            array[1 + compareBefore] = takeThis;//swap
        }
    }
    static int getBiggest(int array[]) {//shows largst value in array
        int biggestValue =  array[0];
        for (int i = 1; i < array.length; i++)
            if (array[i] > biggestValue)
                biggestValue = array[i];
        return biggestValue;
    }

    static void radixSort(int[]array) {// using buckets and Strings to parse negatives
        int maximumNumber = getBiggest(array);
        String conversion = String.valueOf(maximumNumber);//parse any nummber like a negative 
        int numberOfDigits= conversion.length();
        int largestDigit=0;
        for(int i = 0; i < numberOfDigits;i++){
            if (largestDigit<conversion.charAt(i)){//compare digits
                largestDigit=conversion.charAt(i);
            }
        }
        int maxSize = 255;    // max array size
        int n =array.length;
        int[] newArray = new int[n];
        String strArray[]= new String[array.length];// string to parse neg
        for(int f =0; f< array.length;f++){
            strArray[f]= String.valueOf(array[f]);
        }
        System.out.println(Arrays.toString(strArray));
        for (int d = 0; d < numberOfDigits; d++) { //[parses for max size]         
                // show amount of occurences//
            int[] amount = new int[maxSize+1];
            for (int i = 0; i < n; i++) {           
                
                int c= findCharAtInString(i, d, strArray);
                amount[c + 1]++;
                }
                // compute amount of same digit
            for (int r = 0; r < maxSize; r++){
                amount[r+1] += amount[r];
            }

            if (numberOfDigits-1 == d) {//bucketing
                int drop1 = amount[maxSize] - amount[maxSize/2];
                int drop2 = amount[maxSize/2];
                for (int r = 0; r < maxSize/2; r++)
                        amount[r] += drop1;
                for (int r = maxSize/2; r < maxSize; r++)
                        amount[r] -= drop2;
            }
                // transfer to new array
            for (int i = 0; i < n; i++) {
                int c = findCharAtInString(i, d, strArray);
                newArray[amount[c]++] =array[i];
                }
                // Final sorting of old array to new
                for (int i = 0; i < n; i++)
                array[i] = newArray[i];
            }
    }
    public static int findCharAtInString(int k, int digit, String[] array) {//for parsing negatives
        if (digit < 0 || digit >= array[k].length()) {
            return 0;
        }
        return array[k].charAt(digit);// this will 'successfully' recognize negative inputs for bucketing
    }
    
}