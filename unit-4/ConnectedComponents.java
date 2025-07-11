import java.util.*;

public class ConnectedComponents {

    int V; // Number of vertices
    ArrayList<ArrayList<Integer>> adjListArray;

    // Constructor
    ConnectedComponents(int V) {
        this.V = V;
        adjListArray = new ArrayList<>();

        // Initialize adjacency list for each vertex
        for (int i = 0; i < V; i++) {
            adjListArray.add(new ArrayList<>());
        }
    }

    // Function to add undirected edge
    void addEdge(int src, int dest) {
        adjListArray.get(src).add(dest);
        adjListArray.get(dest).add(src);
    }

    // DFS utility function to explore component
    void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int neighbor : adjListArray.get(v)) {
            if (!visited[neighbor]) {
                DFSUtil(neighbor, visited);
            }
        }
    }

    // Function to find and print all connected components
    void connectedComponents() {
        boolean[] visited = new boolean[V];

        for (int v = 0; v < V; v++) {
            if (!visited[v]) {
                // Start DFS from unvisited vertex
                DFSUtil(v, visited);
                System.out.println(); // Newline for next component
            }
        }
    }

    // Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of vertices and edges
        System.out.println("Enter number of vertices:");
        int V = sc.nextInt();
        System.out.println("Enter number of edges:");
        int E = sc.nextInt();

        // Create graph
        ConnectedComponents g = new ConnectedComponents(V);

        // Input edges
        System.out.println("Enter " + E + " edges (as pairs of vertex indices):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            g.addEdge(u, v);
        }

        // Output connected components
        System.out.println("Following are connected components:");
        g.connectedComponents();
    }
}
    
/*

Enter number of vertices:
5
Enter number of edges:
3
Enter 3 edges (as pairs of vertex indices):
1 0
2 1
3 4

Following are connected components:
0 1 2 
3 4

 */



