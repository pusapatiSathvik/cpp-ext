import java.util.*;

public class DistinctIslandsUF {

    private int[] size;
    private int[] parent;
    private int rows, cols;

    // Find with path compression
    public int find(int i) {
        if (parent[i] < 0) return i;
        parent[i] = find(parent[i]);
        return parent[i];
    }

    // Union by size
    public void union(int i, int j) {
        int ri = find(i);
        int rj = find(j);
        if (ri == rj) return;

        if (size[ri] < size[rj]) {
            size[rj] += size[ri];
            parent[ri] = rj;
        } else {
            size[ri] += size[rj];
            parent[rj] = ri;
        }
    }

    // Check if a coordinate is inside grid bounds
    private boolean isValid(int x, int y) {
        return (x >= 0 && y >= 0 && x < rows && y < cols);
    }

    // Main function to count distinct islands
    public int numIslands(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        rows = grid.length;
        cols = grid[0].length;
        size = new int[rows * cols];
        parent = new int[rows * cols];
        Arrays.fill(parent, -1);

        List<int[]> landCells = new ArrayList<>();

        // Traverse grid and connect adjacent land cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != 0) {
                    landCells.add(new int[]{i, j});
                    int idx = i * cols + j;

                    // Union with top and left neighbors (undirected)
                    if (isValid(i - 1, j) && grid[i - 1][j] != 0)
                        union(idx, idx - cols);
                    if (isValid(i, j - 1) && grid[i][j - 1] != 0)
                        union(idx, idx - 1);
                    if (isValid(i + 1, j) && grid[i + 1][j] != 0)
                        union(idx, idx + cols);
                    if (isValid(i, j + 1) && grid[i][j + 1] != 0)
                        union(idx, idx + 1);
                }
            }
        }

        // Group land cells by their parent
        Map<Integer, List<int[]>> components = new HashMap<>();
        for (int[] cell : landCells) {
            int x = cell[0], y = cell[1];
            int root = find(x * cols + y);
            components.computeIfAbsent(root, k -> new ArrayList<>()).add(cell);
        }

        // Normalize shapes and store in HashSet
        Set<String> uniqueShapes = new HashSet<>();

        for (List<int[]> shape : components.values()) {
            // Normalize to relative positions from top-left
            int baseX = shape.get(0)[0], baseY = shape.get(0)[1];
            StringBuilder sb = new StringBuilder();
            for (int[] cell : shape) {
                sb.append((cell[0] - baseX)).append(",").append((cell[1] - baseY)).append(";");
            }
            uniqueShapes.add(sb.toString());
        }

        return uniqueShapes.size();
    }

    // Main method for input/output
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] grid = new int[m][n];

        // Input grid
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

        System.out.println(new DistinctIslandsUF().numIslands(grid));
    }
}
