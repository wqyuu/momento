package ingram.lc.linkNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 */
public class A07_removeNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next=head;
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public ListNode removeNthFromEndME(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode n1 = dummy;
        ListNode n2 = head;
        int s = n;
        while (s!=0){
            n2 = n2.next;
            s --;
        }
        while (null != n2){
            n1 = n1.next;
            n2 = n2.next;
        }
        n1.next = n1.next.next;

        return dummy.next;
    }













    public ListNode removeNthFromEnd20240113(ListNode head, int n) {

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        // -1->1->[2]->3->4->5
        // -1->1->2->3->4->5
        // n=2

        ListNode n1 = dummyNode;
        ListNode n2 = dummyNode;
        while (n!=0){
            n1 = n1.next;
            n--;
        }
        while (null!=n1.next && null!=n2.next){
            n1 = n1.next;
            n2 = n2.next;
        }
        if(null!=n2.next){
            n2.next = n2.next.next;
        }
        return dummyNode.next;
    }














}
