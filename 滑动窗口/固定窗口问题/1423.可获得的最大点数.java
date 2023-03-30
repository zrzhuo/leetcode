/*
 * @lc app=leetcode.cn id=1423 lang=java
 *
 * [1423] 可获得的最大点数
 */

// @lc code=start
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int total = 0, n = cardPoints.length;
        for(int i = 0; i < n; ++i)
            total += cardPoints[i];
        // 固定窗口问题
        int left = 0, right = 0, min = total;
        int sum = 0; // 窗口内数字之和
        // 窗口初始化
        while(right < n - k) {
            sum += cardPoints[right];
            right++;
        }
        min = Math.min(min, sum);
        // 滑动
        while(right < n) {
            sum -= cardPoints[left++];
            sum += cardPoints[right++];
            min = Math.min(min, sum);
        }
        return total - min;
    }
}
// @lc code=end

