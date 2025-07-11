package TRIE;

import java.util.*;

// Node structure for Trie
class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;
    int frequency;
    String word;

    TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
        frequency = 0;
        word = null;
    }
}

// Trie data structure for storing words with their frequencies
class Trie {
    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    // Insert a word with its frequency
    public void insert(String word, int freq) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEndOfWord = true;
        node.frequency = freq;
        node.word = word;
    }

    // DFS to collect words from the Trie and store in a priority queue
    public void collectWords(TrieNode node, PriorityQueue<StringFrequency> pq, int k) {
        if (node == null) return;

        if (node.isEndOfWord) {
            pq.offer(new StringFrequency(node.word, node.frequency));
            if (pq.size() > k) {
                pq.poll(); // Remove the lowest priority element
            }
        }

        for (TrieNode child : node.children) {
            if (child != null) {
                collectWords(child, pq, k);
            }
        }
    }
}

// Helper class to store a word and its frequency
class StringFrequency {
    String word;
    int freq;

    StringFrequency(String word, int freq) {
        this.word = word;
        this.freq = freq;
    }
}

// Comparator for PriorityQueue to maintain min-heap by frequency (and lexicographically)
class WordComparator implements Comparator<StringFrequency> {
    public int compare(StringFrequency a, StringFrequency b) {
        if (a.freq == b.freq) {
            return b.word.compareTo(a.word); // reverse lexicographic order for min-heap
        }
        return Integer.compare(a.freq, b.freq); // lower frequency has higher priority
    }
}

// Main class
public class FrequentWord {
    public static List<String> topKFrequent(String[] words, int k) {
        // Step 1: Count frequency of each word
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        // Step 2: Build Trie with word and its frequency
        Trie trie = new Trie();
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            trie.insert(entry.getKey(), entry.getValue());
        }

        // Step 3: Use min-heap to find top K frequent words
        PriorityQueue<StringFrequency> pq = new PriorityQueue<>(new WordComparator());

        // Step 4: DFS on Trie to fill heap
        trie.collectWords(trie.root, pq, k);

        // Step 5: Extract top K words from heap (in reverse order)
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll().word);
        }
        Collections.reverse(result); // highest freq word first
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] dict = sc.nextLine().split(",");
        int k = sc.nextInt();
        List<String> topWords = topKFrequent(dict, k);
        System.out.println(topWords);
    }
}
// Example-1:
// input =
// ball,are,case,doll,egg,case,doll,egg,are,are,egg,case,are,egg,are,case
// 3
// output =
// [are, case, egg]
