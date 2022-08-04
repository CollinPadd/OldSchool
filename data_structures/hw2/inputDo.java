import java.io.*;
import java.util.ArrayList;
// import java.util.Arrays;
public class inputDo
{
    private static int[][] graph; 
    private static String[] vertexNames; 
    private static int numVertices=0;
    public static void main(String[] args) throws Exception
    { 

                  k=0
            for(String argument: args)
            {
            System.out.println(argument);
            
            }
      
        
    }
    int[] dijkstra(int matrix[][], int start) { 
        // array stores a shortest path of graph from vertex start
        int shortestPath[] = new int[numVertices]; 
  
        // array keeps track which vertex is put in shortest path
        Boolean spArray[] = new Boolean[numVertices]; 
  
        // Initialize all distances as INFINITE for shortestPath
        // and spArray[] as false since no one is put yet
        for (int i = 0; i < numVertices; i++) { 
            shortestPath[i] = Integer.MAX_VALUE; 
            spArray[i] = false; 
        } 
  
        // Set distance of start vertex from itself is always 0
        shortestPath[start] = 0; 
        // Certainly, shortestPath will include all vertices of graph in this case.
        // So we take a loop with graph numVertices to find shortest path.
        for (int i = 0; i < numVertices - 1; i++) { 
             
            // Find the minimum distance vertex in shortest path
            // Apparently, start vertex is chosen firstly
            int u = getMinDist(spArray, shortestPath); 
            // Then mark the start vertex as processed 
            spArray[u] = true; 
            // Update shortestPath value of the adjacent vertices of the 
            // picked vertex. 
            // Keep finding the minimum distance vertex from the adjacent vertices
            for (int v = 0; v < numVertices; v++) {
                // Just update shortestPath[v] in case: 
                if (!spArray[v] && matrix[u][v] != 0 //it is not in spArray, u and v are adjacent
                        && shortestPath[u] != Integer.MAX_VALUE// a distance is not infinite
                        //total weight of path from start vertex to v through u is smaller than current value of shortestPath[v]
                        && shortestPath[u] + matrix[u][v] < shortestPath[v]
                        ) {
                        shortestPath[v] = shortestPath[u] + matrix[u][v]; 
                    }
            }
        }             
        return shortestPath;
    }         
    if (goalInput.contains("lt")) {
        String lessThan="";
        for(int i=2;i<goalInput.length();i++){
            lessThan=lessThan+lessThan.charAt(i);
        }
        int lessThanInt=Integer.valueOf(lessThan);
        for(int i=0;i<numVertices;i++){
            if(sp[i]<lessThanInt){
                System.out.print(sp[getVertexID(sourcePerson)]);
            }
        }

    }
} 
}