/*
 * @lc app=leetcode.cn id=638 lang=java
 *
 * [638] 大礼包
 */

// @lc code=start
class Solution {
    // 某购物清单(needs数组)所需花费的最低价格
    Map<List<Integer>, Integer> solved = new HashMap<>();
    int solving(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // 获取记忆
        if(solved.containsKey(needs))
            return solved.get(needs);
        int n = price.size(), m = special.size();
        // 不使用大礼包的价格
        int result = 0;
        for(int i = 0; i < n; i++)
            result += price.get(i) * needs.get(i);
        // 枚举每个大礼包
        for(List<Integer> spec : special) {
            List<Integer> postNeeds = new ArrayList<>(needs); // 购买当前大礼包后剩余的购物清单
            boolean flag = false; // 检查当前大礼包是否可用(物品数量是否超出待购清单)
            for(int j = 0; j < n; j++) {
                if(spec.get(j) > needs.get(j)){
                    flag = true;
                    break;
                }
                postNeeds.set(j, postNeeds.get(j) - spec.get(j));
            }
            if(flag)
                continue; // 当前大礼包不可用, continue
            // 取最小值
            result = Math.min(result, solving(price, special, postNeeds) + spec.get(n));
        }
        // 存储记忆
        solved.put(needs, result);
        return result;
    }

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return solving(price, special, needs);
    }
}
// @lc code=end