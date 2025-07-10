import java.util.*;

public class KthSmallestSubarraySum {

    // Function to find the k-th smallest subarray sum
    public int kthSmallestSubarraySum(int[] nums, int k) {
        // Set binary search boundaries
        int left = Integer.MAX_VALUE, right = 0;

        for (int num : nums) {
            left = Math.min(left, num);
            right += num;
        }

        // Binary search over possible subarray sums
        while (left < right) {
            int mid = left + (right - left) / 2;

            // Count subarrays with sum ≤ mid
            if (countSubarraysWithSumAtMost(nums, mid) >= k) {
                right = mid;  // Try smaller sum
            } else {
                left = mid + 1;  // Need more subarrays
            }
        }

        return left; // kth smallest subarray sum
    }

    // Helper method to count subarrays with sum ≤ s using sliding window
    private int countSubarraysWithSumAtMost(int[] nums, int s) {
        int start = 0, sum = 0, count = 0;

        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];

            // Shrink the window until sum ≤ s
            while (sum > s) {
                sum -= nums[start++];
            }

            // Add subarrays ending at `end`
            count += end - start + 1;
        }

        return count;
    }

    // Driver code
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: array size and elements
        System.out.println("Enter array elements size:");
        int n = sc.nextInt();
        int[] array = new int[n];

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }

        // Input: value of k
        System.out.println("Enter the k-th smallest subarray sum to find:");
        int k = sc.nextInt();

        // Output result
        KthSmallestSubarraySum obj = new KthSmallestSubarraySum();
        int result = obj.kthSmallestSubarraySum(array, k);
        System.out.println("The " + k + "-th smallest subarray sum is: " + result);
    }
}
/*

Enter array elements size:
3
Enter the elements:
2 1 3
Enter the k-th smallest subarray sum to find:
4

✅ Output:
The 4-th smallest subarray sum is: 3

All subarray sums:

[2], [1], [3], [2,1], [1,3], [2,1,3]
→ Sums: 2, 1, 3, 3, 4, 6
Sorted sums: 1, 2, 3, 3, 4, 6

The 4th smallest sum is 3 ✅

*/


