// Collin Paddock
// Csci 145
// Lab 3
// 4/29/19
// Hello Thank you for viewing my program
import java.util.Scanner;
public class myStringManipulator{
public static void main(String[] Args){
Scanner doWhat = new Scanner(System.in);
System.out.println("Enter String to be manipulated");//Greeting
String statement= doWhat.next();
int bugFix=0;// This is to fix a bug my code experiences. Without this it would print
while (true){// Always true
    if(bugFix>0){// to make my command print once
    System.out.println("Enter Your command(quit, print reverse, replace all, replace single, remove)");
    }
    String replyThing = doWhat.nextLine();////////////////////////TAKE INPUT START HERE
    if (replyThing.equalsIgnoreCase("Quit")){ // This will Quit the progrem
        System.out.println("goodbye");
        return;//break the while loop
    }   
    if (replyThing.equalsIgnoreCase("replace all")){ //user input to run function
        statement=replaceAll(statement);//set old statement to new
        System.out.println("Your new sentence is: "+ statement);
        continue;
    }
    if (replyThing.equalsIgnoreCase("replace single")){//user input to run function
        statement=replaceSingle(statement);//set old statement to new
        System.out.println("Your new sentence is: "+ statement);
        continue;
    }
    if (replyThing.equalsIgnoreCase("remove")){//user input to run function
        statement=remove(statement);//set old statement to new
        System.out.println("Your new sentence is: "+ statement);
        continue;
    }
    if (replyThing.equalsIgnoreCase("print reverse")){//user input to run function
    statement=reverse(statement);//set old statement to new
    System.out.println("Your new sentence is: "+ statement);
    continue;
    }
    else{//When command input is inproper
        bugFix++;// THIS FIXES MY CODE FROM A LOOPING ERROR I HAVE
        if (bugFix>1){// upon first running of my program it will iterate once on its own, further iterations will be from wrong input
        System.out.println("Enter the Correct command");//Wrong INPUT
            continue;
            }
            bugFix++;//Iterate my bugs so first time doesnt loop twice.
        }
    }
}
    public static String replaceAll(String methodString){ // ALL changeLetter BECOME newLetter
            String lastString="";
            Scanner doWhat = new Scanner(System.in); 
            System.out.println("Enter the Character to replace");
            char changeLetter = doWhat.next().charAt(0);// CHANGE THIS
            int indexer= methodString.indexOf(changeLetter);// CHECK IF ITS IN THERE
            if (indexer!=-1){// IF ITS IN THERE IT RUNS
                System.out.println("Enter the new character");
                String newLetter= doWhat.next();// NEW CHARACTER?
                for(indexer=0;indexer<methodString.length();indexer++){//for loop to append together new string
                    if(methodString.charAt(indexer)==changeLetter){
                        lastString+=newLetter;//new letter
                    }
                    else{
                    lastString+=methodString.charAt(indexer); //append old letter
                    }
                }
            return lastString;// We did it
            }
            else{//we didnt do it
            System.out.println("you have entered the wrong Input Please try again");
            return methodString;
            // putting in wrong input
            }           
    }  
    public static String replaceSingle(String methodString){
        System.out.println("Enter the character to replace");
        Scanner doWhat = new Scanner(System.in); 
        char changeLetter = doWhat.next().charAt(0);//replace with this
        int indexer= methodString.indexOf(changeLetter);// makes sure letter is in there
        if (indexer!=-1){//Scans input
            System.out.println("Enter the new character");
            String newLetter= doWhat.next();//to be changed
            int matchCase=0;// index'd letter count
            for(int i=0;i< methodString.length();i++){// matching amounts
                if( methodString.charAt(i)==changeLetter){// for every mtch we increase
                matchCase++; // Counts amount of times string has letter
                }
            }
            System.out.println("Enter Which character number to replace? (out of "+ matchCase +" possible letters)");  // tells user amount of times
            matchCase=0; //reset matches to be referenced
            String lastString="";
            int replaceNum= doWhat.nextInt();// number of which to replace c/o/w = 0/1/2
            for(indexer=0;indexer< methodString.length();indexer++){
                if( methodString.charAt(indexer)==changeLetter){
                    matchCase++;// every time there is a match it increases
                    if (replaceNum==matchCase){// until the amount equals when we want to replace
                    lastString+=newLetter;//then we replace
                    }
                    else{// if we dont replace it stays
                    lastString+= methodString.charAt(indexer); //appending same
                    }
                }
                else{
                lastString+= methodString.charAt(indexer);// appending as usual
                }  
            }
        return lastString;// We did it                         
        } 
        else{
            System.out.println("you have entered the wrong Input Please try again");
            return methodString; // we did not do it
        }
    }
    public static String remove(String methodString){
        System.out.println("Enter the character to remove");
        String lastString="";
        Scanner doWhat = new Scanner(System.in); 
        char changeLetter = doWhat.next().charAt(0);
        int indexer= methodString.indexOf(changeLetter);
        if (indexer!=-1){//Scans input
            for(indexer=0;indexer< methodString.length();indexer++){
                if( methodString.charAt(indexer)==changeLetter){
                    lastString+="";
                }
                else{
                lastString+= methodString.charAt(indexer);
                }  
            }
        return lastString;  // We did it                       
        } 
        else{
            System.out.println("you have entered the wrong Input Please try again");
            return methodString; // we did not do it
        }
    }
    public static String reverse(String methodString){//reverse method is easiest
        String lastString="";// empty string to append
        for(int indexer= methodString.length()-1;indexer!=-1;indexer--){//starting at largest index and going down, -1 is for hitting last 
                lastString=lastString+ methodString.charAt(indexer); //append backwards
            }
        return lastString;// We did it
                        
                                    
    

    }
}