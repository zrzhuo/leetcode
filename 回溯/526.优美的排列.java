/*
 * @lc app=leetcode.cn id=526 lang=java
 *
 * [526] 优美的排列
 */

// @lc code=start
// 回溯
class Solution {
    List<Integer>[] match; // match[num]存放数字num可以放置的所有位置下标
    boolean[] used; // 记录当前位置是否已经放置有整数
    int result = 0;

    void backTrack(int n, int num) {
        // 所有整数都放置到了合适位置, 得到一个优美排列
        if(num > n) {
            result++;
            return;
        }
        // 枚举当前整数可以放置的位置
        for(int i : match[num]) {
            if(!used[i]) {
                used[i] = true;
                backTrack(n, num + 1); // 递归放置下一个整数, 然后回溯
                used[i] = false;
            }
        }
    }

    public int countArrangement(int n) {
        used = new boolean[n + 1];
        match = new List[n + 1];
        for(int num = 1; num <= n; num++) {
            match[num] = new ArrayList<>();
            for(int i = 1; i <= n; i++) {
                if(num % i == 0 || i % num == 0)
                    match[num].add(i);
            }
        }
        backTrack(n, 1);
        return result;
    }
}
// @lc code=end