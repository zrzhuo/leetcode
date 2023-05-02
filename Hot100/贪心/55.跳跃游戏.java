/*
 * @lc app=leetcode.cn id=55 lang=java
 *
 * [55] 跳跃游戏
 */

// @lc code=start
class Solution {
    public boolean canJump(int[] nums) {
        // 贪心: 如果能到达位置i, 则位置i之前的位置一定能到达, 故只需要计算能够到达的最远位置是否大于等于n-1即可
        int n = nums.length;
        int farthest = 0; // 可以到达的最远位置
        for(int i = 0; i < n; i++) {
            if(i <= farthest) {
                farthest = Math.max(farthest, i + nums[i]); // 能到达位置i, 更新farthest
            } else {
                return false; // 不能到达位置i, 则一定到达不了位置n-1
            }
        } 
        return true;
    }
}
// @lc code=end

