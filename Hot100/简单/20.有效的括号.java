/*
 * @lc app=leetcode.cn id=20 lang=java
 *
 * [20] 有效的括号
 */

// @lc code=start
class Solution {
    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Deque<Character> stack = new LinkedList<>();
        for(int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if(cur == '(' || cur == '[' || cur == '{') {
                stack.push(cur);
            } else {
                if(stack.isEmpty() || stack.pop() != map.get(cur))
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
// @lc code=end

