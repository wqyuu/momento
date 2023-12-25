package ingram.zuoBase.p6.linkNode;

/**
 * @Author qywu11
 * @Date 2022/7/26 8:39
 * @Version 1.0
 * 小于某数放左边，大于放右边
 */
public class ListPartition {
    public static class Node{
        public int value;
        public Node next;

        public Node(int data){
            this.value = data;
        }
    }

    public static Node listPartition(Node head, int pivot){
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node mH = null;
        Node mT = null;
        Node next = null;

        // 1->2->5->2->5->8->6->10  [5]
        //
        while (head != null){
            next = head.next;
            head.next = null;
            if(head.value < pivot){
                if(sH == null){
                    sH = head; // 1
                    sT = head; // 1
                }else {
                    sT.next = head; // 1->3
                    sT = head;      // 3->10
                }
            }else if(head.value == pivot){
                if(eH == null){
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = head;
                }
            }else {
                if(mH == null){
                    mH = head;
                    mT = head;
                }else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        if(sT != null){ // 如果有小于区域
            sT.next = eH;
            eT = eT == null ? sT : eT; // 下一步，谁去连大于区域的头，谁就变eT
        }
        // 上面if 不管跑了没有 et
        if(eT != null){ // 如果小于区域和等于区域不是都没有
             eT.next = mH;
        }
        return sH != null ? sH :(eH != null ? eH : mH);
    }

    public static void main(String[] args) {
        Node a1 = new Node(1);
        Node a2 = new Node(2);
        Node a3 = new Node(5);
        Node a4 = new Node(2);
        Node a5 = new Node(5);
        Node a6 = new Node(8);
        Node a7 = new Node(6);
        Node a8 = new Node(10);
        a7.next = a8;
        a6.next = a7;
        a5.next = a6;
        a4.next = a5;
        a3.next = a4;
        a2.next = a3;
        a1.next = a2;


        Node res = listPartition(a1,5);
        System.out.println(res);
    }
}
