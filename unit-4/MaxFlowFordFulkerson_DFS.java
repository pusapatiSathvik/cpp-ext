import java.util.*;

public class MaxFlowFordFulkerson_DFS {

    static int V; // Number of vertices in the graph

    // Function to perform DFS on the residual graph and find an augmenting path
    // Returns true if there is a path from source (s) to sink (t)
    boolean dfs(int[][] rGraph, int s, int t, int[] parent) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        stack.push(s);
        visited[s] = true;
        parent[s] = -1;

        while (!stack.isEmpty()) {
            int u = stack.pop();

            for (int v = 0; v < V; v++) {
                // If the edge has capacity and the vertex is not visited
                if (!visited[v] && rGraph[u][v] > 0) {
                    parent[v] = u;
                    visited[v] = true;
                    stack.push(v);

                    // Stop search if we reach the sink
                    if (v == t)
                        return true;
                }
            }
        }
        return false; // No augmenting path found
    }

    // Function to implement Ford-Fulkerson algorithm and return maximum flow
    int fordFulkerson(int[][] graph, int s, int t) {
        int u, v;

        // Step 1: Create a residual graph
        int[][] rGraph = new int[V][V];
        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        // This array is filled by DFS to store path
        int[] parent = new int[V];
        int maxFlow = 0;

        // Step 2: Augment the flow while there is a path from source to sink
        while (dfs(rGraph, s, t, parent)) {

            // Step 3: Find the minimum residual capacity of the path
            int pathFlow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }

            // Step 4: Update residual capacities of the edges and reverse edges
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= pathFlow;
                rGraph[v][u] += pathFlow;
            }

            // Step 5: Add path flow to overall flow
            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    // Driver method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of vertices: ");
        V = sc.nextInt();

        int[][] graph = new int[V][V];

        System.out.println("Enter the adjacency matrix (capacity of edges):");
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                graph[i][j] = sc.nextInt();

        System.out.println("Enter source vertex (0-based index): ");
        int source = sc.nextInt();

        System.out.println("Enter sink vertex (0-based index): ");
        int sink = sc.nextInt();

        MaxFlowFordFulkerson_DFS ff = new MaxFlowFordFulkerson_DFS();
        int maxFlow = ff.fordFulkerson(graph, source, sink);

        System.out.println("The maximum possible flow is: " + maxFlow);
    }
}




Enter number of vertices: 
6
Enter the adjacency matrix (capacity of edges):
0 16 13 0 0 0
0 0 10 12 0 0
0 4 0 0 14 0
0 0 9 0 0 20
0 0 0 7 0 4
0 0 0 0 0 0
Enter source vertex (0-based index): 
0
Enter sink vertex (0-based index): 
5
  
✅ Output:

The maximum possible flow is: 23
