/* 
This is a Java skeleton code to help you out how to start this assignment.
Please keep in mind that this is NOT a compilable/runnable java file.
Please feel free to use this skeleton code.
Please give a closer look at the "To Do" parts of this file. You may get an idea of how to finish this assignment. 
*/

import java.util.*;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.PrintWriter;

class Assign2 {
   
   static class StockData {	   
	   // To Do: 
	   // Create this class which should contain the information  (date, open price, high price, low price, close price) for a particular ticker
   }
   
   static Connection conn;
   static final String prompt = "Enter ticker symbol [start/end dates]: ";
   
   public static void main(String[] args) throws Exception {
      String paramsFile = "readerparams.txt";
      if (args.length >= 1) {
         paramsFile = args[0];
      }
      
      Properties connectprops = new Properties();
      connectprops.load(new FileInputStream(paramsFile));
      try {
         Class.forName("com.mysql.jdbc.Driver");
         String dburl = connectprops.getProperty("dburl");
         String username = connectprops.getProperty("user");
         conn = DriverManager.getConnection(dburl, connectprops);
         System.out.printf("Database connection %s %s established.%n", dburl, username);
         
         Scanner in = new Scanner(System.in);
         System.out.print(prompt);
         String input = in.nextLine().trim();
         
         while (input.length() > 0) {
            String[] params = input.split("\\s+");
            String ticker = params[0];
            String startdate = null, enddate = null;
            if (getName(ticker)) {
               if (params.length >= 3) {
                  startdate = params[1];
                  enddate = params[2];
               }               
               Deque<StockData> data = getStockData(ticker, startdate, enddate);
               System.out.println();
               System.out.println("Executing investment strategy");
               doStrategy(ticker, data);
            } 
            
            System.out.println();
            System.out.print(prompt);
            input = in.nextLine().trim();
         }

         // Close the database connection

      } catch (SQLException ex) {
         System.out.printf("SQLException: %s%nSQLState: %s%nVendorError: %s%n",
                           ex.getMessage(), ex.getSQLState(), ex.getErrorCode());
      }
   }
   
   static boolean getName(String ticker) throws SQLException {
	  // To Do: 
	  // Execute the first query and print the company name of the ticker user provided (e.g., INTC to Intel Corp.) 
	  // Please don't forget to use a prepared statement
   }

   static Deque<StockData> getStockData(String ticker, String start, String end) {	  
	  // To Do: 
	  // Execute the second query which will return stock information of the ticker (descending on the transaction date)
	  // Please don't forget to use prepared statement	   

      Deque<StockData> result = new ArrayDeque<>();

	  // To Do: 
	  // Loop through all the dates of that company (descending order)
			// Find split if there is any (2:1, 3:1, 3:2) and adjust the split accordingly
			// Include the adjusted data to result (which is a Deque); You can use addFirst method for that purpose
	         
      return result;
   }
   
   static void doStrategy(String ticker, Deque<StockData> data) {
	  //To Do: 
	  // Apply Steps 2.6 to 2.10 explained in the assignment description 
	  // data (which is a Deque) has all the information (after the split adjustment) you need to apply these steps
}
}
