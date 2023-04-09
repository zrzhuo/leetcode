/*
 * @lc app=leetcode.cn id=1100 lang=java
 *
 * [1100] 长度为 K 的无重复字符子串
 */

// @lc code=start
class Solution {
    public int numKLenSubstrNoRepeats(String s, int k) {
        int count = 0; // 记录窗口中“个数为1的字母”的数量，当count==k时，说明该窗口中无重复字符
        int[] counter = new int[26]; // 统计窗口中各字母的数量
        // 固定窗口问题
        int left = 0, right = 0, n = s.length(), ans = 0;
        while(right < Math.min(k, n)) {
            int num = ++counter[s.charAt(right) - 'a'];
            if(num == 1) count++;
            if(num == 2) count--;
            right++;
            if(count == k) ans++;
        }
        while(right < n) {
            int num1 = ++counter[s.charAt(right) - 'a'];
            if(num1 == 1) count++;
            if(num1 == 2) count--;
            right++;
            int num2 = --counter[s.charAt(left) - 'a'];
            if(num2 == 1) count++;
            if(num2 == 0) count--;
            left++;
            if(count == k) ans++;
        }
        return ans;
    }
}
// @lc code=end

