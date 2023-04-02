/*
 * @lc app=leetcode.cn id=139 lang=java
 *
 * [139] 单词拆分
 */


class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        // dp[i]: s[0...i - 1]是否可以被拼接出
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        // 递推
        for(int i = 1; i < n + 1; ++i) {
            // 枚举所有单词
            for(String word: wordDict) {
                int len = word.length();
                // 向前看一个单词的长度
                if(i - len >= 0 && dp[i - len] && s.substring(i - len, i).equals(word)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}

// @lc code=start
// 记忆化搜索
class Solution {
    int[] solved; // solved[i]: s[0...i - 1]是否可以被拼接出
    boolean solving(String s, List<String> wordDict, int i) {
        // 获取记忆
        if(solved[i] != -1)
            return solved[i] == 1;
        // 枚举所有单词
        for(String word : wordDict) {
            int len = word.length();
            if(i - len >= 0 && solving(s, wordDict, i - len) && s.substring(i - len, i).equals(word)) {
                solved[i] = 1; // 存储记忆
                return true;
            }
        }
        solved[i] = 0; // 存储记忆
        return false;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        solved = new int[n + 1];
        Arrays.fill(solved, -1);
        solved[0] = 1; // 初始化
        return solving(s, wordDict, n);
    }
}
// @lc code=end
