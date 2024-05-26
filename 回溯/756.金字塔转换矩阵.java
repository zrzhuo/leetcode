/*
 * @lc app=leetcode.cn id=756 lang=java
 *
 * [756] 金字塔转换矩阵
 */

// @lc code=start
// 回溯
class Solution {
    Map<String, String> map = new HashMap<>();
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        for(String allow : allowed) {
            map.put(allow.substring(0, 2), map.getOrDefault(allow.substring(0, 2), "") + allow.substring(2, 3));
        }
        return backtrack(bottom, new StringBuilder(), 0);
    }

    boolean backtrack(String bottom, StringBuilder next, int idx) {
        if(next.length() == bottom.length() - 1) {
            if(bottom.length() == 2) {
                // 递归出口：bottom长度为2，去map中直接判断
                return map.containsKey(bottom.substring(0, 2));
            } else {
                // 递归判断next是否合法
                return backtrack(next.toString(), new StringBuilder(), 0);
            }
        }
        String key = bottom.substring(idx, idx + 2);
        // 迭代当前两个字母能构成的合法字母
        for(char ch : map.getOrDefault(key, "").toCharArray()) {
            next.append(ch);
            if(backtrack(bottom, next, idx + 1))
                return true;
            next.deleteCharAt(next.length() - 1); // 回溯
        }
        return false;
    }
}
// @lc code=end

