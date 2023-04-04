/*
 * @lc app=leetcode.cn id=368 lang=java
 *
 * [368] 最大整除子集
 */

// @lc code=start
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        // dp[i]: 以nums[i]结尾的最大整除子集的长度
        int[] dp = new int[n];
        int[] track = new int[n]; // track[i]: 以nums[i]结尾的最大整除子集的“倒数第二个元素的下标”, 用于回溯路径
        // 初始化
        Arrays.fill(dp, 1);
        Arrays.fill(track, -1);
        // 递推
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] % nums[j] == 0) {
                    if(dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        track[i] = j;
                    }
                }
            }
        }
        // 获取结果
        int maxLen = 0, idx = -1;
        for(int i = 0; i < n; i++) {
            if(dp[i] > maxLen) {
                maxLen = dp[i];
                idx = i;
            }
        }
        List<Integer> result = new ArrayList<>();
        while(idx != -1) {
            result.add(nums[idx]);
            idx = track[idx];
        }
        Collections.reverse(result);
        return result;
    }
}
// @lc code=end

