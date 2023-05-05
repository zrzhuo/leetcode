/*
 * @lc app=leetcode.cn id=1248 lang=java
 *
 * [1248] 统计「优美子数组」
 */

记count为窗口[left, right)中奇数的个数, 则有:
    1. left固定时, 右移right则count不变或增大
    2. right固定时, 右移left则count不变或减小

// @lc code=start
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        // 奇数个数等于k的子数组的个数 = 奇数个数小于k+1的子数组的个数 - 奇数个数小于k的子数组的个数
        return numLessThan(nums, k + 1) - numLessThan(nums, k);
    }

    // 求nums中"奇数个数小于k的子数组"的个数
    int numLessThan(int[] nums, int k) {
        // 窗口数量问题: 滑动窗口[left, right)
        int left = 0, right = 0, n = nums.length, ans = 0;
        // 定义条件指标: 当前滑动窗口中奇数的个数
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
            // 移动right
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

}
// @lc code=end

