/*
 * @lc app=leetcode.cn id=39 lang=java
 *
 * [39] 组合总和
 */

// @lc code=start
// 回溯
class Solution {
    List<List<Integer>> result;
    LinkedList<Integer> temp;

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        result = new ArrayList<>();
        temp = new LinkedList<>();
        backTrack(nums, target, 0, 0);
        return result;
    }

    void backTrack(int[] nums, int target, int sum, int start) {
        if(sum > target)
            return; // sum已经大于target, 由于所有数字都是正数, 再添加数字依然会大于target, 故直接返回
        if(sum == target) {
            result.add(new ArrayList<>(temp)); // 记录满足要求的组合
            return;
        }
        // 从nums[start...n-1]中选择一个数
        for(int i = start; i < nums.length; i++) {
            temp.addLast(nums[i]);
            backTrack(nums, target, sum +  nums[i], i); // 因为数字可以重复, 故i依然可以选择, 但i之前的数字不能再选择, 否则会出现重复的组合
            temp.removeLast();
        }
    }
}
// @lc code=end


// 回溯 + 剪枝
class Solution {
    List<List<Integer>> result;
    LinkedList<Integer> temp;

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        result = new ArrayList<>();
        temp = new LinkedList<>();
        backTrack(nums, target, 0, 0);
        return result;
    }

    void backTrack(int[] nums, int target, int sum, int start) {
        if(sum > target)
            return; 
        if(sum == target) {
            result.add(new ArrayList<>(temp)); 
            return;
        }
        for(int i = start; i < nums.length; i++) {
            if(sum + nums[i] > target)
                continue; // 剪枝: 当前数字偏大, 后续组合的总和一定大于target, 故跳过后续递归
            temp.addLast(nums[i]);
            backTrack(nums, target, sum +  nums[i], i); 
            temp.removeLast();
        }
    }
}


// 回溯 + 剪枝
class Solution {
    List<List<Integer>> result;
    LinkedList<Integer> temp;

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        result = new ArrayList<>();
        temp = new LinkedList<>();
        Arrays.sort(nums); // 剪枝: 排序
        backTrack(nums, target, 0, 0);
        return result;
    }

    void backTrack(int[] nums, int target, int sum, int start) {
        if(sum > target)
            return; 
        if(sum == target) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i = start; i < nums.length; i++) {
            if(sum + nums[i] > target)
                break; // 剪枝: 由于nums从小到大排序, 当前数字偏大时, 后续数字必然偏大, 故直接break
            temp.addLast(nums[i]);
            backTrack(nums, target, sum +  nums[i], i); 
            temp.removeLast();
        }
    }
}

