package ingram.zuoBase.p18;

/**
 * @Author qywu11
 * @Date 2023/4/3 11:16
 * @Version 1.0
 */
public class Code14_HorseJump {

    /**
     * 在象棋的棋盘上 “马” 从(0,0)到达(x,y),走step步，有多少种方法
     * 棋盘9*10
     * @param x
     * @param y
     * @param step
     * @return
     */
    public static int process(int x,int y,int step){
        // 越界情况 方法数为0
        if(x < 0 || y < 0 || x > 8 || y > 9){
            return 0;
        }
        // 当没有步数时，只有x=0 y=0位置 是有一个方法，这个方法就是不需要动，其他都是0
        if(step == 0){
            return (x == 0 && y == 0)?1:0;
        }

        /**
         * #位置的方法数，要看*位置所有方法数之和，走到*位置的方法在走一步就到#
         * y  0 1 2 3 4 5 6 7 8 9   -> x
         * 0  1
         * 1
         * 2
         * 3          *   *
         * 4        *       *
         * 5            #
         * 6        *       *
         * 7          *   *
         * 8
         */

        // 正常情况内，x,y 步数step的方法数，取决于棋盘上周围8个位置的方法数之和
        return process(x-1,y-2,step - 1) + process(x+1,y-2,step - 1) +
                process(x-1,y+2,step - 1) + process(x+1,y+2,step - 1) +
                process(x+2,y - 1,step - 1) + process(x-2,y - 1,step - 1) +
                process(x+2,y+1,step - 1) + process(x-2,y+1,step - 1) ;
    }

    public static int dpWays(int x,int y,int step){
        if(x<0 || x >8 || y < 0 || y > 9){
            return 0;
        }
        int[][][] dp = new int[9][10][step+1];
        dp[0][0][0] = 1;

        for (int s = 1; s <= step; s++) {
            for (int i = 0; i < 9 ; i++) {
                for (int j = 0; j < 10; j++) {

                    dp[i][j][s] += getValue(dp,i - 1,j + 2,s -1 );
                    dp[i][j][s] += getValue(dp,i + 1,j + 2,s -1 );
                    dp[i][j][s] += getValue(dp,i + 2,j + 1,s -1 );
                    dp[i][j][s] += getValue(dp,i + 2,j - 1,s -1 );
                    dp[i][j][s] += getValue(dp,i + 1,j - 2,s -1 );
                    dp[i][j][s] += getValue(dp,i - 1,j - 2,s -1 );
                    dp[i][j][s] += getValue(dp,i - 2,j - 1,s -1 );
                    dp[i][j][s] += getValue(dp,i - 2,j + 1,s -1 );
                }
            }
        }

        return dp[x][y][step];
    }

    public static int getValue(int[][][] dp,int row,int col,int step){
        if(row<0 || row >8 || col < 0 || col > 9){
            return 0;
        }
        return dp[row][col][step];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step  = 10;
        System.out.println(dpWays(x,y,step));
        System.out.println(process(x,y,step));
    }
}
