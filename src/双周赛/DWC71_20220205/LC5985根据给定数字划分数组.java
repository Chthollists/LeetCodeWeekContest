package 双周赛.DWC71_20220205;

import java.util.Arrays;

/**
 * @author Tomoyo  email:amedeusmaho@163.com
 * @create 2022-02-06 20:27
 * 双指针
 */
public class LC5985根据给定数字划分数组 {

    public int[] pivotArray(int[] nums, int pivot) {
        if (nums == null || nums.length == 0) return new int[0];
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, pivot);
        int l = 0, r = n - 1;
        int i = l, j = r;
        while (l < n || r >= 0) {
            if (nums[l] < pivot) res[i++] = nums[l];
            if (nums[r] > pivot) res[j--] = nums[r];
            l++;
            r--;
        }
        return res;

    }
}
