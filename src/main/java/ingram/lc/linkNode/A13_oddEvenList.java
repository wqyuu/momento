package ingram.lc.linkNode;

/**
 * 328 奇偶链表
 * 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
 * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
 * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
 * 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
 *
 * 示例 1:
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,3,5,2,4]
 */
public class A13_oddEvenList {


    /**
     * 分离节点后合并
     * 维护两个指针 odd 和 even 分别指向奇数节点和偶数节点，初始时 odd = head，even = evenHead。
     * 通过迭代的方式将奇数节点和偶数节点分离成两个链表，每一步首先更新奇数节点，然后更新偶数节点。
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode odd = head;
        ListNode evenHead = head.next;
        ListNode eve = head.next;
        while (null!=eve && null!= eve.next){
            odd.next = eve.next;
            eve.next = odd.next.next;
            odd = odd.next;
            eve = eve.next;
        }

        odd.next = evenHead;
        return head;
    }
}
