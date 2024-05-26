public class Trie {
    // 结点定义
    private static class Node {
        Node[] next; // 子结点数组
        boolean isEnd; // 标志结点是否为某字符串的结尾
        Node() {
            this.next = new Node[26];
            this.isEnd = false;
        }
    }

    private Node root; // 根节点
    private int size; // 单词数量
    public Trie(){
        this.root = new Node();
        size = 0;
    }

    // 插入字符串
    public void insert(String word) {
        Node p = root;
        for(int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if(p.next[idx] == null) {
                p.next[idx] = new Node(); // 不存在该子结点，创建
            }
            p = p.next[idx];
        }
        if(!p.isEnd) {
            size++;
            p.isEnd = true;
        }
    }

    // 查找字符串
    public boolean search(String word) {
        Node p = root;
        for(int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if(p.next[idx] == null) {
                return false; // 当前字符不存在
            }
            p = p.next[idx];
        }
        return p.isEnd; // 是否为结尾
    }

    // 查找前缀
    public boolean startWith(String prefix) {
        Node p = root;
        for(int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if(p.next[idx] == null)
                return false;  // 当前字符不存在
            p = p.next[idx];
        }
        return true;
    }
}
