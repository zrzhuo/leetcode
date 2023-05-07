/*
 * @lc app=leetcode.cn id=973 lang=java
 *
 * [973] 最接近原点的 K 个点
 */

// @lc code=start
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // 大根堆
        PriorityQueue<int[]> heap = new PriorityQueue<>(
            (a, b) -> (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1])
        );
        for(int[] point : points) {
            heap.offer(point);
            if(heap.size() > k)
                heap.poll();
        }
        // 获取结果
        int[][] result = new int[k][];
        for(int i = 0; i < k; i++) {
            result[i] = heap.poll();
        }
        return result;
    }
}
// @lc code=end

