//Collin Paddock
//Program 3
// 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
public class AnalyzeTextStream{
    private static String[] nodeNames;//to 
    private static int numNodes=0;
    public static void main(String[] args) throws Exception { 
        boolean analyze=false;// for analyze arg
        boolean checkArgs = false;// for word input args
        ArrayList<String> userCommand = new ArrayList<String>();/// storage
        for(String command: args) {// iterate over arguments
            userCommand.add(command);
        }
        String targetFile = userCommand.get(0);// example: users.txt
        if (userCommand.size()>= 2){
            String goalInput = userCommand.get(1);
            if (goalInput.equalsIgnoreCase("analyze")){
                analyze=true;
            }
            else{
                checkArgs=true;
            }
        }
       
        File thisFile = new File(targetFile);//take argument file
        BufferedReader fr = new BufferedReader(new FileReader(thisFile));
        ArrayList<String> fileData = new ArrayList<String>();// used once just for argument, does not refer to this again or use this information for anything
        String[] thisLine;
        String parseLine; 
        
        while(null !=(parseLine=fr.readLine())){
            thisLine = parseLine.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");//split at space. and remove 1) punctuation 2) numbers 3) non-letter characters
            for(String thing : thisLine){
                    fileData.add(thing);// add to arrayList "fileData"
            } 
        }
        BST myBST = new BST();
        String addString="";
        for(int i=0; i<fileData.size();i++){// creating bst
            addString=fileData.get(i);
            myBST.insert(addString);
        }
        ////////////////////////////

        if (analyze==true){
             myBST.analyzeBST(myBST.getRoot());
             myBST.printAnalysis();
        }
        if(checkArgs==true){
            //System.out.println(" ");
            for(int i=1;i<userCommand.size();i++){// check args starting at second (first is file)
                myBST.parseArgs(userCommand.get(i));
            }
        }
    }
}

