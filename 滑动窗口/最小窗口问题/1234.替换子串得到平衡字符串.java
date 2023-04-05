/*
 * @lc app=leetcode.cn id=1234 lang=java
 *
 * [1234] 替换子串得到平衡字符串
 */

// @lc code=start
class Solution {
    public int balancedString(String s) {
        // 最小窗口问题: 滑动窗口[left, right)
        int left = 0, right = 0, n = s.length(), ans = n + 1;
        // 定义条件指标: count, 当前窗口内"数量大于等于溢出量"的溢出字符的数量, 所谓"溢出字符", 即数量大于n/4的字符
        int count = 0, size = 0; 
        int[] counter = new int[26]; // counter记录溢出字符的溢出量
        for(int i = 0; i < n; i++)
            counter[s.charAt(i) - 'A']++;
        for(int i = 0; i < 26; i++) {
            if(counter[i] > n / 4) {
                counter[i] -= n / 4; 
                size++; // 累计溢出字符的数量
            } else {
                counter[i] = Integer.MIN_VALUE;
            }
        }
        if(size == 0)
            return 0;
        // 滑动
        while(right < n) {
            // 移动right直到恰好满足条件
            while(right < n) {
                if(count >= size)
                    break;
                char ch = s.charAt(right);
                if(counter[ch - 'A'] != Integer.MIN_VALUE) {
                    if(--counter[ch - 'A'] == 0)
                        count++;
                }
                right++;
            }
            // 移动left直到恰好不满足条件
            while(left < right) {
                if(count < size)
                    break;
                char ch = s.charAt(left);
                if(counter[ch - 'A'] != Integer.MIN_VALUE) {
                    if(++counter[ch - 'A'] == 1)
                        count--;
                }
                left++;
            }
            // 当前满足条件的窗口为[left-1, right)
            ans = Math.min(ans, right - left + 1);
        }
        return ans;
    }
}
// @lc code=end

