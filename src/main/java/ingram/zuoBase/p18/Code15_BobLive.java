package ingram.zuoBase.p18;

/**
 * @Author qywu11
 * @Date 2023/4/5 16:25
 * @Version 1.0
 *
 * 给定 N*M区域，Bob从(i,j)位置出发，向上下左右4个方向，
 * 4个方向都是相同的概率
 * 走k步之后，求获得生存概率
 * 不越界的概率
 */
public class Code15_BobLive {

    public static String live(int N,int M,int i,int j,int K){
        long all = (long) Math.pow(4,K); // k步 ，每步都有4个选择
        long live = process(N,M,i,j,K);
        long gcd = gcd(all,live);
        return String.valueOf((live/gcd) + "/" + (all/gcd));
    }



    public static long process(int N,int M,int row,int col,int rest){
        if(row < 0 || row == N || col < 0 || col == M ){
            return 0;
        }
        // 到达结束位置，而且没越界
        if(rest == 0){
            return 1;
        }

        // 还没走完，row , col也没越界
        long live = process(N,M,row - 1,col,rest -1);
        live += process(N,M,row + 1,col,rest -1);
        live += process(N,M,row,col + 1,rest -1);
        live += process(N,M,row,col - 1,rest -1);

        return live;
    }

    // 公约数
    public static long gcd(long m,long n ){
        return n == 0 ? m : gcd(n,m % n);
    }
}
