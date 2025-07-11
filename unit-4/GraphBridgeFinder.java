import java.util.*;

public class GraphBridgeFinder {

    private int time = 0; // Global time counter for discovery time
    private List<List<Integer>> bridges = new ArrayList<>();

    // Main method to find all bridges
    public List<List<Integer>> findBridges(int n, List<List<Integer>> adj) {
        int[] disc = new int[n];  // Discovery time of each node
        int[] low = new int[n];   // Lowest discovery time reachable
        boolean[] visited = new boolean[n];

        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);

        // DFS from every unvisited node
        for (int u = 0; u < n; u++) {
            if (!visited[u]) {
                dfs(u, -1, disc, low, visited, adj);
            }
        }

        return bridges;
    }

    // DFS helper
    private void dfs(int u, int parent, int[] disc, int[] low, boolean[] visited, List<List<Integer>> adj) {
        visited[u] = true;
        disc[u] = low[u] = time++; // Set discovery and low to current time

        for (int v : adj.get(u)) {
            if (v == parent) continue; // Skip the edge to parent

            if (!visited[v]) {
                dfs(v, u, disc, low, visited, adj);

                // Update low time after visiting child
                low[u] = Math.min(low[u], low[v]);

                // Check for bridge
                if (low[v] > disc[u]) {
                    bridges.add(Arrays.asList(u, v));
                }
            } else {
                // Back edge case: update low[u]
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    // Driver Code
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = scanner.nextInt();

        System.out.print("Enter number of edges: ");
        int m = scanner.nextInt();

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        System.out.println("Enter edges (u v):");
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u); // Undirected graph
        }

        GraphBridgeFinder gbf = new GraphBridgeFinder();
        List<List<Integer>> bridges = gbf.findBridges(n, adj);

        System.out.println("Bridges in the graph:");
        for (List<Integer> bridge : bridges) {
            System.out.println(bridge.get(0) + " - " + bridge.get(1));
        }
    }
}
