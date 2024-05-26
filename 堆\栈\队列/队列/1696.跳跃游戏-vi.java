/*
 * @lc app=leetcode.cn id=1696 lang=java
 *
 * [1696] 跳跃游戏 VI
 */

// @lc code=start
class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        // dp[i]: 到达nums[i]可以获得的最大得分；
        int[] dp = new int[n];
        Deque<Integer> que = new ArrayDeque<>(); // 单调队列维护最大值：队头大，队尾小，注意存放的是下标而不是值
        // 初始化
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = nums[0];
        que.offerLast(0);
        // 递推
        for(int i = 1; i < n; i++) {
            dp[i] = dp[que.peekFirst()] + nums[i];
            // 维护单调队列: 详见239题
            while(!que.isEmpty() && dp[que.peekLast()] < dp[i]) {
                que.pollLast();
            }
            que.offerLast(i);
            if(i >= k - 1) {
                while(!que.isEmpty() && que.peekFirst() < i - k + 1) {
                    que.pollFirst();
                }
            }
        }
        return dp[n - 1];
    }
}
// @lc code=end

