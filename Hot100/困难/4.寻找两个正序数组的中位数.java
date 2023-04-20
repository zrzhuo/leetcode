/*
 * @lc app=leetcode.cn id=4 lang=java
 *
 * [4] 寻找两个正序数组的中位数
 */

// @lc code=start
// 时间复杂度: O(log(m+n))
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if((m + n) % 2 == 1) {
            return 1.0 * getKthElement(nums1, nums2, (m + n) / 2 + 1);
        } else {
            int first = getKthElement(nums1, nums2, (m + n) / 2);
            int second = getKthElement(nums1, nums2, (m + n) / 2 + 1);
            return (m + n) % 2 == 0 ? (1.0 * first + second) / 2 : 1.0 * second;
        }
    }

    public int getKthElement(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int s1 = 0, s2 = 0;
        while (true) {
            // 边界情况
            if (s1 == m)
                return nums2[s2 + k - 1];
            if (s2 == n)
                return nums1[s1 + k - 1];
            if (k == 1)
                return Math.min(nums1[s1], nums2[s2]);
            // 正常情况
            int off = k / 2;
            int ns1 = Math.min(s1 + off, m) - 1;
            int ns2 = Math.min(s2 + off, n) - 1;
            if (nums1[ns1] <= nums2[ns2]) {
                k -= (ns1 - s1 + 1);
                s1 = ns1 + 1;
            } else {
                k -= (ns2 - s2 + 1);
                s2 = ns2 + 1;
            }
        }
    }
}
// @lc code=end


// 时间复杂度: O(m+n)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int i = 0, j = 0;
        int first = 0, second = 0; // 中间的两个数
        for(int k = 0; k < (m + n) / 2; k++) {
            if(i < m && j < n)
                first = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
            else if (i < m)
                first = nums1[i++];
            else if (j < n)
                first = nums2[j++];
        }
        if(i < m && j < n)
            second = nums1[i] < nums2[j] ? nums1[i] : nums2[j];
        else if (i < m)
            second = nums1[i];
        else if (j < n)
            second = nums2[j];
        return (m + n) % 2 == 0 ? (1.0 * first + second) / 2 : 1.0 * second;
    }
}