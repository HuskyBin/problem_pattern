// 迭代版本
class Trie {
    
    private TrieNode root;
    
    private class TrieNode {
        public TrieNode[] children;
        
        public boolean isWord;
        
        public TrieNode() {
            children = new TrieNode[26];
        }
    }

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode pNode = root;
        for (int i = 0; i < word.length(); i++) {
            int pos = word.charAt(i) - 'a';
            if (pNode.children[pos] == null) {
                pNode.children[pos] = new TrieNode();
            }
            pNode = pNode.children[pos];
        }
        pNode.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = findNode(word);
        return node != null && node.isWord;
    }
  
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = findNode(prefix);
        return node != null;
    }    
 
    private TrieNode findNode(String word) {
        TrieNode pNode = root;
        for (int i = 0; i < word.length(); i++) {
            int pos = word.charAt(i) - 'a';
            if (pNode.children[pos] == null) {
                return null;
            }
            pNode = pNode.children[pos];
        }
        return pNode;
    }
}

// Map  版本
class Trie {
    
    private TrieNode root;
    
    private class TrieNode {
        public Map<Character, TrieNode> children;
        
        public boolean isWord;
        
        public TrieNode() {
            children = new HashMap<>();
        }
    }

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode pNode = root;
        for (int i = 0; i < word.length(); i++) {
            char pos = word.charAt(i);
            pNode.children.putIfAbsent(pos, new TrieNode());
            pNode = pNode.children.get(pos);
        }
        pNode.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = findNode(word);
        return node != null && node.isWord;
    }
  
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = findNode(prefix);
        return node != null;
    }    
 
    private TrieNode findNode(String word) {
        TrieNode pNode = root;
        for (int i = 0; i < word.length(); i++) {
            char pos = word.charAt(i);
            if (!pNode.children.containsKey(pos)) {
                return null;
            }
            pNode = pNode.children.get(pos);
        }
        return pNode;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

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
            int pos = curChar - 'a';
            if (children[pos] == null) {
                children[pos] = new TrieNode();
            }
            children[pos].insert(word, index + 1);
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
