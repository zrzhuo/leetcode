/*
 * @lc app=leetcode.cn id=1130 lang=java
 *
 * [1130] 叶值的最小代价生成树
 */

class Solution {
    int[][] max; // max[i][j]: arr[i...j]中的最大值
    int[][] solved; // solved[i][j]: arr[i...j]构成的树的最小代价
    int solving(int[] arr, int i, int j) {
        if(solved[i][j] != Integer.MAX_VALUE)
            return solved[i][j];
        if(i == j)
            return 0; // 单个结点构成的树不存在非叶结点, 故代价为0
        int result = Integer.MAX_VALUE;
        for(int k = i; k < j; k++) {
            // arr[i...k]构成左子树, arr[k+1...j]构成右子树, 而父结点的值为左子树中最大叶结点与右子树最大叶结点的成绩, 即max[i][k] * max[k + 1][j]
            int sum = solving(arr, i, k) + solving(arr, k + 1, j) + max[i][k] * max[k + 1][j];
            result = Math.min(result, sum);
        }
        solved[i][j]= result;
        return result;
    }
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        max = new int[n][n];
        for(int i = 0; i < n; i++) {
            max[i][i] = arr[i];
            for(int j = i + 1; j < n; j++) {
                max[i][j] = Math.max(max[i][j - 1], arr[j]);
            }
        }
        solved = new int[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(solved[i], Integer.MAX_VALUE);
        }
        return solving(arr, 0, n - 1);
    }
}


// @lc code=start
class Solution {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        // max[i][j]: arr[i...j]中的最大值
        int[][] max = new int[n][n];
        for(int i = 0; i < n; i++) {
            max[i][i] = arr[i];
            for(int j = i + 1; j < n; j++) {
                max[i][j] = Math.max(max[i][j - 1], arr[j]);
            }
        }
        // dp[i][j]: arr[i...j]构成的树的最小代价
        int[][] dp = new int[n][n];
        // 初始化
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }
        // 递推
        for(int i = n - 1; i >=0; i--) {
            for(int j = i; j < n; j++) {
                for(int k = i; k < j; k++) {
                    int sum = dp[i][k] + dp[k + 1][j] + max[i][k] * max[k + 1][j];
                    dp[i][j] = Math.min(dp[i][j], sum);
                }
            }
        }
        return dp[0][n - 1];
    }
}
// @lc code=end
