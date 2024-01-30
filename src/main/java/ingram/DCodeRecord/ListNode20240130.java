package ingram.DCodeRecord;

/**
 * @Author qywu11
 * @Date 2024/1/30 14:42
 * @Version 1.0
 * 链表
 */
public class ListNode20240130 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 203.移除链表元素
     * 题意：删除链表中等于给定值 val 的所有节点。
     * 示例 1： 输入：head = [1,2,6,3,4,5,6], val = 6 输出：[1,2,3,4,5]
     * 示例 2： 输入：head = [], val = 1 输出：[]
     * 示例 3： 输入：head = [7,7,7,7], val = 7 输出：[]
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        //创建一个虚拟头结点
        ListNode dummyNode=new ListNode(val-1);
        dummyNode.next=head;
        ListNode prev=dummyNode;
        //确保当前结点后还有结点
        while(prev.next!=null){
            if(prev.next.val==val){
                prev.next=prev.next.next;
            }else{
                prev=prev.next;
            }
        }
        return dummyNode.next;
    }

    // 相加链表 找到相交节点
    /**
     * 160.链表相交
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
     * 题目数据 保证 整个链式结构中不存在环。
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a1 = headA,a2 = headB;
        int n = 0;
        while (null != a1){
            a1 = a1.next;
            n ++;
        }
        while (null != a2){
            a2 = a2.next;
            n --;
        }
        if(n>=0){
            a1 = headA;
            a2 = headB;
        }else {
            a2 = headA;
            a1 = headB;
            n = -n;
        }
        while (null != a1 && n > 0){
            a1 = a1.next;
            n --;
        }
        while (null != a1 && null!=a2){
            if(a1 == a2){
                return a1;
            }
            a1 = a1.next;
            a2 = a2.next;
        }
        return null;

    }
}
