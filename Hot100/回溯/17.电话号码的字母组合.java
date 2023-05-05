/*
 * @lc app=leetcode.cn id=17 lang=java
 *
 * [17] 电话号码的字母组合
 */

// @lc code=start
class Solution {
    String[] letterMap = {"", "", "abc", "def", "ghi", "jkl", "mno","pqrs", "tuv", "wxyz"};
    List<String> result;
    LinkedList<Character> temp;

    void backTrack(String digits, int index) {
        if(index == digits.length()) {
            StringBuilder sb = new StringBuilder();
            for(Character ch : temp)
                sb.append(ch);
            result.add(sb.toString());
            return;
        }
        // 枚举当前键对应的所有字母
        String letters = letterMap[digits.charAt(index) - '0'];
        for(int i = 0; i < letters.length(); ++i) {
            temp.addLast(letters.charAt(i));
            backTrack(digits, index + 1); // 递归选择下一个字母
            temp.removeLast();
        }
    }

    public List<String> letterCombinations(String digits) {
        result = new ArrayList<>();
        temp = new LinkedList<>();
        if(digits.length() == 0)  
            return result;
        backTrack(digits, 0);
        return result;
    }
}
// @lc code=end

