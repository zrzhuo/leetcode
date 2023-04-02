/*
 * @lc app=leetcode.cn id=241 lang=java
 *
 * [241] 为运算表达式设计优先级
 */

// @lc code=start
class Solution {
    // solved[left][right]: expression[left...ritgh]所有可能得值
    List<Integer>[][] solved;
    List<Integer> solving(String expression, int left, int right) {
        // 获取记忆
        if(solved[left][right] != null)
            return solved[left][right];
        List<Integer> result = new ArrayList<>();
        // 枚举每一个运算符
        for(int i = left; i <= right; ++i) {
            char c = expression.charAt(i);
            if(Character.isDigit(c))
                continue;
            List<Integer> leftVals = solving(expression, left, i - 1); // 左侧所有可能值
            List<Integer> rightVals = solving(expression, i + 1, right); // 右侧所有可能值
            for(int a : leftVals) {
                for(int b : rightVals) {
                    if(c == '+')
                        result.add(a + b);
                    else if(c == '-')
                        result.add(a - b);
                    else
                        result.add(a * b);
                }
            } 
        }
        // 若result为空，说明[left, right]区间内没有运算符，只有单独的一个数，此时应将该数纳入result
        if(result.isEmpty()){
            int num = Integer.parseInt(expression.substring(left, right + 1));
            result.add(num);
        }
        // 存储记忆
        solved[left][right] = result;
        return result;
    }

    public List<Integer> diffWaysToCompute(String expression) {
        int n = expression.length();
        solved = new ArrayList[n][n];
        return solving(expression, 0, n - 1);
    }
}
// @lc code=end

