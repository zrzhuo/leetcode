/*
 * @lc app=leetcode.cn id=581 lang=java
 *
 * [581] 最短无序连续子数组
 */

// @lc code=start
// 题解: https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/solution/si-lu-qing-xi-ming-liao-kan-bu-dong-bu-cun-zai-de-/
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        if(n == 1)
            return 0;
        int max = Integer.MIN_VALUE, right = 0;
        for(int i = 0; i < n; i++) {
            if(nums[i] < max) 
                right = i;
            max = Math.max(max, nums[i]);
        }
        int min = Integer.MAX_VALUE, left = n - 1;
        for(int i = n - 1; i >= 0; i--) {
            if(nums[i] > min) 
                left = i;
            min = Math.min(min, nums[i]);
        }
        return Math.max(0, right - left + 1);
    }
}
// @lc code=end
