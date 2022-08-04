// GROUP PROJECT 
// COLLIN PADDOCK _ DARREN MONGOVIN _ ALEC HOOD
import java.util.Scanner;
public class Hangman {
    // private static final boolean testingMode = true;
    public static boolean testingMode = true;
    public static String currentWord=RandomWord.newWord();
    //private static char theWordArray[]=arrayifier(currentWord);
    public static void main(String[] Args){// MAIN
        Scanner doWhat = new Scanner(System.in);//Scann
        boolean gameDone=false;// IS THE GAME DONE
        while(true){// MAIN LOOP TO KEEP GANE RUNNING
            while(!gameDone){// WHILE THE GAME ISNT OVER
                System.out.println("Enter a Difficulty");
                char difficultyLevel = doWhat.next().charAt(0);// FIRST LETTER TAKEN
                difficultyLevel=Character.toLowerCase(difficultyLevel);// NO MATTER THE LETTER
                switch(difficultyLevel){
                    case 'e'://///EASY MODE///
                        if(runTheGame(1)){
                            System.out.println("You Win");
                            gameDone=true;
                            continue;
                        }
                    case 'i':///Intermediate Mode////
                        if(runTheGame(2)){
                             System.out.println("You Win");
                             gameDone=true;
                             continue;
                        }
                    case 'h'://///HARD MODE//////////////
                        if(runTheGame(3)){
                            System.out.println("You Win");
                            gameDone=true;
                            continue;
                        }  
                    default:
                        System.out.println("You have entered an Incorrect Difficulty");// when input is not something we know
                        continue;
                }  
            }
            System.out.println("Would you like to play again (y) (n)");// ARE WE DOING IT AGAIN
            String playAgain=doWhat.next();
            if (playAgain.charAt(0)=='y'){// YES WE ARE
                currentWord=RandomWord.newWord();// GET THE NEW WORD
                gameDone=false;// NOT OVER YET
                continue;    
            }
            if (playAgain.charAt(0)=='n'){// WHEN USER IS DONE PLAYING
                System.out.println("Quitting Game");
                return;
            }
            else{// OTHER  REASON  TO BREAK GANE
                System.out.println("Quitting Game");
                return;
            }
        }
    } 

// Program begins, ask for Difficulty
// E - easy I- intermediate H -hard
//reprompt for invalid input
    public static Boolean runTheGame(int difficulty){
        Scanner doWhat = new Scanner(System.in);
        int guesses=0;// The amount of Guesses change with each Guess
        int spaces=0;// The amount of index's changes with each difficulty
        String stringDifficulty="";// the literal difficulty selected
        String revealString="";// changes from *--------* into the var *currentWord*
        String userNumGuess="";// variable holds the Guessed locations of Letter
        String solveIt="";// Variable to hold user Guess when SOLVING
        String rightSpot="";// reveals the correct spot in RevealString
        boolean hasMatch;// checks if word has the checked letter
        char emptyChar=' ';// to parse spaces provided by input
        if (difficulty==1){//EASY MODE SETTINGs
            guesses=15;
            spaces=7;
            stringDifficulty="Easy";
        }
        if (difficulty==2){//intermediate MODE SETTING
            guesses=12;
            spaces=5;
            stringDifficulty="Intermediate";
        }
        if (difficulty==3){//HARD MODE YEAAAA
            guesses=10;
            spaces=3;
            stringDifficulty="Hard";
        }
        for (int revealMode=0;revealMode<currentWord.length();revealMode++)//sets revealString to -------
            revealString+="-";
        System.out.println("you have selected "+ stringDifficulty +" Difficulty");// tells user difficulty
        if (testingMode=true){
            System.out.println(currentWord);//testing mode
        }
        for (int i=0;i<=guesses;i++){//MAIN GUESS LOGIC. i is the MAIN Iterator
            hasMatch=false;// no matches
            if (currentWord.equalsIgnoreCase(revealString)){// THE LOGIC TO WIN THE GAME
                return true;// YOU WON
            }
            System.out.print("Please enter letter to guess: ");
            solveIt=doWhat.nextLine();
            if (solveIt.equalsIgnoreCase("solve")){// SOLVING WORD
                System.out.print("Please Solve the Word :");
                solveIt=doWhat.next();
                if (solveIt.equalsIgnoreCase(currentWord)){
                    return true;// YOU WON
                }
                else{
                    System.out.print("That is not the Secret Word");
                }
            }
            else{
                char userGuess = solveIt.charAt(0);// TAKES FIRST INPUT
                if (!Character.isDigit(userGuess)){// MAKES SURE ITS RIGHT
                    int javaCheck=currentWord.indexOf(userGuess);
                    if (javaCheck==-1){//MAKES SURE ITS IN THERE
                        System.out.println("You did not guess the right letter");
                        i++; // INCREASE i TO SHOW CORRECT GUESSES
                        System.out.println("Guesses Remaining :"+ (guesses-i)+" revealed word: " +revealString);
                        i--;//SET IT RIGHT
                    }
                    else{//guessed LETTER IS IN THERE
                    System.out.print("Please enter the Spaces you want to Check (separated by spaces): ");
                    for(int k=0;k<1;k++){// JUST TO FIX BUGS I EXPERIENCED
                        userNumGuess=doWhat.nextLine();
                    }
                    if (userNumGuess.length()==spaces){//MAKES SURE RIGHT NUMBER OF INDEX"S INPUT
                        for (int x=0;x<userNumGuess.length();x++){// CHECKS EACH INDEX
                            if(userNumGuess.charAt(x)!=emptyChar&&Character.isDigit(userNumGuess.charAt(x))){
                                // MAKES SURE INPUT IS NOT EMPTY SPACE AND IS A DIGIT
                                int checkForThis=Character.getNumericValue(userNumGuess.charAt(x));
                                if (userGuess==currentWord.charAt(checkForThis)){
                                    hasMatch=true;//CORRECT
                                    rightSpot=revealString;//PARSE REVEALSTRING
                                    revealString=revealLetter(checkForThis, revealString);//CHANGE REVEALSTRING TO NEWER REVEAL STRING
                                }
                            }
                            else{//WRONG INPUT
                                if (!Character.isDigit(userNumGuess.charAt(x))&&(userNumGuess.charAt(x)!=emptyChar)){
                                    //INPUT IS NOT EMPTY SPACE OR AN INTEGER
                                    System.out.println("input "+userNumGuess.charAt(x)+" is not valid");
                                }//tells user what went WRONG
                            }
                        }
                        if (hasMatch&&!rightSpot.equalsIgnoreCase(revealString)){//HAS MATCH MAKES IT WORK
                            System.out.println("Your Guess is in the Word!");
                            System.out.println("Guesses Remaining :"+ (guesses-i)+" revealed word: " +revealString);// YOU DID SOMETHING RIGHT
                            i--;
                        }
                        else{// MAIN LOGIC FOR GETTING IT WRONG
                            System.out.println("The letter was not found in the Spaces provided.");
                            i++;// TELL USER RIGHT GUESS NUMBER
                            System.out.println("Guesses Remaining :"+ (guesses-i)+" revealed word: " +revealString);
                            i--;//SETS IT REAL
                        }
                    }
                    else{// WRONG NUMBER OF INDEXES PUT IN
                        System.out.println("You did not put in the right Input length, you put in "+userNumGuess.length()+" index(including space) rather than "+spaces+"." );
                    System.out.println("Guesses Remaining :"+ (guesses-i)+" revealed word: " +revealString);

                    }
                }           
        }
        else{// When user input is not letter
            System.out.println("Please put in the right Input");
            i--;
        }
    }
        }// When the game is over
    System.out.println("You Lost the game!");
    return false;
    }
    public static String revealLetter(int place, String revealed){ // HOW TO REVEAL THE REVEALSTRING
        String revealer="";// THIS WORD CHANGES TO THE RIGHT LETTER
        for(int i=0;i<currentWord.length();i++){
            if (i==place){// IT WORKS THROUGH SHEER WILLPOWER
                revealer+=currentWord.charAt(place);
            }
            else{// append right letter TO THE END
                revealer+=revealed.charAt(i);
            }
        }
        return revealer;// THE NEWLY REVEALED STRING
    }
    }
