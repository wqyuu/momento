package ingram.linkNode;

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

        while (head != null){
            next = head.next;
            head.next = null;
            if(head.value < pivot){
                if(sH == null){
                    sH = head;
                    sT = head;
                }else {
                    sT.next = head;
                    sT = head;
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
}
