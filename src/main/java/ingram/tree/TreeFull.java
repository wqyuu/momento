package ingram.tree;

/**
 * @Author qywu11
 * @Date 2022/8/15 8:54
 * @Version 1.0
 *
 * 满二叉树(节点个数 = (2^树高度) -1)，递归套路
 */
public class TreeFull {

    public static class Node{
        public int value;
        public Node left;
        public Node right;
    }


    public static class ReturnType{
        public int height;
        public int nodes;
        public ReturnType(int h,int n){
            height = h;
            nodes = n;
        }
    }

    public static boolean isF(Node x){
        if(x == null){
            return true;
        }
        ReturnType data = f(x);
        return data.nodes == (1>> data.height -1);
    }

    public static ReturnType f(Node x){
        if(x == null){
            return new ReturnType(0,0);
        }
        ReturnType leftData = f(x.left);
        ReturnType rightData = f(x.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        int nodes = leftData.nodes + rightData.nodes + 1;
        return new ReturnType(height,nodes);
    }
}
