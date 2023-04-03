import java.util.HashSet;

/*
 * @lc app=leetcode.cn id=395 lang=java
 *
 * [395] 至少有 K 个重复字符的最长子串
 */

// @lc code=start
class Solution {

    // 只考虑: 字符种类数 <= charNum 的子串
    int func(String s, int k, int charNum) {
        // 最大窗口问题: 滑动窗口[left, right)
        int left = 0, right = 0, n = s.length(), ans = 0;
        // 定义条件指标: count表示字符种类数, total表示出现次数大于等于k的字符种类量
        int count = 0, total = 0;
        int[] counter = new int[26];
        // 滑动
        while(right < n) {
            // 移动left直到恰好满足条件
            while(left < right) {
                if(count <= charNum)
                    break;
                if(--counter[s.charAt(left) - 'a'] == 0)
                    count--;
                if(counter[s.charAt(left) - 'a'] == k - 1)
                    total--;
                left++;
            }
            // 移动right直到恰好不满足条件
            while(right < n) {
                if(count > charNum)
                    break;
                if(++counter[s.charAt(right) - 'a'] == 1)
                    count++;
                if(counter[s.charAt(right) - 'a'] == k)
                    total++;
                right++;
            }
            // 当前满足条件的窗口为[left, right - 1)
            if(total == charNum) // total == charNum 时做一次更新
                ans = Math.max(ans, right - 1 - left);
        }
        // 末尾特殊处理
        if(count <= charNum){
            if(total == charNum) // total == charNum 时做一次更新
                ans = Math.max(ans, n - left);
        }
        return ans;
    }

    public int longestSubstring(String s, int k) {
        Set<Character> set = new HashSet<>();
        for(int i = 0; i < s.length(); ++i)
            set.add(s.charAt(i));
        int ans = 0;
        // 枚举charNum
        for(int i = 1; i <= set.size(); i++) 
            ans = Math.max(ans, func(s, k, i));
        return ans;
    }
}
// @lc code=end
