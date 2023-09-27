/*
 * @lc app=leetcode.cn id=2517 lang=java
 *
 * [2517] 礼盒的最大甜蜜度
 */

// @lc code=start
class Solution {
    public int maximumTastiness(int[] price, int k) {
        int n = price.length;
        Arrays.sort(price);
        if(k == 2) 
            return price[n - 1] - price[0];
        if(price[n - 1] == price[0])
            return 0; 
        // x和f(x)：x为甜蜜度，若存在甜蜜度为x的k容量礼盒，则f(x)为1，否则f(x)为0。易知随着x的增大，f(x)由1减为0
        int left = 0, right = (price[n - 1] - price[0]) / (k - 1) + 1;
        // 从左到右查找第一个使得f(x)<1的x
        while(left < right) {
            int mid = left + ((right - left) >> 1);
            if(func(price, k, mid) < 1)
                right = mid;
            else
                left = mid + 1;
        }
        return right - 1;
    }

    int func(int[] price, int k, int x) {
        int count = 1;
        int prev = price[0];
        for(int i = 1; i < price.length; i++) {
            if(price[i] - prev >= x) {
                count++;
                prev = price[i];
            }
        }
        return count >= k ? 1 : 0;
    }
}
// @lc code=end

