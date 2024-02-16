package ingram.zuoBase.p15;

/**
 * @Author qywu11
 * @Date 2023/3/10 17:07
 * @Version 1.0
 * 树形dp问题，动态规划
 * 求整棵树中任一两个节点的最大距离
 */
public class Code09_MaxDistanceInTree {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value = data;
        }
    }

    public static int maxDistance(Node head){
        return process(head).maxDistance;
    }

    public static class Info{
        public int maxDistance;
        public int height;
        public Info(int dis,int h){
            maxDistance = dis;
            height = h;
        }
    }

    // 返回以X为头整棵树两个信息
    public static Info process(Node x){
        if(x == null){
            return new Info(0,0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        // 三种情况
        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height + rightInfo.height + 1;

        int maxDistance = Math.max(p3,Math.max(p1,p2));
        int hight = Math.max(leftInfo.height,rightInfo.height) + 1;

        return new Info(maxDistance,hight);
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(3);

        head1.left.left = new Node(4);
        head1.left.left.left = new Node(5);
        head1.left.left.left.left = new Node(6);

        head1.left.right = new Node(7);
        head1.left.right.right = new Node(8);
        head1.left.right.right.right = new Node(9);

        head1.right.left = new Node(10);

        int max = maxDistance(head1);
        System.out.println(max);
    }
}
