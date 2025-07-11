package TRIE;

import java.util.*;

class Node {
    Node[] links = new Node[26];
    boolean flag = false;
}

class Trie {
    private Node root;

    public Trie() {
        root = new Node();
    }

    // Insert a word into the Trie
    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';

            if (node.links[index] == null) {
                node.links[index] = new Node(); // directly adding child
            }

            node = node.links[index]; // directly navigating
        }

        node.flag = true; // directly mark end of word
    }

    // Check if all prefixes of the word exist in the Trie
    public boolean checkIfAllPrefixExists(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';

            if (node.links[index] == null) {
                return false;
            }

            node = node.links[index];

            if (!node.flag) {
                return false;
            }
        }

        return true;
    }
}

public class LongestWord {

    public static String completeString(int n, String[] a) {
        Trie trie = new Trie();
        for (String word : a) {
            trie.insert(word);
        }

        String longest = "";

        for (String word : a) {
            if (trie.checkIfAllPrefixExists(word)) {
                if (word.length() > longest.length() || 
                   (word.length() == longest.length() && word.compareTo(longest) < 0)) {
                    longest = word;
                }
            }
        }

        return longest.equals("") ? "None" : longest;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] dict = sc.nextLine().split(" ");
        System.out.println(completeString(dict.length, dict));
    }
}
