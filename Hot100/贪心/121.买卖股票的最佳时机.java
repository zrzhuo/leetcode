/*
 * @lc app=leetcode.cn id=121 lang=java
 *
 * [121] 买卖股票的最佳时机
 */

// @lc code=start
class Solution {
    public int maxProfit(int[] prices) {
        // 贪心: 遇到更低的价格时即选择更低的价格买入, 并在遇到比买入价更高的价格时, 尝试以该价格卖出, 记录最大利润
        int buy = prices[0]; // 买入价
        int profit = 0; // 最大利润
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] < buy) {
                buy = prices[i]; // 到更低的价格时即选择更低的价格买入
            } else {
                profit = Math.max(profit, prices[i] - buy); //到比买入价更高的价格时, 尝试以该价格卖出, 记录最大利润
            }
        }
        return profit;
    }
}
// @lc code=end

