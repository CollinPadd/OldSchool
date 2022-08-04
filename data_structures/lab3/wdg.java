// Collin Paddock
//Lab 3


import java.util.LinkedList; 
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
@SuppressWarnings("unchecked")
public class WeightedDirGraph{
    public static void main(String[] args) throws IOException {
        int vertex;
        int to;
        String from = " ";
        int cost;
        BufferedReader doThis = new BufferedReader(new InputStreamReader(System.in));// buffered reader instance
        while (true) {// while loop
            System.out.print("How many Vertices: ");
            vertex = Integer.valueOf(doThis.readLine());// vertex input by user
            Graph theGraph= new Graph(vertex);
            boolean loop = true;
            while (loop) {
                System.out.print("from vertex ");
                from = doThis.readLine();
                if(from.equalsIgnoreCase("end")){
                    loop=false;
                }
                else{
                System.out.print("to vertex ");
                to = Integer.valueOf(doThis.readLine());
                System.out.print("But at what cost? ");
                cost = Integer.valueOf(doThis.readLine());
                theGraph.addEdge(to, Integer.parseInt(from), cost);
            }
            }
            theGraph.printGraph();

        }
    }


    // In this program, the values/IDs of the vertices (0, 1, 2, and 3 in Figure 1)
    // correspond to the index values of the array in the Graph that contain the
    // LinkedList objects.
    static class Edge {
        int from;
        int to;
        int cost;
        public Edge(int from, int to, int cost) {
            this.to = to;
            this.from = from;
            this.cost = cost;
        }
    }
    static class Graph {
        int vertices;
        LinkedList<Edge>[] adjList; 
        public Graph(int numVertices) {
            vertices = numVertices;
            adjList = new LinkedList[vertices];
            
            for (int i = 0; i <vertices ; i++) {

                adjList[i] = new LinkedList<>();

            }
        }
        public void addEdge(int to, int from, int cost) {//The constructor for Edge should have three parameters, which are from, to, and cost.
            Edge newEdge = new Edge(to, from, cost);
            
    
            adjList[from].addFirst(newEdge);
        }
        public void printGraph() {
            for (int i = 0; i < vertices; i++) {
                    LinkedList<Edge> graphThis = adjList[i];
                    for (int j = 0; j < graphThis.size(); j++){
                            System.out.println("The Vertex " + graphThis.get(j).from + " is attached to " + graphThis.get(j).to + " with weighted cost " + graphThis.get(j).cost);
                    }
            }
        }
    }
}

    
