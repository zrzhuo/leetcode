/*
 * @lc app=leetcode.cn id=140 lang=java
 *
 * [140] 单词拆分 II
 */

// @lc code=start
class Solution {
    List<String>[] solved; // solved[i]: s[0...i-1]可以构成的句子
    List<String> solving(String s, List<String> wordDict, int i) {
        // 获取记忆
        if(solved[i] != null)
            return solved[i];
        List<String> result = new ArrayList<>();
        // 枚举所有单词
        for(String word : wordDict) {
            int len = word.length();
            // 匹配该单词
            if(i - len >= 0 && s.substring(i - len, i).equals(word)){
                List<String> sentences = solving(s, wordDict, i - len); // 求解s[0...i-len]
                // 每个句子后面添加word
                for(String sentence : sentences) {
                    if(sentence.length() > 0)
                        sentence += " ";
                    sentence += word;
                    result.add(sentence);
                }
            }
        }
        solved[i] = result; // 存储记忆
        return result;
    }
    public List<String> wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        solved = new ArrayList[n + 1];
        // 初始化
        solved[0] = new ArrayList<>();
        solved[0].add(""); 
        return solving(s, wordDict, n);
    }
}
// @lc code=end

