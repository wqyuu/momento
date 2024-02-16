package ingram.lc;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class A03_RightSideView {


    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public static List<Integer> rightSideView(TreeNode root) {
        if(null == root){
            return  new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Map<TreeNode, Integer> leMap = new HashMap<>();
        List<TreeNode> curLevelNodeList = new ArrayList<>();
        Map<Integer,List<TreeNode>> lnMap = new LinkedHashMap<>();
        Queue<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);
        leMap.put(root,0);
        curLevelNodeList.add(root);
        lnMap.put(0,curLevelNodeList);

        while (!treeNodes.isEmpty()) {
            TreeNode cur = treeNodes.poll();
            Integer level = leMap.get(cur);
            if (null != cur.left) {
                leMap.put(cur.left,level+1);
                List<TreeNode> list = lnMap.getOrDefault(level+1,new ArrayList<>());
                list.add(cur.left);
                lnMap.put(level+1,list);
                treeNodes.add(cur.left);
            }
            if (null != cur.right) {
                leMap.put(cur.right,level+1);
                List<TreeNode> list = lnMap.getOrDefault(level+1,new ArrayList<>());
                list.add(cur.right);
                lnMap.put(level+1,list);
                treeNodes.add(cur.right);
            }
        }
        for (List<TreeNode> list: lnMap.values()) {
            TreeNode rightNode =  list.get(list.size()-1);
            res.add(rightNode.val);
        }

        return res;
    }

    public static int maxLevelSum(TreeNode root) {
        if(null == root){
            return  -1;
        }
        Map<TreeNode, Integer> leMap = new HashMap<>();
        Map<Integer,Integer> totalMap = new LinkedHashMap<>();
        Queue<TreeNode> treeNodes = new LinkedList<>();
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
        Map<Integer,Integer> maxMap = new HashMap<>();
        AtomicInteger MaxLevel = new AtomicInteger(Integer.MIN_VALUE);
        totalMap.forEach((k,v)->{
            if(max.get() <v){
                max.set(v);
                MaxLevel.set(k);
            }
        });

        return MaxLevel.get();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode a1 = new TreeNode(2);
        TreeNode a2 = new TreeNode(3);
        TreeNode a3 = new TreeNode();
        TreeNode a4 = new TreeNode(5);
        TreeNode a5 = new TreeNode();
        TreeNode a6 = new TreeNode(4);
        root.left = a1;
        root.right = a2;
        a1.right = a4;
        a2.right = a6;
        List<Integer> res = rightSideView(root);
        for (Integer k: res ) {
            System.out.println(k);
        }

        // -100,-200,-300,-20,-5,-10,null
        TreeNode root2 = new TreeNode(-100);
        TreeNode b1 = new TreeNode(-200);
        TreeNode b2 = new TreeNode(-300);
        TreeNode b3 = new TreeNode(-20);
        TreeNode b4 = new TreeNode(-5);
        TreeNode b5 = new TreeNode(-10);
        TreeNode b6 = new TreeNode(-32127);
        root2.left = b1;
        root2.right = b2;

        b1.left =b3;
        b1.right = b4;
        b2.left = b5;
        int max = maxLevelSum(root2);
        System.out.println(max);
    }


}
