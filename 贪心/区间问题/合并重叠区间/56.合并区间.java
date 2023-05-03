/*
 * @lc app=leetcode.cn id=56 lang=java
 *
 * [56] 合并区间
 */

// @lc code=start
class Solution {
    public int[][] merge(int[][] intervals) {
        // 按左端点从小到大进行排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> result = new ArrayList<>();
        // 当前区间为[left, right]
        int left = intervals[0][0];
        int right = intervals[0][1];
        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] <= right) {
                // 合并区间
                left = Math.min(left, intervals[i][0]);
                right = Math.max(right, intervals[i][1]); 
            } else {
                result.add(new int[]{left, right});
                // 重置区间
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }
        // 处理末尾
        result.add(new int[]{left, right});
        // 获取结果
        return result.toArray(new int[result.size()][]);
    }
}
// @lc code=end

