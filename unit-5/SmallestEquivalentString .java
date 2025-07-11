public class SmallestEquivalentString {

    // DSU for 26 lowercase letters
    static class DSU {
        int[] parent = new int[26]; // 'a' to 'z'

        DSU() {
            for (int i = 0; i < 26; i++) {
                parent[i] = i; // Initially, each character is its own parent
            }
        }

        // Find with path compression
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        // Union by keeping the smallest lexicographic character as root
        void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) return;
            // Always attach the larger root to the smaller one
            if (px < py) {
                parent[py] = px;
            } else {
                parent[px] = py;
            }
        }
    }

    public static String smallestEquivalentString(String s1, String s2, String baseStr) {
        DSU dsu = new DSU();

        // Step 1: Union characters from s1 and s2
        for (int i = 0; i < s1.length(); i++) {
            int c1 = s1.charAt(i) - 'a';
            int c2 = s2.charAt(i) - 'a';
            dsu.union(c1, c2);
        }

        // Step 2: For each character in baseStr, replace with lex smallest equivalent
        StringBuilder result = new StringBuilder();
        for (char c : baseStr.toCharArray()) {
            int root = dsu.find(c - 'a');
            result.append((char) (root + 'a'));
        }

        return result.toString();
    }

    // Example test
    public static void main(String[] args) {
        System.out.println(smallestEquivalentString("parker", "morris", "parser")); // "makkek"
        System.out.println(smallestEquivalentString("hello", "world", "hold"));     // "hdld"
        System.out.println(smallestEquivalentString("leetcode", "programs", "sourcecode")); // "aauaaaaada"
    }
}
