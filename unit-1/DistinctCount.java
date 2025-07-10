import java.util.*;

public class DistinctCount {

    // Function to find distinct element count in each window of size k
    public static void findDistinctCount(int[] arr, int k) {
        // HashMap to store the frequency of elements in the current window
        HashMap<Integer, Integer> map = new HashMap<>();

        // Traverse the first window of size k
        for (int i = 0; i < k; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        // Print distinct count for the first window
        System.out.print(map.size() + " ");

        // Process the rest of the windows
        for (int i = k; i < arr.length; i++) {
            // Remove the element going out of the window
            int outElement = arr[i - k];
            if (map.get(outElement) == 1) {
                map.remove(outElement);
            } else {
                map.put(outElement, map.get(outElement) - 1);
            }

            // Add the new element coming into the window
            int inElement = arr[i];
            map.put(inElement, map.getOrDefault(inElement, 0) + 1);

            // Print the number of distinct elements in the current window
            System.out.print(map.size() + " ");
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read array size
        System.out.println("Enter array elements size:");
        int n = sc.nextInt();
        int[] array = new int[n];

        // Read array elements
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }

        // Read subarray (window) size
        System.out.println("Enter the subarray size:");
        int k = sc.nextInt();

        // Call function to find and print distinct counts
        findDistinctCount(array, k);
    }
}
