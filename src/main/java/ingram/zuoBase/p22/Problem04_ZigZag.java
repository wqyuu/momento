package ingram.zuoBase.p22;

/**
 * @Author qywu11
 * @Date 2023/4/21 15:45
 * @Version 1.0
 * 蛇形打印矩形
 */
public class Problem04_ZigZag {
    public static void printMatrixZigZag(int[][] matrix){
        int ar = 0;
        int ac = 0;
        int br = 0;
        int bc = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;
        while (ar != endR + 1){
            printLevel(matrix,ar,ac,br,bc,fromUp);
            ar = ac == endC ? ar + 1 : ar;
            ac = ar == endR ? ac + 1 : ac;
            br = bc == endC ? br + 1 : br;
            bc = br == endR ? bc + 1 : bc;
            fromUp = !fromUp;
        }

    }

    public static void  printLevel(int[][] m,int tR,int tC,int dR,int dC,boolean f){

        if(f){
            while (tR != dR + 1){
                System.out.println(m[tR ++][tC --] + " ");
            }
        }else {
            while (dR != tR - 1){
                System.out.println(m[dR --][tC ++] + " ");
            }
        }
    }

}
