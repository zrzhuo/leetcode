// 小根堆
class MinHeap {
    int[] heap;     // 完全二叉树, 对于结点i, 其父结点为(i-1)/2, 子结点分别为 2*i+1, 2*i+2 
    int size;       // 当前元素个数
    int capacity;   // 容量

    MinHeap(int capacity) {
        this.heap = new int[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    void offer(int val) {
        if(size == capacity) 
            return; // 容量不足
        heap[size] = val; // 新增元素暂时存放在数组末尾, 即完全二叉树的最后一个节点
        size++;
        // 从下到上调整: 不断与父结点进行比较和交换
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
        // 从上到下调整: 不断与子结点进行比较和交换
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

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }
    
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}