/*
 * @lc app=leetcode.cn id=283 lang=java
 *
 * [283] 移动零
 */

// @lc code=start
class Solution {
    public void moveZeroes(int[] nums) {
        int n = nums.length, p = 0;
        for(int i = 0; i < n; i++) {
            if(nums[i] != 0) {
                nums[p++] = nums[i];
            }
        }
        for(int i = p; i < n; i++) {
            nums[i] = 0;
        }
        return;
    }
}
// @lc code=end

