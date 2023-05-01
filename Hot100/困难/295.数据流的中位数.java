/*
 * @lc app=leetcode.cn id=295 lang=java
 *
 * [295] 数据流的中位数
 */

// @lc code=start
class MedianFinder {
    PriorityQueue<Integer> maxHeap; // 大根堆, 存放数字较小的半部分数据
    PriorityQueue<Integer> minHeap; // 小根堆, 存放数字较大的半部分数据

    public MedianFinder() {
        minHeap = new PriorityQueue<>((a, b) -> a - b);
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }
    
    public void addNum(int num) {
        int s1 = maxHeap.size(), s2 = minHeap.size();
        // 对第一个数字的特殊处理
        if(s1 == 0) {
            maxHeap.offer(num);
            return;
        }
        // 更新时需保持: maxHeap的最大值 <= minHeap的最小值
        if(s1 == s2) {
            if(num <= maxHeap.peek()) {
                maxHeap.offer(num); // 直接插入maxHeap
            } else {
                minHeap.offer(num); // 先插入minHeap
                maxHeap.offer(minHeap.poll()); // 将minHeap中的最小值转移到maxHeap中
            }
        } 
        else if(s1 == s2 + 1) {
            if(num > maxHeap.peek()) {
                minHeap.offer(num); // 直接插入minHeap
            } else {
                maxHeap.offer(num); // 先插入maxHeap
                minHeap.offer(maxHeap.poll()); // 将maxHeap中的最大值转移到minHeap中
            }
        }
    }
    
    public double findMedian() {
        int s1 = maxHeap.size(), s2 = minHeap.size();
        if((s1 + s2) % 2 == 1) 
            return maxHeap.peek() * 1.0;
        else 
            return (maxHeap.peek() + minHeap.peek()) * 1.0 / 2;
    }
}
// @lc code=end
