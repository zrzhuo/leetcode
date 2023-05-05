/*
 * @lc app=leetcode.cn id=1358 lang=java
 *
 * [1358] 包含所有三种字符的子字符串数目
 */

记count为窗口[left, right)中不同字符的个数, 则有:
    1. left固定时, 右移right则count不变或增大
    2. right固定时, 右移left则count不变或减小

// @lc code=start
class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        // 不同字符个数等于3的窗口的个数 = 子序列总数 - 不同字符个数小于3的窗口的个数
        long res = 1L * (n + 1) * n / 2 - lessThan(s, 3);
        return (int)res;
    }

    // 求不同字符个数小于k的窗口的个数
    int lessThan(String s, int k) {
        // 窗口数量问题: 滑动窗口[left, right)
        int left = 0, right = 0, n = s.length(), ans = 0;
        // 定义条件指标: 当前窗口中不同字符的个数
        int count = 0;
        int[] counter = new int[3];
        // 滑动
        while(right < n) {
            // 移动left直到恰好满足要求
            while(left < right) {
                if(count < k)
                    break;
                if(--counter[s.charAt(left) - 'a'] == 0)
                    count--;
                left++;
            }
            // 累计满足要求的区间个数
            ans += right - left;
            // 移动right
            if(++counter[s.charAt(right) - 'a'] == 1)
                count++;
            right++;
        }
        // 末尾特殊处理, 此时right == n
        while(left < right) {
            if(count < k)
                break;
            if(--counter[s.charAt(left) - 'a'] == 0)
                count--;
            left++;
        }
        ans += right - left;
        return ans;
    }
}
// @lc code=end

