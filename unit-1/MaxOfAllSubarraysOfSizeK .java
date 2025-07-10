import java.util.*;

public class MaxOfAllSubarraysOfSizeK {

    // Function to find max of all subarrays of size k
    private static int[] maxofAllSubarray(int[] a, int k) {
        int n = a.length;
        int[] maxOfSubarrays = new int[n - k + 1];
        int idx = 0;

        // Max Heap using reverse order
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < n; windowEnd++) {
            q.add(a[windowEnd]);

            // If window size equals k
            if (windowEnd - windowStart + 1 == k) {
                int maxElement = q.peek();
                maxOfSubarrays[idx++] = maxElement;

                // Remove the element going out of the window only if it equals max
                if (a[windowStart] == maxElement) {
                    q.remove(); // Removes only one occurrence
                }

                windowStart++; // Slide the window
            }
        }

        return maxOfSubarrays;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input size
        System.out.println("Enter array elements size:");
        int n = sc.nextInt();
        int[] a = new int[n];

        // Input elements
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        // Input subarray size
        System.out.println("Enter the subarray size:");
        int k = sc.nextInt();
        sc.close();

        // Compute result
        int[] result = maxofAllSubarray(a, k);

        // Print result
        System.out.println("Max of each subarray:");
        for (int val : result) {
            System.out.print(val + " ");
        }
    }
}

/*
🧪 Sample Input:
        Enter array elements size:
        9
        Enter the elements:
        1 2 3 1 4 5 2 3 6
        Enter the subarray size:
        3
    
✅ Output:
        Max of each subarray:
        3 3 4 5 5 5 6


*/
