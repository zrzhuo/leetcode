/*
 * @lc app=leetcode.cn id=495 lang=java
 *
 * [495] 提莫攻击
 */

// @lc code=start
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int n = timeSeries.length;
        // 区间化
        int[][] intervals = new int[n][2];
        for(int i = 0; i < n; i++) {
            intervals[i][0] = timeSeries[i];
            intervals[i][1] = timeSeries[i] + duration - 1; 
        }
        // 合并区间
        int[][] merged = merge(intervals);
        // 获取总和
        int count = 0;
        for(int[] interval : merged) {
            count += interval[1] - interval[0] + 1;
        }
        return count;
    }
    public int[][] merge(int[][] intervals) {
        // 按左端点从小到大进行排序
        // Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
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

class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int count = 0;
        int start = 0, end = 0; // 当前中毒状态的开始时间和结束时间
        for(int i = 0; i < timeSeries.length; i++) {
            // 本次攻击时, 未处于中毒状态
            if(timeSeries[i] > end) {
                count += end - start; // 结算上次中毒状态的持续时长
                start = timeSeries[i];
                end = timeSeries[i] + duration;
            }
            // 本次攻击时, 已经处于中毒状态
            else {
                start = start
                end = timeSeries[i] + duration; // 延长中毒状态结束时间
            }
        }
        count += end - start;
        return count;
    }
}