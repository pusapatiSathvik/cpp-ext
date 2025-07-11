import java.util.*;

public class ConnectedComponents {

    int[] parent;
    int[] size;

    // Main function to count connected components
    public int countComponents(int n, int[][] edges) {
        parent = new int[n];
        size = new int[n];

        // Initialize DSU: Each node is its own parent initially
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
            size[i] = 1;
        }

        int components = n;

        // Process each edge
        for (int[] edge : edges) {
            int p1 = find(edge[0]);
            int p2 = find(edge[1]);

            if (p1 != p2) {
                // Union by size
                if (size[p1] < size[p2]) {
                    size[p2] += size[p1];
                    parent[p1] = p2;
                } else {
                    size[p1] += size[p2];
                    parent[p2] = p1;
                }
                components--; // One component merged
            }
        }

        return components;
    }

    // Find with path compression
    private int find(int i) {
        if (parent[i] < 0) return i;
        parent[i] = find(parent[i]);
        return parent[i];
    }

    // Main method for input/output
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of nodes and edges
        int n = sc.nextInt();
        int e = sc.nextInt();
        int[][] edges = new int[e][2];

        // Input edges
        for (int i = 0; i < e; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
        }

        // Output result
        System.out.println(new ConnectedComponents().countComponents(n, edges));
    }
}
