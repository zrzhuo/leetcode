/*
 * @lc app=leetcode.cn id=300 lang=java
 *
 * [300] 最长递增子序列
 */

class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // dp[i]: 以nums[i]结尾的最长递增子序列的长度
        int[] dp = new int[n];
        // 初始化
        Arrays.fill(dp, 1);
        // 递推
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        // 获取结果
       return Arrays.stream(dp).max().getAsInt();
    }
}


// @lc code=start
class Solution {
    int binarySearch(int[] dp, int left, int right, int target){
        // 从left到right，查找第一个使得f(x)>=target的x
        while(left < right){
            int mid = left + (right - left) / 2;
            if(dp[mid] >= target)
                right = mid;
            else
                left = mid + 1;
        }
        return right;
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // dp[i]: 长度为i的递增子序列的“末尾元素的最小值”, 另分析易得dp[i]为递增序列
        int[] dp = new int[n + 1];
        // 初始化
        dp[1] = nums[0];
        int len = 1; // len为当前“已获得的上升子序列”的最长长度
        // 递推
        for(int i = 1; i < n; ++i){
            if(nums[i] > dp[len]){
                len++; // 长度加1
                dp[len] = nums[i]; // 记录长度为len的上升子序列的“末尾元素的最小值”
            }
            // ！！最难理解的地方！！
            else{
                int index = binarySearch(dp, 1, len, nums[i]); // 二分查找需要更新的下标
                dp[index] = nums[i]; // 更新
            }
        }
        return len;
    }
}
// @lc code=end

