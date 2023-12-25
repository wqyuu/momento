package ingram.zuoBase.p15;

/**
 * @Author qywu11
 * @Date 2023/3/28 16:41
 * @Version 1.0
 *
 * Morris相当于对递归的模拟，在左树转一圈又回到自己，
 *
 * Morris遍历要点：
 *
 * 只要当前节点有左孩子，那么就可以访问它两次，如果没有左孩子只会访问一次。
 * Morris遍历使用二叉树节点中大量指向null的指针，由Joseph Morris 于1979年发明。
 * 时间复杂度：O(n)
 * 额外空间复杂度：O(1)
 */
public class Code11_MorrisTraversal {

    public static class Node{
        public int value;
        Node left;
        Node right;

        public Node(int data){
            this.value = data;
        }
    }


    // 递归版本回到3次

    public static void morris(Node head){

        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if(mostRight != null){ // mostRight是cur左孩子
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                // mostRight变成cur左子树上，最右节点
                if(mostRight.right == null){ // 第一次到cur节点
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else { // mostRight.right == cur  第二次到cur节点
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    /**
     * 先序：【第二次到达不打印】只一次，打印；二次，第一次打印
     * @param head
     */
    public static void morrisPre(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if(mostRight != null){ // mostRight是cur左孩子
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                // mostRight变成cur左子树上，最右节点
                if(mostRight.right == null){ // 第一次到达cur
                    System.out.println(cur.value);
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else { // mostRight.right == cur
                    mostRight.right = null;
                }
            }else { // 没有左树
                System.out.println(cur.value);
            }
            cur = cur.right;
        }
    }

    public static void morrisIn(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if(mostRight != null){ // mostRight是cur左孩子
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                // mostRight变成cur左子树上，最右节点
                if(mostRight.right == null){ // 第一次到达cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else { // mostRight.right == cur
                    mostRight.right = null;
                }
            }
            System.out.println(cur.value);
            cur = cur.right;
        }
    }

    public static void morrisPos(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if(mostRight != null){ // mostRight是cur左孩子
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                // mostRight变成cur左子树上，最右节点
                if(mostRight.right == null){ // 第一次到达cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else { // mostRight.right == cur
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    // 以X为头的树，逆序打印这棵树右边界
    public static void printEdge(Node X){
        Node tail = reverseEdge(X); // 逆序
        Node cur = tail;
        while (cur != null){
            System.out.println(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail); // 打印完调回去
    }

    public static Node reverseEdge(Node from){
        Node pre = null;
        Node next = null;
        while (from != null){
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }


    public static boolean isBST(Node head){
        if(head == null){
            return true;
        }
        Node cur = head;
        Node mostRight = null;
        int preValue = Integer.MIN_VALUE;
        while (cur != null){
            mostRight = cur.left;
            if(mostRight != null){ // mostRight是cur左孩子
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                // mostRight变成cur左子树上，最右节点
                if(mostRight.right == null){ // 第一次到达cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else { // mostRight.right == cur
                    mostRight.right = null;
                }
            }
            if(cur.value <= preValue){
                return false;
            }
            preValue = cur.value;

            cur = cur.right;
        }
        return true;
    }

    public static void morris20030531(Node head){
        if(null == head){
            return;
        }

        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if(mostRight != null){
                while (mostRight.right!=null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else{
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

}
