/*
 * @lc app=leetcode.cn id=189 lang=java
 *
 * [189] 轮转数组
 */

// @lc code=start
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reserve(nums, 0, n - 1);
        reserve(nums, 0, k - 1);
        reserve(nums, k, n - 1);
        return;
    }

    void reserve(int[] nums, int start, int stop) {
        int i = start, j = stop;
        while(i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
        return;
    }
}
// @lc code=end

