import java.util.*;

public class ValidWordAbbreviation {

    // Function to check if abbreviation is valid
    public static boolean isValidWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        int m = word.length(), n = abbr.length();

        while (i < m && j < n) {
            char current = abbr.charAt(j);

            // If character is digit, parse the full number
            if (Character.isDigit(current)) {
                if (current == '0') return false;  // No leading zeros allowed

                int num = 0;
                while (j < n && Character.isDigit(abbr.charAt(j))) {
                    num = num * 10 + (abbr.charAt(j) - '0');
                    j++;
                }
                i += num;  // Skip 'num' characters in word
            } else {
                // If not digit, match character directly
                if (i >= m || word.charAt(i) != abbr.charAt(j)) {
                    return false;
                }
                i++;
                j++;
            }
        }

        // Both pointers should reach the end
        return i == m && j == n;
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter word: ");
        String word = sc.next();
        System.out.print("Enter abbreviation: ");
        String abbr = sc.next();

        boolean result = isValidWordAbbreviation(word, abbr);
        System.out.println(result);
    }
}
/*

word = "kmit"
abbr = "4"   → valid (skip all 4 letters)
word = "kmit"
abbr = "k2t" → valid ("k" matches, skip 2 -> "m", "i", then match "t")
word = "kmit"
abbr = "k03t" → invalid (leading 0)

 */

