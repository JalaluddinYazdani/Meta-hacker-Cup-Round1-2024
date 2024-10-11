import java.util.*;

public class E {
    static final int MOD = 998244353;
    static final int ALPHABET_SIZE = 26;

    static class TrieNode {
        TrieNode[] children;

        TrieNode() {
            children = new TrieNode[ALPHABET_SIZE];
        }
    }

    static class Trie {
        TrieNode root;
        int nodeCount;

        Trie() {
            root = new TrieNode();
            nodeCount = 1;  // The root node is already created
        }

        void insert(String s) {
            insertHelper(root, s, 0);
        }

        private void insertHelper(TrieNode node, String s, int index) {
            if (index == s.length()) return;

            if (s.charAt(index) == '?') {
                // Replace '?' with every possible letter
                for (int i = 0; i < ALPHABET_SIZE; i++) {
                    if (node.children[i] == null) {
                        node.children[i] = new TrieNode();
                        nodeCount = (nodeCount + 1) % MOD;
                    }
                    insertHelper(node.children[i], s, index + 1);
                }
            } else {
                int letterIndex = s.charAt(index) - 'A';
                if (node.children[letterIndex] == null) {
                    node.children[letterIndex] = new TrieNode();
                    nodeCount = (nodeCount + 1) % MOD;
                }
                insertHelper(node.children[letterIndex], s, index + 1);
            }
        }

        int getNodeCount() {
            return nodeCount;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // Number of test cases

        for (int t = 1; t <= T; t++) {
            int N = sc.nextInt();  // Number of wildcard strings
            sc.nextLine();  // Consume the newline

            Trie trie = new Trie();
            for (int i = 0; i < N; i++) {
                String S = sc.nextLine();
                trie.insert(S);
            }

            System.out.println("Case #" + t + ": " + trie.getNodeCount());
        }
        sc.close();
    }
}
