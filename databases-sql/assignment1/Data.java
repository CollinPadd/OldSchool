//Collin Paddock 
import java.util.ArrayList;
public class Data{// node class
    private ArrayList<String[]> companyData= new ArrayList<String[]>();// instantiate a data type to hold all data, ArrayList of String[]
    public double track=1;// stores yesterday price to compare in a split
    double craziestDay=0;//stores craziest day so far
    String sayCraziestDay=" ";//defaults to this statement
    public int lineCount=0;// represents amount of iterations per company
    int dayTally = 0;// tracks amount of crazy days(as required) or splits
    private String company;//holds company name for aesthetics
    public void findCrazyDays(){
        Boolean checkFirst=false;//checks if first line in company data, assume we need to check
        for(int i=0;i<lineCount;i++){
            //i represents the day we will check
            String[] thisDay=companyData.get(i);
            if (checkFirst){
                track=Double.parseDouble(thisDay[2]);// when in a new company, this will set and maintain opening value for the first day
                checkFirst=true;// first day is done
            }
            double compare = dayCraze(thisDay);// analyze day
             if(compare>=.15){
                dayTally++;//tally up
                compare =compare*10000;//formatting
                if (Math.abs(compare)>Math.abs(craziestDay)){
                    craziestDay=compare;//set new crazy day
                    sayCraziestDay="The craziest day: "+ thisDay[1] +" "+ String.format("%.2f",compare/100);//set string to be said at end
                }
                System.out.println("Crazy day: "+ thisDay[1]+" "+String.format("%.2f",compare/100));//print success to screen move to next
             }
        }
    }
    public void findSplit(){
        dayTally=0;// set this to 0 for split #
        for(int i=0;i<lineCount;i++){
            String[] thisDay=companyData.get(i);
            //i represents the day
            double compare = Double.parseDouble(thisDay[5])/track;// divide new day by tracked day
            if(Math.abs((compare-2.0))<.20){//given formula
                System.out.println("2:1 stock split on "+thisDay[1]+"\t"+thisDay[5]+" -->   "+ String.format("%.2f",track));
                dayTally++;
            }if(Math.abs((compare-3.0))<.30){//given formula
                System.out.println("3:1 stock split on "+thisDay[1]+"\t"+thisDay[5]+" -->   "+ String.format("%.2f",track));
                dayTally++;
            }if(Math.abs((compare-1.5))<.15){//given formula
                System.out.println("3:2 stock split on "+thisDay[1]+"\t"+thisDay[5]+" -->   "+ String.format("%.2f",track));
                dayTally++;
            }
            track=Double.parseDouble(thisDay[2]);//set this day to compare to next
        }
    }
    public String getCompany(){// pull specific node value ( is private)
        return this.company;
    }
    public ArrayList<String[]> getData(){// pull specific node value ( is private)
        return this.companyData;
    }
    public void insert(ArrayList<String[]> info){//insert this node's value
       this.companyData=info;// we fill this with
    }
    public void setCo(String company){
        this.company=company;// assigns arbitrary string for this node
    }
    public double dayCraze(String[] thisDay) {
        double high=Double.parseDouble(thisDay[3]);// takes high price from array
        double low=Double.parseDouble(thisDay[4]);// takes low price from array
        double craze = (high-low)/high;//given formula
        return craze;
    }
}
