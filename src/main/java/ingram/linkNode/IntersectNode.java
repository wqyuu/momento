package ingram.linkNode;

/**
 * @Author qywu11
 * @Date 2022/7/27 8:50
 * @Version 1.0
 * 两个单链表，不知是否有环，判断相交
 */
public class IntersectNode {

    public static Node getIntersectNode(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        // 两个无环链表
        if(loop1 == null && loop2 == null){
            return noLoop(head1,head2);
        }
        // 两个有环链表
        if(loop1 != null && loop2 != null){
            return bothLoop(head1,loop1,head2,loop2);
        }
        // 一个有环链表、一个无环
        return null;
    }


    // 找到链表第一个入环节点，如果无环，返回null
    public static Node getLoopNode(Node head){
        if(head == null ||head.next == null || head.next.next == null){
            return null;
        }
        Node n1 = head.next; // 慢指针
        Node n2 = head.next.next; // 快指针
        while (n1 != n2){
            if(n2.next == null || n2.next.next == null){
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        n2 = head; // n2 回到头节点
        while (n1 != n2){
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    public static Node noLoop(Node head1,Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null){
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null){
            n--;
            cur2 = cur2.next;
        }
        // 判断尾结点是否相等
        if(cur1 != cur2){
            return null;
        }
        // 重定位，cur1 = 长链表
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        // 长链表先走差值步
        while (n != 0){
            n --;
            cur1 = cur1.next;
        }
        // 长短链表一起走，相遇就是相交节点
        while (cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;

    }

    public static Node bothLoop(Node head1,Node loop1,Node head2,Node loop2){
        Node cur1 = null;
        Node cur2 = null;
        // 入环节点相等
        if(loop1 == loop2){
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1){
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2){
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0){
                n --;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else{ // 入环节点不同
            cur1 = loop1.next;
            // 绕环一圈
            while (cur1 != loop1){
                // 是否和loop2相遇，相遇就是相交节点
                if(cur1 == loop2){
                    return loop1;
                }
                cur1 = cur1.next;
            }
            // 绕一圈未相遇，两个链表不相交
            return null;
        }

    }
}
