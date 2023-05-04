/*
 * @lc app=leetcode.cn id=2106 lang=java
 *
 * [2106] 摘水果
 */

// @lc code=start
class Solution {
    public int maxTotalFruits(int[][] fruits, int start, int k) {
        // 滑动窗口[left, right)
        int left = 0, right = 0, n = fruits.length, ans = 0;
        int sum = 0;
        // 定义条件指标: 走完当前区间[fruits[left][0], fruits[right][0] - 1]需要的步数step
        // 滑动
        while(right < n) {
            // 移动left直到恰好满足要求
            while(left < right) {
                if(getStep(fruits[left][0], fruits[right - 1][0], start) <= k)
                    break;
                sum -= fruits[left][1];
                left++;
            }
            // 更新最大值
            ans = Math.max(ans, sum);
            sum += fruits[right][1];
            right++;
        }
        // 末尾特殊处理, 此时right == n
        while(left < right) {
            if(getStep(fruits[left][0], fruits[n - 1][0], start) <= k)
                break;
            sum -= fruits[left][1];
            left++;
        }
        ans = Math.max(ans, sum);
        return ans;
    }
    int getStep(int left, int right, int start) {
        return right - left + Math.min(Math.abs(start - left), Math.abs(right - start));
    }
}
// @lc code=end

class Solution {
    public int maxTotalFruits(int[][] fruits, int start, int k) {
        int n = fruits.length;
        // 水果数量的前缀和: [left, right)范围内的水果总和 = prefix[right] - prefix[left]
        int[] prefix = new int[n + 1]; 
        for(int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + fruits[i - 1][1];
        }
        // 枚举所有的行走方案
        int left = 0, right = 0, result = 0;
        for(int i = 0; i <= k / 2; i++) {
            // 向左走i步, 再向右走k-i步, 行走过的区间为[start - i, start - i + (k - i) + 1)
            left = binarySearch(fruits, start - i);
            right = binarySearch(fruits, start - 2 * i + k + 1);
            result = Math.max(result, prefix[right] - prefix[left]);
            // 向右走i步, 再向左走k-i步, 行走过的区间为[start + i - (k - i), start + i + 1)
            left = binarySearch(fruits, start + 2 * i - k);
            right = binarySearch(fruits, start + i + 1);
            result = Math.max(result, prefix[right] - prefix[left]);
        }
        return result;
    }

    // 查找fruits中target的插入位置
    int binarySearch(int[][] fruits, int target) {
        int left = 0, right = fruits.length;
        while(left < right) {
            int mid = left + (right - left) / 2;
            int now = fruits[mid][0];
            if(now >= target)
                right = mid;
            else
                left = mid + 1; 
        }
        return right;
    }
}