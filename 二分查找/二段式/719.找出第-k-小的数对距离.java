/*
 * @lc app=leetcode.cn id=719 lang=java
 *
 * [719] 找出第 K 小的数对距离
 */

// @lc code=start
// 二分 + 双指针
class Solution {
    int func(int[] nums, int x){
        // x为一个数，f(x)为nums中“距离小于等于x的数对”的个数
        int f = 0;
        // 双指针
        int j = 0, n = nums.length;
        for(int i = 0; i < n; ++i){
            while(j < n && nums[j] - nums[i] <= x) {
                j++;
            }
            f += j - i - 1;
        }
        return f;
    }

    public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        // x和f(x), x为一个数，f(x)为nums中“距离小于等于x的数对”的个数
        // f(x)随着x的增大而增大
        int left = 0, right = nums[n - 1] - nums[0];
        int target = k;
        // 从左到右查找第一个使得f(x)>=target的x
        while(left < right){
            int mid = left + (right - left) / 2;
            int now = func(nums, mid);
            if(now >= target)
                right = mid;
            else
                left = mid + 1;
        }
        return right;
    }
}
// @lc code=end

// 优先队列
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((int[] a, int[] b) -> {
            return Math.abs(nums[a[0]] - nums[a[1]]) - Math.abs(nums[b[0]] - nums[b[1]]);
        });
        for(int i = 0; i < n - 1; i++) {
            minHeap.add(new int[]{i, i + 1});
        }
        int result = -1;
        for(int t = 0; t < k; t++) {
            int[] min = minHeap.poll();
            int i = min[0], j = min[1];
            result = Math.abs(nums[i] - nums[j]);
            if(j + 1 < n)
                minHeap.add(new int[]{i, j + 1});
        }
        return result;
    }
}