/*
 * @lc app=leetcode.cn id=932 lang=java
 *
 * [932] 漂亮数组
 */

// @lc code=start
// 分治+记忆化：https://leetcode.cn/problems/beautiful-array/solutions/18740/piao-liang-shu-zu-by-leetcode/
class Solution {
    Map<Integer, int[]> solved = new HashMap<>();
    int[] solving(int n) {
        // 获取记忆
        if(solved.containsKey(n)) {
            return solved.get(n);
        }
        int[] left = solving((n + 1) / 2); // 分治前半部
        int[] right = solving(n / 2); // 分治前半部
        int[] result = new int[n];
        int k = 0;
        for(int i = 0; i < left.length; i++) {
            result[k++] = left[i] * 2 - 1; // 仿射变换
        }
        for(int i = 0; i < right.length; i++) {
            result[k++] = right[i] * 2; // 仿射变换
        }
        // 存储记忆
        solved.put(n, result);
        return result;
    }

    public int[] beautifulArray(int n) {
       // 递归出口
       solved.put(1, new int[]{1});
       return solving(n);
    }
}
// @lc code=end

