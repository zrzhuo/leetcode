/*
 * @lc app=leetcode.cn id=3031 lang=java
 *
 * [3031] 将单词恢复初始状态所需的最短时间 II
 */

// @lc code=start
class Solution {
    public int minimumTimeToInitialState(String word, int k) {
        int n = word.length();
        int[] z = new int[n]; // z函数
        z[0] = 0;
        int left = 0, right = 0; // 维护z-box
        for(int i = 1; i < n; i++) {
            if(i <= right) {
                z[i] = Math.min(z[i - left], right - i + 1); // 落在z-box内
            } else {
                z[i] = 0; // 落在z-box外
            }
            // 尝试继续匹配
            while(z[i] + i < n && word.charAt(z[i]) == word.charAt(z[i] + i)) {
                z[i]++;
            }
            // 更新z-box
            if(i + z[i] - 1 > right) {
                left = i;
                right = i + z[i] - 1;
            }
            // 当前位置可以恰好执行若干次移除操作，且剩余字符完全匹配word前缀
            if(i % k == 0 && z[i] == n - i) {
                return i / k;
            }
        }
        return (n - 1) / k + 1; // 移除所有字符
    }
}
// @lc code=end

