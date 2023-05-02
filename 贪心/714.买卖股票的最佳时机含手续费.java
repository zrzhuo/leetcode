/*
 * @lc app=leetcode.cn id=714 lang=java
 *
 * [714] 买卖股票的最佳时机含手续费
 */

// @lc code=start
class Solution {
    public int maxProfit(int[] prices, int fee) {
        // 买入股票的成本即价格和手续费之和
        // 贪心: 可以以更低的成本买入时, 以更低的成本买入; 一旦能够获取利润, 即贪心的获取该利润
        int result = 0;
        int cost = prices[0] + fee; // 买入股票的成本
        for(int i = 1; i < prices.length; ++i){
            if(prices[i] + fee < cost) {
                cost = prices[i] + fee; //可以以更低的成本买入时, 以更低的成本买入
            } 
            else if (prices[i] > cost) {
                result += prices[i] - cost; // 一旦能够获取利润, 即贪心的获取该利润
                cost = prices[i]; // !!!当我们卖出一支股票时，我们就立即获得了以相同价格并且免除手续费买入一支股票的权利
            } 
        }
        return result;
    }   
}
// @lc code=end

