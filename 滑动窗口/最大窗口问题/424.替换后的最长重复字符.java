/*
 * @lc app=leetcode.cn id=424 lang=java
 *
 * [424] 替换后的最长重复字符
 */

// @lc code=start
class Solution {
    
    public int characterReplacement(String s, int k) {
        // 最大窗口问题: 滑动窗口[left, right)
        int left = 0, right = 0, n = s.length(), ans = 0;
        // 定义条件指标: 当前窗口中最多字符的数量
        int maxCount = 0; 
        int[] counter = new int[26];
        // 滑动
        while(right < n) {
            // 移动left直到恰好满足条件
            while(left < right) {
                if(right - left - maxCount <= k)
                    break;
                --counter[s.charAt(left) - 'A'];
                maxCount = findMax(counter); // maxCount递减
                left++;
            }
            // 移动right直到恰好不满足条件
            while(right < n) {
                if(right - left - maxCount > k)
                    break;
                ++counter[s.charAt(right) - 'A'];
                maxCount = findMax(counter); // maxCount递增
                right++;
            }
            // 当前满足条件的窗口为[left, right - 1)
            ans = Math.max(ans, right - 1 - left);
        }
        // 末尾特殊处理
        if(right - left - maxCount <= k)
            ans = Math.max(ans, n - left);
        return ans;
    }

    // 寻找最多字符的个数
    int findMax(int[] counter) {
        int max = 0;
        for(int i = 0; i < 26; ++i)
            max = Math.max(max, counter[i]);
        return max;
    }
}
// @lc code=end
