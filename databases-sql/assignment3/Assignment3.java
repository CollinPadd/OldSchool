//Collin Paddock
//Assignment 3

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.io.*;
import java.text.*; 
//javac -cp .;mysql-connector-java-8.0.20\mysql-connector-java-8.0.20.jar Assignment3.java
//java -cp .;mysql-connector-java-8.0.20\mysql-connector-java-8.0.20.jar Assignment3
class Assignment3 {
    static final String defaultReaderParams = "readerparams.txt";
    static final String defaultWriterParams = "writerparams.txt";
    static Connection readerConn = null;   
    static Connection writerConn = null;
    static Connection conn = null;
    static final String getDatesQuery =
        "select max(startDay) as start, min(endDay) as end" +
        "  from (select Ticker, min(TransDate) as startDay, max(TransDate) as endDay," +
        "            count(distinct TransDate) as tradingDays" +
        "          from company natural join pricevolume" +
        "          where Industry = ?" +
        "          group by Ticker" +
        "          having tradingDays >= ?) as TickerDates";
    
    static final String getTickerDatesQuery = 
        "select Ticker, min(TransDate) as startDay, max(TransDate) as endDay," +
        "      count(distinct TransDate) as tradingDays" +
        "  from company natural join pricevolume" +
        "  where Industry = ?" +
        "    and TransDate >= ? and TransDate <= ?" +
        "  group by Ticker" +
        "  having tradingDays >= ?" +
        "  order by Ticker";
        
    static final String getIndustryPriceDataQuery =
        "select Ticker, TransDate, OpenPrice, ClosePrice" +
        "  from pricevolume natural join company" +
        "  where Industry = ?" +
        "    and TransDate >= ? and TransDate <= ?" +
        "  order by TransDate, Ticker";

	static final String getAllIndustries = 
            "select distinct Industry" +
            "  from company" +
            "  order by Industry";		
    static final String dropPerformanceTable =
        "drop table if exists Performance;";
    static final String createPerformanceTable =
        "create table Performance (" +
        "  Industry char(30)," +
        "  Ticker char(6)," +
        "  startDay char(10)," +
        "  endDay char(10)," +
        "  TickerReturn char(12)," +
        "  IndustryReturn char(12)" +
        "  );";
    static final String insertPerformance =
        "insert into Performance(Industry, Ticker, startDay, endDay, TickerReturn, IndustryReturn)" +
        "  values(?, ?, ?, ?, ?, ?);";
    static class IndustryData {
        public IndustryData(List<String> tickers2, int numDays2, String startDay2, String endDay2, List<String[]> intervalSet2) {
            tickers=tickers2;
            numDays=numDays2;
            startDay=startDay2;
            endDay=endDay2;
            intervalSet=intervalSet2;
        }
        List<String> tickers;
        int numDays;
        String industryName;
        String startDay;
        String endDay;
        List<String[]> intervalSet;
        List<Double> industryAdjustedDays;
        private static class thisDayNum {
            String day = "";
            static double openAdjusted = 0.0;
            static double closeAdjusted = 0.0;
        }
        // To Do: Create this class which  contains the list of the tickers, the common days, start date, and end date
    }
    public static void main(String[] args) throws Exception {
        // Get connection properties
        Properties readerProps = new Properties();
        readerProps.load(new FileInputStream(defaultReaderParams));
        Properties writerProps = new Properties();
        writerProps.load(new FileInputStream(defaultWriterParams));
        try {
            // Setup Reader and Writer Connection
            setupReader(readerProps);
            setupWriter(writerProps);
            List<String> industries = getIndustries();
            System.out.printf("%d industries found%n", industries.size());
            for (String industry : industries) {
                System.out.printf("%s%n", industry);
            }
            System.out.println();            
			for (String industry : industries) {
                System.out.printf("Processing %s%n", industry);
                IndustryData iData = processIndustry(industry);
                if (iData != null && iData.tickers.size() > 2) {
                    System.out.printf("%d accepted tickers for %s(%s - %s), %d common dates%n",
                        iData.tickers.size(), industry, iData.startDay, iData.endDay, iData.numDays);//given format
                    processIndustryGains(industry, iData);
                }
                else
                    System.out.printf("Insufficient data for %s => no analysis%n", industry);
                System.out.println();
            }
            
            // Close everything you don't need any more 
            System.out.println("Database connections closed");
        } catch (SQLException ex) {
            System.out.printf("SQLException: %s%nSQLState: %s%nVendorError: %s%n",
                                    ex.getMessage(), ex.getSQLState(), ex.getErrorCode());
        }
    }
    static void setupReader(Properties connectProps) throws SQLException {
        String dburl = connectProps.getProperty("dburl");
        String username = connectProps.getProperty("user");
        readerConn = DriverManager.getConnection(dburl, connectProps);
        System.out.printf("Reader connection %s %s established.%n", dburl, username);
        // getDates = readerConn.prepareStatement(getDatesQuery);
        // getTickerDates = readerConn.prepareStatement(getTickerDatesQuery);
        // getIndustryPriceData = readerConn.prepareStatement(getIndustryPriceDataQuery);//idk what these are for
    } 
    static void setupWriter(Properties connectProps) throws SQLException {
        String dburl = connectProps.getProperty("dburl");
        String username = connectProps.getProperty("user");
        writerConn = DriverManager.getConnection(dburl, connectProps);
        System.out.printf("Writer connection %s %s established.%n", dburl, username);
        // Create Performance Table; If this table already exists, drop it first
        Statement tstmt = writerConn.createStatement();
        tstmt.execute(dropPerformanceTable);
        tstmt.execute(createPerformanceTable);
        tstmt.close();
        //insertPerformanceData = writerConn.prepareStatement(insertPerformance);
    } 
    static List<String> getIndustries() throws SQLException {//list of all the industries
		List<String> result = new ArrayList<>();
		// To Do: Execute the appropriate query (you need one of them) and return a list of the industries 
        // int linecount=0;
        PreparedStatement statement = readerConn.prepareStatement(getAllIndustries);
        ResultSet industries = statement.executeQuery();
        while (industries.next()) {
            result.add(industries.getString("Industry"));
        }
        return result;
    }
    static IndustryData processIndustry(String industry) throws SQLException,ParseException {
        // To Do: Execute the appropriate SQL queries (you need two of them) and return an object of IndustryData    
        PreparedStatement pstmst = readerConn.prepareStatement(getDatesQuery);
        pstmst.setString(1, industry);
        pstmst.setString(2, "150");//from rubric
        ResultSet rs = pstmst.executeQuery();
        String startDay="";
        String endDay="";
        while (rs.next()){// get
            startDay = rs.getString("start");
            endDay = rs.getString("end");    
        }
        //got dates for next query
        pstmst = readerConn.prepareStatement(getTickerDatesQuery);
        pstmst.setString(1, industry);
        pstmst.setString(2, "150");
        pstmst.setString(3, endDay);
        pstmst.setString(4, startDay);
        rs = pstmst.executeQuery();
        ArrayList<String> tickerList = new ArrayList<>();
        int numDays=99999;// find minimum amount of days
        while (rs.next()) {
            String getTicker = rs.getString("Ticker");
            tickerList.add(getTicker);
            if (Integer.parseInt(rs.getString("TradingDays"))<numDays){
                numDays=rs.getInt("TradingDays");// minimum days among trading
            }
        }
        pstmst=readerConn.prepareStatement("select P.TransDate, P.openPrice, P.closePrice "+"from pricevolume P "+"where Ticker = ? and TransDate >= ? "+"and TransDate <= ?"); //get the max min date and the min max date
        pstmst.setString(1, tickerList.get(0));
        pstmst.setString(2, startDay);
        pstmst.setString(3, endDay);
        rs = pstmst.executeQuery();
        List<String[]> intervalSet = new ArrayList<>();//holds interval dates
        String[] intervalDates = new String[2];//hold's two values, start of interval and end
        int daysLeft = 60;//iterate to 0 and reset
        int intervalCount=0;//inefficient
        while(rs.next()){
            if (daysLeft==60){
               intervalDates[0] = rs.getString("TransDate");//save
            }
            if (daysLeft==0){
                intervalDates[1] = rs.getString("TransDate");//save
                intervalSet.add(intervalDates);
                intervalDates = new String[2];//new
                daysLeft=60;//reset amount of days
                intervalCount++;//how many intervals
            }
            daysLeft--;
        }
        //we have the dates of the intervals
        int commonDays=tickerList.size()*intervalCount;// now use given formula to calculate common days
        return new IndustryData(tickerList, commonDays, startDay, endDay, intervalSet);
    }
    public static void processIndustryGains(String thisIndustry, IndustryData info) throws SQLException {
        List<String> tickerName=info.tickers;
        List<String[]> tradingInterval =info.intervalSet;
        //get the interval range of dates so that
        for(int i=0; i< tradingInterval.size();i++){// for each interval we check
            for (String ticker : tickerName) { //each ticker
                String[] parseInterval =tradingInterval.get(i);
                String startDay = parseInterval[0];//from the start date
                String endDay = parseInterval[1];//to the end date
                PreparedStatement pstmt = readerConn.prepareStatement
                        ("select Ticker, TransDate, openPrice, closePrice" +
                                " from pricevolume " +
                                " where Ticker = ? and TransDate>= ? and TransDate <= ?" +
                                "order by TransDate DESC");
    
                pstmt.setString(1, ticker);//so we take a certain ticker
                pstmt.setString(2, startDay);//and 
                pstmt.setString(3, endDay);//date range
                String date;//and parse any date
                double splitOpen = 0.0;//adjusting 
                double splitClose=0.0;//for 
                double dividend = 1;//split
                ArrayList<IndustryData.thisDayNum> fixList = new ArrayList<>();//store the split list and reorder
                ResultSet result = pstmt.executeQuery();
                while (result.next()) {//for each day
                    date = result.getString("TransDate");//we take the transdate
                    double openPrice = Double.parseDouble(result.getString("OpenPrice"));//and 
                    double closePrice = Double.parseDouble(result.getString("ClosePrice"));//the unadjusted prices
                    splitClose = closePrice;
                    splitOpen = openPrice;
                    double splitAdjust = splitAdjustment(date, splitOpen, splitClose);//find if the day causes a split
                    if (splitAdjust!=0) {
                        dividend= dividend*splitAdjust; //and apply adjustment   
                    }
                    double adjustedOpen =openPrice / dividend;//to open
                    double adjustedClose =closePrice / dividend;//and close
                    IndustryData.thisDayNum aGivenDay = new IndustryData.thisDayNum();//we take the day to compare to the industy basket
                    aGivenDay.day = date;//save to instance
                    Assignment3.IndustryData.thisDayNum.openAdjusted = openPrice / dividend;//save
                    Assignment3.IndustryData.thisDayNum.closeAdjusted = closePrice / dividend;//ticker info
                    fixList.add(0, aGivenDay);// and reorder the list
                    if (fixList.size() >= 1) {
                        writeToLocation(adjustedClose, adjustedOpen, thisIndustry, ticker, startDay, endDay, info.tickers.size());
                    }
                }
            }
        }
    }
    public static double getIndustryReturn(String startDay, String endDay, String industry,String notThisTicker) throws SQLException{
        PreparedStatement getIndustryData = readerConn.prepareStatement("select sum(P.openPrice) as openTotal, sum(P.closePrice) as closeTotal "+"  from pricevolume P natural join company " +   " where Industry = ? "+" and Ticker not in ( ? ) "+"    and TransDate >= ? and TransDate <= ? ");//from pdf
        getIndustryData.setString(1, industry);//we get the industry data
        getIndustryData.setString(2, notThisTicker);//excluding a specific ticker
        getIndustryData.setString(3, startDay);//and for a range of dates
        getIndustryData.setString(4, endDay);
        double returnThis=0.0;
        ResultSet result = getIndustryData.executeQuery();
        while(result.next()){
            double openNum = Double.parseDouble(result.getString("openTotal"));
            double closeNum = Double.parseDouble(result.getString("closeTotal"));
            returnThis = closeNum/openNum;// get fraction of the sum of days
           // return the sum of values when k =1 
        }
        return returnThis;
    }
    private static void writeToLocation(double closePrice, double openPrice, String thisIndustry, String thisTicker, String startDay, String endDay, int tickerSize) throws SQLException {
        double tickerReturn = (closePrice / openPrice) - 1;//given formula
        double industryParse=getIndustryReturn(startDay,endDay,thisIndustry,thisTicker);
        double industryReturn = ((1/tickerSize-1)*industryParse)-1;
        PreparedStatement insertPerformanceData = writerConn.prepareStatement(insertPerformance);
        insertPerformanceData.setString(1, thisIndustry);//so for each industry
        insertPerformanceData.setString(2, thisTicker);//and each ticker
        insertPerformanceData.setString(3, startDay);//we write the range
        insertPerformanceData.setString(4, endDay);//of the dates
        insertPerformanceData.setString(5,String.format("%10.7f", tickerReturn));//and write the calculated tickerReturn
        insertPerformanceData.setString(6,String.format("%10.7f", industryReturn));//and industryReturn
        insertPerformanceData.executeUpdate();// to the performance table
    }
    
    private static double splitAdjustment(String date, double tomorrowMarket, double thisDayClose) {//perform split adjustment from assignment 1 and assignment2
        double futureMarket = thisDayClose / tomorrowMarket;//calculate split
        if (Math.abs(futureMarket- 2.00) < 0.20) {
                return 2.00;
        } else if (Math.abs(futureMarket- 3.00) < 0.20) {
                return 3.00;
        } else if (Math.abs(futureMarket- 1.50) < 0.15) {
                return 1.50;
        }//no adjustment
            return 0;
        }   
    }
               