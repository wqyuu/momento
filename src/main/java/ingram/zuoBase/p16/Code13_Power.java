package ingram.zuoBase.p16;

/**
 * @Author qywu11
 * @Date 2023/3/31 18:07
 * @Version 1.0
 */
public class Code13_Power {

    public static boolean is2Power(int n){
        return (n & (n-1)) == 0;
    }

    public static boolean is4Power(int n){
        return (n & (n-1)) == 0 &&  (n & 0x55555555) != 0;
    }

    public static void main(String[] args) {
        System.out.println(is4Power(4));
        System.out.println(is4Power(16));
        System.out.println(is4Power(63));
    }
}
