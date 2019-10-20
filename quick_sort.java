public class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }       
        int result = findKthLargestCore(nums, 0, nums.length - 1, nums.length - k);
        return result;
    }

    private int findKthLargestCore(int[] nums, int start, int end, int k) {
        int left = start;
        int right = end;
        int pivot = nums[end];

        while (left != right) {
            while (nums[left] < pivot && left < right) {
                left++;
            }
            while (nums[right] >= pivot && left < right) {
                right--;
            }

            swap(nums, left, right);
        }
        swap(nums, left, end);

        if (k == left) {
            return pivot;
        }
        else if (k < left) {
            return findKthLargestCore(nums, start, left - 1, k);
        }
        else {
            return findKthLargestCore(nums, left + 1, end, k);
        }
    }

    public void swap(int[] nums, int aIndex, int bIndex) {
        int temp = nums[aIndex];
        nums[aIndex] = nums[bIndex];
        nums[bIndex] = temp;
    }
}
