package 双周赛.DWC71_20220205;

import java.util.Arrays;

/**
 * @author Tomoyo  email:amedeusmaho@163.com
 * @create 2022-02-06 20:28
 * 排序、贪心
 */
public class LC5984拆分数位后四位数字的最小和 {
    public int minimumSum(int num) {
        int[] bit = new int[4];
        int i = 0;
        while (i < 4) {
            bit[i++] = num % 10;
            num /= 10;
        }
        Arrays.sort(bit);
        return bit[0] * 10 + bit[1] * 10 + bit[2] + bit[3];
    }
}
