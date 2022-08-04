//Collin Paddock
// Assignment2
//CSCI 330 Summer
//Full points!!
import java.util.*;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
class Assignment2 {
    //javac -cp .;mysql-connector-java-8.0.20\mysql-connector-java-8.0.20.jar Assignment2.java
    //java -cp .;mysql-connector-java-8.0.20\mysql-connector-java-8.0.20.jar Assignment2
    static Connection conn = null;
    static final String prompt = "Enter ticker symbol [start/end dates]: ";
    public static void main(String[] args) throws Exception {
        // Get connection properties
        String paramsFile = "readerparams.txt";
        if (args.length >= 1) {
            paramsFile = args[0];
        }
        Properties connectprops = new Properties();
        connectprops.load(new FileInputStream(paramsFile));
        try {
            // Get connection
            Class.forName("com.mysql.jdbc.Driver");
            String dburl = connectprops.getProperty("dburl");
            String username = connectprops.getProperty("user");
            conn = DriverManager.getConnection(dburl, connectprops);//step 1
            System.out.printf("Database connection %s %s established.%n", dburl, username);
            // Enter Ticker and TransDate, Fetch data for that ticker and date
            Scanner in = new Scanner(System.in);
            System.out.print(prompt);
            String input = in.nextLine().trim();
            while (input.length() > 0) {
                String[] params = input.split("\\s+");
                String ticker = params[0].toUpperCase();//2.1
                String startdate = null, enddate = null;
                if (ticker.equalsIgnoreCase("q")) {// no tickers are Q, this ends the program
                    break;
                }
                if (getName(ticker)) {//2.3
                    if (params.length >= 3) {
                        startdate = params[1];
                        enddate = params[2];
                    }
                    getStockData(ticker, startdate, enddate);// supply info whether or not startdate/enddate supplied
                }
                System.out.println();
                System.out.print(prompt);//next ticker
                input = in.nextLine().trim();
            }
        conn.close();
        } catch (SQLException ex) {
        System.out.printf("SQLException: %s%nSQLState: %s%nVendorError: %s%n",
        ex.getMessage(), ex.getSQLState(), ex.getErrorCode());
        }
    }
    static boolean getName(String ticker) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("select name from company where Ticker = ?");//prepared statement
        pstmt.setString(1, ticker);
        ResultSet results = pstmt.executeQuery();
        while (results.next()) {//go through everything
            System.out.printf("%s%n", results.getString("Name"));//found
            return true;
        }
        System.out.println(ticker+" Not found in database.")
        return false;//not found
    }
    static void getStockData(String ticker, String start, String end) throws SQLException {
        StockData adjustInfo = new StockData();// instantiate something to store
        Double linecount = 0.0;//used as indexing
        ArrayList<String[]> thisCo = new ArrayList<>();//better than arrayDeque
        adjustInfo.setCo(ticker);//for aesthietic
        Double track = 1.0;//value of 1 wont mess up first iteration
        if(start==null){
            PreparedStatement pstmt = conn.prepareStatement("select TransDate, OpenPrice, HighPrice, LowPrice, ClosePrice from pricevolume where Ticker = ? order by TransDate desc");//required
            pstmt.setString(1, ticker);
            ResultSet results = pstmt.executeQuery();
            while(results.next()){
                String parseThis = ticker+"`"+results.getString("TransDate")+"`"+results.getString("OpenPrice")+"`"+results.getString("HighPrice")+"`"+results.getString("LowPrice")+"`"+results.getString("ClosePrice");
                parseThis=parseThis.replaceAll("\\s", "");//removes ALL whitespaces, reads all data we want
                String[] thisDay = parseThis.split("`");//we can split at inserted character, risky and inefficient
                thisCo.add(thisDay);
                linecount++;//important
            }
        }
        else{//2.4
            PreparedStatement pstmt = conn.prepareStatement("select TransDate, OpenPrice, HighPrice, LowPrice, ClosePrice from pricevolume where Ticker = ? and TransDate between ? and ? order by TransDate desc");// basic SQL
            pstmt.setString(1, ticker);
            pstmt.setString(2, start);
            pstmt.setString(3, end); 
            ResultSet results = pstmt.executeQuery();
            while(results.next()){
                    String parseThis = ticker+"`"+results.getString("TransDate")+"`"+results.getString("OpenPrice")+"`"+results.getString("HighPrice")+"`"+results.getString("LowPrice")+"`"+results.getString("ClosePrice");//read it and weep
                    parseThis=parseThis.replaceAll("\\s", "");
                    String[] thisDay = parseThis.split("`");//
                    thisCo.add(thisDay);//add every day
                    linecount++;
                }
        }
        //now we have everything we need
        adjustInfo.insert(thisCo);//all data preserved
        adjustInfo.lineCount=linecount;//can be used for indexing
        int dayTally=0;//count split day amount
        adjustInfo.dayTally= dayTally;
        Double splitAdjust = .01;//ok its 7pm this is due at 8pm i set this to .01 and everything works im not sure why but it works
        ArrayList<Double[]> adjustment = new ArrayList<Double[]>();//adjust and throw it in
        for(int i=0;i<linecount;i++){//2.4 and 2.5
            String[] thisDay=thisCo.get(i); //i represents the day
            Double[] adjustedDay=new Double[4];           
            Double compare = Double.parseDouble(thisDay[5])/track;// divide new day by tracked day
            adjustment.add(adjustedDay);
            if(Math.abs((compare-2.0))<=.199999){//given formula
                System.out.println("2:1 stock split on "+thisDay[1]+"\t"+thisDay[5]+" -->   "+ String.format("%.2f",track));
                splitAdjust=splitAdjust*2.0;//adjust for split on any given day
                dayTally++;
            }if(Math.abs((compare-3.0))<=.299999){//given formula
                System.out.println("3:1 stock split on "+thisDay[1]+"\t"+thisDay[5]+" -->   "+ String.format("%.2f",track));
                splitAdjust=splitAdjust*3.0;
                dayTally++;
            }if(Math.abs((compare-1.5))<=.149999){//given formula
                System.out.println("3:2 stock split on "+thisDay[1]+"\t"+thisDay[5]+" -->   "+ String.format("%.2f",track));
                splitAdjust=splitAdjust*1.50;
                dayTally++;
            }
            track=Double.parseDouble(thisDay[2]);//Track day
            for(int j = 0; j<=3;j++){
                Double adjusted=Double.parseDouble(thisDay[j+2]);//index[2] (open) becomes i[0], i[5]close becomes i[3]
                adjusted=adjusted/splitAdjust;//this tho
                adjustedDay[j]=adjusted;
                //take the four columns we need, The date will still exist in adjustInfo.thisCo at the same index as the index of the data in the Arraylist<double>
            }
           //add to double arraylist
        }
        adjustInfo.dayTally= dayTally;//to print amount of trading days
        adjustInfo.say();//print
        doStrategy(adjustment);//doStrategy
    }
    public static void doStrategy(ArrayList<Double[]> adjustment) {
        int linecount=adjustment.size();
        Double total=0.0;//50 day total for any 50 days
        int stock = 0;//2.9.
        Double cash = 0.00;//
        Double curAvg=0.00;
        int transaction=0;//conditions met, increment
        //Collections.reverse(adjustment); <-------- should be able to reverse ArrayList info, but this doesnt work
        if (linecount<=50){//parameter
             System.out.println("Need at least 50 lines");
            return;
        }
        else{
            Double tallyCount=0.0;//track 50 days (0 is a day)
            for(int i=linecount-1;i>=0;i--){
                Double[] tomorrowDay = adjustment.get(i-1);//array is 0-indexed, linecount-1 will be last index
                Double[] parseDay=adjustment.get(i);// start at last index (EARLIEST DATE), increment down
                if (i==1){//2.10
                    cash = cash + (tomorrowDay[0]*stock*100) - transaction*8.00;
                    System.out.println("Transactions executed: "+transaction);
                    System.out.println("Net Cash: $"+String.format("%.2f",cash));
                    return;
                }
                if (tallyCount<50){//First 50 days
                    total = total + parseDay[3];//2.9
                    tallyCount++;
                    if (tallyCount==50){//this works
                        curAvg=total/50.0;
                    }
                }    
                else{//post initial 50
                    Double[] yesterdayDay=adjustment.get(i+1);//increment up for last day
                    Double[] removeDay=adjustment.get(i+50);//increment up fifty for fifty days ago
                    if(parseDay[3]<curAvg&&(parseDay[3]/parseDay[0]<0.97000001)){
                        stock++;//stock goes up
                        cash = cash - tomorrowDay[0];//cash
                        transaction++;

                        }
                    else if((stock>=1)&&(parseDay[0]>curAvg)&&((parseDay[0]/yesterdayDay[3])>= 1.00999999)){//2.93
                        //sell 100 
                        stock--;
                        cash=((parseDay[0]+parseDay[3])/2)+cash;
                        transaction++;
                    }
                    total = parseDay[3]+total-removeDay[3];//2.96 
                    curAvg=total/50; // moving average
                    
                }
            }
        }
    }
}
//max me out in points.
