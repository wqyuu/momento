package ingram.zuoBase.p6.linkNode;


class LinkNInsagream {

    public static boolean isPalindrome(ListNode head) {
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


    public static boolean hasCycle(ListNode head) {
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

    public static ListNode detectCycle(ListNode head) {
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

    // 12358
    // 12467
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode head = new ListNode(-1);
        ListNode pre = head;
        while (null !=list1 && null != list2){
            if(list1.val >= list2.val){
                pre.next = list1;
                list1 = list1.next;
            }else {
                pre.next = list2;
                list2 = list2.next;
            }
            pre = pre.next;
        }
        pre.next = null == list1? list2 : list1;
        return head.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode sumNode = new ListNode(-1);
        ListNode preNode = sumNode;
        int pre = 0;
        while (null != l1 && null != l2){
            int a = l1.val;
            int b = l2.val;
            int sum = a + b + pre;
            int cur = sum;
            if(sum>9){
                pre = sum / 10;
                cur = sum % 10;
            }else{
                pre = 0;
            }
            preNode.next = new ListNode(cur);
            l1 = l1.next;
            l2 = l2.next;
            preNode = preNode.next;
        }
        ListNode residue = null == l1 ? l2 : l1;
        while (null != residue){
            int sum = pre + residue.val;
            int cur = sum;
            if(sum>9){
                pre = sum / 10;
                cur = sum % 10;
            }else{
                pre = 0;
            }
            preNode.next = new ListNode(cur);
            residue = residue.next;
            preNode = preNode.next;
        }
        if(0 != pre){
            preNode.next = new ListNode(pre);
        }
        return sumNode.next;
    }

    // head = [1,2,3,4,5], n = 2  =>[1,2,3,5]
    // 2 + x = 5
    // x
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode n1 = dummy;
        ListNode n2 = head;
        int s = n;
        while (s!=0){
            n2 = n2.next;
            s --;
        }
        while (null != n2.next){
            n1 = n1.next;
            n2 = n2.next;
        }
        n1.next = n1.next.next;

        return dummy.next;
    }

    // 2->1->3->8
    // 0->2->1->3->8
    // 0->1->3->8
    // 2->3->8
    //
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(1);
        head.next = node1;
        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;

        // 9,9,9,9,9,9,9
        ListNode b6 = new ListNode(9);
        ListNode b7 = new ListNode(9);
        ListNode b5 = new ListNode(9);
        ListNode b4 = new ListNode(9);
        ListNode b3 = new ListNode(9);
        ListNode b2 = new ListNode(9);
        ListNode b1 = new ListNode(9);
        b2.next = b3;
        b1.next = b2;
        b3.next = b4;
        b4.next = b5;
        b5.next = b6;
        b6.next = b7;

        ListNode a4 = new ListNode(9);
        ListNode a3 = new ListNode(9);
        ListNode a2 = new ListNode(9);
        ListNode a1 = new ListNode(9);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;

        addTwoNumbers(b1,a1);
//        node6.next = node3;
//        reverseList(node3);
//        isPalindrome(head);

//        int a = 5;
//        int b = 8;
//        int sum = a + b;
//        int pre = 0;
//        int cur = 0;
//        if(sum>9){
//            pre = sum / 10;
//            cur = sum % 10;
//        }
//        System.out.println("pre:"+pre);
//        System.out.println("cur:"+cur);
    }
















//    Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}