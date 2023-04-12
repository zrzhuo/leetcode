/*
 * @lc app=leetcode.cn id=238 lang=java
 *
 * [238] 除自身以外数组的乘积
 */

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        // 前缀
        int[] prefix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * nums[i];
        }
        // 后缀
        int[] suffix = new int[n];
        suffix[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i];
        }
        // 求解
        int[] ans = new int[n];
        ans[0] = suffix[1];
        ans[n - 1] = prefix[n - 2];
        for (int i = 1; i < n - 1; ++i) {
            ans[i] = prefix[i - 1] * suffix[i + 1];
        }
        return ans;
    }
}

// @lc code=start
// 空间优化
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        int prefix = 1, suffix = 1;
        for (int k = 0; k < n; k++) {
            int i = k; // i: 从0到n-1
            int j = n - 1 - k; // j: 从n-1到0
            ans[i] *= prefix; // 前缀[0...i-1]
            ans[j] *= suffix; // 后缀[j+1...n-1]
            // 更新prefix和suffix
            prefix *= nums[i]; // 前缀[0..i]
            suffix *= nums[j]; // 后缀[j...n-1]
        }
        return ans;
    }
}
// @lc code=end
