/*
 * @lc app=leetcode.cn id=78 lang=java
 *
 * [78] 子集
 */

class Solution {
    List<List<Integer>> result;
    LinkedList<Integer> temp;
    public List<List<Integer>> subsets(int[] nums) {
        result = new ArrayList<>();
        temp = new LinkedList<>();
        backTrack(nums, 0);
        return result;
    }
    
    void backTrack(int[] nums, int start) {
        result.add(new ArrayList<>(temp));
        // 枚举每一个可以选择的数作为当前选择的数
        for(int i = start; i < nums.length; i++) {
            temp.addLast(nums[i]);
            backTrack(nums, i + 1); // 递归选择下一个数
            temp.removeLast(); 
        }
    }
}


// @lc code=start
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // 空集
        // 枚举每个数
        for(int i = 0; i < nums.length; i++) {
            int size = result.size();
            // 枚举当前已纳入所有的子集
            for(int j = 0; j < size; j++) {
                List<Integer> curr = new ArrayList<>(result.get(j));
                curr.add(nums[i]); // 加入当前数
                result.add(curr);
            }
        }
        return result;
    }
}
// @lc code=end

nums: [0, 1, 2, 3,]
第0轮: []
第1轮: [] [0]
第2轮: [] [0] [1] [0,1]
第3轮: [] [0] [1] [0,1] [2] [0,2] [1,2] [0,1,2]
第4轮: [] [0] [1] [0,1] [2] [0,2] [1,2] [0,1,2] [3] [0,3] [1,3] [0,1,3] [2,3] [0,2,3] [1,2,3] [0,1,2,3]
