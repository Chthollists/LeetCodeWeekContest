package 双周赛.DWC71_20220205;

import java.util.PriorityQueue;

/**
 * @author Tomoyo  email:amedeusmaho@163.com
 * @create 2022-02-06 20:06
 * 前缀和、后缀和、优先队列
 */
public class LC5987删除元素后和的最小差值 {

    public static void main(String[] args) {
        int[] nums = new int[]{7, 9, 5, 8, 1, 3};
        nums = new int[]{3, 1, 2};
        System.out.println(new LC5987删除元素后和的最小差值().minimumDifference(nums));
    }

    public long minimumDifference(int[] nums) {
        if (nums == null || nums.length == 0) return Long.MAX_VALUE;
        int len = nums.length, n = len / 3;
        long sum = 0L;

        // 小根堆, 存放数组中后 n 个最大元素
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 2 * n; i < len; i++) {
            minHeap.add(nums[i]);
            sum += nums[i];
        }

        // 更新大根堆和小根堆，找到前 n 个最小元素 和 后 n 个最大元素的分界点
        // 最大后缀和
        long[] sumSecond = new long[n + 1];
        sumSecond[n] = sum;
        for (int i = 2 * n - 1; i >= n; i--) {
            if (minHeap.peek() < nums[i]) {
                minHeap.add(nums[i]);
                sum += nums[i] - minHeap.poll();
            }
            sumSecond[i - n] = sum;
        }

        // 大根堆, 存放数组中前 n 个最小元素
        sum = 0L;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < n; i++) {
            maxHeap.add(nums[i]);
            sum += nums[i];
        }

        // 最小前缀和
        long[] sumFirst = new long[n + 1];
        sumFirst[0] = sum;
        for (int i = n; i < 2 * n; i++) {
            if (maxHeap.peek() > nums[i]) {
                maxHeap.add(nums[i]);
                sum += nums[i] - maxHeap.poll();
            }
            sumFirst[i - n + 1] = sum;
        }

        long minDiff = Long.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            minDiff = Math.min(minDiff, sumFirst[i] - sumSecond[i]);
        }
        return minDiff;

    }
}
