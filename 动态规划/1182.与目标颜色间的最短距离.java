/*
 * @lc app=leetcode.cn id=1182 lang=java
 *
 * [1182] 与目标颜色间的最短距离
 */

// @lc code=start
class Solution {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        int n = colors.length, inf = Integer.MAX_VALUE;
        // left
        int[][] left = new int[3][n];
        left[0][0] = colors[0] == 1 ? 0 : inf;
        left[1][0] = colors[0] == 2 ? 0 : inf;
        left[2][0] = colors[0] == 3 ? 0 : inf;
        for (int i = 1; i < n; i++) {
            left[0][i] = left[0][i - 1] != inf ? left[0][i - 1] + 1 : inf;
            left[1][i] = left[1][i - 1] != inf ? left[1][i - 1] + 1 : inf;
            left[2][i] = left[2][i - 1] != inf ? left[2][i - 1] + 1 : inf;
            left[colors[i] - 1][i] = 0;
        }
        // right
        int[][] right = new int[3][n];
        right[0][n - 1] = colors[n - 1] == 1 ? 0 : inf;
        right[1][n - 1] = colors[n - 1] == 2 ? 0 : inf;
        right[2][n - 1] = colors[n - 1] == 3 ? 0 : inf;
        for (int i = n - 2; i >= 0; i--) {
            right[0][i] = right[0][i + 1] != inf ? right[0][i + 1] + 1 : inf;
            right[1][i] = right[1][i + 1] != inf ? right[1][i + 1] + 1 : inf;
            right[2][i] = right[2][i + 1] != inf ? right[2][i + 1] + 1 : inf;
            right[colors[i] - 1][i] = 0;
        }
        // ans
        List<Integer> ans = new ArrayList<>(queries.length);
        for (int[] query : queries) {
            int idx = query[0], color = query[1] - 1;
            int dis = Math.min(left[color][idx], right[color][idx]);
            if (dis == inf)
                ans.add(-1);
            else
                ans.add(dis);
        }
        return ans;
    }
}
// @lc code=end
