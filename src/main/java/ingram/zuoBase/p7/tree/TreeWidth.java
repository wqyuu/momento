package ingram.zuoBase.p7.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @Author qywu11
 * @Date 2022/8/3 9:01
 * @Version 1.0
 */
public class TreeWidth {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 宽度遍历
     * @param head
     */
    public static void w(Node head){
        if(head == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);
            if(cur.left != null){
                queue.add(cur.left);
            }
            if(cur.right != null){
                queue.add(cur.right);
            }

        }
    }

    /**
     * 找出最大层节点个数
     * @param head
     * @return
     */
    public static int w2(Node head){
        if(head == null){
            return -1;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Map<Node,Integer> levelMap = new HashMap<>();
        levelMap.put(head,1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = Integer.MIN_VALUE;
        /**
         *
         *               a
         *             b   c
         *           d  e f  g
         *
         *           curLevel = 1 , curLevelNodes = 0   Q[a]   Map{a:1,}
         *           curLevel = 1 , curLevelNodes = 1   Q[b,c]   Map{a:1,b:2,c:2}
         *           curLevel = 2 , curLevelNodes = 1 ,max = 1,  Q[c,d,e]   Map{a:1,b:2,c:2,d:3,e:3}
         *           curLevel = 2 , curLevelNodes = 2 ,max = 1,  Q[d,e,f,g]   Map{a:1,b:2,c:2,d:3,e:3,f:3,g:3}
         *           curLevel = 3 , curLevelNodes = 1 ,max = 2,  Q[e,f,g]   Map{a:1,b:2,c:2,d:3,e:3,f:3,g:3}
         *
         */
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if(curNodeLevel == curLevel){
                curLevelNodes ++;
            } else {
                max = Math.max(max,curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }

            if(cur.left != null){
                levelMap.put(cur.left,curNodeLevel+1);
                queue.add(cur.left);
            }
            if(cur.right != null){
                levelMap.put(cur.right,curNodeLevel+1);
                queue.add(cur.right);
            }

        }
        return max;
    }



    public static int w3(Node head){
        int max = Integer.MIN_VALUE;
        if(head == null){
            return -1;
        }

        int curNodeLevel = 1;
        int curNodes = 0;
        Map<Node, Integer> map = new HashMap<>();

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        map.put(head,curNodeLevel);
        while (!queue.isEmpty()){
            Node cur = queue.poll(); 
            if(curNodeLevel == map.get(cur)){
                curNodes ++;
            }else {
                max = Math.max(max,curNodes);
                curNodeLevel++;
                curNodes = 1;
            }

            if(cur.left != null){
                map.put(cur.left,curNodeLevel+1);
                queue.add(cur.left);
            }
            if(cur.right != null){
                map.put(cur.right,curNodeLevel+1);
                queue.add(cur.right);
            }
        }


        return max;
    }

    public static void main(String[] args) {

        TreeWidth.Node a = new TreeWidth.Node(2);
        TreeWidth.Node al1 = new TreeWidth.Node(1);
        TreeWidth.Node ar1 = new TreeWidth.Node(3);
        TreeWidth.Node al2 = new TreeWidth.Node(4);
        TreeWidth.Node ar2 = new TreeWidth.Node(5);
        a.left = al1;
        a.right = ar1;
        al1.left = al2;
        al1.right = ar2;
        int max = w3(a);
        System.out.println(max);
    }
}
