package ingram.zuoBase.p21;


/**
 * @Author qywu11
 * @Date 2023/4/21 10:20
 * @Version 1.0
 *
 * 树形DP
 * 二叉树套路
 */
public class Problem08_MaxSumInTree {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * root为头的整棵树上，最大路径和是多少，返回
     * 路径要求，从root出发到叶节点，算一个路径
     * @param root
     * @return
     */
    int process2(Node root){
        if(root.left == null &&root.right == null){ // 节点是叶节点，直接返回节点值
            return root.value;
        }
        int next = 0;
        if(root.left != null){
            next = process2(root.left); // 左树不为空，算出最大路径值
        }
        if(root.right!=null){
            next = Math.max(next,process2(root.right)); // 右树不为空，算出最大路径值，与之前求得值比大小
        }

        return next + root.value;
    }

}
