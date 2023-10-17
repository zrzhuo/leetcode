/*
 * @lc app=leetcode.cn id=1024 lang=java
 *
 * [1024] 视频拼接
 */


// 类似于 55.跳跃游戏
// @lc code=start
class Solution {
    public int videoStitching(int[][] clips, int time) {
        // 统计每个起始点能到达的最远位置
        int[] dist = new int[time + 1];
        for(int[] clip : clips) {
            int s = clip[0], t = clip[1];
            if(s <= time) {
                dist[s] = Math.max(dist[s], t);
            }
        }
        // 补充其他起始点能到达的最远位置
        int fathest = 0;
        for(int i = 0; i <= time; i++) {
            if(fathest < i) {
                return -1; // 到达不了当前位置，无法进行拼接
            }
            fathest = Math.max(fathest, dist[i]);
            dist[i] = fathest;
        }
        // 求至少跳几步可以到达time位置
        int res = 0;
        int curr = 0;
        while(curr < time) {
            curr = dist[curr];
            res++;
        }
        return res;
    }
}
// @lc code=end

