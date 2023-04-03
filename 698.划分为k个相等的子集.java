/*
 * @lc app=leetcode.cn id=698 lang=java
 *
 * [698] 划分为k个相等的子集
 */

// @lc code=start
class Solution {
    int per;
    int[] solved; // 记录每个状态是否可行
    boolean solving(int[] nums, int state, int total) {
        // 获取记忆
        if(solved[state] != -1)
            return solved[state] == 1;
        for(int i = 0; i < nums.length; i++) {
            // 剪枝: 由于已排序, 当前数字过大时, 后续数字都过大, 直接break
            if(total + nums[i] > per)
                break;
            // 当前数字还未被选用
            if(((state >> i) & 1) != 0) {
                int nextState = state ^ (1 << i); // 选用当前数字, 将对应位变为0
                int nextTotal = total + nums[i];
                if(nextTotal == per)
                    nextTotal = 0; // 当前组已满, 开始下一组, 从0开始
                if(solving(nums, nextState, nextTotal)) {
                    // 存储记忆
                    solved[state] = 1;
                    return true;
                }
            }
        }
        // 存储记忆
        solved[state] = 0; 
        return false;
    }
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0, n = nums.length;
        for(int i = 0; i < n; i++)
            sum += nums[i];
        if(sum % k > 0)
            return false; // 无法均分, 直接返回false
        per = sum / k;
        Arrays.sort(nums); // 排序, 方便剪枝
        if(nums[n - 1] > per)
            return false; // 剪枝: 当最大的数大于per时, 此时一定不能均分为k个组, 返回false
        // 初始化solved
        solved = new int[1 << n];
        Arrays.fill(solved, -1);
        solved[0] = 1;
        // 由于1 <= k <= len(nums) <= 16, 故可以用比特位代表该数字是否未被选用
        int state = (1 << n) - 1; // 初始时所有位置都为1, 即所有数字都为被选用
        return solving(nums, state, 0);
    }
}
// @lc code=end

