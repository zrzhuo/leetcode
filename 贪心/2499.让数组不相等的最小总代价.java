/*
 * @lc app=leetcode.cn id=2499 lang=java
 *
 * [2499] 让数组不相等的最小总代价
 */

// @lc code=start
class Solution {
    public long minimumTotalCost(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // 将数组分为两部分，相等的一部分称为内部，剩余的部分称为外部
        // 统计内部的有关信息
        int[] cnt = new int[n + 1];
        int maxCnt = 0, maxNum = 0, len = 0;
        long idxSum = 0;
        for(int i = 0; i < n; i++) {
            if(nums1[i] == nums2[i]) {
                int num = nums1[i];
                cnt[num]++; // 统计数字的出现次数
                if(cnt[num] > maxCnt) {
                    maxCnt = cnt[num];
                    maxNum = num; // 出现次数最多的数字
                }
                idxSum += i; // 内部元素的下标之和
                len++; // 内部元素的个数
            }
        }
        // 可以内部解决, 即内部不存在"数量多于总数一半"的数字, 从而只需要在内部两两交换即可
        if(maxCnt <= len / 2)
            return idxSum; 
        // 不可以内部解决, 即内部存在"数量多于总数一半"的数字, 此时从外部寻找帮助, 即将多出的数字交换到外部
        // 贪心, 优先选择下标小的"可交换的"外部元素
        for(int i = 0; i < n; i++) {
            if(nums1[i] != nums2[i]) {
                if(nums1[i] != maxNum && nums2[i] != maxNum) {
                    idxSum += i;
                    len++;
                }
                if(maxCnt <= len / 2)
                    return idxSum; // 数量已足够
            }
        }
        return -1;
        
    }
}
// @lc code=end

