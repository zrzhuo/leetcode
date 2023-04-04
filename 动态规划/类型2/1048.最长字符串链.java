/*
 * @lc app=leetcode.cn id=1048 lang=java
 *
 * [1048] 最长字符串链
 */

// @lc code=start
class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (String a, String b) -> {
            return a.length() - b.length();
        });
        int n = words.length;
        // dp[i]: 以words[i]结尾的最长字符串链的长度
        int[] dp = new int[n];
        // 初始化
        Arrays.fill(dp, 1);
        // 递推
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                // 当word[j]是word[i]的前身时
                if(check(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); // 更新最大值
                }
            }
        }
        // 获取结果
        int result = 0;
        for(int i = 0; i < n; i++)
            result = Math.max(result, dp[i]);
        return result;
    }

    // 判断word1是否是word2的前身
    boolean check(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if(m != n - 1)
            return false;
        int i = 0, j = 0;
        while(i < m && j < n) {
            if(word1.charAt(i) == word2.charAt(j))
                i++;
            j++;
        }
        return i == m;
    }
}
// @lc code=end

