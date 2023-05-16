/*
 * @lc app=leetcode.cn id=43 lang=java
 *
 * [43] 字符串相乘
 */

// @lc code=start
// 朴素解法
class Solution {
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0"))
            return "0";
        int m = num1.length(), n = num2.length();
        // m位数和n位数相乘, 结果为m+n位或m+n-1位
        int len = m + n;
        int[] array = new int[len]; 
        // 不进位乘法, 时间复杂度为O(m * n)
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int curr = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
                array[i + j + 1] += curr; 
            }
        }
        // 进位
        for(int i = len - 1; i > 0; i--) {
            array[i - 1] += array[i] / 10; 
            array[i] %= 10;
        }
        // 转换为字符串
        StringBuilder result = new StringBuilder();
        if(array[0] > 0)
            result.append(array[0]); // 最高位不为0
        for(int i = 1; i < len; i++) {
            result.append(array[i]);
        }
        return result.toString();
    }
}
// @lc code=end

不进位乘法, 以 234 * 456 = 106704 为例:
                 0   1   2
    ********************** 字符串下标
                 2   3   4
                 4   5   6
    ---------------------- 每位相乘, 但不为进位
                12  18  24
            10  15  20 
         8  12  16
    ---------------------- 每位相加
         8  22  43  38  24
    ---------------------- 进位, 得到最后结果
     1   0   6   7   0   4
    ********************** 数组下标
     0   1   2   3   4   5



// 朴素解法
class Solution {
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0"))
            return "0";
        // 区分长数和短数, 可降低时间复杂度
        String big = num1, small = num2;
        if(num1.length() < num2.length()) {
            big = num2;
            small = num1;
        }
        int m = big.length(), n = small.length();
        String result = "";
        // 时间复杂度为O(mn + n^2)
        for(int i = 0; i < n; i++) {
            result += '0';
            result = add(result, mul(big, small.charAt(i))); // 时间复杂度为O(m + n) 
        }
        return result;
    }

    // 计算num * c, 其中, 0 <= c <= 9, 时间复杂度为O(n)
    String mul(String num, char c) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for(int i = num.length() - 1; i >= 0; i--) {
            int curr = (num.charAt(i) - '0') * (c - '0') + carry;
            sb.append(curr % 10);
            carry = curr / 10;
        }
        if(carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    // 计算num1 + num2, 时间复杂度为O(m + n)
    String add(String num1, String num2) {
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