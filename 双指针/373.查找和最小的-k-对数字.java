/*
 * @lc app=leetcode.cn id=373 lang=java
 *
 * [373] 查找和最小的 K 对数字
 */

// @lc code=start
// 优先队列
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        List<List<Integer>> result = new ArrayList<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((int[] a, int[] b) -> {
            return nums1[a[0]] + nums2[a[1]] - nums1[b[0]] - nums2[b[1]];
        });
        for(int i = 0; i < m; i++) {
            minHeap.add(new int[]{i, 0});
        }
        for(int t = 0; t < k; t++) {
            if(minHeap.isEmpty()) 
                break;
            int[] min = minHeap.poll();
            int i = min[0], j = min[1];
            List<Integer> curr = new ArrayList<>();
            curr.add(nums1[i]);
            curr.add(nums2[j]);
            result.add(curr);
            if(j + 1 < n)
                minHeap.add(new int[]{i, j + 1});
        }
        return result;
    }
}
// @lc code=end

// 二分 + 双指针 + 剪枝
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        // x和f(x)：x为一个数，f(x)是“和小于等于x”的数对的个数，则f(x)单调递增
        int left = nums1[0] + nums2[0], right = nums1[m - 1] + nums2[n - 1];
        // 从左到右查找第一个使得f(x)>=k的x
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(func(mid, nums1, nums2, k) >= k)
                right = mid;
            else 
                left = mid + 1;
        }
        int x = right; // 目标k对数字的和应该小于等于x
        // 获取结果
        List<List<Integer>> result = new ArrayList();
        for(int num1 : nums1) {
            for(int num2 : nums2) {
                if(num1 + num2 < x)
                    result.add(Arrays.asList(num1, num2));
                else
                    break;
            }
        }
        k -= result.size();
        if(k > 0) {
            for(int num1 : nums1) {
                for(int num2 : nums2) {
                    if(num1 + num2 == x) {
                        result.add(Arrays.asList(num1, num2));
                        if(--k == 0)
                            return result;
                    } else if(num1 + num2 > x) {
                        break;
                    }
                }
            }
        }
        return result;
    } 

    int func(int x, int[] nums1, int[] nums2, int k) {
        // 求“和小于等于x”的数对的个数
        int f = 0;
        int j = nums2.length - 1;
        for(int i = 0; i < nums1.length; i++) {
            while(j >= 0 && nums1[i] + nums2[j] > x) {
                j--;
            }
            f += j + 1;
            // 剪枝：后续j都为-1，f不会再增大
            if(j == -1)
                break;
            // 剪枝：f已经大于等于k，f无需再增大
            if(f >= k)
                break;
        }
        return f;
    }
}
