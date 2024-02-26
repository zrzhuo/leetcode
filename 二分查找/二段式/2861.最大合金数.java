/*
 * @lc app=leetcode.cn id=2861 lang=java
 *
 * [2861] 最大合金数
 */

// @lc code=start
class Solution {
    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int res = 0;
        for(int i = 0; i < k; i++) {
            res = Math.max(res, machineMax(composition.get(i), stock, cost, budget));
        }
        return res;
    }

    // 二分查找，求每台机器的最大产量
    private int machineMax(List<Integer> composition, List<Integer> stock, List<Integer> cost, int budget) {
        int min = Integer.MAX_VALUE; // stock本身能够支持的产量
        for(int i = 0; i < stock.size(); i++) {
            min = Math.min(min, stock.get(i) / composition.get(i));
        }
        int left = min, right = budget + min + 1; // 起始区间
        while(left < right) {
            int mid = (right - left) / 2 + left;
            if(totalCost(composition, stock, cost, mid) > budget) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right - 1;
    }

    // 某机器生产t数量的合金需要的成本，随t的增大而增大
    private long totalCost(List<Integer> composition, List<Integer> stock, List<Integer> cost, int t) {
        long totalCost = 0;
        for(int i = 0; i < stock.size(); i++) {
            totalCost += Math.max(0L, (long)composition.get(i) * t - stock.get(i)) * cost.get(i);
        }
        return totalCost;
    }
}
// @lc code=end

