package TRIE;

import java.util.*;

public class SuffixTrie {
    static final int NUM_CHARS = 26;

    // Trie Node for lowercase English letters
    static class SuffixTrieNode {
        SuffixTrieNode[] children = new SuffixTrieNode[NUM_CHARS];
        boolean isEndOfWord;

        SuffixTrieNode() {
            isEndOfWord = false;
        }
    }

    static SuffixTrieNode root;

    // Function to insert a suffix into the trie
    static void insert(String word) {
        word = word.toLowerCase();  // Ensure lowercase
        SuffixTrieNode currentNode = root;

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';

            if (currentNode.children[index] == null) {
                currentNode.children[index] = new SuffixTrieNode();
            }

            currentNode = currentNode.children[index];
        }

        currentNode.isEndOfWord = true;
    }

    // Helper function to check if a node marks end of a suffix
    static boolean isLeafNode(SuffixTrieNode node) {
        return node.isEndOfWord;
    }

    // Print all suffixes stored in the trie
    static void print(SuffixTrieNode node, char[] str, int level) {
        if (isLeafNode(node)) {
            // Print the current collected characters as a suffix
            System.out.println(new String(str, 0, level));
        }

        for (int i = 0; i < NUM_CHARS; i++) {
            if (node.children[i] != null) {
                str[level] = (char) (i + 'a');
                print(node.children[i], str, level + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter any string to construct suffix trie:");
        String input = sc.nextLine().toLowerCase(); // Convert input to lowercase

        root = new SuffixTrieNode();

        // Insert all suffixes into the trie
        for (int i = 0; i < input.length(); i++) {
            insert(input.substring(i));
        }

        System.out.println("Suffixes stored in the trie:");
        print(root, new char[100], 0);
    }
}

