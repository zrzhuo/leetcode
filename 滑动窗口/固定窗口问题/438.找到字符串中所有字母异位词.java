/*
 * @lc app=leetcode.cn id=438 lang=java
 *
 * [438] 找到字符串中所有字母异位词
 */

// @lc code=start
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int n = s.length(), m = p.length();
        List<Integer> ans = new LinkedList<>();
        if(n < m)
            return ans;
        // 固定窗口问题
        int left = 0, right = 0;
        int count = 0; // 当前窗口未覆盖的"不同字符的个数"
        int[] counter = new int[26];
        for(int i = 0; i < m; ++i) {
            if(++counter[p.charAt(i) - 'a'] == 1)
                count++;
        }
        // 初始化窗口
        while(right < m) {
            if(--counter[s.charAt(right) - 'a'] == 0)
                count--;
            right++;
        }
        if(count == 0)
            ans.add(left);
        // 滑动
        while(right < n) {
            if(--counter[s.charAt(right) - 'a'] == 0)
                count--;
            right++;
            if(++counter[s.charAt(left) - 'a'] == 1)
                count++;
            left++;
            if(count == 0)
                ans.add(left);
        }
        return ans;
    }
}
// @lc code=end

