/*
 * @lc app=leetcode.cn id=435 lang=java
 *
 * [435] 无重叠区间
 */

相似题: 646.最长数对链

// @lc code=start
// 贪心: O(nlogn)
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        // 按右端点从小到大进行排序
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int result = 1, n = intervals.length;
        int right = intervals[0][1]; // 当前右端点
        for(int i = 1; i < n; i++) {
            // 贪心的选择后继数对，并更新right
            if(intervals[i][0] >= right) {
                result++;
                right = intervals[i][1];
            }
        }
        return n - result;
    }
}
// @lc code=end


// 动态规划: O(n^2), 超时
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        // 按左端点从小到大排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int n = intervals.length;
        // dp[i]: 以pairs[i]结尾的数对链的长度
        int[] dp = new int[n];
        // 初始化
        Arrays.fill(dp, 1); 
        // 递推
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                // 数对i可以跟在数对j的后面时
                if(intervals[j][1] <= intervals[i][0])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        // 获取结果
        int result = 0;
        for(int i = 0; i < n; i++) {
            result = Math.max(result, dp[i]);
        }
        return n - result;
    }
}