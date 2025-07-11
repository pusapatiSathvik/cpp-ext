import java.util.*;

public class IndexPairs {

    // Trie node definition
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord = false;
    }

    // Root of the Trie
    private final TrieNode root = new TrieNode();

    // Insert a word into the Trie
    private void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEndOfWord = true;
    }

    // Find all index pairs [i, j] such that text.substring(i, j + 1) is in the words list
    public List<int[]> indexPairs(String text, String[] words) {
        // Step 1: Build Trie with the given words
        for (String word : words) {
            insert(word);
        }

        List<int[]> result = new ArrayList<>();

        // Step 2: Search text starting at each index using the Trie
        for (int i = 0; i < text.length(); i++) {
            TrieNode node = root;
            int j = i;

            while (j < text.length()) {
                int idx = text.charAt(j) - 'a';

                // Break if no path in Trie
                if (node.children[idx] == null) {
                    break;
                }

                node = node.children[idx];

                // If it's the end of a word, add index pair
                if (node.isEndOfWord) {
                    result.add(new int[]{i, j});
                }

                j++;
            }
        }

        return result;
    }

    // Helper to print list of index pairs
    private static void printResult(List<int[]> pairs) {
        for (int[] pair : pairs) {
            System.out.println(Arrays.toString(pair));
        }
    }

    // Main method for input and output
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the text:");
        String text = sc.nextLine();

        System.out.println("Enter words (space separated):");
        String[] words = sc.nextLine().split(" ");

        IndexPairs solution = new IndexPairs();
        List<int[]> result = solution.indexPairs(text, words);

        System.out.println("Index Pairs:");
        printResult(result);
    }
}
