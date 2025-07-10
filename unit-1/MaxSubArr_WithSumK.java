import java.util.*;

public class MaxSubArr_WithSumK {

    // Function to find max sum of subarray of size k
    public int maxSum(int[] a, int k) {
        int n = a.length;
        if (k > n) return -1;  // Edge case: if k > n, return invalid

        // Calculate sum of first window of size k
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += a[i];
        }

        int answer = sum;  // Initialize answer with sum of first window

        // Slide the window
        for (int i = k; i < n; i++) {
            sum = sum + a[i] - a[i - k]; // Add new, remove old
            answer = Math.max(answer, sum);
        }

        return answer;
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: size of array
        int n = sc.nextInt();
        int[] a = new int[n];

        // Input: elements of array
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        // Input: window size k
        int k = sc.nextInt();

        MaxSubArr_WithSumK obj = new MaxSubArr_WithSumK();
        int result = obj.maxSum(a, k);

        // Output the result
        System.out.println(result);
    }
}
