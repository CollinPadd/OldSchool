
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
class Assignment3Skeleton {
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
                        iData.tickers.size(), industry, iData.startDay, iData.endDay, iData.numDays);
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
        // getIndustryPriceData = readerConn.prepareStatement(getIndustryPriceDataQuery);
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
    
    static List<String> getIndustries() throws SQLException {
		List<String> result = new ArrayList<>();
		// To Do: Execute the appropriate query (you need one of them) and return a list of the industries 
        // int linecount=0;
        PreparedStatement statement = readerConn.prepareStatement(getAllIndustries);
        ResultSet industries = statement.executeQuery();
        while (industries.next()) {
            result.add(industries.getString("Industry"));
            // linecount++;
            // IndustryData theBasket = new IndustryData();
            // theBasket.industryName = industries.getString("Industry");
        }
        return result;
    }
    
    static IndustryData processIndustry(String industry) throws SQLException,ParseException {
        // To Do: Execute the appropriate SQL queries (you need two of them) and return an object of IndustryData    
        PreparedStatement statement = readerConn.prepareStatement(getDatesQuery);
       
        statement.setString(1, industry);
        statement.setString(2, "150");
        ResultSet rs = statement.executeQuery();

        String startDay="";
        String endDay="";
        while (rs.next()){
            startDay = rs.getString("start");
            endDay = rs.getString("end");    
        }
        //got dates

        statement = readerConn.prepareStatement(getTickerDatesQuery);

        statement.setString(1, industry);
        statement.setString(2, "150");
        statement.setString(3, endDay);
        statement.setString(4, startDay);
        rs = statement.executeQuery();
        
        List<String> tickerList = new ArrayList<>();

        // List<String> startDays = new ArrayList<>();
        // List<String> endDays = new ArrayList<>();
        int numDays=99999;
        while (rs.next()) {
            String getTicker = rs.getString("Ticker");
            tickerList.add(getTicker);
            // if (industry.equalsIgnoreCase("Telecommunications Services")){
            //     System.out.println(rs.getString("TradingDays"));
            // }
            if (Integer.parseInt(rs.getString("TradingDays"))<numDays){
                numDays=rs.getInt("TradingDays");
            }

            // String storestartDay = rs.getString("startDay");//for java compare strings
            // //startDays.add(storestartDay);
            // String storeendDay = rs.getString("endDay");
            // //endDays.add(storeendDay);
        }
        statement=readerConn.prepareStatement("select P.TransDate, P.openPrice, P.closePrice "+"from pricevolume P "+"where Ticker = ? and TransDate >= ? "+"and TransDate <= ?"); 
        statement.setString(1, tickerList.get(0));
        statement.setString(2, startDay);
        statement.setString(3, endDay);
        
        rs = statement.executeQuery();
        List<String[]> intervalSet = new ArrayList<>();
        String[] intervalDates = new String[2];
        int daysLeft = 60;
        while(rs.next()){
            if (daysLeft==60){
               intervalDates[0] = rs.getString("TransDate");
            }
            if (daysLeft==0){
                intervalDates[1] = rs.getString("TransDate");
                intervalSet.add(intervalDates);
                intervalDates = new String[2];
                daysLeft=60;
            }

            daysLeft--;
        }
        // String startDay=findStart(startDays);
        // String endDay=findEnd(endDays);
        //find largest start dates
        //find smallest end dates
        
        return new IndustryData(tickerList, numDays, startDay, endDay, intervalSet);
    }
    
    // Don't forget to do the split adjustment
        // After those calculations, insert the data into the Performance table you created earlier. You may use the following way to do that for each company (or ticker) of an indsutry: 
        public static void processIndustryGains(String thisIndustry, IndustryData info) throws SQLException {
            List<String> tickerName=info.tickers;
            List<String[]> tradingInterval =info.intervalSet;
            // Get primary performance values ticker return
            for(int i=0; i< tradingInterval.size();i++){
            for (String ticker : tickerName) {
                String[] parseInterval =tradingInterval.get(i);
                String startDay = parseInterval[0];
                //System.out.println("start date " + startDay);
                String endDay = parseInterval[1];
                //System.out.println("end date " + endDay);
                PreparedStatement pstmt = readerConn.prepareStatement
                        ("select Ticker, TransDate, openPrice, closePrice" +
                                " from pricevolume " +
                                " where Ticker = ? and TransDate>= ? and TransDate <= ?" +
                                "order by TransDate DESC");
    
                pstmt.setString(1, ticker);
                //System.out.println("ok");
                pstmt.setString(2, startDay);
                //System.out.println("ok2");
                pstmt.setString(3, endDay);
                //System.out.println("ok3");
                ResultSet result = pstmt.executeQuery();
    
                String date;
                double tomorrowMarket = 0.0;
                double thisDayClose;
                double dividend = 1;
            
           
                ArrayList<IndustryData.thisDayNum> fixList = new ArrayList<>();
    
    
               
                while (result.next()) {
                
                    date = result.getString("TransDate");
               
                    double openPrice = Double.parseDouble(result.getString("OpenPrice"));
                    double closePrice = Double.parseDouble(result.getString("ClosePrice"));
                 
                    thisDayClose = closePrice;
             
                 
                    double splitAdjust = splitAdjustment(date, tomorrowMarket, thisDayClose);
                    if (splitAdjust> 0) {
                   
                        dividend= dividend* splitAdjust;
                        
                    }
           
                    IndustryData.thisDayNum aGivenDay = new IndustryData.thisDayNum();
                    aGivenDay.day = date;
          
                    Assignment3Skeleton.IndustryData.thisDayNum.openAdjusted = openPrice / dividend;
                    Assignment3Skeleton.IndustryData.thisDayNum.closeAdjusted = closePrice / dividend;

                    fixList.add(0, aGivenDay);
                    tomorrowMarket = openPrice;
                    if (fixList.size() >= 1) {
                        writeToLocation(closePrice, openPrice, thisIndustry, ticker, startDay, endDay);
    
                    }
                }
            }
        }
    }
    
        private static void writeToLocation(double closePrice, double openPrice, String thisIndustry, String thisTicker, String startDay, String endDay) throws SQLException {
            double tickerReturn = (closePrice / openPrice) - 1;
            PreparedStatement insertPerformanceData = writerConn.prepareStatement(
                    "insert into Performance(Industry, Ticker, startDay, endDay, TickerReturn, IndustryReturn)"
                            + "values(?, ?, ?, ?, ?, ?)");
            insertPerformanceData.setString(1, thisIndustry);
            insertPerformanceData.setString(2, thisTicker);
            insertPerformanceData.setString(3, startDay);
            insertPerformanceData.setString(4, endDay);
            insertPerformanceData.setString(5,String.format("%10.7f", tickerReturn));
            insertPerformanceData.setString(6,String.format("%10.7f", tickerReturn));
    
            insertPerformanceData.executeUpdate();
        }
    
        private static double splitAdjustment(String date, double tomorrowMarket, double thisDayClose) {
            double futureMarket = thisDayClose / tomorrowMarket;
           
            if (Math.abs(futureMarket- 2.00) < 0.20) {
             
                return 2.00;
               
            } else if (Math.abs(futureMarket- 3.00) < 0.20) {
                return 3.00;
            } else if (Math.abs(futureMarket- 1.50) < 0.15) {
                return 1.50;
            }
            return 0;
        }
        
    }
                
                // static void processIndustryGains(String industry, IndustryData data) throws SQLException {
                //     // To Do:
                //     // In this method, you should calculate the ticker return and industry return. Look at the assignment description to know how to do that 
                //     PreparedStatement statement = readerConn.prepareStatement(getIndustryPriceDataQuery);
                //     int linecount=0;
                //     double track=0;
                //     double tommorrowPrice = 0.0;
                //     //double splitAdjustment = 1;
                //     ArrayList<String[]> thisInd = new ArrayList<String[]>();
                //     int dayCount = data.numDays;
                //     int intervalCount=data.intervalSet.size();
                //     for(int j=0;j<intervalCount;j++){
            
                //         //run comparisons for industr
                //         // for date
            
                //      for(int i=0;i<dayCount;i++){//dont need to do ticker, just use bulk info
                //             String begin = data.intervalSet.get(j)[0];
                //             String end =data.intervalSet.get(j)[1];
                //             statement.setString(1, industry);
                //             statement.setString(2, begin);
                //             statement.setString(3, end); 
                //             ResultSet results = statement.executeQuery();
                //             while(results.next()){
                //                         String parseThis = results.getString("TransDate")+"`"+results.getString("OpenPrice")+"`"+results.getString("ClosePrice");//read it and weep
                //                         parseThis=parseThis.replaceAll("\\s", "");
                //                         String[] thisDay = parseThis.split("`");//
                //                         thisInd.add(thisDay);//add every day
                //                         linecount++;
                //             }
                            
                //             //now we have everything we need
                           
                //             //int dayTally=0;//count split day amount
                //             Double splitAdjustment = .01;
                //             ArrayList<Double[]> adjustment = new ArrayList<Double[]>();//adjust and throw it in
                //             for(int l=0;l<linecount;l++){//2.4 and 2.5
                //                 String[] thisDay=thisInd.get(l); //L represents the day
                //                 Double[] adjustedDay=new Double[2];           
                //                 Double compare = Double.parseDouble(thisDay[2])/track;// divide new day by tracked day
                //                 adjustment.add(adjustedDay);
                //                 if(Math.abs((compare-2.0))<=.199999){//given formula
                //                     // System.out.println("2:1 stock split on "+thisDay[1]+"\t"+thisDay[2]+" -->   "+ String.format("%.2f",track));
                //                     splitAdjustment=splitAdjustment*2.0;//adjust for split on any given day
                //                     //dayTally++;
                //                 }if(Math.abs((compare-3.0))<=.299999){//given formula
                //                     // System.out.println("3:1 stock split on "+thisDay[1]+"\t"+thisDay[2]+" -->   "+ String.format("%.2f",track));
                //                     splitAdjustment=splitAdjustment*3.0;
                //                     //dayTally++;
                //                 }if(Math.abs((compare-1.5))<=.149999){//given formula
                //                     // System.out.println("3:2 stock split on "+thisDay[1]+"\t"+thisDay[2]+" -->   "+ String.format("%.2f",track));
                //                     splitAdjustment=splitAdjustment*1.50;
                //                     //dayTally++;
                //                 }
                //                 track=Double.parseDouble(thisDay[2]);//Track day
                //                 for(int c = 1; c<3;c++){
                //                     Double adjusted=Double.parseDouble(thisDay[c]);//index[2] (open) becomes i[0], i[5]close becomes i[3]
                //                     adjusted=adjusted/splitAdjustment;//this tho
                //                     adjustedDay[c-1]=adjusted;
                //                     //take the four columns we need, The date will still exist in adjustInfo.thisInd at the same index as the index of the data in the Arraylist<double>
                //                 }
                //                 adjustment.add(adjustedDay);
                //                //add to double arraylist
                //             }
                //         }
                //     }
                //     }
                // }
        //ticker return = (closeprice/openprice)-1
		
			// insertPerformanceData.setString(1, industry);
			// insertPerformanceData.setString(2, ticker);
			// insertPerformanceData.setString(3, startDay);
			// insertPerformanceData.setString(4, endDay);
			// insertPerformanceData.setString(5, String.format("%10.7f", tickerReturn);
			// insertPerformanceData.setString(6, String.format("%10.7f", industryReturn);
			// int result = insertPerformanceData.executeUpdate();
