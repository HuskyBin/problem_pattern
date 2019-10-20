315： Count Of Smaller Numbers After Self

public class Solution {
    
    public List<Integer> countSmaller(int[] nums) {
        int len = (nums == null? 0 : nums.length);
        
        int[] idxs = new int[len];
        int[] count = new int[len];
        
        for (int i = 0; i < len; i++) idxs[i] = i;
        
        mergeSort(nums, idxs, 0, len, count);
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i : count) list.add(i);
        
        return list;
    }
    
    private void mergeSort(int[] nums, int[] idxs, int start, int end, int[] count) {
        if (start + 1 >= end) return;
        
        int mid = (end - start) / 2 + start;
        mergeSort(nums, idxs, start, mid, count);
        mergeSort(nums, idxs, mid, end, count);
        
        merge(nums, idxs, start, end, count);
    }
    
    private void merge(int[] nums, int[] idxs, int start, int end, int[] count) {
        int mid = (end - start) / 2 + start;
        
        int[] tmp = new int[end - start];
        int[] tmpidx = new int[end - start];
        int i = start, j = mid, k = 0;
        while (k < end - start) {
            if (i < mid) {
                if (j < end && nums[j] < nums[i]) {
                    tmpidx[k] = idxs[j];
                    tmp[k++] = nums[j++];
                } else {
                    count[idxs[i]] += j - mid; // add those already counted
                    tmpidx[k] = idxs[i];
                    tmp[k++] = nums[i++];
                }
                
            } else {
                tmpidx[k] = idxs[j];
                tmp[k++] = nums[j++];
            }
        }
        
        System.arraycopy(tmpidx, 0, idxs, start, end - start);
        System.arraycopy(tmp, 0, nums, start, end - start);
    }
}


439: Reverse Pairs:

class Solution {
public int reversePairs(int[] nums) {
    return reversePairsSub(nums, 0, nums.length - 1);
}
    
private int reversePairsSub(int[] nums, int l, int r) {
    if (l >= r) return 0;
        
    int m = l + ((r - l) >> 1);
    int res = reversePairsSub(nums, l, m) + reversePairsSub(nums, m + 1, r);
        
    int i = l, j = m + 1, k = 0, p = m + 1;
    int[] merge = new int[r - l + 1];
        
    while (i <= m) {
        while (p <= r && nums[i] > 2L * nums[p]) p++;
        res += p - (m + 1);
				
        while (j <= r && nums[i] >= nums[j]) merge[k++] = nums[j++];
        merge[k++] = nums[i++];
    }
        
    while (j <= r) merge[k++] = nums[j++];
        
    System.arraycopy(merge, 0, nums, l, merge.length);
        
    return res;
}
}


327: Count Range of Sum
class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null) {
            return 0;
        }
        long[] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        
        return mergeCore(sum, 0, sum.length - 1, lower, upper);
    }
    
    private int mergeCore(long[] sum, int start, int end, int lower, int upper) {
        if (start >= end) {
            return 0;
        }
        
        int mid = start + (end - start) / 2;
        
        int count = mergeCore(sum, start, mid, lower, upper) + mergeCore(sum, mid + 1, end, lower, upper);
        
        long[] arr = new long[end - start + 1];
        
        int i = start;
        int j = mid + 1;
        
        int k = mid + 1;
        
        int arrIndex = 0;
        int secondIndex = mid + 1;
        while (i <= mid) {
            while (j <= end && sum[j] - sum[i] < lower) {
                j++;
            }
            while (k <= end && sum[k] - sum[i] <= upper) {
                k++;
            }
            count += k - j;
            
            while (secondIndex <= end && sum[secondIndex] < sum[i]) {
                arr[arrIndex++] = sum[secondIndex++];
            }
            
            arr[arrIndex++] = sum[i++];
        }
        
        while (secondIndex <= end) {
            arr[arrIndex++] = sum[secondIndex++];
        }
        
        System.arraycopy(arr, 0, sum, start, arr.length);
        return count;
    }
}

https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/138154/The-C%2B%2B-merge-sort-template-for-pairs-'i'-'j'-problem

Merge Sort
Merge Sort is suitable for problems which look for some pairs i, j
such that i < j, and nums[i], nums[j] satisfy some constraints.

We can find such pairs during merge sort. In each recursion, before we merge two sorted subarrays, the i is in the left sorted subarray, and the j is in the right sorted subarray. So, we can just go through both sorted subarray to find the valid i and j pairs. As long as this step is O(n), the total time complexity would be O(n log n).

    // [l, r) is the interval to be sorted
    int sort_count(iterator l, iterator r) {
        if (r - l <= 1) return; 
        // step 1. find the middle
        iterator m = l + (r - l) / 2;
        // step 2. sort left and right subarray
        int count = sort_count(l, m) + sort_count(m, r);
        /* step 3. write your code here for counting the pairs (i, j).*/
				
        // step 4. call inplace_merge to merge
        inplace_merge(l, m, r);
        return count;
    }
Such problems do not care about the order of js as long as j > i. The j could be possibly anywhere after i as long as nums[j] and nums[i] satisfy the given constraint. This is the KEY feature because we can sort the nums[j]s. Balanced Binary Search Tree would work well for this purpose, because we can insert the elements one-by-one, store the inserted element arrays in BST and search for those valid js in BST given i. But it requires extra code to build the BST and also keep it balanced to avoid O(N^2) worst time complexity. For problems like these, Segment Tree and Binary Indexed Tree are also good choices and give O(n log n) time complexity.

Merge sort avoids extra data structure. For code interviews, merge sort code seems to be easier and you can just mention how to use BST, Segment Tree and Binary Indexed Tree to get more credits for the interview.

C++ provides built-ins for merge sort including:

merge(l1.begin(), l1.end(), l2.begin(), l2.end(), result.begin()); which stores the merged array in result
inplace_merge(l.begin(), l.middle, l.end()) where array [begin, middle) is merged with array [middle, end).
LeetCode 315. Count of Smaller Numbers After Self. Return the number of js such that i < j and nums[j] < nums[i].

    #define iterator vector<vector<int>>::iterator
    void sort_count(iterator l, iterator r, vector<int>& count) {
        if (r - l <= 1) return;
        iterator m = l + (r - l) / 2;
        sort_count(l, m, count);
        sort_count(m, r, count);
        for (iterator i = l, j = m; i < m; i++) {
            while (j < r && (*i)[0] > (*j)[0]) j++;
            count[(*i)[1]] += j - m; // add the number of valid "j"s to the indices of *i
        }
        inplace_merge(l, m, r);
    }
    vector<int> countSmaller(vector<int>& nums) {
        vector<vector<int>> hold;
        int n = nums.size();
        for (int i = 0; i < n; ++i) hold.push_back(vector<int>({nums[i], i})); // "zip" the nums with their indices
        vector<int> count(n, 0);
        sort_count(hold.begin(), hold.end(), count);
        return count;
    }
LeetCode 493. Reverse Pairs. Return the number of reverse pairs s.t. i < j and nums[i] > 2*nums[j].

    int sort_count(vector<int>::iterator begin, vector<int>::iterator end) {
        if (end - begin <= 1) return 0;
        vector<int>::iterator middle = begin + (end  - begin) / 2;
        int count = 0;
        if (begin < middle) count += sort_count(begin, middle);
        if (middle < end) count += sort_count(middle, end);
        vector<int>::iterator i, j;
        for (i = begin, j = middle; i < middle; ++i) { // double pointers trick
            while (j < end && *i > 2L * *j) {
                j++;
            }
            count += j - middle;
        }
        inplace_merge(begin, middle, end);
        return count;
    }
    int reversePairs(vector<int>& nums) {
        return sort_count(nums.begin(), nums.end());
    }
LeetCode 327. Count of Range Sum. Return the number of range sums that lie in [lower, upper] inclusive-inclusive. Let prefix-array sum be sums[0..n+1], the problem is to find pairs of i and j such that lower <= sums[j] - sums[i] <= upper.

    int countRangeSum(vector<int>& nums, int lower, int upper) {
        int n = nums.size();
        vector<long> sums(n + 1, 0);
        for (int i = 0; i < n; ++i) sums[i + 1] = sums[i] + nums[i];
        return sort_count(sums, 0, n + 1, lower, upper);
    }
    
    int sort_count(vector<long>& sums, int l, int r, int lower, int upper) {
        if (r - l <= 1) return 0;
        int m = (l + r) / 2, i, j1, j2;
        int count = sort_count(sums, l, m, lower, upper) + sort_count(sums, m, r, lower, upper);
        for (i = l, j1 = j2 = m; i < m; ++i) { 
            // we have two j pointers now and one i pointer, but still linear time
            while (j1 < r && sums[j1] - sums[i] < lower) j1++; 
            while (j2 < r && sums[j2] - sums[i] <= upper) j2++;
            count += j2 - j1;
        }
        inplace_merge(sums.begin() + l, sums.begin() + m, sums.begin() + r);
        return count;
    }
