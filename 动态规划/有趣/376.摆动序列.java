/*
 * @lc app=leetcode.cn id=376 lang=java
 *
 * [376] 摆动序列
 */

class Solution {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        // dp1[i]: nums[0...i]中, 最后的差值为正的最长摆动序列的长度
        // dp2[i]: nums[0...i]中, 最后的差值为负的最长摆动序列的长度
        int[] dp1 = new int[n], dp2 = new int[n];
        dp1[0] = 1;
        dp2[0] = 1;
        for(int i = 1; i < n; i++) {
            if(nums[i] > nums[i - 1]) {
                dp1[i] = dp2[i - 1] + 1;
                dp2[i] = dp2[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                dp1[i] = dp1[i - 1];
                dp2[i] = dp1[i - 1] + 1;
            } else {
                dp1[i] = dp1[i - 1];
                dp2[i] = dp2[i - 1];
            }
        }
        return Math.max(dp1[n - 1], dp2[n - 1]);
    }
}

// @lc code=start
// 空间优化
class Solution {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        // dp1[i]: nums[0...i]中, 最后的差值为正的最长摆动序列的长度
        // dp2[i]: nums[0...i]中, 最后的差值为负的最长摆动序列的长度
        int dp1 = 1, dp2 = 1;
        for(int i = 1; i < n; i++) {
            if(nums[i] > nums[i - 1])
                dp1 = dp2 + 1;
            else if (nums[i] < nums[i - 1])
                dp2 = dp1 + 1;
        }
        return Math.max(dp1, dp2);
    }
}
// @lc code=end

