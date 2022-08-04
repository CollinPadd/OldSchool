import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.InputStreamReader;
public class FindUsers{
    private static int[][] graph; 
    private static String[] vertexNames; 
    private static int numVertices=0;
    public static void main(String[] args) throws Exception { 
        ArrayList<String> userCommand = new ArrayList<String>();
        for(String command: args) {// iterate over arguments
            userCommand.add(command);
        }
        String targetFile = userCommand.get(0);// example: users.txt
        String sourcePerson = userCommand.get(1);// the "from"
        String BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        String goalInput = userCommand.get(2);// user command or person; "ltx" "gtx" "eqx" where x is input digit
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));     
        File file = new File(targetFile);
        BufferedReader fr = new BufferedReader(new FileReader(file));
        ArrayList<String> fileData = new ArrayList<String>();
        String[] thisLine;
        String parseLine; 
        int lineCount=0;
        while(null !=(parseLine=fr.readLine())){
            thisLine = parseLine.split(" ");// new input at this point
            for(String thing : thisLine){
                    fileData.add(thing);// add to arrayList "fileData"
                } 
            lineCount++;
        }
        vertexNames=getNames(fileData, lineCount);//STRING ARRAY OF NAMES
        numVertices=vertexNames.length;
        createGraph();
        populateGraph(fileData);
        printGraph(graph,vertexNames);
        int[] sp = dijkstra(graph, getVertexID(sourcePerson));
        // System.out.println("Path " + Arrays.toString(sp));
        for (int i = 0; i < vertexNames.length; i++) {//For finding two persons, this will print
            if (goalInput.equalsIgnoreCase(vertexNames[i])) {
                System.out.println("Path " + sourcePerson + " " + goalInput + " " + sp[getVertexID(goalInput)]);
                System.exit(0);
            }
        }
        if (goalInput.contains("lt")) {//parse less than command 
            String lessThan="";
            for(int i=2;i<goalInput.length();i++){
                lessThan=lessThan+goalInput.charAt(i);
            }
            int lessThanInt=Integer.valueOf(lessThan);
            for(int i=0;i<numVertices;i++){
                if(sp[i]<lessThanInt){
                    System.out.print(vertexNames[i]+" ");
                }
            }
        }
        if (goalInput.contains("gt")) {//parse greater than command
            String lessThan="";
            for(int i=2;i<goalInput.length();i++){
                lessThan=lessThan+goalInput.charAt(i);
            }
            int lessThanInt=Integer.valueOf(lessThan);
            for(int i=0;i<numVertices;i++){
                if(sp[i]>lessThanInt){
                    System.out.print(vertexNames[i]+" ");
                }
            }
        }
        if (goalInput.contains("eq")) {// parse equal to command
            String lessThan="";
            for(int i=2;i<goalInput.length();i++){
                lessThan=lessThan+goalInput.charAt(i);
            }
            int lessThanInt=Integer.valueOf(lessThan);
            for(int i=0;i<numVertices;i++){
                if(sp[i]==lessThanInt){
                    System.out.print(vertexNames[i]+" ");
                }
            }
        }
    }
    private static void createGraph() {
        // the graph 2D array,numVertices × numVertices, and all entries (zero).
        graph = new int[numVertices][numVertices];
    }
    private static void populateGraph(ArrayList<String> arr) {// 
        String vertex1 = "";
        String vertex2 = "";
        int edgeLength = 0;
        for (int i = 0; i < arr.size(); i++)
            if (arr.get(i) != null) {
                vertex1 = arr.get(i);
                vertex2 = arr.get(i + 1);
                edgeLength = Integer.valueOf(arr.get(i + 2));// will take arrayList with ex < fred, susan, 12, turing ,cobol 12> and take the first 3 elements, then the next 3 at 
                i++;//skip name
                i++;//skip number
                addEdge(vertex1, vertex2, edgeLength);
            }
    }
    private static int getVertexID(String vertexName) {
        // receives as input a String, and checks for string in array vertexNames.
        for (int i = 0; i < numVertices; i++) {
            if (vertexNames[i].equalsIgnoreCase(vertexName)) {
                return i;
            }
        }
        return -1;// if value is not in array
    }
    public static void printGraph(int[][] arr, String[] names) {
        // i made this iterate over the graph class 2D array, and print the values
        // within the array.
        for (int i = 0; i < arr.length; i++) {
            System.out.print(names[i] + "\t");
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print("|" + " " + arr[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
    }
    private static void addEdge(String vertex1, String vertex2, int edgeLength) {
        if ((getVertexID(vertex1) != -1) || ((getVertexID(vertex2) != -1))) {// or statement
            graph[getVertexID(vertex1)][getVertexID(vertex2)] = edgeLength;// one
            // System.out.println("Added "+ vertex1+"-"+vertex2);
        } else {// error message if for some reason something went wrong
            System.out.println(
                    "Cannot add edge " + vertex1 + "-" + vertex2 + " because at least one of these do not exist.");// exact wording
        }
    }
    public static String[] getNames(ArrayList<String> fileData, int lineCount) throws Exception {// create an array of the names of users, the indexing is very important
        int number = (fileData.size() * 2) / 3;//need to only grab two-thirds of the data
        ArrayList<String> noRepeatList = new ArrayList<String>();
        String[] nameList = new String[number];
        int k = 0;
        for (int j = 0; j < fileData.size(); j++) {
            char temp = fileData.get(j).charAt(0);
            if (Character.isLetter(temp)) {
                nameList[k] = fileData.get(j);
                k++;
            } 
            else {
                continue;
            }
        }
        k = 0;
        for (int h = 0; h < nameList.length; h++) {
            boolean repeat = false;
            for (int g = h + 1; g < nameList.length; g++) {
                if (nameList[h].equals(nameList[g])) {
                    repeat = true;
                }
            }
            if (repeat == false) {
                noRepeatList.add(nameList[h]);
                k++;
            }
        }
        String[] noRepeatArray = new String[noRepeatList.size()];
        for (int i = 0; i < noRepeatList.size(); i++) {
            noRepeatArray[i] = noRepeatList.get(i);
        }
        return noRepeatArray;
        // Printing the content of words
    }
    static int[] dijkstra(int myGraph[][], int sourcePerson) {// dijkstra program made from
        int distanceIndex[]= new int[numVertices]; 
        Boolean indexIsShortest[]= new Boolean[numVertices]; 
        for (int i=0; i<numVertices; i++) { 
            indexIsShortest[i]=false; 
            distanceIndex[i]=Integer.MAX_VALUE; // as infinite as i can get
        } 
        distanceIndex[sourcePerson]=0; 
        for (int i=0; i<numVertices; i++) { 
            int s=getMinDist(indexIsShortest, distanceIndex);
            indexIsShortest[s]=true;
            for (int index=0; index<numVertices;index++) {
                if ((distanceIndex[s]!=Integer.MAX_VALUE) && (!indexIsShortest[index]) && (myGraph[s][index]!=0) && (myGraph[s][index] + distanceIndex[s] <distanceIndex[index])){
                    distanceIndex[index] =  myGraph[s][index]+distanceIndex[s];
                }
                
            }
        }
        return distanceIndex;
    }
    static int getMinDist(Boolean[] truthArray,int[] shortPaths) {
        int minIndex=Integer.MAX_VALUE;// max value
        int index=0;// will remain 0 if same person
        for (int i=0;i<numVertices;i++) {
            if(minIndex>shortPaths[i]&&truthArray[i]==false){
                minIndex=shortPaths[i];
                index=i;
            }
        }
        return index;
    }   
}