/*
 * @lc app=leetcode.cn id=1187 lang=java
 *
 * [1187] 使数组严格递增
 */

// @lc code=start
class Solution {
    int[] preHandle(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for(int num : arr) {
            set.add(num);
        }
        int[] res = new int[set.size()];
        int i = 0;
        for(int num : set) {
            res[i++] = num;
        }
        Arrays.sort(res);
        return res;
    }

    int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(arr[mid] > target)
                right = mid;
            else
                left = mid + 1;
        }
        return right;
    }

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        arr2 = preHandle(arr2); // 对arr2去重以提高效率, 并排序以方便二分查找
        int m = arr1.length, n = arr2.length, inf = Integer.MAX_VALUE;
        int k = Math.min(m, n); // 最多进行min(m,n)次替换
        // dp[i][t]: 对arr1[0...i]进行t次替换后组成的严格递增数组中, 末尾元素的最小值
        int[][] dp = new int[m][k + 1];
        // 初始化
        for(int i = 0; i < m; i++)
            Arrays.fill(dp[i], inf);
        dp[0][0] = arr1[0];
        for(int i = 1; i < m; i++) {
            if(dp[i - 1][0] < inf && arr1[i] > arr1[i - 1])
                dp[i][0] = arr1[i];
        }
        for(int t = 1; t <= k; t++) {
            dp[0][t] = Math.min(arr1[0], arr2[0]);
        }
        // 递推
        for(int i = 1; i < m; i++) {
            for(int t = 1; t <= k; t++) {
                // 保留arr1[i]
                if(arr1[i] > dp[i - 1][t]) {
                    dp[i][t] = Math.min(dp[i][t], arr1[i]);
                }
                // 替换arr1[i]
                if(dp[i - 1][t - 1] < inf) {
                    int idx = binarySearch(arr2, dp[i - 1][t - 1]);
                    if(idx < n)
                        dp[i][t] = Math.min(dp[i][t], arr2[idx]);
                }
            }
        }
        // 获取结果
        for(int t = 0; t <= k; t++) {
            if(dp[m - 1][t] < inf)
                return t;
        }
        return -1;
    }

   
}
// @lc code=end

