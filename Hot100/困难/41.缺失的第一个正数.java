/*
 * @lc app=leetcode.cn id=41 lang=java
 *
 * [41] 缺失的第一个正数
 */

对于长度为n的数组, 有如下结论:
当元素包含[1,n]中所有数时, 没有出现的最小整数为n+1, 否则, 没有出现的最小整数一定是[1,n]之一
因此, [1,n]没有出现的最小数即为结果, 若所有数都出现, 则结果为n+1

// @lc code=start
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            // 当nums[i]处于[1,n]内时，将其交换到"正确的位置"上, 即下标nums[i]-1处
            while(nums[i] > 0 && nums[i] < n + 1) {
                // 若待交换的两个数是相等的，已经无需继续交换，要退出循环，否则会无限循环
                if(nums[i] == nums[nums[i] - 1])
                    break;
                swap(nums, i, nums[i] - 1);
            }
        }
        // 依次检查[1,n], 看其是否被交换到正确的位置上
        for(int i = 1; i <= n; ++i)
            if(nums[i - 1] != i)
                return i;
        // [1,n]中的数全部都出现在正确的位置上 
        return n + 1;
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
// @lc code=end


// 负号标记法, 空间O(1)
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // 将所有小于等于0的数修改为n+1, 这样做不会影响结果, 因为只考虑位于[1,n]中的数字即可
        for(int i = 0; i < n; i++) {
            if(nums[i] <= 0)
                nums[i] = n + 1;
        }
        // 对于[1,n]中的数字, 将数字和下标做对应: 数字k对应下标k-1
        // 为出现过的数做标记, 标记方法是: 将"数字所对应的下标"处的元素置为负数
        for(int i = 0; i < n; i++) {
            int idx = Math.abs(nums[i]) - 1; // 当前数对应的下标
            if(idx < n) {
                nums[idx] = -Math.abs(nums[idx]); // 做标记
            }
        }
        // 此时对于[1,n]范围内的数, 所有出现的数的对应下标处都做了负数标记, 缺失的数则为未做标记
        for(int i = 0; i < n; i++) {
            if(nums[i] > 0)
                return i + 1;
        }
        return n + 1; // [1,n]中的数全部出现
    }
}

// 哈希表, 空间O(n)
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            set.add(num);
        }
        for(int i = 1; i <= n; i++) {
            if(!set.contains(i))
                return i;
        }
        return n + 1;
    }
}
