/*
 * @lc app=leetcode.cn id=67 lang=java
 *
 * [67] 二进制求和
 */

// @lc code=start
// 与 415.字符串相加 相似, 只有十进制和二进制的差别
class Solution {
    public String addBinary(String num1, String num2) {
        StringBuffer sb = new StringBuffer();
        int i = num1.length() - 1, j = num2.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int curr = x + y + carry;
            sb.append(curr % 2);
            carry = curr / 2;
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

