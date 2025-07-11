import java.util.*;

public class SegmentTreeMax {
    private int[] tree; // Segment Tree array
    private int[] nums; // Original input array
    private int n;      // Size of original array

    // Constructor to initialize and build the segment tree
    public SegmentTreeMax(int[] nums) {
        this.nums = nums;
        this.n = nums.length;

        // Height of the tree = ceil(log2(n))
        int height = (int) Math.ceil(Math.log(n) / Math.log(2));
        // Maximum size of segment tree = 2 * 2^height - 1
        int maxSize = 2 * (int) Math.pow(2, height) - 1;

        tree = new int[maxSize];

        buildTree(0, 0, n - 1); // Build from root
    }

    // Recursive function to build segment tree
    private void buildTree(int treeIndex, int left, int right) {
        if (left == right) {
            tree[treeIndex] = nums[left]; // Leaf node
            return;
        }

        int mid = left + (right - left) / 2;

        // Build left and right subtrees
        buildTree(2 * treeIndex + 1, left, mid);
        buildTree(2 * treeIndex + 2, mid + 1, right);

        // Internal node stores max of left and right children
        tree[treeIndex] = Math.max(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
    }

    // Public method to query max in range [l, r]
    public int queryRangeMax(int l, int r) {
        return queryRangeMax(0, 0, n - 1, l, r);
    }

    // Recursive query function
    private int queryRangeMax(int treeIndex, int left, int right, int l, int r) {
        // No overlap
        if (right < l || left > r) return Integer.MIN_VALUE;

        // Total overlap
        if (left >= l && right <= r) return tree[treeIndex];

        // Partial overlap
        int mid = left + (right - left) / 2;
        int leftMax = queryRangeMax(2 * treeIndex + 1, left, mid, l, r);
        int rightMax = queryRangeMax(2 * treeIndex + 2, mid + 1, right, l, r);

        return Math.max(leftMax, rightMax);
    }

    // Public method to update a single element
    public void update(int index, int newValue) {
        nums[index] = newValue; // Update original array
        updateTree(0, 0, n - 1, index, newValue);
    }

    // Recursive update function
    private void updateTree(int treeIndex, int left, int right, int index, int newValue) {
        // Index is outside range
        if (index < left || index > right) return;

        // Leaf node
        if (left == right) {
            tree[treeIndex] = newValue;
            return;
        }

        int mid = left + (right - left) / 2;

        // Update left or right subtree
        updateTree(2 * treeIndex + 1, left, mid, index, newValue);
        updateTree(2 * treeIndex + 2, mid + 1, right, index, newValue);

        // Update current node with max of children
        tree[treeIndex] = Math.max(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
    }

    // Get max of the entire array
    public int getMax() {
        return tree[0]; // Root node has global max
    }

    // Driver code for testing
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9, 11};

        SegmentTreeMax st = new SegmentTreeMax(nums);

        // Query max in range [1, 4]
        System.out.println("Maximum value in range [1, 4]: " + st.queryRangeMax(1, 4)); // 9

        // Update index 2 to value 10
        st.update(2, 10);

        // Query again after update
        System.out.println("Maximum value in range [1, 4] after update: " + st.queryRangeMax(1, 4)); // 10

        // Global max of the entire array
        System.out.println("Maximum value of the array: " + st.getMax()); // 11
    }
}
