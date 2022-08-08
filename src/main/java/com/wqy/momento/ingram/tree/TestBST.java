package com.wqy.momento.ingram.tree;

/**
 * @Author qywu11
 * @Date 2022/8/4 9:27
 * @Version 1.0
 */
public class TestBST {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
    }

    public static int preValue = Integer.MIN_VALUE;

    /**
     * 判断搜索二叉树
     * @param head
     * @return
     */
    public static boolean checkBST(Node head){
        if(head == null){
            return true;
        }
        boolean isLeftBst = checkBST(head.left);
        if(!isLeftBst){
            return false;
        }
        if(head.value <= preValue){
            return false;
        }else {
            preValue = head.value;
        }
        return checkBST(head.right);
    }
}
