/*
 * @lc app=leetcode.cn id=826 lang=java
 *
 * [826] 安排工作以达到最大收益
 */

// @lc code=start
// 贪心 + 二分
class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = profit.length;
        int[][] dap = new int[n][2];
        for(int i = 0; i < n; i++) {
            dap[i][0] = difficulty[i]; // dap[i][0]: 第i项工作的难度
            dap[i][1] = profit[i];     // dap[i][1]: 第i项工作的利润
        } 
        // 对dap数组进行排序: 先按difficulty从小到大, 再按profit从大到小
        Arrays.sort(dap, (a, b) -> {
            if(a[0] != b[0])
                return a[0] - b[0];
            return b[1] - a[1];
        });
        // 修改dap数组: 将dap[i][1]修改为, 前0...i项工作的利润的最大值max
        // 此时dap数组具有以下含义: dap[i][0]是能力值, dap[i][1]是具备该能力值的工人可以获取的最大利润
        int max = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, dap[i][1]);
            dap[i][1] = max;
        }
        // 二分查找: 查找每个工人可以获得的最大利润
        int result = 0;
        for(int i = 0; i < worker.length; i++) {
            result += binarySearch(dap, worker[i]);
        }
        return result;
    }

    // 查找能力值为abilit的工人可以获取的最大利润
    int binarySearch(int[][] dap, int ability) {
        // x和f(x): x为工人的能力值，f(x)为该工人能完成的最难工作的难度
        int left = 0, right = dap.length;
        int target = ability;
        // 从left到right，查找第一个使得f(x)>target的x
        while(left < right){
            int mid = left + (right - left) / 2;
            int now = dap[mid][0];
            if(now > target)
                right = mid;
            else
                left = mid + 1;
        }
        // right为0时，返回0
        return right == 0? 0: dap[right - 1][1];
    }
}
// @lc code=end

