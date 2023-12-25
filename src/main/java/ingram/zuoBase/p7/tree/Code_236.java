package ingram.zuoBase.p7.tree;

/**
 * @Author qywu11
 * @Date 2023/3/28 18:38
 * @Version 1.0
 */
public class Code_236 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        return null;
    }


    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }
        // 左右节点其中一个是root节点，直接返回root
        if(p == root || q == root){
            return root;
        }

        return null;

    }


    public boolean isSymmetric1(TreeNode p,TreeNode q) {

        if(p == null && q == null){
            return true;
        }
        if(p == null || q == null){
            return false;
        }

        return p.val == q.val && isSymmetric1(p.left,q.right) && isSymmetric1(p.right,q.left);

    }

}
