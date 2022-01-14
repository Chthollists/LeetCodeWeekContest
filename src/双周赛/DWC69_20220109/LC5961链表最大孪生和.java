package 双周赛.DWC69_20220109;

import 双周赛.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chthollists email:
 * @create 2022-01-09 16:08
 * <p>
 * 在一个大小为 n 且 n 为 偶数 的链表中，对于 0 <= i <= (n / 2) - 1 的 i ，第 i 个节点（下标从 0 开始）的孪生节点为第 (n-1-i) 个节点 。
 * 比方说，n = 4 那么节点 0 是节点 3 的孪生节点，节点 1 是节点 2 的孪生节点。这是长度为 n = 4 的链表中所有的孪生节点。
 * 孪生和 定义为一个节点和它孪生节点两者值之和。
 * 给你一个长度为偶数的链表的头节点 head ，请你返回链表的 最大孪生和 。
 * 链表的节点数目是 [2, 105] 中的 偶数 。
 * 1 <= Node.val <= 105
 * 输入：head = [5,4,2,1]
 * 输出：6
 */
public class LC5961链表最大孪生和 {
    public int pairSum1(ListNode head) {
        if (head == null) return 0;
        List<Integer> valList = new ArrayList<>();
        valList.add(head.val);
        while (head.next != null) {
            head = head.next;
            valList.add(head.val);
        }
        int n = valList.size();
        int maxTwinSum = 0;
        for (int i = 0; i < n / 2; i++) {
            maxTwinSum = Math.max(maxTwinSum, valList.get(i) + valList.get(n - i - 1));
        }
        return maxTwinSum;
    }

    public int pairSum(ListNode head) {
        if (head == null) return 0;
        ListNode fast = head;
        ListNode slow = head;
        // 1. 寻找链表中点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 2. 反转后半链表
        ListNode cur = slow;
        ListNode prev = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }

        // 3. 计算孪生和
        int maxTwinSum = 0;
        while(head != null && prev != null) {
            maxTwinSum = Math.max(maxTwinSum, head.val + prev.val);
            head = head.next;
            prev = prev.next;
        }
        return maxTwinSum;
    }
}
