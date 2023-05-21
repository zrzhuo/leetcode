/*
 * @lc app=leetcode.cn id=52 lang=java
 *
 * [52] N 皇后 II
 */

// @lc code=start
class Solution {
    boolean[] col;  // 记录该列是否已经放置皇后
    boolean[] dia1; // 记录从左上到右下的斜线上是否已经放置皇后, 同一斜线上的r-c相等
    boolean[] dia2; // 记录从左下到右上的斜线上是否已经放置皇后, 同一斜线上的r+c相等
    List<StringBuilder> temp = new ArrayList<>();
    int result = 0;
    
    void backTrack(int n, int r) {
        // 所有行都已放置皇后, 成功得到一个方案
        if(r == n) {
            result++; 
            return;
        }
        // 枚举可以放置皇后的位置: 当位置(r, c)对应的列和两条斜线都未放置皇后, 则该位置可以放置皇后
        for(int c = 0; c < n; c++) {
            int d1 = r - c + n - 1; // 当前位置所处斜线的标号, 加n-1是给一个偏移量, 以满足下标大于0
            int d2 = r + c;         // 当前位置所处斜线的标号
            if(!col[c] && !dia1[d1] && !dia2[d2]) {
                col[c] = dia1[d1] = dia2[d2] = true;
                temp.get(r).setCharAt(c, 'Q');
                backTrack(n, r + 1); // 递归放置r+1行, 然后回溯
                temp.get(r).setCharAt(c, '.'); 
                col[c] = dia1[d1] = dia2[d2] = false;
            }
        }
    }

    public int totalNQueens(int n) {
        col = new boolean[n];
        dia1 = new boolean[2 * n - 1]; 
        dia2 = new boolean[2 * n - 1];
        for(int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < n; j++) {
                sb.append('.');
            }
            temp.add(sb);
        }
        backTrack(n, 0);
        return result;
    }
}
// @lc code=end

