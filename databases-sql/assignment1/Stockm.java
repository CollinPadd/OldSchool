//Collin Paddock
//330 Summer 2020
//Assignement1
import java.util.*;
import java.io.*;
public class Stockm{
    public static void main(String[] args) throws IOException {
        // ArrayList<String> LineDa = new ArrayList<String>();
        ArrayList<String> userCommand = new ArrayList<String>();
        for(String command: args) {// iterate over arguments
            userCommand.add(command);//arguements go into an arrayList
        }
        String targetFile = userCommand.get(0);// grabs first user arguement
        BufferedReader fr = new BufferedReader(new FileReader(targetFile));//buffers the file for analytics
        String parseLine;// takes file line and becomes a string
        String[] thisLine;//we then split that line into a string
        int lineCount=0;//tally for amount of line in a SPECIFIC company
        String company="";//compare this against first index of each line
        ArrayList<String[]>companyData = new ArrayList<String[]>();//holds String[] array of entire line, IE anyLine[0] holds company, [1] date etc...
        Boolean isFirstLine= true;// need to differentiate first line of each company
        while(null != (parseLine = fr.readLine())){
            isFirstLine=true;//is it the first line for this company, assume not
            thisLine = parseLine.split("\\t");//split each string(line) at tab, thisLine is an array
            if (thisLine[0].equalsIgnoreCase(company)){
                isFirstLine=false;// check company of line against current (var) company
            }
            if (isFirstLine){// new company
                company=thisLine[0];//set current company to this 
                if(!companyData.isEmpty()){
                mineThisCompany(companyData,lineCount);//compute for previous company(if exists)
                }
                lineCount=0;//reset line tally
                System.out.println("PROCESSING: "+company);
                System.out.println("========================");
                companyData = new ArrayList<String[]>();//new arraylist for new company
                companyData.add(thisLine);// add entire line as an array
            }
            else{//if not first line of a new company
            companyData.add(thisLine);// add entire line as an array
            }
            lineCount++;//increase line tally for this company
            
        }
        mineThisCompany(companyData,lineCount);//run one more time for our last company, loop will end after last line.
    }

    public static void mineThisCompany(ArrayList<String[]> thisCompany,int lineCount) {
        Data analyzeInfo = new Data();// instantiate new data container for analyzing info
        analyzeInfo.insert(thisCompany);//put all data from company
        analyzeInfo.lineCount=lineCount;//set line tally for iteration
        analyzeInfo.findCrazyDays();
        System.out.println("Total crazy days = "+ analyzeInfo.dayTally);
        System.out.println(analyzeInfo.sayCraziestDay);
        System.out.println(" ");
        analyzeInfo.findSplit();
        System.out.println("Total number of splits: "+ analyzeInfo.dayTally);
        System.out.println(" ");
    }
}
//thanks!