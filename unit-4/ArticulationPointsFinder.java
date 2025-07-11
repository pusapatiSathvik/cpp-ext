import java.util.*;

public class ArticulationPointsFinder {

    private int time = 0;
    private Set<Integer> articulationPoints = new HashSet<>();

    public Set<Integer> findArticulationPoints(int n, List<List<Integer>> adj) {
        int[] disc = new int[n]; // Discovery time
        int[] low = new int[n];  // Lowest reachable time
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];

        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(parent, -1);

        // Run DFS on each component
        for (int u = 0; u < n; u++) {
            if (!visited[u]) {
                dfs(u, visited, disc, low, parent, adj);
            }
        }

        return articulationPoints;
    }

    private void dfs(int u, boolean[] visited, int[] disc, int[] low, int[] parent, List<List<Integer>> adj) {
        visited[u] = true;
        disc[u] = low[u] = time++;
        int children = 0; // Number of children in DFS tree

        for (int v : adj.get(u)) {
            if (!visited[v]) {
                children++;
                parent[v] = u;
                dfs(v, visited, disc, low, parent, adj);

                // After return from child
                low[u] = Math.min(low[u], low[v]);

                // Rule 1: non-root node
                if (parent[u] != -1 && low[v] >= disc[u]) {
                    articulationPoints.add(u);
                }

                // Rule 2: root node
                if (parent[u] == -1 && children > 1) {
                    articulationPoints.add(u);
                }
            } else if (v != parent[u]) {
                // Back edge
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    // Driver code
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

        ArticulationPointsFinder apf = new ArticulationPointsFinder();
        Set<Integer> result = apf.findArticulationPoints(n, adj);

        System.out.println("Articulation points:");
        for (int point : result) {
            System.out.println(point);
        }
    }
}
