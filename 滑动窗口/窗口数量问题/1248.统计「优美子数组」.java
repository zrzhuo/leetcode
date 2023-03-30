/*
 * @lc app=leetcode.cn id=1248 lang=java
 *
 * [1248] 统计「优美子数组」
 */

// @lc code=start
class Solution {

    // 求nums中"奇数个数小于k的子数组"的个数
    int numLessThan(int[] nums, int k) {
        // 窗口数量问题: 滑动窗口[left, right)
        int left = 0, right = 0, n = nums.length, ans = 0;
        // 定义条件指标: 当前子数组中奇数的个数
        int count = 0;
        // 滑动
        while(right < n) {
            // 移动left直到恰好满足要求
            while(left < right) {
                if(count < k)
                    break;
                if(nums[left] % 2 == 1)
                    count--;
                left++;
            }
            ans += right - left;
            if(nums[right] % 2 == 1)
                count++;
            right++;
        }
        // 末尾特殊处理, 此时right == n
        while(left < right) {
            if(count < k)
                break;
            if(nums[left] % 2 == 1)
                count--;
            left++;
        }
        ans += right - left;
        return ans;
    }

    public int numberOfSubarrays(int[] nums, int k) {
        return numLessThan(nums, k + 1) - numLessThan(nums, k);
    }
}
// @lc code=end

