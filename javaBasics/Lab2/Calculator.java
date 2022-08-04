// Collin Paddock
// Csci 145
// Lab 2
// 4/19/19
// Hello Thank you for viewing my program



// this scans 


import java.text.DecimalFormat;//for using code and changing the decimals around

// import javax.lang.model.util.ElementFilter;
public class Calculator{
    //main method
    //take input
    
           
public static void main(String[] Args){
Scanner doWhat = new Scanner(System.in);
System.out.println("Operation");//Greeting
boolean inputType=true;
while (true){// set loop to reiterate when needed 
    String replyThing;//User input set to nothing
        if(inputType=false){// WHEN MISTAKES
            System.out.println("you have entered the wrong Input Please try again");
        }
        inputType=true;
            if(inputType=true){
            System.out.println("A-Addition S-Subtraction M-Multiplication D-Division Q-Quit(directions will print additional time for each successive you do ;)");//directions
            // This runs twice its litterally glitched or something
            }
            double inputOne=0;//Input one set to nothing
            double inputTwo=0;//Input two set to nothing
           replyThing = doWhat.nextLine();
            
            //Calculation base put my programs here
            if (replyThing.equalsIgnoreCase("Q")){ // This will Quit the progrem
                        System.out.println("goodbye");
                        break;//break the while loop
                }   
                    //Make sure input is DOUBLE
                      
    if (replyThing.equalsIgnoreCase("A")){
            //When users specifies This letter it takes it to specific method
            System.out.println("What is the First input");
                if (doWhat.hasNextDouble()){//Scans input type
                inputOne = doWhat.nextDouble();//Scans for input
                System.out.println("What is the second input");
                inputTwo = doWhat.nextDouble();//scans for input
                //Format input to be double
                addition(inputOne,inputTwo);//Invoking the addition
                }
                else{
                System.out.println("you have entered the wrong Input Please try again");
            
                // putting in wrong input
                    }
                //invoke method
            }
    else if (replyThing.equalsIgnoreCase("M")){
                //When users specifies This letter it takes it to specific method
                System.out.println("What is the First input");
                    if (doWhat.hasNextDouble()){//Scans input type
                    
                    
                    inputOne = doWhat.nextDouble();//Scans for input
                    System.out.println("What is the second input");
                    inputTwo = doWhat.nextDouble();//scans for input
                    //Format input to be double
                    multiplication(inputOne,inputTwo);// INVOKE FUNCTION
                    }
                    else{
                    System.out.println("you have entered the wrong Input Please try again");
                
                    // putting in wrong input
                        }
                    //invoke method
                }
        else if (replyThing.equalsIgnoreCase("D")){
                    //When users specifies This letter it takes it to specific method
                    System.out.println("What is the First input");
                        if (doWhat.hasNextDouble()){//Scans input type
                        inputOne = doWhat.nextDouble();//Scans for input
                        System.out.println("What is the second input");
                        inputTwo = doWhat.nextDouble();//scans for input
                        //Format input to be double
                        division(inputOne,inputTwo);
                        }
                        else{
                        System.out.println("you have entered the wrong Input Please try again");
                    
                        // putting in wrong input
                        }
                        //invoke method
        }
        else if (replyThing.equalsIgnoreCase("S")){
                        //When users specifies This letter it takes it to specific method
                        System.out.println("What is the First input");
                            if (doWhat.hasNextDouble()){//Scans input type for double
                            
                            
                                inputOne = doWhat.nextDouble();//Scans for input
                                System.out.println("What is the second input");
                                inputTwo = doWhat.nextDouble();//scans for input
                            //Format input to be double
                                subtraction(inputOne,inputTwo);// INVOKE THE METHOD
                            }
                            else{// Wrong Input
                            System.out.println("you have entered the wrong Input Please try again");// putting in wrong input
                            }
        }
                 
            else{// FOR WHEN IT DOESNT WORK like at all
                    inputType=false;// wrong
                    continue;
                    // putting in wrong input
                        }
                    //invoke method
            
                
    }

}

        public static void addition(double InputOne,double InputTwo){
            DecimalFormat doub = new DecimalFormat("0.00"); // THis is to make it look like it does in the lab. setting the decimals to 2 places
            double answer = InputOne + InputTwo;//math
            System.out.print("The sum of "+ doub.format(InputOne)+" + "+doub.format(InputTwo)+" is = "+ doub.format(answer) + "\n"); //basic arithmatic
            //FORMAT FOR HAVING TWO DECIMAL PLACES

        }
        public static void subtraction(double InputOne,double InputTwo){
            DecimalFormat doub = new DecimalFormat("0.00"); // THis is to make it look like it does in the lab. setting the decimals to 2 places
            // doub means dthe double
            double answer = InputOne - InputTwo; //math
            System.out.print("The difference of "+ doub.format(InputOne)+" - "+doub.format(InputTwo)+" is = "+ doub.format(answer) + "\n"); //basic arithmatic
            //FORMAT FOR HAVING TWO DECIMAL PLACES
        }
        public static void multiplication(double InputOne,double InputTwo){
            DecimalFormat doub = new DecimalFormat("0.00"); // THis is to make it look like it does in the lab. setting the decimals to 2 places
            double answer = InputOne * InputTwo;//math
            System.out.print("The product of "+ doub.format(InputOne)+" * "+doub.format(InputTwo)+" is = "+ doub.format(answer) + "\n");  //basic arithmatic
            //FORMAT FOR HAVING TWO DECIMAL PLACES
        }
        public static void division(double InputOne,double InputTwo){
            DecimalFormat doub = new DecimalFormat("0.00"); // THis is to make it look like it does in the lab. setting the decimals to 2 places
            double answer = InputOne / InputTwo;//math
            System.out.print("The quotient of "+ doub.format(InputOne)+" / "+doub.format(InputTwo)+" is = "+ doub.format(answer) + "\n");  //basic arithmatic
            //FORMAT FOR HAVING TWO DECIMAL PLACES
        }
    } 

    // catch (Exception e) {
    //     
    //     System.out.println("Wrong");
    // }
