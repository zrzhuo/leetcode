/*
 * @lc app=leetcode.cn id=2369 lang=java
 *
 * [2369] 检查数组是否存在有效划分
 */

class Solution {
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        if(n == 1)
            return false;
        if(n == 2)
            return nums[0] == nums[1];
        boolean[] dp1 = new boolean[n]; // dp1[i]: nums[0...i]是否存在有效划分，且最后子数组符合条件1
        boolean[] dp2 = new boolean[n]; // dp2[i]: nums[0...i]是否存在有效划分，且最后子数组符合条件2
        boolean[] dp3 = new boolean[n]; // dp3[i]: nums[0...i]是否存在有效划分，且最后子数组符合条件3
        // 初始化
        dp1[1] = (nums[0] == nums[1]);
        dp2[2] = (nums[0] == nums[1] && nums[1] == nums[2]);
        dp3[2] = (nums[0] == nums[1] - 1 && nums[1] == nums[2] - 1);
        // 递推
        for(int i = 3; i < n; i++) {
            if(dp1[i - 2] || dp2[i - 2] || dp3[i - 2]) {
                dp1[i] = (nums[i] == nums[i - 1]);
            }
            if(dp1[i - 3] || dp2[i - 3] || dp3[i - 3]) {
                dp2[i] = (nums[i] == nums[i - 1] && nums[i - 1] == nums[i - 2]);
            }
            if(dp1[i - 3] || dp2[i - 3] || dp3[i - 3]) {
                dp3[i] = (nums[i] == nums[i - 1] + 1 && nums[i - 1] == nums[i - 2] + 1);
            }
        }
        return dp1[n - 1] || dp2[n - 1] || dp3[n - 1];
    }
}

// @lc code=start
class Solution {
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        if(n == 1)
            return false;
        if(n == 2)
            return nums[0] == nums[1];
        // dp[i]: nums[0...i]是否存在有效划分
        boolean[] dp = new boolean[n];
        // 初始化
        dp[1] = (nums[0] == nums[1]);
        dp[2] = ((nums[0] == nums[1] && nums[1] == nums[2]) || (nums[0] == nums[1] - 1 && nums[1] == nums[2] - 1));
        // 递推
        for(int i = 3; i < n; i++) {
            dp[i] = ((dp[i - 2] && nums[i] == nums[i - 1]) 
                  || (dp[i - 3] && ((nums[i] == nums[i - 1] && nums[i - 1] == nums[i - 2]) || (nums[i] == nums[i - 1] + 1 && nums[i - 1] == nums[i - 2] + 1))));
            
        }
        return dp[n - 1];
    }
}
// @lc code=end
