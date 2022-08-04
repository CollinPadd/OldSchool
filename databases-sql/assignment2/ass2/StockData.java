//Collin Paddock
//Assignment2 data file
import java.util.ArrayList;
public class StockData{
       
        public Double lineCount=0.0;// represents amount of iterations per company
        public int dayTally = 0;// tracks amount of crazy days(as required) or splits
        private String ticker;//holds company name for aesthetics 
        ArrayList<String[]> companyData= new ArrayList<String[]>();
        public String getCompany(){// pull specific node value ( is private)
            return this.ticker;
        }
        public ArrayList<String[]> getData(){// pull specific node value ( is private)
            return this.companyData;
        }
        public String[] findDay(int day){// pull specific node value ( is private)
            return this.companyData.get(day);
        }
        public void setDay(int index, String[] dayInfo){
            this.companyData.set(index, dayInfo);
        }
      
        public void insert(ArrayList<String[]> info){//insert this node's value
           this.companyData=info;// we fill this with
        }
        public void say(){
           System.out.println(dayTally+" in "+lineCount+" trading days");//required
        }
        public void setCo(String ticker) {
            this.ticker = ticker;// assigns arbitrary string for this node
        }
}