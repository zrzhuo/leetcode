/*
 * @lc app=leetcode.cn id=394 lang=java
 *
 * [394] 字符串解码
 */

// @lc code=start
class Solution {
    public String decodeString(String s) {
        Deque<Integer> numStack = new LinkedList<>();
        Deque<StringBuilder> strStack = new LinkedList<>(); 
        StringBuilder str = new StringBuilder(); // str记录某个方括号内的实际字符串
        int num = 0; // num记录某个方括号的重复次数
        for(int i  = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(Character.isLetter(ch)) {
                str.append(ch); // 更新str
            }
            else if(Character.isDigit(ch)) {
                num = num * 10 + ch - '0'; // 更新num
            }
            // 此时, 遇到一对新的括号
            else if(ch == '[') {
                numStack.push(num); // 该对括号的重复次数已经求解出, 入栈
                num = 0; // 将num置为0, 以便记录下一对括号的重复次数
                strStack.push(str); // 将上一对括号的实际字符串(可能仍未完全解出)记录到栈内
                str = new StringBuilder(); // 将str重置, 以便记录当前括号的实际字符串
            }
            // 此时, 当前括号的实际字符串已经求解完毕, 将状态恢复到上一层括号
            else if(ch == ']') {
                int n = numStack.pop(); // 从栈顶取出当前括号的重复次数
                StringBuilder pre = strStack.pop(); // 从栈顶取出上个括号的实际字符串
                for(int k = 0; k < n; k++) // 将当前括号的实际字符串重复n次, 挂在pre后面
                    pre.append(str);
                str = pre; // 将str恢复为pre
            }
        }
        return str.toString();
    }   
}
// @lc code=end