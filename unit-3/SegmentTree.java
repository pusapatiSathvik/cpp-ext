import java.util.*;

public class SegmentTree {
    private int[] tree; // Segment tree array
    private int[] nums; // Original input array
    private int n;      // Size of the original array

    // Constructor to initialize the Segment Tree
    public SegmentTree(int[] nums) {
        this.nums = nums;
        this.n = nums.length;

        // Calculate size of segment tree array: 2 * 2^ceil(log2(n)) - 1
        int height = (int) Math.ceil(Math.log(n) / Math.log(2));
        int maxSize = 2 * (int) Math.pow(2, height) - 1;

        tree = new int[maxSize];

        buildTree(0, 0, n - 1); // Start building from root
    }

    // Build the segment tree recursively
    private void buildTree(int treeIndex, int left, int right) {
        if (left == right) {
            tree[treeIndex] = nums[left]; // Leaf node stores array element
            return;
        }

        int mid = left + (right - left) / 2;

        // Build left and right subtrees
        buildTree(2 * treeIndex + 1, left, mid);
        buildTree(2 * treeIndex + 2, mid + 1, right);

        // Merge results into current node
        tree[treeIndex] = tree[2 * treeIndex + 1] + tree[2 * treeIndex + 2];
    }

    // Query the sum in range [queryLeft, queryRight]
    public int queryRange(int queryLeft, int queryRight) {
        return queryRange(0, 0, n - 1, queryLeft, queryRight);
    }

    private int queryRange(int treeIndex, int left, int right, int queryLeft, int queryRight) {
        // Case 1: No overlap
        if (right < queryLeft || left > queryRight) return 0;

        // Case 2: Total overlap
        if (left >= queryLeft && right <= queryRight) return tree[treeIndex];

        // Case 3: Partial overlap — split and combine
        int mid = left + (right - left) / 2;
        int leftSum = queryRange(2 * treeIndex + 1, left, mid, queryLeft, queryRight);
        int rightSum = queryRange(2 * treeIndex + 2, mid + 1, right, queryLeft, queryRight);

        return leftSum + rightSum;
    }

    // Update the value at a specific index
    public void update(int index, int newValue) {
        int diff = newValue - nums[index]; // Calculate the difference
        nums[index] = newValue;            // Update the original array
        updateTree(0, 0, n - 1, index, diff);
    }

    private void updateTree(int treeIndex, int left, int right, int index, int diff) {
        // If the index is outside the range, return
        if (index < left || index > right) return;

        // Update the current node
        tree[treeIndex] += diff;

        // If not a leaf node, continue updating children
        if (left != right) {
            int mid = left + (right - left) / 2;
            updateTree(2 * treeIndex + 1, left, mid, index, diff);     // Left child
            updateTree(2 * treeIndex + 2, mid + 1, right, index, diff); // Right child
        }
    }

    // Optional: Get the total sum of the entire array
    public int getTotalSum() {
        return tree[0]; // Root node contains total sum
    }

    // Main method to test functionality
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Input: array size and number of queries
        int n = scan.nextInt();
        int q = scan.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scan.nextInt();
        }

        SegmentTree st = new SegmentTree(nums);

        // Handle queries
        while (q-- > 0) {
            int opt = scan.nextInt();

            if (opt == 1) {
                // Query sum in range [l, r]
                int l = scan.nextInt();
                int r = scan.nextInt();
                System.out.println(st.queryRange(l, r));
            } else {
                // Update index with new value
                int index = scan.nextInt();
                int value = scan.nextInt();
                st.update(index, value);
            }
        }

        scan.close();
    }
}
/*
 
input :
8 5
4 2 13 4 25 16 17 8
1 2 6
1 0 7
2 2 18
2 4 17
1 2 7


output:
75
89
80

*/