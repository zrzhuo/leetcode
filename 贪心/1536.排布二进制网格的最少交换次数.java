/*
 * @lc app=leetcode.cn id=1536 lang=java
 *
 * [1536] 排布二进制网格的最少交换次数
 */

// @lc code=start
class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        // 统计每一行右侧0的个数
        int[] zero = new int[n];
        for(int i = 0; i < n; i++) {
            for(int j = n - 1; j >= 0; j--) {
                if(grid[i][j] == 0)
                    zero[i]++;
                else
                    break;
            }
        }
        int result = 0;
        for(int i = 0; i < n; i++) {
            // 当前行0的个数不满足要求时, 进行交换
            if(zero[i] < n - 1 - i) {
                // 贪心: 寻找后面第一个满足要求的行，进行交换
                for(int j = i + 1; j < n; j++) {
                    if(zero[j] >= n - 1 - i) {
                        // 依次交换相邻行
                        for(int k = j; k > i; k--) {
                            int temp = zero[k];
                            zero[k] = zero[k - 1];
                            zero[k - 1] = temp;
                        }
                        result += j - i; // 累计交换次数
                        break;
                    }
                }
                // 不存在有效方案
                if(zero[i] < n - 1 - i)
                    return -1;
            }
        }
        return result;
    }
}
// @lc code=end

