/*
 * @lc app=leetcode.cn id=2386 lang=java
 *
 * [2386] 找出数组的第 K 大和
 */

// @lc code=start
// 二分
class Solution {
    public long kSum(int[] nums, int k) {
        int n = nums.length;
        long total = 0; // 正数之和
        for(int i = 0; i < n; i++) {
            if(nums[i] >= 0) {
                total += nums[i];
            } else {
                nums[i] = -nums[i]; // 负数转为正数
            }
        }
        Arrays.sort(nums); // 排序
        // 题解：https://leetcode.cn/problems/find-the-k-sum-of-an-array/?envType=daily-question&envId=2024-03-09
        // total为正数之和，亦为nums的最大子序列和，有：
        // nums的第k大子序列和 = total - |nums|的第k小子序列和
        return total - kMinSumBS(nums, k);
    }

    int cnt;
    long kMinSumBS(int[] nums, int k) {
        long sum = 0;
        for(int num : nums) {
            sum += num;
        }
        long left = 0, right = sum;
        // f(x): f(x)为“序列和小于等于x“的子序列的个数，随着x增大而增大
        // 查找第一个使得f(x)>=k-1的x，则x
        while(left < right) {
            long mid = left + (right - left) / 2;
            cnt = 0;
            dfs(nums, mid, k, 0, 0); // dfs求cnt
            if(cnt >= k - 1) { 
                right = mid;
            } else {
                left = mid +1;
            }
        }
        return right;
    }

    void dfs(int[] nums, long x, int k, long sum, int idx) {
        if(idx == nums.length) {
            return; // 递归出口
        }
        if(sum + nums[idx] > x) {
            return; // 剪枝：后续sum均大于x，无需再递归
        }
        if(cnt >= k - 1) {
            return; // 剪枝：cnt已经大于等于k-1，目的已达到
        }
        cnt++;
        dfs(nums, x, k, sum + nums[idx], idx + 1);
        dfs(nums, x, k, sum, idx + 1);
    }

}
// @lc code=end

// 堆
class Solution {
    public long kSum(int[] nums, int k) {
        int n = nums.length;
        long total = 0; // 正数之和
        for(int i = 0; i < n; i++) {
            if(nums[i] >= 0) {
                total += nums[i];
            } else {
                nums[i] = -nums[i]; // 负数转为正数
            }
        }
        Arrays.sort(nums); // 排序
        // 题解：https://leetcode.cn/problems/find-the-k-sum-of-an-array/?envType=daily-question&envId=2024-03-09
        // total为正数之和，亦为nums的最大子序列和，有：
        // nums的第k大子序列和 = total - |nums|的第k小子序列和
        return total - kMinSum(nums, k);
    }

    // 求非递减的非负数组nums的第k小子序列和
    long kMinSum(int[] nums, int k) {
        PriorityQueue<long[]> heap = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        long[] curr = new long[]{0, -1};
        heap.offer(curr);
        for(int i = 0; i < k; i++) {
            curr = heap.poll();
            long sum = curr[0];
            int idx = (int)curr[1];
            if(idx == nums.length - 1) {
                continue;
            }
            heap.offer(new long[]{sum + nums[idx + 1], idx + 1});
            if(idx != -1) {
                heap.offer(new long[]{sum - nums[idx] + nums[idx + 1], idx + 1});
            }
        }
        return curr[0];
    }
}