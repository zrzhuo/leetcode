/*
 * @lc app=leetcode.cn id=1542 lang=java
 *
 * [1542] 找出最长的超赞子字符串
 */

状态压缩: 由于s中只包含数字, 故用10个比特位代表10个数字个数的奇偶性, 例如:
    00000 01001, 代表0和3的个数为奇数, 其他数字的个数为偶数
    10000 00001, 代表0和9的个数为奇数, 其他数字的个数为偶数

class Solution {
    public int longestAwesome(String s) {
        int n = s.length(), result = 0;
        Map<Integer, Integer> map = new HashMap<>(); // 记录某状态第一次出现的位置
        int state = 0; // s的前缀的状态
        map.put(0, -1); // 初始化
        for(int i = 0; i < n; i++) {
            int num = s.charAt(i) - '0';
            state ^= 1 << num; // 当前前缀s[0...i]的状态
            // 回文串中允许有一个字符的个数为奇数, 枚举这个字符
            for(int k = 0; k < 10; k++) {
                int preState = state ^ (1 << k); // 相匹配的前缀状态
                if(map.containsKey(preState))
                    result = Math.max(result, i - map.get(preState));
            }
            // 回文串中全是偶数
            if(map.containsKey(state))
                result = Math.max(result, i - map.get(state));
            else
                map.put(state, i);
        }
        return result;
    }
}

// @lc code=start
// 数组代替哈希表
class Solution {
    public int longestAwesome(String s) {
        int n = s.length(), result = 0;
        int[] stateLoc = new int[1 << 10]; // 记录某状态第一次出现的位置
        Arrays.fill(stateLoc, -2);
        int state = 0; // s的前缀的状态
        stateLoc[0] = -1;
        for(int i = 0; i < n; i++) {
            int num = s.charAt(i) - '0';
            state ^= 1 << num; // 当前前缀s[0...i]的状态
            // 回文串中允许有一个字符的个数为奇数, 枚举这个字符
            for(int k = 0; k < 10; k++) {
                int preState = state ^ (1 << k); // 相匹配的前缀状态
                if(stateLoc[preState] != -2)
                    result = Math.max(result, i - stateLoc[preState]);
            }
            // 回文串中全是偶数
            if(stateLoc[state] != -2)
                result = Math.max(result, i - stateLoc[state]);
            else
                stateLoc[state] = i;
        }
        return result;
    }
}
// @lc code=end
