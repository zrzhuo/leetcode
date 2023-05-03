/*
 * @lc app=leetcode.cn id=406 lang=java
 *
 * [406] 根据身高重建队列
 */

// @lc code=start
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        // 将数组按h从小到大, 再按k从大到小进行排序
        Arrays.sort(people, (a, b) -> {
            if(a[0] != b[0])
                return a[0] - b[0];
            return b[1] - a[1];
        });
        int[][] result = new int[n][];
        // 枚举每个元素
        for(int[] person : people) {
            int k = person[1];
            for(int i = 0; i < n; i++) {
                // 只考虑空位置
                if(result[i] == null) {
                    // 跳过k个空位置
                    k--;
                    if(k == -1) {
                        result[i] = person;
                        break;
                    }
                }
            }
        }
        return result;
    }
}
// @lc code=end

