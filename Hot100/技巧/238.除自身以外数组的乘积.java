/*
 * @lc app=leetcode.cn id=238 lang=java
 *
 * [238] 除自身以外数组的乘积
 */

// @lc code=start
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        int prefix = 1, suffix = 1;
        for(int i = 0; i < n; i++) {
            int l = i, r = n - 1 - i;
            ans[l] *= prefix;
            ans[r] *= suffix;
            prefix *= nums[l];
            suffix *= nums[r];
        }
        return ans;
    }
}
// @lc code=end

