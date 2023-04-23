/*
 * @lc app=leetcode.cn id=347 lang=java
 *
 * [347] 前 K 个高频元素
 */

// @lc code=start
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 统计频率
        Map<Integer, Integer> counter = new HashMap<>();
        for(int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        // 优先队列, 频率越小越优先(小根堆)
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(
            (Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) -> {
                return a.getValue() - b.getValue();
            }
        );
        for(Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            heap.offer(entry);
            if(heap.size() > k)
                heap.poll();
        }
        // 获取结果
        int[] res = new int[k];
        for(int i = k - 1; i >= 0; i--) {
            res[i] = heap.poll().getKey();
        }
        return res;
    }
}
// @lc code=end

