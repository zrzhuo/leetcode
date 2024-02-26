/*
 * @lc app=leetcode.cn id=2799 lang=java
 *
 * [2799] 统计完全子数组的数目
 */

记count为窗口[left, right)中不同元素的个数, 则有:
    1. left固定时, 右移right则count不变或增大
    2. right固定时, 右移left则count不变或减小

// @lc code=start
class Solution {
    public int countCompleteSubarrays(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            set.add(num);
        }
        int k = set.size();
        return lessThan(nums, k + 1) - lessThan(nums, k);
    }

    // 求不同元素数量小于k的子数组的个数
    private int lessThan(int[] nums, int k) {
        // 窗口数量问题: 滑动窗口[left, right)
        int left = 0, right = 0, n = nums.length, ans = 0;
        // 定义条件指标: 当前窗口中不同元素的个数
        int count = 0;
        int[] counter = new int[2001];
        // 滑动
        while(right < n) {
            // 移动left直到恰好满足要求
            while(left < right) {
                if(count < k)
                    break;
                if(--counter[nums[left]] == 0)
                    count--;
                left++;
            }
            // 累计满足要求的区间个数
            ans += right - left;
            // 移动right
            if(++counter[nums[right]] == 1)
                count++;
            right++;
        }
        // 末尾特殊处理, 此时right == n
        while(left < right) {
            if(count < k)
                break;
            if(--counter[nums[left]] == 0)
                count--;
            left++;
        }
        ans += right - left;
        return ans;
    }
}
// @lc code=end

