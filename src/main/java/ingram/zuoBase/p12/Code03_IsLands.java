package ingram.zuoBase.p12;

/**
 * @Author qywu11
 * @Date 2023/2/17 10:32
 * @Version 1.0
 * 岛问题
 * 一个二维数组，查找其中连到一块的1个数，例如下面这个有2个岛
 * |0|1|1|
 * |0|0|1|
 * |0|0|0|
 * |1|0|0|
 */
public class Code03_IsLands {

    /**
     * 统计为1的数量
     * @param m
     * @return
     */
    public static int countIsLands(int[][] m){
        if(m == null || m[0] == null){
            return 0;
        }
        int N = m.length;
        int M = m[0].length;
        // 每次找到为1的就+1，infect会让周边的都变为2
        int res = 0;
        for (int i = 0;i< N; i++){
            for (int j = 0;j<M;j++){
                if(m[i][j] == 1){
                    res ++;
                    infect(m,i,j,N,M);
                }
            }
        }
        return res;
    }

    /**
     * 感染过程，将值为1的上下左右查出来，把等于1的全部设置为2，避免下次再用到导致死循环
     * @param m 二维数组
     * @param i 第几行
     * @param j 第几列
     * @param N 行长度
     * @param M 列长度
     */
    public static void infect(int[][] m,int i,int j,int N,int M){
        // i,j 没越界，并且当前位置值是1
        if(i< 0 || i>= N || j < 0 || j >= M || m[i][j] != 1){
            return;
        }
        m[i][j] = 2;
        infect(m,i + 1,j,N,M);
        infect(m,i - 1,j ,N,M);
        infect(m,i,j + 1,N,M);
        infect(m,i,j - 1,N,M);
    }
}
