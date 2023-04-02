/*
 * @lc app=leetcode.cn id=395 lang=java
 *
 * [395] 至少有 K 个重复字符的最长子串
 */

// @lc code=start
class Solution {

    // 只考虑字符种类数为t的子串
    int func(String s, int k, int t) {
        // 最大窗口问题: 滑动窗口[left, right)
        int left = 0, right = 0, n = s.length(), ans = 0;
        // 定义条件指标: count表示窗口内的字符种类数, total表示窗口内出现次数少于k的字符的数量
        int count = 0, total = 0;
        int[] counter = new int[26];
        // 滑动
        while(right < n) {
            // 移动left直到恰好满足条件
            while(left < right) {
                if(count <= t)
                    break;
                if(--counter[s.charAt(left) - 'a'] == 0)
                    count--;
                if(--counter[s.charAt(left) - 'a'] == k - 1)
                    total--;
                left++;
            }
            // 移动right直到恰好不满足条件
            while(right < n) {
                if(count > t)
                    break;
                if(++counter[s.charAt(right) - 'a'] == 1)
                    count++;
                if(++counter[s.charAt(right) - 'a'] == k)
                    total++;
                right++;
            }
            // 当前满足条件的窗口为[left, right - 1)
            if(total == count)
                ans = Math.max(ans, right - 1 - left);
        }
        // 末尾特殊处理
        if(count <= t && total == count)
            ans = Math.max(ans, n - left);
        return ans;
    }

    public int longestSubstring(String s, int k) {
        int ans = 0;
        for(int i = 1; i <= 26; i++) 
            ans = Math.max(ans, func(s, k, i));
        return ans;
    }
}
// @lc code=end

