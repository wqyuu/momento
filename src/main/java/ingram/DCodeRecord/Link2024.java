package ingram.DCodeRecord;

/**
 * @Author qywu11
 * @Date 2024/1/17 14:30
 * @Version 1.0
 */
public class Link2024 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 203.移除链表元素
    // 1->2->3  3
    public ListNode removeElements(ListNode head, int val) {
        //创建一个虚拟头结点
        ListNode dummyNode = new ListNode(val - 1);
        dummyNode.next = head;
        ListNode prev = dummyNode;
        //确保当前结点后还有结点
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return dummyNode.next;
    }

    // pre:N<-1<-2<-3
    // 206。翻转
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (null!=cur){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // N->1->2->3->4->5
    // N->2->1   3->4->5
    // N->2->1->4->3  5
    // N->2->1->4->3  5
    // 24. 两两交换链表中的节点
    public static ListNode swapPairs(ListNode head) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        ListNode cur = dummyNode.next;
        while (null != cur && null != cur.next){
            ListNode next = cur.next;
            cur.next = null;
            ListNode next2 = next.next;
            pre.next = next;
            next.next = cur;
            pre = cur;
            cur = next2;
        }
        if(null != cur){
            pre.next = cur;
        }
//        pre.next = cur;
        return dummyNode.next;
    }

    // 19.删除链表的倒数第N个节点
    // N->1->2->3->4->5
    // 1 4
    // 2 3
    public static ListNode delNthNode(ListNode head,int n) {
        ListNode node = head;
        ListNode cur = new ListNode(-1);
        cur.next = head;
        int size = 0;
        while (null!=node){
            node = node.next;
            size ++;
        }
        if(n % size == 0){
            cur.next = cur.next.next;
            return cur.next;
        }
        n = size - n % size;

        while (n>0){
            cur = cur.next;
            n --;
        }
        cur.next = cur.next.next;
        return head;
    }

    /**
     * 160. 相交链表
     * 简单
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a1 = headA;
        ListNode a2 = headB;

        int i = 0;
        while (null!=a1){
            a1 = a1.next;
            i ++;
        }
        while (null!=a2){
            a2 = a2.next;
            i --;
        }
        a1 = headA;
        a2 = headB;
        if(i>0){
            while (i>0){
                a1 = a1.next;
                i --;
            }
        }else {
            while (i<0){
                a2 = a2.next;
                i ++;
            }
        }

        while (null!=a1 && null!=a2){
            if(a1.equals(a2)){
                return a1;
            }
            a1 = a1.next;
            a2 = a2.next;
        }
        return null;
    }

    /**
     * 234. 回文链表
     * 简单
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     * @param head
     * @return
     */
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
        ListNode reverseList = reverseListOfPalindrome(n1);
        ListNode n3 = reverseList;
        n2 = head;
        while (null != n3.next){
            if(n2.val != n3.val){
                return false;
            }
            n3 = n3.next;
            n2 = n2.next;
        }
        reverseListOfPalindrome(reverseList);
        return true;
    }

    private static ListNode reverseListOfPalindrome(ListNode head) {
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

    /**
     * 141. 环形链表
     * 简单
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if(null == head || null == head.next){
            return false;
        }
        ListNode fast =  head;
        ListNode slow =  head;
        while (null != fast.next && null != fast.next.next){
            slow =  slow.next;
            fast =  fast.next.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    /**
     * 142. 环形链表 II
     * 中等
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 不允许修改 链表。
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if(null == head || null == head.next){
            return null;
        }
        ListNode fast =  head;
        ListNode slow =  head;
        while (null != fast.next && null != fast.next.next){
            slow =  slow.next;
            fast =  fast.next.next;
            if(fast == slow){
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    /**
     * 328. 奇偶链表
     * 中等
     * 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
     * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
     * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
     * 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        ListNode oh = new ListNode();
        ListNode eh = new ListNode();
        ListNode ot = oh;
        ListNode et = eh;

        boolean isOdd = true;
        while (head!=null){
            if(isOdd){
                ot.next = head;
                ot = ot.next;
            }else {
                et.next = head;
                et = ot.next;
            }
            isOdd = !isOdd;
            head = head.next;
        }
        ot.next = eh.next;
        et.next = null;
        return oh.next;
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        root.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
//        swapPairs(node3);
        delNthNode(node3,3);
    }
}
