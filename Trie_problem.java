// 递归版本
class Trie {
    
    private TrieNode root;
    
    private class TrieNode {
        public TrieNode[] children;
        
        public boolean isWord;
        
        public TrieNode() {
            children = new TrieNode[26];
        }
        
        public void insert(String word, int index) {
            if (index == word.length()) {
                this.isWord = true;
                return;
            }
            char curChar = word.charAt(index);
            if (children[curChar - 'a'] == null) {
                children[curChar - 'a'] = new TrieNode();
            }
            children[curChar - 'a'].insert(word, index + 1);
        }
        
        public TrieNode find(String word, int index) {
            if (index == word.length()) {
                return this;
            }
            int pos = word.charAt(index) - 'a';
            if (children[pos] == null) {
                return null;
            }
            return children[pos].find(word, index + 1);
        }
    }

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        root.insert(word, 0);
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root.find(word, 0);
        return node != null && node.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root.find(prefix, 0);
        return node != null;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
