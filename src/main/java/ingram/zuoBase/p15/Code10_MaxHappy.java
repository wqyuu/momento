package ingram.zuoBase.p15;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author qywu11
 * @Date 2023/3/15 15:16
 * @Version 1.0
 *
 * 树形dp
 * 以左树要信息，右树要信息 分类
 *
 * 派对的最大快乐值
 * 上级发请柬直接下级不能来
 *
 * 1.可能性分类
 * X参与、X不参与
 *
 *
 * X参与
 * x乐+a整棵树a不来最大快乐+b整棵树b不来最大快乐+c整棵树c不来最大快乐
 *
 * X不参与
 * 0+max(a来整棵树最大快乐,a不来整棵树最大快乐)+ max(b来,b不来)+max(c来，c不来)
 *
 *
 * 2.列出所有需要的信息
 * 子树来整棵树最大快乐，子树不来整棵树最大快乐
 *
 * 3.
 *
 * 4.basecase
 * x是最基层员工，来：x自己 不来: 0
 *
 * 求X不是基层员工 参与、不参与
 * 返回 来/不来 两个数据
 *
 */
public class Code10_MaxHappy {


    public static class Info{
        public int lai;
        public int bulai;

        public Info(int lai, int bulai) {
            this.lai = lai;
            this.bulai = bulai;
        }
    }

    public static Info max(Emp x) {
        if(x.nexts == null){
            return new Info(x.happy,0);
        }

        int lai = 0;
        int bulai = 0;
        for (Emp emp: x.nexts){
            lai += x.happy + max(emp).bulai;
            bulai += Math.max(max(emp).bulai,max(emp).lai);
        }

        return new Info(lai,bulai);
    }

    public static int maxHappy(Emp x){
        return Math.max(max(x).lai,max(x).bulai);
    }

    public static class Emp{
        public int happy;
        public List<Emp> nexts;

        public Emp(int happy){
            this.happy = happy;
            nexts = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        Emp root = new Emp(100);
        createTree(root, 3);

        printTree(root, 3);
        int a =  maxHappy(root);
        System.out.println(a);
    }

    private static void createTree(Emp root, int depth) {
        Random random = new Random();
        if(depth == 0) return;

        int n = random.nextInt(5);
        for(int i=0; i<n; i++){
            Emp emp = new Emp(random.nextInt(100));
            root.nexts.add(emp);
            createTree(emp, depth-1);
        }
    }

    private static void printTree(Emp root, int depth) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<depth; i++){
            sb.append("  ");
        }
        System.out.println(sb.toString() + root.happy);
        for(Emp emp : root.nexts){
            printTree(emp, depth+1);
        }
    }

    private static String getDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        while(depth > 0){
            sb.append("  ");
            depth--;
        }
        return sb.toString();
    }
}
