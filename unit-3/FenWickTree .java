import java.util.*;

class FenWickTree {
    int[] nums;  // original array
    int[] BIT;   // Binary Indexed Tree (1-based index)
    int n;       // size of array

    // Constructor to initialize the Fenwick Tree
    public FenWickTree(int[] nums) {
        this.nums = nums;
        n = nums.length;
        BIT = new int[n + 1]; // BIT is 1-indexed
        for (int i = 0; i < n; i++) {
            init(i, nums[i]);  // Build BIT with initial values
        }
    }

    // Initialize or update BIT with value at index i
    public void init(int i, int val) {
        i++; // BIT uses 1-based indexing
        while (i <= n) {
            BIT[i] += val;
            i += (i & -i);  // Go to parent node in BIT
        }
    }

    // Update value at index `i` to new value `val`
    public void update(int i, int val) {
        int diff = val - nums[i];  // Find the difference
        nums[i] = val;             // Update original array
        init(i, diff);             // Update BIT with the diff
    }

    // Get prefix sum from 0 to i
    public int getSum(int i) {
        int sum = 0;
        i++; // Convert to 1-based index
        while (i > 0) {
            sum += BIT[i];
            i -= (i & -i);  // Move to parent
        }
        return sum;
    }

    // Get range sum from i to j
    public int sumRange(int i, int j) {
        return getSum(j) - getSum(i - 1);
    }

    // Driver code to test the Fenwick Tree
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt(); // size of array
        int q = scan.nextInt(); // number of queries

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scan.nextInt();
        }

        FenWickTree ft = new FenWickTree(nums);

        while (q-- > 0) {
            int opt = scan.nextInt();
            if (opt == 1) {
                // Query: Sum from s1 to s2
                int s1 = scan.nextInt();
                int s2 = scan.nextInt();
                System.out.println(ft.sumRange(s1, s2));
            } else {
                // Update: set value at index to val
                int ind = scan.nextInt();
                int val = scan.nextInt();
                ft.update(ind, val);
            }
        }

        scan.close();
    }
}
/* 

✅ Sample Input 
8 5
1 2 13 4 25 16 17 8
1 2 6
output->75
1 0 7
output->86
2 2 18
2 4 17
1 2 7
output->80

*/




