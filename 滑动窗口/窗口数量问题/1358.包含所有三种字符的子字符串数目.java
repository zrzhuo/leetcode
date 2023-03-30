/*
 * @lc app=leetcode.cn id=1358 lang=java
 *
 * [1358] 包含所有三种字符的子字符串数目
 */

// @lc code=start
class Solution {
    public int numberOfSubstrings(String s) {
        // 窗口数量问题: 滑动窗口[left, right), ans为不同字符个数小于3的窗口的个数
        int left = 0, right = 0, n = s.length(), ans = 0;
        // 定义条件指标: 当前窗口中不同字符的个数
        int count = 0;
        int[] counter = new int[3];
        // 滑动
        while(right < n) {
            // 移动left直到恰好满足要求
            while(left < right) {
                if(count < 3)
                    break;
                if(--counter[s.charAt(left) - 'a'] == 0)
                    count--;
                left++;
            }
            // 累计满足要求的区间个数
            ans += right - left;
            if(++counter[s.charAt(right) - 'a'] == 1)
                count++;
            right++;
        }
        while(left < right) {
            if(count < 3)
                break;
            if(--counter[s.charAt(left) - 'a'] == 0)
                count--;
            left++;
        }
        ans += right - left;
        // 子序列总数 - ans
        long res = 1L * (n + 1) * n / 2 - ans;
        return (int)res;
    }
}
// @lc code=end

