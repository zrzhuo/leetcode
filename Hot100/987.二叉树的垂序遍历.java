/*
 * @lc app=leetcode.cn id=987 lang=java
 *
 * [987] 二叉树的垂序遍历
 */

// @lc code=start
// 先序遍历 + 整体排序
class Solution {
    List<Integer[]> nodes = new LinkedList<>(); // 记录所有的结点信息：[列编号，行编号，结点值]
    int minCol = 0;
    void preOrder(TreeNode root, int col, int row) {
        if(root == null)
            return;
        nodes.add(new Integer[]{col, row, root.val});
        preOrder(root.left, col - 1, row + 1);
        preOrder(root.right, col + 1, row + 1);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root == null)
            return res;
        // 先序遍历（层次遍历也可）
        preOrder(root, 0, 0);
        // 排序：按列标号、行编号、结点值进行排序
        Collections.sort(nodes, (Integer[] a, Integer[] b) -> {
            if(a[0] != b[0])
                return a[0] - b[0];
            if(a[1] != b[1])
                return a[1] - b[1];
            return a[2] - b[2];
        });
        // 转换为List
        int preCol = -1000;
        for(int i = 0; i < nodes.size(); ++i) {
            Integer[] node = nodes.get(i);
            int col = node[0], val = node[2];
            if(col == preCol){
                List<Integer> list = res.get(res.size() - 1);
                list.add(val);
            } else {
                List<Integer> list = new LinkedList<>();
                list.add(val);
                res.add(list);
                preCol = col;
            }
        }
        return res;
    }
}
// @lc code=end


// 层次遍历 + 部分排序
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root == null)
            return res;
        Map<Integer, List<Integer>> map = new HashMap<>();  // 按列统计结点值
        Queue<TreeNode> nodeQue = new LinkedList<>(); // 层序遍历需要的队列
        Queue<Integer> indexQue = new LinkedList<>(); // 层序遍历时，同步记录结点应处于的”列的编号（相对值）”
        nodeQue.offer(root); 
        indexQue.offer(0); // root结点处于编号为0的列
        int minIdx = 0; // 记录最左列的编号
        while(!nodeQue.isEmpty()) {
            int size = nodeQue.size();
            Map<Integer, List<Integer>> addMap = new HashMap<>();  // 按列统计新增的结点值
            for(int i = 0; i < size; ++i) {
                TreeNode cur = nodeQue.poll(); // 当前结点
                int idx = indexQue.poll(); // 当前结点处于的列的编号
                minIdx = Math.min(minIdx, idx); // 更新最左列的编号
                // 将当前结点的值放在对应的列表中
                List<Integer> list = addMap.getOrDefault(idx, new LinkedList<>());
                list.add(cur.val); 
                addMap.put(idx, list);
                // 处理子结点，入队的同时，其应处于的列的编号同时入队
                if(cur.left != null) {
                    nodeQue.offer(cur.left);
                    indexQue.offer(idx - 1);
                }
                if(cur.right != null) {
                    nodeQue.offer(cur.right);
                    indexQue.offer(idx + 1);
                }
            }
            // 将addMap和并到map中
            for(Map.Entry<Integer, List<Integer>> entry : addMap.entrySet()) {
                int idx = entry.getKey();
                List<Integer> vals = entry.getValue();
                Collections.sort(vals); // 排序
                List<Integer> list = map.getOrDefault(idx, new LinkedList<>());
                list.addAll(vals); // 合并
                map.put(idx, list);
            }
        }
        // 根据按编号提取每个列表
        for(int i = 0; i < map.size(); ++i)
            res.add(map.get(minIdx + i));
        return res;
    }
}