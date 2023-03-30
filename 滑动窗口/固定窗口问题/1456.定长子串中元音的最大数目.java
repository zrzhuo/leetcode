/*
 * @lc app=leetcode.cn id=1456 lang=java
 *
 * [1456] 定长子串中元音的最大数目
 */

// @lc code=start
class Solution {
    public int maxVowels(String s, int k) {
        // 固定窗口问题
        int left = 0, right = 0, n = s.length(), ans = 0;
        int count = 0; // 当前窗口内的元音字母的个数
        // 窗口初始化
        while(right < k) {
            char c = s.charAt(right);
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                count++;
            right++;
        }
        ans = Math.max(ans, count);
        while(right < n) {
            char l = s.charAt(left), r = s.charAt(right);
            if(l == 'a' || l == 'e' || l == 'i' || l == 'o' || l == 'u')
                count--;
            left++;
            if(r == 'a' || r == 'e' || r == 'i' || r == 'o' || r == 'u')
                count++;
            right++;
            ans = Math.max(ans, count);
        }
        return ans;
    }
}
// @lc code=end

