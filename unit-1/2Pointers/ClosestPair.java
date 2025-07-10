import java.util.*;

public class ClosestPair {

    // Function to find the pair with the closest sum to x
    void printClosest(int arr1[], int arr2[], int m, int n, int x) {
        int l = 0;         // Pointer for arr1 (start)
        int r = n - 1;     // Pointer for arr2 (end)

        int diff = Integer.MAX_VALUE;  // Minimum difference
        int res_l = 0, res_r = 0;      // To store result indices

        // Traverse arrays using two pointers
        while (l < m && r >= 0) {
            int sum = arr1[l] + arr2[r];
            int currentDiff = Math.abs(sum - x);

            // Update result if current pair is closer to x
            if (currentDiff < diff) {
                diff = currentDiff;
                res_l = l;
                res_r = r;
            }

            // Move the pointer that gives us a sum closer to x
            if (sum > x) {
                r--; // Try smaller number from arr2
            } else {
                l++; // Try larger number from arr1
            }
        }

        // Print the closest pair
        System.out.println("The closest pair is [" + arr1[res_l] + ", " + arr2[res_r] + "]");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClosestPair ob = new ClosestPair();

        // Input for arr1
        System.out.print("Enter size of array_1: ");
        int n1 = sc.nextInt();
        int[] arr1 = new int[n1];
        System.out.println("Enter sorted values of array_1:");
        for (int i = 0; i < n1; i++) {
            arr1[i] = sc.nextInt();
        }

        // Input for arr2
        System.out.print("Enter size of array_2: ");
        int n2 = sc.nextInt();
        int[] arr2 = new int[n2];
        System.out.println("Enter sorted values of array_2:");
        for (int i = 0; i < n2; i++) {
            arr2[i] = sc.nextInt();
        }

        // Input for target sum
        System.out.print("Enter the target number (x): ");
        int x = sc.nextInt();

        // Find and print closest pair
        ob.printClosest(arr1, arr2, n1, n2, x);
    }
}
/*

🧪 Sample Input:

Enter size of array_1: 4
Enter sorted values of array_1:
1 4 5 7
Enter size of array_2: 4
Enter sorted values of array_2:
10 20 30 40
Enter the target number (x): 32

✅ Output:

The closest pair is [1, 30]

 */


