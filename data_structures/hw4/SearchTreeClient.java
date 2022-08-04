import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SearchTreeClient {
    public static void main(String[] args) throws IOException {
        SearchTree<String> flights = new SearchTree<String>();
        ArrayList<String> userCommand = new ArrayList<String>();
        for(String command: args) {// iterate over arguments
            userCommand.add(command);
        }
        String targetFile = userCommand.get(0);
        File thisFile = new File(targetFile);
        BufferedReader fr = new BufferedReader(new FileReader(targetFile));
        ArrayList<String> fileData = new ArrayList<String>();
        String[] thisLine;
        String parseLine;
        int lineCount=0;
        while(null !=(parseLine=fr.readLine())){
             thisLine = parseLine.split(" ");// new input at this point
            // for(String thing : thisLine){
            //         flights.add(thing);// add to arrayList "fileData"
            //     } 
            flights.add(parseLine);
            lineCount++;
        }
        
    
        System.out.println();
        System.out.println("In Order List:");
        flights.print();
        System.out.println();


    }

}