package ingram.lc.tree;

/**
 * 101. 对称二叉树
 * 简单
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 */
public class A04_isSymmetric {

    public boolean isSymmetric(TreeNode root) {

        return isSymmetric(root.left,root.right);
    }

    public boolean isSymmetric(TreeNode left,TreeNode right) {
        if(null == left && null == right){
            return true;
        }
        if(null == left || null == right){
            return false;
        }

        boolean p1 = isSymmetric(left.left,right.right);
        boolean p2 = isSymmetric(left.right,right.left);
        if(left.val == right.val && p1 && p2){
            return true;
        }
        return false;
    }
}
