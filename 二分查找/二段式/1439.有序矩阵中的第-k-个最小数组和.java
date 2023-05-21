/*
 * @lc app=leetcode.cn id=1439 lang=java
 *
 * [1439] 有序矩阵中的第 k 个最小数组和
 */

// @lc code=start
class Solution {
    int[][] mat;
    int count;
    int initSum;
    int target;
    void dfs(int x, int sum, int row){
        int m = mat.length, n = mat[0].length;
        if(row == m) 
            return; // 递归出口
        if(sum > x) 
            return; // 剪枝1：现在的sum已经大于x，而mat[i][j]>0，继续累加sum一定会大于x
        if(count > target)
            return; // 剪枝2：count已经大于target，即f(x)与target的比较结果已经出来，无需继续累加cnt
        // 当前行选择下标为0的数
        dfs(x, sum, row + 1);
        // 当前行选择下标大于0的数
        for(int col = 1; col < n; ++col){
            sum = sum - mat[row][col - 1] + mat[row][col];
            if(sum > x)
                break; // 剪枝3：因为每一行都是递增的，之后的sum一定会更大，无需继续枚举后续的mat[row][col]
            ++count;
            dfs(x, sum, row + 1);
        }
    }
    
    int func(int x){
        // f(x)为“数组和小于等于x”的数组个数
        count = 1; // initSum小于等于x, 故count初始为1
        dfs(x, initSum, 0);
        return count;
    }

    public int kthSmallest(int[][] mat, int k) {
        this.mat = mat;
        this.target = k - 1;
        int m = mat.length, n = mat[0].length;
        for(int i = 0; i < m; i++) {
            initSum += mat[i][0];
        }
        // f(x)为“数组和小于等于x”的数组个数, 显然f(x)随着x的增大而增大
        // 计算x的边界
        int left = 0, right = 0;
        for(int i = 0; i < m; ++i){
            left += mat[i][0];
            right += mat[i][n - 1];
        }
        // f(x)单调递增，从左到右查找第一个使得f(x)>k-1的x
        while(left < right){
            int mid = left + (right - left) / 2;
            if(func(mid) > k - 1)
                right = mid;
            else
                left = mid + 1;
        }
        return right;
    }
}
// @lc code=end

