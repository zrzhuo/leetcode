/*
 * @lc app=leetcode.cn id=763 lang=java
 *
 * [763] 划分字母区间
 */

// @lc code=start
class Solution {
    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        // 统计各字母出现的次数
        int[] counter = new int[26];
        for(int i = 0; i < n; i++) {
            counter[s.charAt(i) - 'a']++;
        }
        List<Integer> result = new ArrayList<>();
        int count = 0; // 当前片段需要纳入的剩余字母数
        int len = 0; // 当前片段的长度
        for(int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            // 遇到新的字母, 将该字母加入目标字母
            if(counter[ch] > 0) {
                count += counter[ch];
                counter[ch] = 0;
            }
            // 纳入当前字母
            count--; 
            len++;
            // 目标字母全部纳入完毕
            if(count == 0) {
                result.add(len);
                len = 0;
            }
        }
        return result;
    }
}
// @lc code=end

