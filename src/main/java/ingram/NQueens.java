package ingram;

/**
 * @Author qywu11
 * @Date 2023/1/6 15:35
 * @Version 1.0
 */
public class NQueens {

    public static int num1(int n){
        if(n < 1){
            return 0;
        }
        int[] record = new int[n]; // record[i] -> i行的皇后，放在第几列
        return process1(0,record,n);
    }

    // 不共行、不共列、不共斜线
    // 目前来到第i行
    // record[0..i-1]表示之前的行，放了皇后的位置
    // n代表整体一共多少行
    // 返回值是摆完所有皇后，合理的摆法有多少种
    public static int process1(int i,int[] record,int n){
        if(i == n){
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) { // j代表列, 尝试i行所有列
            // 验证i行放j列，会不会与(0..i-1)的皇后共列、共斜线
            if(isValid(record,i,j)){
                record[i] = j;
                res += process1(i + 1,record,n);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record,int i,int j){
        for (int k = 0;k < i; k ++){
            if(j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)){
                return false;
            }
        }
        return true;
    }
}
