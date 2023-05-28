/*
 * @lc app=leetcode.cn id=2322 lang=java
 *
 * [2322] 从树中删除边的最小分数
 */

// @lc code=start
class Solution {
    List<Integer>[] tree;
    int[] father; // father[i]: 结点i的父节点
    int[] xor; // xor[i]: 以结点i为根的子树的异或和
    
    // 计算以curr为根的子树的异或和，并存入xor数组
    int dfs(int curr, int[] nums) {
        int x = nums[curr];
        for(int child : tree[curr]) {
            if(child != father[curr]) {
                father[child] = curr;
                x ^= dfs(child, nums);
            }
        }
        xor[curr] = x;
        return x;
    }

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length, result = Integer.MAX_VALUE;
        xor = new int[n];
        father = new int[n];
        // 建树（图）
        tree = new List[n];
        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for(int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }
        father[0] = -1; // 以结点0作为根
        dfs(0, nums);
        // isChild[i][j]: 结点j是否是结点i的子孙
        boolean[][] isChild = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            int fa = father[i];
            while(fa != -1) {
                isChild[fa][i] = true;
                fa = father[fa];
            }
        }
        // 选取两个子树进行分割
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < n; j++) {
                if(i == j)
                    continue;
                if(isChild[i][j]) 
                    result = Math.min(result, findMin(xor[j], xor[i] ^ xor[j], xor[0] ^ xor[i])); // 子树j在子树i上
                else if(isChild[j][i]) 
                    result = Math.min(result, findMin(xor[i], xor[j] ^ xor[i], xor[0] ^ xor[j])); // 子树i在子树j上
                else 
                    result = Math.min(result, findMin(xor[i], xor[j], xor[0] ^ xor[i] ^ xor[j])); // 子树i和子树j独立
            }
        }
        return result;
    }

    int findMin(int a, int b, int c) {
        int max = Math.max(a, Math.max(b, c));
        int min = Math.min(a, Math.min(b, c));
        return max - min;
    }
}
// @lc code=end

