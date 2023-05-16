/*
 * @lc app=leetcode.cn id=22 lang=java
 *
 * [22] 括号生成
 */

// @lc code=start
class Solution {
    List<String> result = new ArrayList<>();
    StringBuilder temp = new StringBuilder();
    void backTrack(int n, int left, int right) {
        if(left == n && right == n) {
            result.add(temp.toString());
            return;
        }
        // 当前括号序列不合法
        if(left < right || left > n || right > n) {
            return;
        }
        // 尝试添加(
        temp.append('(');
        backTrack(n, left + 1, right);
        temp.deleteCharAt(temp.length() - 1);
        // 尝试添加)
        temp.append(')');
        backTrack(n, left, right + 1);
        temp.deleteCharAt(temp.length() - 1);
    }
    public List<String> generateParenthesis(int n) {
        backTrack(n, 0, 0);
        return result;
    }
}
// @lc code=end

