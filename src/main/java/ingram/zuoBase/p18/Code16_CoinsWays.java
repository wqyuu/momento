package ingram.zuoBase.p18;

/**
 * @Author qywu11
 * @Date 2023/4/5 14:49
 * @Version 1.0
 * arr[]正数，每种货币，无重复值，可使用无限张
 * 最终要获得的钱数是aim
 * 例如：有[1,25,19,30,3,2,5,7,8,10,29,15]这么多面值钱币，构成50 有多少种方法
 *
 */
public class Code16_CoinsWays {
    public static int way1(int[] arr,int aim){
        return process(arr,0,aim);
    }

    // 使用arr[index...]所有面值
    // 需要搞定的钱数是rest
    // 返回找零的方法数
    public static int process(int[] arr,int index,int rest){
        if(index == arr.length){
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        // i 多少张
        for (int i = 0;arr[index] * i <= rest; i++) {
            ways += process(arr,index + 1 , rest - arr[index]*i);
        }

        return ways;
    }

    public static int way2(int[] arr,int aim){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N+1][aim+1];
        dp[N][0] = 1;
        for (int index =N - 1; index >=0 ; index--) {
            for (int rest = 0; rest <= aim ; rest++) {
                int ways = 0;
                // i 多少张
                for (int i = 0;arr[index] * i <= rest; i++) {
                    ways += dp[index + 1 ][rest - arr[index]*i];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    // 根据二维表 推位置依赖
    public static int way3(int[] arr,int aim){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N+1][aim+1];
        dp[N][0] = 1;
        for (int index =N - 1; index >=0 ; index--) {
            for (int rest = 0; rest <= aim ; rest++) {
                dp[index][rest] = dp[index + 1 ][rest];
                if(rest - arr[index] >= 0){
                    dp[index][rest] +=  dp[index][rest- arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    // for test
    public static int[] generateRandomArray(int len,int max){
        int[] arr = new int[(int)(Math.random() * len) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // 对数器
    public static void main(String[] args) {
        System.out.println("----start----");
        int len = 10;
        int max = 10;
        int testTime = 10000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(len,max);
            int aim = (int) (Math.random() * 3 * max) + max;
            if(way1(arr,aim) != way2(arr,aim) || way2(arr,aim) != way3(arr,aim)){
                System.out.println("ooops!");
                break;
            }
        }
        System.out.println("----end----");
    }
}
