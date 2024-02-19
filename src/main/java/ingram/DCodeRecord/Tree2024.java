package ingram.DCodeRecord;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author qywu11
 * @Date 2024/2/7 15:24
 * @Version 1.0
 */
public class Tree2024 {

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
    /**
     * 94.二叉树的中序遍历
     * 简单
     * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
     * 示例 1：
     * 输入：root = [1,null,2,3]
     * 输出：[1,3,2]
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
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

    /**
     * 104.二叉树的最大深度 简单的递归题
     * 简单
     * 给定一个二叉树 root ，返回其最大深度。
     * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
     *
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：3
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }

    /**
     * 236.二叉树的最近公共祖先
     * 中等
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
     * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 示例 1：
     * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * 输出：3
     * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right =  lowestCommonAncestor(root.right,p,q);
        if(left != null && right != null) return root;
        if(left == null){
            return right;
        }
        return left;
    }

    /**
     * 101.对称二叉树
     * 简单
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     *
     * 示例 1：
     * 输入：root = [1,2,2,3,4,4,3]
     * 输出：true
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if(null == root){
            return true;
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
        if(left.val != right.val){
            return false;
        }
        return isSymmetric(left.left,right.right) && isSymmetric(left.right,right.left);
    }

    /**
     * 226. 翻转二叉树
     * 简单
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     *
     * 示例 1：
     * 输入：root = [4,2,7,1,3,6,9]
     * 输出：[4,7,2,9,6,3,1]
     * @param root
     * @return
     */
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

    /**
     * 437.路径总和 III
     * 中等
     * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
     * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     *
     * 示例 1：
     * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
     * 输出：3
     * 解释：和等于 8 的路径有 3 条，如图所示。
     * @param root
     * @param sum
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
        if (node == null) {
            return 0;
        }
        int res = 0;
        currSum += node.val;
        res += prefixSumCount.getOrDefault(currSum - target, 0);
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
        res += recursionPathSum(node.left, prefixSumCount, target, currSum);
        res += recursionPathSum(node.right, prefixSumCount, target, currSum);
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }

    /**
     * 1161.最大层内元素和 层序遍历题
     * 中等
     * 给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
     * 请返回层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。
     *
     * 示例 1：
     * 输入：root = [1,7,0,7,-8,null,null]
     * 输出：2
     * 解释：
     * 第 1 层各元素之和为 1，
     * 第 2 层各元素之和为 7 + 0 = 7，
     * 第 3 层各元素之和为 7 + -8 = -1，
     * 所以我们返回第 2 层的层号，它的层内元素之和最大。
     * @param root
     * @return
     */
    public int maxLevelSum(TreeNode root) {
        if(null == root){
            return  -1;
        }
        // 记录节点层数
        Map<TreeNode, Integer> leMap = new HashMap<>();
        // 记录每层节点总数
        Map<Integer,Integer> totalMap = new LinkedHashMap<>();
        // 节点队列 只放每层节点
        Queue<TreeNode> treeNodes = new LinkedList<>();

        // 先放入root
        treeNodes.add(root);
        leMap.put(root,1);
        totalMap.put(1,root.val);

        while (!treeNodes.isEmpty()) {
            TreeNode cur = treeNodes.poll();
            Integer level = leMap.get(cur);
            if (null != cur.left) {
                leMap.put(cur.left,level+1);
                treeNodes.add(cur.left);
                totalMap.put(level+1, totalMap.getOrDefault(level+1,0)+cur.left.val);
            }
            if (null != cur.right) {
                leMap.put(cur.right,level+1);
                treeNodes.add(cur.right);
                totalMap.put(level+1, totalMap.getOrDefault(level+1,0)+cur.right.val);
            }
        }
        AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
        AtomicInteger MaxLevel = new AtomicInteger(Integer.MIN_VALUE);
        totalMap.forEach((k,v)->{
            if(max.get() <v){
                max.set(v);
                MaxLevel.set(k);
            }
        });

        return MaxLevel.get();
    }


    /**
     * 450.删除二叉搜索树中的节点  这题很难
     * 中等
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     * 一般来说，删除节点可分为两个步骤：
     * 首先找到需要删除的节点；
     * 如果找到了，删除它。
     *
     * 示例 1:
     * 输入：root = [5,3,6,2,4,null,7], key = 3
     * 输出：[5,4,6,2,null,null,7]
     * 解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
     * 一个正确的答案是 [5,4,6,2,null,null,7],
     * 另一个正确答案是 [5,2,6,null,4,null,7]。
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.right == null) {
                return root.left;
            }
            if (root.left == null) {
                return root.right;
            }
            TreeNode successor = root.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            root.right = deleteNode(root.right, successor.val);
            successor.right = root.right;
            successor.left = root.left;
            return successor;
        }
        return root;
    }


}
