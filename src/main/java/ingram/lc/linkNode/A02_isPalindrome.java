package ingram.lc.linkNode;

/**
 * 234. 回文链表
 *
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 * 输入：head = [1,2,2,1]
 * 输出：true
 *
 * 输入：head = [1,2]
 * 输出：false
 */
public class A02_isPalindrome {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        // 1 -> 2 -> 3 -> 4 <- 3 <- 2    1
        ListNode n1 = head;
        ListNode n2 = head;
        while (null !=n2.next && null!=n2.next.next){
            n1 = n1.next;
            n2 = n2.next.next;
        }
        ListNode reverseList = reverseList(n1);
        ListNode n3 = reverseList;
        n2 = head;
        while (null != n3.next){
            if(n2.val != n3.val){
                return false;
            }
            n3 = n3.next;
            n2 = n2.next;
        }
        reverseList(reverseList);
        return true;
    }

    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
