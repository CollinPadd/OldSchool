// Collin Paddock
//Lab 3
import java.util.LinkedList; 
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
@SuppressWarnings("unchecked")
public class WeightedDirGraph{
    public static void main(String[] args) throws IOException {// A main() function, that prompts the user to input the count of vertices in the graph, followed by the from (vertex), to (vertex), and cost combinations. Once a user is done providing the graph’s details, details of the graph are printed to the screen, via invoking the function printGraph.
        int vertex;
        String from = " ";
        int cost;
        int to;
        BufferedReader doThis = new BufferedReader(new InputStreamReader(System.in));// buffered reader instance
        while (true) {// while loop
            System.out.print("How many Vertices: ");
            vertex = Integer.valueOf(doThis.readLine());// vertex input by user
            Graph theGraph= new Graph(vertex);//instantiate graph
            boolean doALoop = true;
            while (doALoop) {
                System.out.print("from vertex ");
                from = doThis.readLine();
                if(from.equalsIgnoreCase("end")){//break Case
                    doALoop=false;
                }
                else{
                    System.out.print("to vertex ");
                    to = Integer.valueOf(doThis.readLine());
                    System.out.print("But at what cost? ");
                    cost = Integer.valueOf(doThis.readLine());
                    theGraph.addEdge(to, Integer.parseInt(from), cost);// add this edge
                }
            }
            theGraph.printGraph();
            return;
        }
    }
    static class Edge {// An Edge class*, whose properties are from, to, and cost. Whenever a new edge is created, it is constructed to contain that information. 
        int from;
        int to;
        int cost;
        public Edge(int from, int to, int cost) {
            this.to = to;
            this.from = from;
            this.cost = cost;
        }
    }
    static class Graph {// whose properties are vertices, and edges in the form of a LinkedList of Edge objects. The Graph class also has addEdge and printGraph functions. 
        int vertices;
        LinkedList<Edge>[] adjList; 
        public Graph(int numVertices) {//2 instance variables, 3 vertices of type int, which is the count of vertices, and adjList, which is the array of LinkedLists, composed of Edge objects. 
            vertices = numVertices;// assignment
            adjList = new LinkedList[vertices];
            for (int i = 0; i <vertices ; i++) {
                adjList[i] = new LinkedList<>(); // gotta loop this to populate
            }
        }
        public void addEdge(int to, int from, int cost) {//The constructor for Edge should have three parameters, which are from, to, and cost.
            Edge newEdge = new Edge(to, from, cost);
            adjList[from].addFirst(newEdge);
        }
        public void printGraph() {// iterate over the adjList, and print all edge IDs, as well as the costs of each edge
            for (int i = 0; i < vertices; i++) {// for each vertex
                    LinkedList<Edge> graphThis = adjList[i];//this graph 
                    for (int j = 0; j < graphThis.size(); j++){// size must be used from the graph
                            System.out.println("The Vertex " + graphThis.get(j).to + " is attached to " + graphThis.get(j).from + " with weighted cost " + graphThis.get(j).cost);
                    }
            }
        }
    }
}