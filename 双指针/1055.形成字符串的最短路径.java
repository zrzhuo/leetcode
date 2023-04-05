/*
 * @lc app=leetcode.cn id=1055 lang=java
 *
 * [1055] 形成字符串的最短路径
 */

// @lc code=start
// 双指针
class Solution {
    public int shortestWay(String source, String target) {
        int m = source.length(), n = target.length();
        // 判断是否为-1
        boolean[] existed = new boolean[26];
        for(int i = 0; i < m; i++) {
            existed[source.charAt(i) - 'a'] = true;
        }
        for(int i = 0; i < n; i++) {
            if(existed[target.charAt(i) - 'a'] == false)
                return -1;
        }
        // 进行匹配
        int i = 0, j = 0, round = 0;
        while(j < n) {
            if(source.charAt(i) == target.charAt(j)) 
                j++;
            i++;
            if(i == m) {
                i = 0; 
                round++;
            }
        }
        if(i != 0)
            round++; // 当前轮次也要算上
        return round;
    }
}
// @lc code=end

