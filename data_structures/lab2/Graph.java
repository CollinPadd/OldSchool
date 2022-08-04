// Collin Paddock
// CSCI 241 WINTER
// Lab 2
//Good coding practices

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Graph{// main variable made by me on 2/01/2020
    private static int[][] graph; 
    private static String[] vertexNames; 
    private static final int numVertices = 5;
    //These class variables are graph objects 
    public static void main(String args[]) throws IOException{// exactly as specified
        createGraph();
        createGraphVertexNames();
        printVertexNames(); 
        promptEdgeInput(); 
        printGraph();
        }
    private static void createGraph(){
        //the graph 2D array,numVertices × numVertices, and all entries (zero).
       graph = new int[numVertices][numVertices];
       

    }
    
    private static void printVertexNames(){
    //iterates over the contents of vertexNames,showing the string values of the array.
        for (int i = 0; i < numVertices;i++){
            System.out.println(vertexNames[i]);
        }
    }
    private static void createGraphVertexNames(){
        //populate array vertexNames with the Strings apple, grape, banana, orange, and pineapple.
        vertexNames = new String[numVertices];
        vertexNames[0]="apple";
        vertexNames[1]="grape";
        vertexNames[2]="banana";
        vertexNames[3]="orange";
        vertexNames[4]="pineapple";
        

    }//
    private static int getVertexID(String vertexName){
        //receives as input a String, and checks for string  in array vertexNames.
        for (int i=0;i < numVertices;i++){
            if(vertexNames[i].equalsIgnoreCase(vertexName)){
                return i;

            }
        }
        return -1;// if value is not in array


    }
    private static void printGraph(){
        //i made this iterate over the graph class 2D array, and print the values within the array.
        for (int i=0;i< numVertices;i++){
            if (vertexNames[i]=="apple"){// to have "nice looking"
                System.out.print(vertexNames[i] + "     | ");// spacing is specific to each index
            }
            if (vertexNames[i]=="grape"){
                System.out.print(vertexNames[i] + "     | ");// will look into \t for lab 3 
                }
            if (vertexNames[i]=="banana"){
                System.out.print(vertexNames[i] + "    | ");
                }
            if (vertexNames[i]=="orange"){
                    System.out.print(vertexNames[i] + "    | ");
                }
            if (vertexNames[i]=="pineapple"){
                        System.out.print(vertexNames[i] + " | ");
                }
            for (int j=0; j< numVertices;j++){// print values
                System.out.print(graph[i][j]+" | ");
            }
            System.out.print("\n");// new line
        }

    }
    private static void addEdge(String vertex1, String vertex2){
        //retrieve the index values,  i used getVertexID, paramters vertex1 and vertex2, so when both getVertexID returns someting other than -1, the graph class variable becomes 1 at the proper row/column positions, and the column/row positions.
        
        if ((getVertexID(vertex1)!=-1)||((getVertexID(vertex2)!=-1))){// or statement
            graph[getVertexID(vertex1)][getVertexID(vertex2)]=1;// one 
            graph[getVertexID(vertex2)][getVertexID(vertex1)]=1;//two
            System.out.println("Added "+ vertex1+"-"+vertex2);
        }
        else{//error message if for some reason something went wrong
            System.out.println("Cannot add edge "+ vertex1 +"-"+vertex2+" because at least one of these do not exist.");// exact wording
        }
    }
    private static void promptEdgeInput() throws IOException{
        // If a user inputs done, the loop should terminate. This function should invoke addEdge using as arguments the two command line vertex names (the end points of the edge) supplied by the user. See Figure 1 for an example.
        String vertex1=" ";
        String vertex2=" ";
        BufferedReader doThis = new BufferedReader(new InputStreamReader(System.in));//buffered reader instance
       while (true){// while loop 
        System.out.print("Enter First Vertex: ");
        vertex1 =doThis.readLine();// first vertex input by user
        System.out.print("Enter Second Vertex: ");
        vertex2 = doThis.readLine();
        if ((vertex1.equalsIgnoreCase("done"))||(vertex2.equalsIgnoreCase("done"))){
            return;
        }
        addEdge(vertex1, vertex2);// starts next function
        }
    }





}