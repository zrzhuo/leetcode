/*
 * @lc app=leetcode.cn id=967 lang=java
 *
 * [967] 连续差相同的数字
 */

// @lc code=start
class Solution {
    List<Integer> res = new ArrayList<>();
    void dfs(int n, int k, int num, int len) {
        if(len == n) {
            res.add(num);
            return;
        }
        int last = num % 10; // 当前数字最后一位
        int bigger = last + k, smaller = last - k;
        if(bigger <= 9) {
            dfs(n, k, num * 10 + bigger, len + 1);
        }
        if(smaller >= 0 && smaller != bigger) { // smaller != bigger, 用于去重
            dfs(n, k, num * 10 + smaller, len + 1);
        }
        return;
    }

    public int[] numsSameConsecDiff(int n, int k) {
        for(int i = 1; i < 10; i++) {
            dfs(n, k, i, 1);
        }
        int[] arr = new int[res.size()];
        for(int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }
}
// @lc code=end

