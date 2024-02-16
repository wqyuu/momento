package ingram.lc.linkNode;

public class A11_sortList {

    public static ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public static ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    public static ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(5);
        ListNode n2 = new ListNode(3);
        ListNode n1 = new ListNode(4);
        listNode.next = n1;
        n1.next = n2;
//        sortList(listNode);
        sortList1(listNode,null);
    }

    public static ListNode sortList1(ListNode head, ListNode tail) {
        if(head == null){
            return null;
        }
        if(head.next == tail){
            head.next = null;
            return head;
        }

        ListNode fast = head,slow = head;
        while (fast!=tail){
            slow = slow.next;
            fast = fast.next;
            if(fast!=tail){
                fast = fast.next;
            }
        }

        ListNode mid = slow;
        ListNode s1 = sortList1(head,mid);
        ListNode s2 = sortList1(mid,tail);

        ListNode dummyHead = new ListNode(0);
        ListNode tmp = dummyHead;
        while (s1!=null && s2!=null){
            if(s1.val < s2.val){
                tmp.next = s1;
                s1 = s1.next;
            }else{
                tmp.next = s2;
                s2 = s2.next;
            }
            tmp = tmp.next;
        }
        tmp.next = null == s1?s2:s1;

        return dummyHead.next;
    }

}
