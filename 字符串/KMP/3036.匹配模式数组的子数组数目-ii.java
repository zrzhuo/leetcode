/*
 * @lc app=leetcode.cn id=3036 lang=java
 *
 * [3036] 匹配模式数组的子数组数目 II
 */

// @lc code=start
// kmp变形
class Solution {
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int n = nums.length - 1, m = pattern.length;
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = -Integer.compare(nums[i], nums[i + 1]);
        }
        // kmp
        int[] next = getNext(pattern); // 计算next数组
        int i = 0, j = 0, res = 0;
        while(i < n) {
            if(arr[i] == pattern[j]) {
                i++;
                j++;
            } else if(j > 0) {
                j = next[j];
            } else {
                i++;
            }
            // 找到一个匹配子数组，回退j以继续寻找
            if(j == m) {
                res++;
                j = next[j];
            }
        }
        return res;
    }

    int[] getNext(int[] pattern) {
        int[] next = new int[pattern.length + 1];
        next[0] = -1;
        for(int i = 1; i <= pattern.length; i++) {
            int cur = next[i - 1];
            while(cur != -1 && pattern[cur] != pattern[i - 1]) {
                cur = next[cur];
            }
            next[i] = cur + 1;
        }
        return next;
    }
}
// @lc code=end

