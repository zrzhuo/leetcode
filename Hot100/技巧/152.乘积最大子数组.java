/*
 * @lc app=leetcode.cn id=152 lang=java
 *
 * [152] 乘积最大子数组
 */

class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        // dp1[i]: 以nums[i]结尾的连续非空子数组的最大乘积
        // dp2[i]: 以nums[i]结尾的连续非空子数组的最小乘积
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        // 初始化
        dp1[0] = nums[0];
        dp2[0] = nums[0];
        // 递推
        for(int i = 1; i < n; ++i) {
            if(nums[i] > 0) {
                dp1[i] = Math.max(nums[i], dp1[i - 1] * nums[i]);
                dp2[i] = Math.min(nums[i], dp2[i - 1] * nums[i]);
            } else if (nums[i] < 0) {
                dp1[i] = Math.max(nums[i], dp2[i - 1] * nums[i]);
                dp2[i] = Math.min(nums[i], dp1[i - 1] * nums[i]);
            } else {
                dp1[i] = 0
                dp2[i] = 0
            }
        }
        // 获取结果
        int result = nums[0];
        for(int i = 0; i < n; ++i) {
            result = Math.max(result, dp1[i]);
        }
        return result;
    }
}

// @lc code=start
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        // dp1[i]: 以nums[i]结尾的连续非空子数组的最大乘积
        // dp2[i]: 以nums[i]结尾的连续非空子数组的最小乘积
        int dp1 = nums[0], dp2 = nums[0];
        int result = nums[0];
        // 递推
        for(int i = 1; i < n; ++i) {
            int pre1 = dp1, pre2 = dp2;
            if(nums[i] > 0) {
                dp1 = Math.max(nums[i], pre1 * nums[i]);
                dp2 = Math.min(nums[i], pre2 * nums[i]);
            } else if (nums[i] < 0) {
                dp1 = Math.max(nums[i], pre2 * nums[i]);
                dp2 = Math.min(nums[i], pre1 * nums[i]);
            } else {
                dp1 = 0;
                dp2 = 0;
            }
            result = Math.max(result, dp1);
            result = Math.max(result, dp2);
        }
        return result;
    }
}
// @lc code=end
