/*
 * @lc app=leetcode.cn id=890 lang=java
 *
 * [890] 查找和替换模式
 */

// @lc code=start
class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        for(String word : words) {
            if(check(word, pattern))
                result.add(word);
        }
        return result;
    }

    boolean check(String word, String pattern) {
        int[] cntW = new int[26];
        int[] cntP = new int[26];
        for(int i = 0; i < word.length(); i++) {
            int chw = word.charAt(i) - 'a';
            int chp = pattern.charAt(i) - 'a';
            if(cntW[chw] == 0 && cntP[chp] == 0) {
                // 映射：chw和chp通过i+1形成映射
                cntW[chw] = i + 1;
                cntP[chp] = i + 1;
            } else {
                // 判断chw和chp是否正确映射
                if(cntW[chw] != cntP[chp])
                    return false;
            }
        }
        return true;
    }
}
// @lc code=end

