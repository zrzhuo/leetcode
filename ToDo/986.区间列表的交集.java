/*
 * @lc app=leetcode.cn id=986 lang=java
 *
 * [986] 区间列表的交集
 */

// @lc code=start
class Solution {
    public int[][] intervalIntersection(int[][] first, int[][] second) {
        List<int[]> result = new ArrayList<>();
        int i = 0, j = 0;
        while(i < first.length && j < second.length) {
            int left = Math.max(first[i][0], second[j][0]);
            int right = Math.min(first[i][1], second[j][1]);
            // ???
            if(left <= right)
                result.add(new int[]{left, right});
            // ???
            if(first[i][1] < second[j][1])
                i++;
            else
                j++;
        }
        return result.toArray(new int[result.size()][]);
    }
}
// @lc code=end

