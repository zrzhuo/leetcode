/*
 * @lc app=leetcode.cn id=136 lang=java
 *
 * [136] 只出现一次的数字
 */

// @lc code=start
class Solution {
    public int singleNumber(int[] nums) {
        int xor = 0;
        for(int num : nums) {
            xor ^= num;
        }
        return xor;
    }
}
// @lc code=end

异或的性质: 两个整数a和b
1. a = b 时, a ^ b = 0
2. a = 0 时, a ^ b = b