package ingram.zuoBase.p7.tree;
import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class S1 {







    public TreeNode searchBST(TreeNode root, int val) {
        if(null == root){
            return null;
        }
        if(root.val == val){
            return root;
        }
        TreeNode left = searchBST(root.left,val);
        TreeNode right = searchBST(root.right,val);
        if(null == left && right == null){
            return null;
        }
        return null == left ? right: left;
    }

    public TreeNode searchBST2(TreeNode root, int val) {
        if(null == root || (null == root.left && null == root.right)){
            return null;
        }
        if((null != root.left && root.left.val == val) || (null != root.right && root.right.val == val)){
            return root;
        }
        TreeNode left = searchBST2(root.left,val);
        TreeNode right = searchBST2(root.right,val);
        if(null == left && right == null){
            return null;
        }
        return null == left ? right: left;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode searchNode = searchBST2(root,key);
        if(null == searchNode){
            return null;
        }

        return null;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root,res);
        return res;
    }


    public void inorder(TreeNode root,List<Integer> res){
        if(null == root){
            return;
        }
        inorder(root.left,res);
        res.add(root.val);
        inorder(root.right,res);
    }


    //    1
    //  2   3
    // 4 5
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> treeNodes = new Stack<>();
        while (!treeNodes.isEmpty() || null != root){
            if(null != root){
                treeNodes.add(root);
                root = root.left;
            }else{
                TreeNode node = treeNodes.pop();
                res.add(node.val);
                root = node.right;
            }
        }

        return res;
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> treeNodes = new Stack<>();
        while (!treeNodes.isEmpty() || null != root){
            if(null != root){
                treeNodes.add(root);
                root = root.left;
            }else{
                TreeNode node = treeNodes.pop();
                res.add(node.val);
                root = node.right;
            }
        }

        return res;
    }

    public TreeNode invertTree(TreeNode root) {
        if(null == root){
            return null;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    //    1
    //  2   2
    // 4 5 5 4
    public boolean isSymmetric(TreeNode root) {
        if(null == root){
            return false;
        }
        if(null == root.left && null == root.right){
            return true;
        }
        if(null == root.left){
            return false;
        }
        if(root.left.val !=  root.right.val){
            return false;
        }

        return isSymmetric(root.left,root.right);
    }
    public boolean isSymmetric(TreeNode left,TreeNode right) {
        if(null == left && null == right){
            return true;
        }
        if(null == left || null == right){
            return false;
        }
        if(left.left != right.right || left.right != right.left){
            return false;
        }
        return isSymmetric(left.left,right.left) && isSymmetric(left.right,right.right);
    }


    public int diameterOfBinaryTree(TreeNode root) {

        return dbTree(root,0);
    }

    int dbTree(TreeNode root,int height){
        if(root  == null){
            return height;
        }

        int leftH = dbTree(root.left,height + 1);
        int rightH = dbTree(root.right,height + 1);
        return Math.max(leftH,rightH);
    }

    int preValue = Integer.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        boolean isLeftBst = isValidBST(root.left);
        if(!isLeftBst){
            return false;
        }
        if(root.val <= preValue){
            return false;
        }else {
            preValue = root.val;
        }
        return isValidBST(root.right);
    }

    public int kthSmallest(TreeNode root, int k) {

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || null != root){
            if(null != root){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                k --;
                if(k == 0){
                    return root.val;
                }
                stack.push(root.right);
                root = root.right;
            }
        }
        return 0;
    }

    public void flatten(TreeNode root) {
        if(null == root){
            return;
        }
        TreeNode node = new TreeNode();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            node.right = cur;
            node.left = null;
            node = node.right;
            if(null != cur.right){
                stack.push(cur.right);
            }
            if(null != cur.left){
                stack.push(cur.left);
            }
        }
        root = node.right;
    }



    /**
     *        10
     *      5   -3
     *     3  2    11
     *   3  2  1
     *
     * t=8  r=3
     * i=10
     *
     *  t=8  r=3
     *  i=10
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        // key是前缀和, value是大小为key的前缀和出现的次数
        Map<Long, Integer> prefixSumCount = new HashMap<>();
        // 前缀和为0的一条路径
        prefixSumCount.put(0L, 1);
        // 前缀和的递归回溯思路
        return recursionPathSum(root, prefixSumCount, sum, 0L);
    }

    /**
     * 前缀和的递归回溯思路
     * 从当前节点反推到根节点(反推比较好理解，正向其实也只有一条)，有且仅有一条路径，因为这是一棵树
     * 如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
     * 所以前缀和对于当前路径来说是唯一的，当前记录的前缀和，在回溯结束，回到本层时去除，保证其不影响其他分支的结果
     * @param node 树节点
     * @param prefixSumCount 前缀和Map
     * @param target 目标值
     * @param currSum 当前路径和
     * @return 满足题意的解
     */
    private int recursionPathSum(TreeNode node, Map<Long, Integer> prefixSumCount, int target, long currSum) {
        // 1.递归终止条件
        if (node == null) {
            return 0;
        }
        // 2.本层要做的事情
        int res = 0;
        // 当前路径上的和
        currSum += node.val;

        //---核心代码
        // 看看root到当前节点这条路上是否存在节点前缀和加target为currSum的路径
        // 当前节点->root节点反推，有且仅有一条路径，如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
        // currSum-target相当于找路径的起点，起点的sum+target=currSum，当前点到起点的距离就是target
        res += prefixSumCount.getOrDefault(currSum - target, 0);
        // 更新路径上当前节点前缀和的个数
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
        //---核心代码

        // 3.进入下一层
        res += recursionPathSum(node.left, prefixSumCount, target, currSum);
        res += recursionPathSum(node.right, prefixSumCount, target, currSum);
        /**
         *        10
         *      5   -3
         *     3  2    11
         *   3  2  1
         */
        // 4.回到本层，恢复状态，去除当前节点的前缀和数量
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }






    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}