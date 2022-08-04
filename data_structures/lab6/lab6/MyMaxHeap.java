// Name : Collin Paddock
// Description :
 // Date : March 12, 2020
// MyMaxHeap class 
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
public class MyMaxHeap{
public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  //create reader
    HeapMax myHeap = new HeapMax(15);// max size 15
    System.out.println("type integers to insert into your max heap. ");// from lab
    System.out.println("press return after each one. a maximum of 15");
    System.out.println("elements are allowed. Type end when done");
    String userIn = br.readLine();// read input
    while(!userIn.equalsIgnoreCase("end")){// catch cast 'end'
        myHeap.insert(Integer.parseInt(userIn));// add to heap
        userIn = br.readLine();// ask again
    }
    myHeap.printMaxHeap();// display
 }
}
