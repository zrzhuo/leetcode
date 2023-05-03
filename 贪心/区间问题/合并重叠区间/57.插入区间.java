/*
 * @lc app=leetcode.cn id=57 lang=java
 *
 * [57] 插入区间
 */


// @lc code=start
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int idx = binarySearch(intervals, newInterval[0]); // 二分查找newInterval应该插入的位置
        // 构建新的区间数组
        int[][] inter = new int[n + 1][];
        System.arraycopy(intervals, 0, inter, 0, idx);
        inter[idx] = newInterval;
        System.arraycopy(intervals, idx, inter, idx + 1, n - idx);
        // 合并重叠区间
        return merge(inter);
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

    int binarySearch(int[][] intervals, int target) {
        int left = 0, right = intervals.length;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(intervals[mid][0] >= target) 
                right = mid;
            else
                left = mid + 1;
        }
        return right;
    }
}
// @lc code=end


class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        List<int[]> result = new ArrayList<>();
        // 当前区间为[left, right]
        int left = newInterval[0];
        int right = newInterval[1];
        boolean flag = false; // 新合并的区间只有一个, 故用一个标记表示新合并的区间是否已经插入
        for(int[] interval : intervals) {
            // 当前区间在新区间的左侧且无交集
            if(interval[1] < left) {
                result.add(interval);
            } 
            // 当前区间在新区间的右侧且无交集
            else if(interval[0] > right) {
                // 新合并的区间未插入
                if(flag == false) {
                    result.add(new int[]{left, right});
                    flag = true;
                }
                result.add(interval);
            }
            // 当前区间与新区间有交集, 合并 
            else {
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        // 新合并的区间未插入
        if(flag == false)
            result.add(new int[]{left, right});
        return result.toArray(new int[result.size()][]);
    }
}
