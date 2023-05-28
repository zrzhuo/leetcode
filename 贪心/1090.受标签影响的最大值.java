/*
 * @lc app=leetcode.cn id=1090 lang=java
 *
 * [1090] 受标签影响的最大值
 */

// @lc code=start
class Solution {
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        int[][] vl = new int[n][2];
        for(int i = 0; i < n; i++) {
            vl[i][0] = values[i];
            vl[i][1] = labels[i];
        }
        // 贪心：优先选择value值较大的项
        Arrays.sort(vl, (a, b) -> b[0] - a[0]);
        int result = 0, count = 0; 
        Map<Integer, Integer> used = new HashMap<>(); // 记录value值已经选择的次数
        for(int i = 0; i < n; i++) {
            if(count == numWanted)
                break; // 数量已经达到numWanted，退出
            int value = vl[i][0], lable = vl[i][1];
            int repeat = used.getOrDefault(lable, 0);
            if(repeat < useLimit) {
                result += value;
                count++;
                used.put(lable, repeat + 1);
            } 
        }
        return result;
    }
}
// @lc code=end

