//Collin Paddock
// Csci 145
// Lab 2
// 4/19/19
// Hello Thank you for viewing my program




import java.util.Scanner;//I NEED THIS TO HELP
public class Summation
{//HELLO THANKS FOR LOOKING AT MY CODE
   public static void main(String args[]){
       sumIntegers();
   }
   public static void sumIntegers(){

      int numbNumb;//Assign variable for number of time loop Itereates
      int sumNumb=0;// assigns resulting sum to 0
      int numbDone;//to be used in loop iterator
     // Assigning Variables that will be used
     // *try to implement has next int or has next double through scanner
      Scanner in = new Scanner(System.in);
     //Establishing a user input method
      System.out.println("How many Integers will be Summed?");
      
      numbNumb = in.nextInt();
   
      //Creating a new integer loop
     
      for (numbDone = 1; numbDone<=numbNumb; numbDone++){
      //For Loop (x,y,z) for use of program running as many times as user says
        System.out.println("Integer to be added");
        Scanner in2 = new Scanner(System.in);

        //Another Scanner because its not a bad thing
        sumNumb = sumNumb + in2.nextInt();
        //accumulator variable to be added together
         }
         System.out.println("Your total is "+ sumNumb);
         //Return amount
   }
}