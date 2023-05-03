/*
 * @lc app=leetcode.cn id=646 lang=java
 *
 * [646] 最长数对链
 */


// @lc code=start
// 贪心: O(nlogn)
class Solution {
    public int findLongestChain(int[][] pairs) {
        // 按右端点从小到大进行排序
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int count = 1;
        int right = pairs[0][1]; // 当前右端点
        for(int i = 1; i < pairs.length; i++) {
            if(pairs[i][0] > right) {
                right = pairs[i][1];
                count++;
            }
        }
        return count;
    }
}
// @lc code=end
