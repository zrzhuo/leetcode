/*
 * @lc app=leetcode.cn id=2317 lang=java
 *
 * [2317] 操作后的最大异或和
 */

a & (a ^ x) = r
1    1   0    1    
1    1   1    0 ***
0    0   0    0
0    0   1    0
可见, 该操作只能将1变为0

a ^ b ^ c ^ d ^... 的结果, 取决于某位置上1的个数的奇偶性

class Solution {
    public int maximumXOR(int[] nums) {
        int[] cnt = new int[32]; // 统计32个位置上1的个数
        for(int num : nums) {
            for(int k = 0; k < 32; k++) {
                if(((num >> k) & 1) == 1) {
                    cnt[k]++;
                }
            }
        }
        // 让每个位置上1的个数都是奇数, 即可保证该位置异或后为1
        int res = 0;
        for(int i = 0; i < 32; i++) {
            if(cnt[i] != 0) {
                res += 1 << i;
            }
        }
        return res;
    }
}

进一步, 只要存在一个数在某位置上为1, 则结果在该位置上一定可以为1
故使用或操作保留每个位置的1即可

// @lc code=start
class Solution {
    public int maximumXOR(int[] nums) {
        int res = 0;
        for(int num : nums) {
            res |= num; // 或操作可以保留所有的1
        }
        return res;
    }
}
// @lc code=end
