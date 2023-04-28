/*
 * @lc app=leetcode.cn id=208 lang=java
 *
 * [208] 实现 Trie (前缀树)
 */

// @lc code=start
class Trie {
    Trie[] next; // 指向子结点的数组
    boolean isEnd; // 标志结点是否为某字符串的结尾
    public Trie() {
        next = new Trie[26];
        isEnd = false;
    }
    
    public void insert(String word) {
        Trie p = this;
        for(int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if(p.next[idx] == null)
                p.next[idx] = new Trie(); // 不存在该子结点，进行创建
            p = p.next[idx];
        }
        p.isEnd = true; // 结尾标记 
    }
    
    public boolean search(String word) {
        Trie p = this;
        for(int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if(p.next[idx] == null) // 当前字符不存在
                return false;
            p = p.next[idx];
        }
        return p.isEnd; // 是否为结尾
    }
    
    public boolean startsWith(String prefix) {
        Trie p = this;
        for(int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if(p.next[idx] == null)
                return false;  // 当前字符不存在
            p = p.next[idx];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
// @lc code=end
