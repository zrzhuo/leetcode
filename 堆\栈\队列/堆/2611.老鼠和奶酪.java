/*
 * @lc app=leetcode.cn id=2611 lang=java
 *
 * [2611] 老鼠和奶酪
 */

// @lc code=start
class Solution {
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int n = reward1.length;
        int sum = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i = 0; i < n; i++) {
            sum += reward2[i]; // 先全部选择reward2
            minHeap.offer(reward1[i] - reward2[i]); // reward1和reward2在i位置的差值
            if(minHeap.size() > k)
                minHeap.poll();
        }
        List<Integer> list = new ArrayList<>(minHeap);  // 转换为列表, 提高效率
        for(int i = 0; i < k; i++) {
            sum += list.get(i);
        }
        return sum;
    }
}
// @lc code=end

