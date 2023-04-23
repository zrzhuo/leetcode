/*
 * @lc app=leetcode.cn id=42 lang=java
 *
 * [42] 接雨水
 */

// @lc code=start
// 动态规划
class Solution {
    public int trap(int[] height) {
        // 位置i能接到的雨水，取决于其两侧最高柱子中的较小值
        int n = height.length;
        // left[i]: i位置左侧最高柱子
        // right[i]: i位置右侧最高柱子
        int[] left = new int[n], right = new int[n];
        for(int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i - 1]);
        }
        for(int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i + 1]);
        }
        // 获取结果
        int count = 0;
        for(int i = 0; i < n; i++) {
            int min = Math.min(left[i], right[i]);
            if(min > height[i])
                count += min - height[i];
        }
        return count;
    }
}
// @lc code=end

// 双指针
class Solution {
    public int trap(int[] height) {
        // 位置i能接到的雨水，取决于其两侧最高柱子中的较小值
        int n = height.length;
        int left = 0, right = n - 1;
        while(left < right)
    }
}