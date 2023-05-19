package ingram.zuoBase.p26;

import java.util.Scanner;

/**
 * @Author qywu11
 * @Date 2023/5/18 14:42
 * @Version 1.0
 *
 * 小Q得到一个神奇的数列: 1, 12, 123,…12345678910,1234567891011…。
 * 并且小Q对于能否被3整除这个性质很感兴趣。
 * 小Q现在希望你能帮他计算一下从数列的第l个到第r个(包含端点)有多少个数可以被3整除。
 * 输入描述:
 * 输入包括两个整数l和r(1 <= l <= r <= 1e9), 表示要求解的区间两端。
 * 输出描述:
 * 输出一个整数, 表示区间内能被3整除的数字个数。
 */
public class Problem04_Div3AddArr {

        public static void main(String[] args){
            Scanner s = new Scanner(System.in);
            int start = s.nextInt();
            int end = s.nextInt();
            if(start>end){
                System.out.println(0);
                return;
            }
            int count = 0;
            for(long i = start;i<=end;i++){
                long r = ((1+i)*i/2)%3;
                if(r==0)
                    count++;
            }
            System.out.println(count);
        }
}
