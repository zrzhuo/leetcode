/*
 * @lc app=leetcode.cn id=452 lang=java
 *
 * [452] 用最少数量的箭引爆气球
 */

// @lc code=start
class Solution {
    public int findMinArrowShots(int[][] points) {
        // 按右端点从小到大进行排序
        Arrays.sort(points, (a, b) -> {
            if(a[1] < b[1]) // 注意防止溢出
                return -1;
            else if(a[1] > b[1])
                return 1;
            else
                return 0;
        });
        int count = 1;
        int right = points[0][1]; // 当前右端点
        for(int i = 1; i < points.length; i++) {
            if(points[i][0] > right) {
                right = points[i][1];
                count++;
            } 
        }
        return count;
    }
}
// @lc code=end
