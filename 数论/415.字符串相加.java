/*
 * @lc app=leetcode.cn id=415 lang=java
 *
 * [415] 字符串相加
 */

// @lc code=start
class Solution {
    public String addStrings(String num1, String num2) {
        StringBuffer sb = new StringBuffer();
        int i = num1.length() - 1, j = num2.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int curr = x + y + carry;
            sb.append(curr % 10);
            carry = curr / 10;
            i--;
            j--;
        }
        if(carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
// @lc code=end

