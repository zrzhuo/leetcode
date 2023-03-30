/*
 * @lc app=leetcode.cn id=904 lang=java
 *
 * [904] 水果成篮
 */

// @lc code=start
class Solution {
    public int totalFruit(int[] fruits) {
        // 最大窗口问题: 滑动窗口[left, right)
        int left = 0, right = 0, n = fruits.length, ans = 0;
        // 定义条件指标: 当前窗口中的水果种类数
        int count = 0;
        int[] counter = new int[n];
        // 滑动
        while(right < n) {
            // 移动left直到恰好满足条件
            while(left < right) {
                if(count <= 2)
                    break;
                if(--counter[fruits[left]] == 0)
                    count--;
                left++;
            }
            // 移动right直到恰好不满足条件
            while(right < n) {
                if(count > 2)
                    break;
                if(++counter[fruits[right]] == 1)
                    count++;
                right++;
            }
            // 当前满足条件的窗口为[left, right - 1)
            ans = Math.max(ans, right - 1 - left);
        }
        // 末尾特殊处理
        if(count <= 2)
            ans = Math.max(ans, n - left);
        return ans;
    }
}
// @lc code=end
