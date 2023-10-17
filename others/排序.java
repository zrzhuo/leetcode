
public class Main {
    public static void main(String[] args) {
        int[] nums = {7, 11, 1, 5, 12, 9, 4, 5, 6, 7};
        new QuickSort().sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}

// 快速排序
class QuickSort {
    public void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int low, int high) {
        if(low >= high)
            return;
        int pivotIndex = partition(nums, low, high);
        quickSort(nums, low, pivotIndex - 1); // 递归
        quickSort(nums, pivotIndex + 1, high); // 递归
    }

    private int partition(int[] nums, int low, int high) {
        int pivot = new Random().nextInt(high - low + 1) + low; // 随机选取中枢
        swap(nums, low, pivot); // 将中枢放在首位
        int left = low + 1, right = high;
        while (true) {
            while (left <= right && nums[left] <= nums[low])
                left++;  // 在左侧寻找大于中枢的数
            while (left <= right && nums[right] >= nums[low])
                right--; // 在右侧寻找小于中枢的数
            if(left >= right)
                break; // 两指针相遇, 退出循环
            swap(nums, left, right); // 交换两数
        }
        swap(nums, right, low); // 将中枢交换到正确位置
        return right;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// 冒泡排序
class BubbleSort {
    public void sort(int[] nums) {
        // k为未排序序列的长度
        for(int k = nums.length; k > 0; k--) {
            boolean finished = true; // 排序是否完成
            // 对未排序序列进行一趟冒泡
            for(int i = 0; i < k - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                    finished = false; // 排序未完成
                }
            }
            if(finished)
                break; // 排序已完成, 提前退出循环
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// 归并排序
class MergeSort {
    public void sort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    public void mergeSort(int[] nums, int left, int right) {
        if(left >= right)
            return; // 递归出口
        int mid = (right - left) / 2 + left;
        mergeSort(nums, left, mid); // 递归
        mergeSort(nums, mid + 1, right); // 递归 
        merge(nums, left, right, mid); // 归并
    }

    private void merge(int[] nums, int left, int right, int mid) {
        int[] temp = new int[right - left + 1]; // 存放中间结果
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right)
            temp[k++] = nums[i] < nums[j] ? nums[i++] : nums[j++];
        while (i <= mid)
            temp[k++] = nums[i++];
        while (j <= right)
            temp[k++] = nums[j++];
        System.arraycopy(temp, 0, nums, left, k); // 中间结果拷贝到原数组
    }
}