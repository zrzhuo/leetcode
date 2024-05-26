/*
 * @lc app=leetcode.cn id=496 lang=java
 *
 * [496] 下一个更大元素 I
 */

// @lc code=start
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums2.length;
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack =  new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                int j = stack.pop();
                map.put(nums2[j], nums2[i]);
            }
            stack.push(i);
        }
        int[] res = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}
// @lc code=end

