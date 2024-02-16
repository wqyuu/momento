package ingram.zuoBase.p22;

/**
 * 打印旋转矩阵
 * 有一个NxN整数矩阵，请编写一个算法，将矩阵顺时针旋转90度。
 * 给定一个NxN的矩阵，和矩阵的阶数N,请返回旋转后的NxN矩阵。
 * 数据范围：0<n<3000<n<300，矩阵中的值满足 0≤val≤10000≤val≤1000
 * 要求：空间复杂度 O(N2)O(N2)，时间复杂度 O(N2)O(N2)
 * 进阶：空间复杂度 O(1)O(1)，时间复杂度 O(N2)O(N2)
 *
 * a b c d
 * e f g h
 * i j k l
 * p o m n
 *
 * 思路：旋转90度，会将外圈和内圈都旋转90度，也就是讲四边旋转，再将四边进行分组，4个1组
 * 如，第一组为[a,d,n,p]
 * 第二组[b,h,m,i]
 * 取左上角坐标为(a,b),右下角坐标为(c,d)，则分组规律中，第i个分组 四个元素为
 * m[a][b + i] 左上
 * m[c - i][b] 左下
 * m[a + i][d] 右上
 * m[c][d - i] 右下
 */
public class Problem03_RotateMatrix {

    /**
     * 将矩阵顺时针旋转90度
     *
     * @param matrix 输入的矩阵
     */
    public static void rotate(int[][] matrix) {
        int tR = 0; // 上边界
        int tC = 0; // 左边界
        int dR = matrix.length - 1; // 下边界
        int dC = matrix[0].length - 1; // 右边界
        while (tR < dR) { // 通过不断缩小边界的方式转圈
            rotateEdge(matrix, tR++, tC++, dR--, dC--); // 转动当前边界
        }
    }

    /**
     * 旋转给定边界上的元素
     *
     * @param m 边界所在的矩阵
     * @param a 边界上的左上角行坐标
     * @param b 边界上的左上角列坐标
     * @param c 边界上的右下角行坐标
     * @param d 边界上的右下角列坐标
     */
    public static void rotateEdge(int[][] m, int a, int b, int c, int d) {
        int times = d - b; // 当前边界上元素的数量
        int tmp = 0; // 临时变量，用于元素交换
        for (int i = 0; i != times; i++) { // 循环交换元素
            tmp = m[a][b + i];
            m[a][b + i] = m[c - i][b];
            m[c - i][b] = m[c][d - i];
            m[c][d - i] = m[a + i][d];
            m[a + i][d] = tmp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}