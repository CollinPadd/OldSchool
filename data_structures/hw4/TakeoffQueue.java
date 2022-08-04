import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.lang.Math;
public class TakeoffQueue {
    static int currentTime = 0;
    public static void main(String[] args) throws IOException {
        ArrayList<String> flights = new ArrayList<String>();// store file data, will change this to tree
        ArrayList<String> userCommand = new ArrayList<String>();// process input
        for (String command : args) {// iterate over arguments
            userCommand.add(command);
        }
        String targetFile = userCommand.get(0);
        int commandNum = Integer.valueOf(userCommand.get(1));
        File thisFile = new File(targetFile);
        BufferedReader fr = new BufferedReader(new FileReader(targetFile));
        String parseLine;
        int lineCount = 0;
        while (null != (parseLine = fr.readLine())) {
            flights.add(parseLine);// add to arrayList "fileData"
            lineCount++;
        }

        // System.out.println(flights.size());
        if (commandNum == 1) {
            MaxHeap pq = new MaxHeap(lineCount);
            for (int i = 1; i < flights.size() + 1; i++) {
                pq.insert(i);
                pq.maxHeap();
            }
            int[] theArray = pq.arrayHeap();
            // take from heap array
            for (int j = 0; j < theArray.length - 1; j++) {
                System.out.println(getFlightName(flights.get(j)));
            }
        }
        if (commandNum == 2) {
            MaxHeap pq = new MaxHeap(lineCount);
            for (int i = 0; i < flights.size(); i++) {
                pq.insert(getPassengerCount(flights.get(i)));

                // System.out.println(getPassengerCount(flights.get(i)));
                pq.maxHeap();
            }
            int[] theArray = new int[lineCount];
            int num;
            System.out.println(lineCount);
            // pq.print();
            for (int j = 0; j < lineCount; j++) {

                num = pq.extractMax();
                // System.out.println(num);
                theArray[j] = num;
            }
            System.out.print(Arrays.toString(theArray));
            String thisString = "";
            int numC = 0;
            // take from heap array
            for (int j = 0; j < theArray.length; j++) {
                for (int i = 0; i < flights.size(); i++) {
                    int currentCheck = getPassengerCount(flights.get(i));
                    if (currentCheck == theArray[j] && !thisString.contains(getFlightName(flights.get(i)))) {
                        System.out.println(getFlightName(flights.get(i)) + " "
                                + Integer.toString(getPassengerCount(flights.get(i))));
                        thisString = thisString + getFlightName(flights.get(i));
                    }
                }
            }
        }

        if (commandNum == 3) {
            MinHeap rq = new MinHeap(lineCount);
            MaxHeap pq = new MaxHeap(lineCount);
            for (int i = 0; i < flights.size(); i++) {
                pq.insert(getPassengerCount(flights.get(i)));
                rq.insert(convertTime(flights.get(i)));
                rq.minHeap();
                pq.maxHeap();
            }
            int[] tempArray = new int[lineCount];// might both need to be arraylists so we can know size
            int[] temp2Array = new int[lineCount];
        String[] theQueue = new String[lineCount];
           int index=0;//corresponding to arrays above
           int index2=0;
           //need while loop to reinsert ints that have been popped off/////////////////////////////////////////////////////////
            for (int i = 0; i < flights.size(); i++) {// 
                int minTime =rq.extractMin();// size might break ////////////////////////////////////////////////////////////////////////////////
                int flightTime =Integer.parseInt(getFlightTime(flights.get(i)));
                if (minTime == flightTime){// index i holds the flight time
                    if(!runwayFull(flightTime)){// if runway is not full
                        flightTime = takeoffTime(getPassengerCount(flights.get(i)))+flightTime; // calculate new time
                        currentTime=flightTime;// set current time to this time
                        System.out.println("flight"+getFlightName(flights.get(i))+"departed at"+Integer.toString(currentTime));
                    }
                    else{
                        for(int k = 0; k < flights.size(); k++){// need to fix this to reference passengercount X flighttime 
                            int temp3 = pq.extractMax();// size might have to be checked////////////////////////////////////////////////////////////////
                            int temp4 = getPassengerCount(flights.get(k));
                            if((temp3 == temp4)){// at this point, the runway is full, index (i) is the next lowest flight, and index (k) is the passenger count of that flight
                                // if(flightTime>currentTime){///////stuck
                                // }
                            }
                            else{// to be reinserted
                                temp2Array[index2] = temp3;
                                index2++;
                            }
                        }
                    }
               }
               else{// to be reinserted
                   tempArray[index]=minTime;
                   index++;
               }
            /// within while loop but not for loop//////////////////////////////////////////////////////////////////////////////////////////////////////////// 
              //reinsert tempArray into minheap, reinsert temp2Array into max heap  
              //for x = 0 ,x<tempArray.size x++
                // rq.insert(tempArray[x])
              //    rq.minheap
              //for y = 0,y<temp2Array.size y++
              //  pq.insert(temp2Array[y])
              //   pq.maxheap
            }
    }

    }
    public static boolean runwayFull(int time){
        if(currentTime==0){
            return false;
        }
        if(time>= currentTime){
            return true;
        }
        return false;
    }
    public static int takeoffTime(int passengerCount){
        double takeoffTime = passengerCount/2;
        int takeoffTimeCel = (int) Math.ceil(takeoffTime);
        return takeoffTimeCel;
    }
    public static String getFlightName(String s){
        String[] thisArray = new String[4];
        thisArray=s.split(" ");
        return thisArray[0];
    }
    public static String getFlightTime(String s){
        String[] thisArray = new String[4];
        thisArray=s.split(" ");
        return thisArray[3];
    }
    public static int getPassengerCount(String s){
        String[] thisArray = new String[4];
        thisArray=s.split(" ");
        return Integer.valueOf(thisArray[4]);
    }
    public static int convertTime(String s){
        String time= getFlightTime(s);
        String[] thisArray = new String[1];
        thisArray=time.split(":");
        int changeTime = Integer.valueOf(thisArray[1]);
        int hourCheck = Integer.valueOf(thisArray[0]);
        if(hourCheck<12){
         hourCheck=hourCheck+12;
        }
        hourCheck=hourCheck*100;  
        changeTime=hourCheck+changeTime;
        return changeTime;
    }
}

