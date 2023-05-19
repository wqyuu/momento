package ingram.zuoBase.p22;

/**
 * 【题目】 给定一个整型矩阵matrix，请按照转圈的方式打印它。
 * 例如： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 打印结果为：1，2，3，4，8，12，16，15，14，13，9， 5，6，7，11， 10
 * 【要求】 额外空间复杂度为O(1)。
 *
 * 思路：
 * 不要局限于一个个怎么变，考虑左上角和右下角。打印边上的框，然后再往内进一个。
 *
 * 左上坐标(tR,tC) 右上坐标(dR,dC)
 */
public class Problem02_PrintMatrixSpiralOrder {

    public static void spiralOrderPrint(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while (tR <= dR && tC <= dC) {
            printEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    public static void printEdge(int[][] m, int a, int b, int c, int d) {
        if (a == c) {
            for (int i = b; i <= d; i++) {
                System.out.print(m[a][i] + " ");
            }
        } else if (b == d) {
            for (int i = a; i <= c; i++) {
                System.out.print(m[i][b] + " ");
            }
        } else {
            int curC = b;
            int curR = a;
            while (curC != d) {
                System.out.print(m[a][curC] + " ");
                curC++;
            }
            while (curR != c) {
                System.out.print(m[curR][d] + " ");
                curR++;
            }
            while (curC != b) {
                System.out.print(m[c][curC] + " ");
                curC--;
            }
            while (curR != a) {
                System.out.print(m[curR][b] + " ");
                curR--;
            }
        }
    }
}