/*
 * @lc app=leetcode.cn id=215 lang=java
 *
 * [215] 数组中的第K个最大元素
 */

// @lc code=start
// 手动实现堆
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 定义小根堆, 容量为k+1即可
        MinHeap heap = new MinHeap(k + 1);
        for(int i = 0; i < nums.length; i++) {
            heap.offer(nums[i]);
            if(heap.size() > k)
                heap.poll(); // 排除k+1个数中的最小数, 该数一定不是所求
        }
        return heap.peek(); // 剩余k个数中的最大数即为所求
    }
}
// 手动实现的小根堆
class MinHeap {
    int[] heap;
    int size;
    int capacity;

    MinHeap(int capacity) {
        this.heap = new int[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    boolean isEmpty() {
        return size == 0;
    }

    int size() {
        return size;
    }

    void offer(int val) {
        if(size == capacity) 
            return; // 容量不足
        heap[size] = val; // 新增元素暂时存放在数组末尾
        size++;
        // 调整: 不断与父结点进行比较和交换
        int idx = size - 1;
        while(idx > 0) {
            if(heap[idx] < heap[(idx - 1) / 2])
                swap(idx, (idx - 1) / 2); // 当前节点较小, 上浮
            else
                break;
            idx = (idx - 1) / 2;
        }
    }

    int poll() {
        int val = heap[0];
        heap[0] = heap[size - 1]; // 用最后一个结点填充heap[0]
        size--;
        // 调整: 不断与子结点进行比较和交换
        int idx = 0;
        while(2 * idx + 1 < size) {
            int left = 2 * idx + 1, right = 2 * idx + 2;
            int smaller = left; // 左右子结点的较小者
            if(right < size && heap[right] < heap[left])
                smaller = right;
            if(heap[idx] > heap[smaller])
                swap(smaller, idx); // 当前节点较大, 下沉
            else
                break;
            idx = smaller;
        }
        return val;
    }

    int peek() {
        return heap[0];
    }
    
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
// @lc code=end


// 使用PriorityQueue
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 定义小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(int i = 0; i < nums.length; i++) {
            heap.offer(nums[i]);
            if(heap.size() > k)
                heap.poll(); // 排除k+1个数中的最小数, 该数一定不是所求
        }
        return heap.peek(); // 剩余k个数中的最大数
    }
}
