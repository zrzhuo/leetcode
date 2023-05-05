/*
 * @lc app=leetcode.cn id=46 lang=java
 *
 * [46] 全排列
 */

// @lc code=start
// 31题：下一个排列
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 第一个排列
        int count = 1;
        for(int i = 1; i <= n; i++) {
            count *= i;
        }
        for(int i = 0; i < count; i++) {
            List<Integer> list = new ArrayList<>(n);
            for(int num : nums)
                list.add(num);
            result.add(list);
            nextPermutation(nums); // 下一个排列
        }
        return result;
    }

    public void nextPermutation(int[] nums) {
        int n = nums.length;
        // 寻找nums末尾的最长递减序列nums[s...n-1]
        int s = n - 1;
        while(s > 0 && nums[s - 1] >= nums[s]) {
            s--;
        }
        // s大于0, 即整个nums并非完全递减时, 进行如下操作
        if(s > 0) {
            int idx = binarySearch(nums, s, n - 1, nums[s - 1]); // 从nums[s...n-1]中查找最后一个比nums[s-1]大的数
            swap(nums, s - 1, idx); // 交换
        }
        reverse(nums, s, n - 1); // 再将nums[s...n-1]进行一次翻转
    }

    int binarySearch(int[] nums, int start, int stop, int target) {
        int left = start, right = stop + 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] <= target) 
                right = mid;
            else
                left = mid + 1;
        }
        return right - 1;
    }

    void reverse(int[] nums, int start, int stop) {
        int i = start, j = stop;
        while(i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
// @lc code=end


// 回溯
class Solution {
    List<List<Integer>> result; 
    LinkedList<Integer> temp;

    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        temp = new LinkedList<>();
        backTrack(nums);
        return result;
    }

    void backTrack(int[] nums) {
        int n = nums.length;
        // 所有数字都已经纳入当前排列
        if(temp.size() == n) {
            result.add(new ArrayList<>(temp));
            return;
        }
        // 枚举未使用的数字: 数组的前半部分[0, temp.size)是已经使用过的数
        for(int i = temp.size(); i < n; i++) {
            temp.addLast(nums[i]);
            swap(nums, i, temp.size() - 1); // 把当前数交换到前半部分，防止后续递归再次被纳入
            backTrack(nums);
            swap(nums, i, temp.size() - 1); // 还原数组
            temp.removeLast(); 
        }
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


// 回溯
class Solution {
    List<List<Integer>> result; 
    LinkedList<Integer> temp;
    boolean[] used; // 记录数字是否已经被使用

    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        temp = new LinkedList<>();
        used = new boolean[nums.length];
        backTrack(nums);
        return result;
    }

    void backTrack(int[] nums) {
        int n = nums.length;
        // 所有数字都已经纳入当前排列
        if(temp.size() == n) {
            result.add(new ArrayList<>(temp));
            return;
        }
        // 枚举未使用的数字
        for(int i = 0; i < n; i++) {
            if(!used[i]) {
                temp.addLast(nums[i]);
                used[i] = true; // 记录已经使用过，防止后续递归再次被纳入
                backTrack(nums);
                temp.removeLast();
                used[i] = false; // 还原为未使用
            }
        }
    }
}