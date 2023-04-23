/*
 * @lc app=leetcode.cn id=56 lang=java
 *
 * [56] 合并区间
 */

// @lc code=start
class Solution {
    public int[][] merge(int[][] intervals) {
        // 排序
        Arrays.sort(intervals, (int[] a, int[] b) -> {
            if(a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });
        List<int[]> list = new ArrayList<>();
        // 当前区间为[left, right]
        int left = intervals[0][0];
        int right = intervals[0][1];
        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] <= right) {
                // 合并区间
                right = Math.max(right, intervals[i][1]); 
            } else {
                // 记录区间
                list.add(new int[]{left, right});
                // 重置区间
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }
        // 处理末尾
        list.add(new int[]{left, right});
        // 获取结果
        int[][] result = new int[list.size()][2];
        int i = 0;
        for(int[] interval : list) {
            result[i][0] = interval[0];
            result[i][1] = interval[1];
            i++;
        }
        return result;
    }
}
// @lc code=end

