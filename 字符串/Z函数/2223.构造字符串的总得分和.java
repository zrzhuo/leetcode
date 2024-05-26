/*
 * @lc app=leetcode.cn id=2223 lang=java
 *
 * [2223] 构造字符串的总得分和
 */

// @lc code=start
class Solution {
    public long sumScores(String s) {
        int n = s.length();
        int[] z = new int[n];
        z[0] = 0; 
        int left = 0, right = 0; // z-box
        for(int i = 1; i < n; i++) {
            if(i <= right) {
                z[i] = Math.min(z[i - left], right - i + 1); // i落在z-box内
            } else {
                z[i] = 0; // i落在z-box外
            }
            // 超出z-box的部分向后暴力匹配
            while(z[i] + i < n && s.charAt(z[i]) == s.charAt(z[i] + i)) {
                z[i]++;
            }
            // 更新z-box
            if(i + z[i] - 1 > right) {
                left = i;
                right = i + z[i] - 1;
            }
        }
        long res = 0;
        for(int p : z) {
            res += p;
        }
        return res + n;
    }
}
// @lc code=end

