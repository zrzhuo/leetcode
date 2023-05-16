/*
 * @lc app=leetcode.cn id=66 lang=java
 *
 * [66] 加一
 */

// @lc code=start
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        // 最后一位加1
        digits[n - 1]++;
        for(int i = n - 1; i > 0; i--) {
            digits[i - 1] += digits[i] / 10;
            digits[i] %= 10;
        }
        int carry = digits[0] / 10; // 最终进位
        digits[0] %= 10;
        // 获取结果
        if(carry == 0) {
            return digits;
        }
        int[] result = new int[n + 1];
        result[0] = carry;
        System.arraycopy(digits, 0, result, 1, n);
        return result;
    }
}
// @lc code=end

