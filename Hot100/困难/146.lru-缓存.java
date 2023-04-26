/*
 * @lc app=leetcode.cn id=146 lang=java
 *
 * [146] LRU 缓存
 */

// @lc code=start
class LRUCache {
    // 存放key到结点的映射
    Map<Integer, ListNode> map; 
    // 虚拟头尾结点, 维护一个双向链表作为队列, 队头元素是最久未被访问的元素, 队尾元素是最近刚被访问的元素
    ListNode header, tailer;
    int capacity; // 容量
    int size; // 当前大小

    public LRUCache(int capacity) {
        map = new HashMap<>();
        header = new ListNode(-1, -1);
        tailer = new ListNode(-1, -1);
        header.next = tailer;
        tailer.prev = header;
        this.capacity = capacity;
        this.size = 0;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) 
            return -1;
        ListNode node = map.get(key);
        remove(node); // 移除
        offer(node); // 再添加到队尾
        return node.value;
    }
    
    public void put(int key, int value) {
        if(!map.containsKey(key)) {
            // 容量不足
            if(size == capacity) {
                map.remove(header.next.key);
                remove(header.next);
                size--;
            }
            ListNode node = new ListNode(key, value);
            map.put(key, node); // 插入哈希表
            offer(node); // 插入队尾
            size++;
        } else {
            ListNode node = map.get(key);
            node.value = value;
            remove(node); // 移除
            offer(node); // 再添加到队尾
        }
    }

    // 向队尾添加指定结点
    void offer(ListNode node) {
        tailer.prev.next = node;
        node.next = tailer;
        node.prev = tailer.prev;
        tailer.prev = node;
    }

    // 从队列中删除指点结点
    void remove(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }
}

// 双链表结点
class ListNode {
    int key;
    int value;
    ListNode prev;
    ListNode next;
    ListNode(int key, int val) {
        this.key = key;
        this.value = val;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end

