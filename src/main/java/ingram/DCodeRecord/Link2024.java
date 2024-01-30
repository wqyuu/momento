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
