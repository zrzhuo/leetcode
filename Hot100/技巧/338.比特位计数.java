/*
 * @lc app=leetcode.cn id=338 lang=java
 *
 * [338] 比特位计数
 */

// @lc code=start
class Solution {
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        result[0] = 0;
        for(int i = 1; i <= n; i++) {
            if(i % 2 == 0)
                result[i] = result[i / 2]; // i/2左移一位，即可得到i，故i的1个数与i/2的相等
            else
                result[i] = result[i / 2] + 1; // i/2左移一位，末位再加1，得到i, 故i的1个数比i/2多1
        }
        return result;
    }
}
// @lc code=end

0 000 0
1 001 1
2 010 1
3 011 2
4 100 1
5 101 2
6 110 2
7 111 3
