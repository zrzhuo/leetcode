/*
 * @lc app=leetcode.cn id=2830 lang=java
 *
 * [2830] 销售利润最大化
 */

// @lc code=start
class Solution {
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        // 按end分组
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        for(List<Integer> offer : offers) {
            map.putIfAbsent(offer.get(1), new ArrayList<>());
            map.get(offer.get(1)).add(offer);
        }
        // dp[i]: 出售房子[0...i - 1]可获得的最大利润
        int[] dp = new int[n + 1];
        // 初始化
        dp[0] = 0;
        // 递推
        for(int i = 1; i <= n; i++) {
            // 不卖房子i-1
            dp[i] = dp[i - 1];
            // 卖房子i-1
            if(!map.containsKey(i - 1)) 
                continue;
            for(List< Integer> offer: map.get(i - 1)) {
                dp[i] = Math.max(dp[i], dp[offer.get(0)] + offer.get(2));
            }
        }
        return dp[n];
    }
}
// @lc code=end


class Solution {
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        // 按end排序
        Collections.sort(offers, (a, b) -> a.get(1) - b.get(1));
        int m = offers.size();
        // dp[i]: 从offers[0...i]中可获得的最大利润
        int[] dp = new int[m];
        // 初始化
        dp[0] = offers.get(0).get(2);
        // 递推
        for(int i = 1; i < m; i++) {
            // 不选择offers[i]
            dp[i] = dp[i - 1];
            // 选择offers[i]
            int left = 0, right = i;
            while(left < right) {
                int mid = (right - left) / 2 + left;
                if(offers.get(mid).get(1) >= offers.get(i).get(0)) 
                    right = mid;
                else
                    left = mid + 1;
            }
            if(right == 0)
                dp[i] = Math.max(dp[i], offers.get(i).get(2));
            else
                dp[i] = Math.max(dp[i], offers.get(i).get(2) + dp[right - 1]);
        }
        return dp[m - 1];
    }
}
