import java.util.*;

/*
✅ Problem Statement:
You are given:

An array calories[] of daily calorie intake,

An integer k representing the size of the window,

A lower and upper threshold.

For every k-day window:

If the sum < lower, score -1

If the sum > upper, score +1

Else, score 0

 */

public class DietPlanPerformance {

    // Function to calculate diet performance
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int windowSum = 0;
        int points = 0;

        // Calculate sum for the first window
        for (int i = 0; i < k; i++) {
            windowSum += calories[i];
        }

        // Adjust points for the first window
        if (windowSum < lower) {
            points--;
        } else if (windowSum > upper) {
            points++;
        }

        // Slide the window through the rest of the array
        for (int i = k; i < calories.length; i++) {
            windowSum += calories[i] - calories[i - k];

            if (windowSum < lower) {
                points--;
            } else if (windowSum > upper) {
                points++;
            }
            // No points if windowSum == lower || upper
        }

        return points;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read array size
        System.out.print("Enter calories size: ");
        int n = sc.nextInt();
        int[] calories = new int[n];

        // Read calorie values
        System.out.print("Enter the calories: ");
        for (int i = 0; i < n; i++) {
            calories[i] = sc.nextInt();
        }

        // Read window size and thresholds
        System.out.print("Enter the number of days (k): ");
        int k = sc.nextInt();
        System.out.print("Enter the lower value: ");
        int lower = sc.nextInt();
        System.out.print("Enter the upper value: ");
        int upper = sc.nextInt();

        // Compute and display performance
        DietPlanPerformance dpp = new DietPlanPerformance();
        int result = dpp.dietPlanPerformance(calories, k, lower, upper);
        System.out.println("Total performance points: " + result);
    }
}
