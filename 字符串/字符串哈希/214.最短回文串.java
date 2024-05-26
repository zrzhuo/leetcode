/*
 * @lc app=leetcode.cn id=214 lang=java
 *
 * [214] 最短回文串
 */

本质：寻找最长的前缀回文串
// @lc code=start
class Solution {
    public String shortestPalindrome(String s) {
        int base = 26, mod = 1000000007; // 字符串哈希
        long left = 0, right = 0, mul = 1;
        int k = -1;
        for(int i = 0; i < s.length(); i++) {
            left = (left * base + s.charAt(i) - 'a') % mod; // 正序哈希值
            right = (right + (s.charAt(i) - 'a') * mul) % mod; // 逆序哈希值
            mul = (mul * base) % mod;
            if(left == right) {
                k = i;
            }
        }
        StringBuilder sb = new StringBuilder(s.substring(k + 1)).reverse().append(s);
        return sb.toString();
    }
}
// @lc code=end


// kmp
class Solution {
    public String shortestPalindrome(String s) {
        int n = s.length();
        String r = new StringBuilder(s).reverse().toString(); // 翻转s
        int[] next = getNext(s); // s的next数组
        // 在r中查找s
        int i = 0, j = 0;
        while(i < n && j < n) {
            if(r.charAt(i) == s.charAt(j)) {
                i++;
                j++;
            } else if(j > 0) {
                j = next[j];
            } else {
                i++;
            }
        }
        // 此时s[0...j-1]即为s的最长前缀回文串
        StringBuilder sb = new StringBuilder(s.substring(j)).reverse().append(s);
        return sb.toString();
    }

    int[] getNext(String s) {
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
        return next;
    }

}
