/*
 * @lc app=leetcode.cn id=45 lang=java
 *
 * [45] 跳跃游戏 II
 */

// @lc code=start
class Solution {
    public int jump(int[] nums) {
        // 在位置i时，i+nums[i]即为该位置所能覆盖的最远距离.
        // 每次跳跃要贪心的选择落地位置, 以保证新的覆盖范围达到最大
        int n = nums.length, count = 0;
        int currDist = 0; // 当前能达到的最远距离
        int nextDist = 0; // 做完下一次跳跃, 能达到的最远距离
        for(int i = 0; i < n - 1; i++) {
            nextDist = Math.max(nextDist, i + nums[i]);
            // 到达当前最远距离时，贪心的做一次跳跃，从而将最远距离扩大至nextRange
            if(i == currDist) {
                currDist = nextDist;
                count++;
            }
        }
        return count;
    }
}
// @lc code=end

