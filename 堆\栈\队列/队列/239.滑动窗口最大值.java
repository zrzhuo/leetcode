/*
 * @lc app=leetcode.cn id=239 lang=java
 *
 * [239] 滑动窗口最大值
 */

// @lc code=start
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length, idx = 0;
        int[] ans = new int[n - k + 1];
        // 单调队列，队头最大，队尾最小，注意存放的是下标而不是值
        Deque<Integer> que = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            // 此时的滑动窗口的右边界为i, 队列中的现存的所有下标j一定小于i, 
            // 1. 若nums[j] < nums[i], 则nums[j]一定不是当前窗口中的最大值, 故可以直接抛弃nums[j]
            // 2. 若nums[j] = nums[i], 因为随后会把nums[i]入队, 故也可以直接抛弃nums[j]
            while(!que.isEmpty() && nums[que.peekLast()] <= nums[i])  {
                que.pollLast();
            }
            que.offerLast(i);
            // 滑动窗口固定大小为k
            if(i >= k - 1) {
                // 当前的滑动窗口为[i-k+1,i], 故队列中小于i-k+1的下标都不在窗口呢, 应该将这些下标移除
                // 由于我们只关心最大值, 而最大值在队头, 故只需要在队头移除这些小于i-k+1的下标, 从而将窗口内的最大值暴露到队头
                while(!que.isEmpty() && que.peekFirst() < i - k + 1) {
                    que.pollFirst();
                }
                ans[idx++] = nums[que.peekFirst()];
            }
        }
        return ans;
    }
}
// @lc code=end

