package com.wqy.momento.ingram.tree;

/**
 * @Author qywu11
 * @Date 2022/8/15 8:54
 * @Version 1.0
 *
 * 搜索树，递归套路
 */
public class TreeSearch {

    public static class Node{
        public int value;
        public Node left;
        public Node right;
    }


    public static class ReturnType{
        public boolean isBST;
        public int min;
        public int max;
        public ReturnType(boolean isBST,int min,int max){
            isBST = isBST;
            min = min;
            max = max;
        }
    }

    public static ReturnType process(Node x){
        if(x == null){
            return null;
        }
        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);
        int min = x.value;
        int max = x.value;
        if(leftData!=null){
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        if(rightData!=null){
            min = Math.min(min, rightData.min);
            max = Math.max(max, rightData.max);
        }

        boolean isBST = true;
        if(leftData!=null && (!leftData.isBST || leftData.max >= x.value) ){
            isBST = false;
        }
        if(rightData!=null && (!rightData.isBST || rightData.min <= x.value) ){
            isBST = false;
        }
        return new ReturnType(isBST,min,max);
    }
}
