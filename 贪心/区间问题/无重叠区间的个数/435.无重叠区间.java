/*
 * @lc app=leetcode.cn id=435 lang=java
 *
 * [435] 无重叠区间
 */


// @lc code=start
// 贪心: O(nlogn)
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        // 按右端点从小到大进行排序
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int count = 1;
        int right = intervals[0][1]; // 当前右端点
        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] >= right) {
                right = intervals[i][1];
                count++;
            }
        }
        // 需要删除的区间数 = 区间总数 - 无重叠区间的个数
        return intervals.length - count;
    }
}
// @lc code=end
