/*
 * @lc app=leetcode.cn id=11 lang=java
 *
 * [11] 盛最多水的容器
 */

// @lc code=start
class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        int max = 0;
        int left = 0, right = n - 1;
        while(left < right) {
            int now = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(max, now);
            if(height[left] < height[right])
                left++; // 此时, 以left为左边界的所有区间的面积, 都不可能比now大, 故通过left++, 使这些区间被排除
            else
                right--; // 此时, 以right为右边界的所有区间的面积, 都不可能比now大, 故通过right++, 使这些区间被排除
        }
        return max;
    }
}
// @lc code=end

