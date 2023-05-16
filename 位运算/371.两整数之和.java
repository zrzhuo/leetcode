/*
 * @lc app=leetcode.cn id=371 lang=java
 *
 * [371] 两整数之和
 */

// 递归
class Solution {
    public int getSum(int a, int b) {
        if(b == 0)
            return a;
        int sum = a ^ b;  
        int carry = (a & b) << 1;
        return getSum(sum, carry);
    }
}

// @lc code=start
// 迭代
class Solution {
    public int getSum(int a, int b) {
        int sum = a;
        int carry = b;
        while (carry != 0) {
            int newSum = sum ^ carry;
            int newCarry = (sum & carry) << 1;
            sum = newSum;
            carry = newCarry;
        }
        return sum;
    }
}
// @lc code=end


对与二进制整数a和b, 有:
    a + b = (a ^ b) + ((a & b) << 1)

以 11 + 15 = 26 为例:
     1011   a
     1111   b
    -----
       10
      10   
     01
    10   
    -----
    11010   a + b
    -----
     0100   (a ^ b)
    1011    (a & b) << 1
    -----
    11010   (a ^ b) + ((a & b) << 1)






