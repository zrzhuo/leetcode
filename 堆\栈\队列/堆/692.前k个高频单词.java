/*
 * @lc app=leetcode.cn id=692 lang=java
 *
 * [692] 前K个高频单词
 */

// @lc code=start
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        // 统计频率
        Map<String, Integer> counter = new HashMap<>();
        for(String word : words) {
            counter.put(word, counter.getOrDefault(word, 0) + 1);
        }
        // 优先队列
        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(
            (Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) -> {
                if(a.getValue() != b.getValue())
                    return a.getValue() - b.getValue();
                return b.getKey().compareTo(a.getKey());
            }
        );
        for(Map.Entry<String, Integer> entry : counter.entrySet()) {
            heap.offer(entry);
            if(heap.size() > k)
                heap.poll();
        }
        // 获取结果
        List<String> res = new ArrayList<>(k);
        for(int i = 0; i < k; i++) {
            res.add(heap.poll().getKey());
        }
        Collections.reverse(res);
        return res;
    }
}
// @lc code=end

