//Collin Paddock
// Csci 145
// Lab 2
// 4/19/19
// Hello Thank you for viewing my program



import java.util.Scanner;// import scanner
public class smallestDouble{//The main thing of the main
    public static void main(String [] args){
        findSmallestDouble();//Hello Thank you for looking at my code!
    }
    public static void findSmallestDouble(){
        Scanner input = new Scanner(System.in);//make scanner
        System.out.println("How many Doubles will be entered?");//question
        double loopNum= input.nextDouble();//iterator for While loop
        System.out.println("Enter the first Double");
        double numOne=input.nextDouble();//Assign first input, the FIRST LOWEST input
        double numTwo=0;//Set this value up to be changed and checked
        while (loopNum>=2){//Loop set to include iterator already run
            System.out.println("What is the Next Double");//Well....? What is it..? 
            numTwo=input.nextDouble();//Input to be checked 
            if(numOne<=numTwo){// if loop to determine lowest between two
            numTwo=numOne;//if numONe is lower it becomes the lowest
            }
            else{// if loop other option
            numOne=numTwo;//If numTwo is lower sets numOne to be lowest
            }
        loopNum--;//decrease it at an iteration
        }
        System.out.println("The smallest Double is " + numOne);//Returns to user the input asked
    }
}