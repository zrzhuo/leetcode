/*
 * @lc app=leetcode.cn id=989 lang=java
 *
 * [989] 数组形式的整数加法
 */

// @lc code=start
class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        int n = num.length;
        // 直接加到最后一位, 然后再依次进位
        num[n - 1] += k;
        for(int i = n - 1; i > 0; i--) {
            num[i - 1] += num[i] / 10;
            num[i] %= 10;
        }
        int carry = num[0] / 10; // 最终进位
        num[0] %= 10;
        // 获取结果
        List<Integer> result = new ArrayList<>();
        while(carry > 0) {
            result.add(carry % 10);
            carry /= 10;
        }
        Collections.reverse(result);
        for(int i = 0; i < n; i++) {
            result.add(num[i]);
        }
        return result;
    }
}
// @lc code=end

