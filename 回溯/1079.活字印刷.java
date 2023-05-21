/*
 * @lc app=leetcode.cn id=1079 lang=java
 *
 * [1079] 活字印刷
 */

class Solution {
    int result = 0;
    int size = 0;
    boolean[] used; // 记录字符是否已被使用
    void backTrack(char[] str) {
        if(size > 0)
            result++;
        for(int i = 0; i < str.length; i++) {
            if(used[i])
                continue; // 当前字符已经纳入, 跳过
            // 当前字符与上一个字符相同时, 如果上一个字符未纳入, 则当前字符也不应纳入, 否则会出现重复 跳过
            if(i > 0 && str[i] == str[i - 1] && !used[i - 1])
                continue;
            used[i] = true;
            size++;
            backTrack(str);
            size--;
            used[i] = false;
        }
    }

    public int numTilePossibilities(String tiles) {
        used = new boolean[tiles.length()];
        char[] str = tiles.toCharArray();
        Arrays.sort(str); // 排序, 用于去重
        backTrack(str);
        return result;
    }
}


// @lc code=start
class Solution {
    public int numTilePossibilities(String tiles) {
        int n = tiles.length();
        // 统计每种字符的个数
        int[] counter = new int[26];
        for(int i = 0; i < n; i++) {
            counter[tiles.charAt(i) - 'A']++;
        }
        int t = 0;
        for(int i = 0; i < 26; i++) {
            if(counter[i] > 0)
                counter[t++] = counter[i]; // counter[0, t)保存所有字符的个数
        }
        // 预处理组合数
        int[][] comb = new int[n + 1][n + 1];
        for(int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;
            for(int j = 1; j < i; j++) {
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
            }
        }
        // 动态规划
        // dp[i][j]: 使用前i种字符, 可以构造出的长度为j的字母序列的数目
        int[][] dp = new int[t + 1][n + 1];
        // 初始化
        for(int i = 0; i <= t; i++) {
            dp[i][0] = 1; // 用i种字符去构造空序列, 方案只有一种
        }
        // 递推
        for(int i = 1; i <= t; i++) {
            for(int j = 1; j <= n; j++) {
                // 枚举当前字符的个数
                for(int k = 0; k <= Math.min(counter[i - 1], j); k++) {
                    dp[i][j] += dp[i - 1][j - k] * comb[j][k]; // 使用k个当前字符, 放在k个位置, 共有C(j, k)中方法
                }
            }
        }
        // 累计结果
        int result = 0;
        for(int j = 1; j <= n; j++) {
            result += dp[t][j];
        }
        return result;
    }

}
// @lc code=end