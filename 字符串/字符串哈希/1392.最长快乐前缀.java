/*
 * @lc app=leetcode.cn id=1392 lang=java
 *
 * [1392] 最长快乐前缀
 */

// @lc code=start
// 字符串哈希
class Solution {
    public String longestPrefix(String s) {
        int n = s.length();
        int base = 31, mod = (int)1e9 + 7; // base和mod
        long left = 0, right = 0, mul = 1;
        int max = -1;
        for(int i = 0; i < n - 1; i++) {
            left = (left * base + s.charAt(i) - 'a') % mod;
            right = ((s.charAt(n - 1 - i) - 'a') * mul + right) % mod;
            mul = (mul * base) % mod;
            if(left == right) {
                max = Math.max(max, i); // 前缀哈希值和后缀哈希值相等时，更新max
            }
        }
        return s.substring(0, max + 1);
    }
}
// @lc code=end


// kmp：next数组
class Solution {
    public String longestPrefix(String s) {
        int n = s.length();
        int[] next = new int[n + 1];
        next[0] = -1;
        for(int i = 1; i <= n; i++) {
            int cur = next[i - 1];
            while(cur != -1 && s.charAt(cur) != s.charAt(i - 1)) {
                cur = next[cur];
            }
            next[i] = cur + 1;
        }
        return s.substring(0, next[n]);
    }
}