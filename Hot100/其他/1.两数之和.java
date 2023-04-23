/*
 * @lc app=leetcode.cn id=1 lang=java
 *
 * [1] 两数之和
 */

// 哈希表
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        // 记录已经出现的数字,即该数字在nums中的下标
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0;  i < n; i++) {
            int pre = target - nums[i];
            if(map.containsKey(pre)) {
                return new int[]{map.get(pre), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}

// @lc code=start
// 排序 + 双指针
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int[][] numsIndex = new int[n][2];
        for(int i = 0; i < n; i++) {
            numsIndex[i][0] = i;
            numsIndex[i][1] = nums[i];
        }
        Arrays.sort(numsIndex, (int[] a, int[] b) -> {
            return a[1] - b[1];
        });
        // 双指针
        int left = 0, right = n - 1;
        while(left < right) {
            int sum = numsIndex[left][1] + numsIndex[right][1];
            if(sum < target)
                left++;
            else if(sum > target)
                right--;
            else
                return new int[]{numsIndex[left][0], numsIndex[right][0]};
        }
        return null;
    }
}
// @lc code=end
