/*
 * @lc app=leetcode.cn id=2086 lang=java
 *
 * [2086] 从房屋收集雨水需要的最少水桶数
 */

// @lc code=start
class Solution {
    public int minimumBuckets(String hamsters) {
        char[] chars = street.toCharArray();
        int n = chars.length, result = 0;
        for(int i = 0; i < n; i++) {
            if(chars[i] == 'H') {
                if(i < n - 1 && chars[i + 1] == '.') {
                    result++; // 放在当前房屋后面，即chars[i + 1]处
                    i += 2;   // 因为chars[i + 1]处已经有桶了，故char[i + 2]处的房屋可以忽略
                } else if (i > 0 && chars[i - 1]== '.') {
                    result++; // 放在当前房屋前面，即chars[i - 1]处
                } else {
                    return -1; // 当前房屋前后没有空位，无解
                }
            }
        }
        return result;
    }
}
// @lc code=end

