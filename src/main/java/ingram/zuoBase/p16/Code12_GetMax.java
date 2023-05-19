package ingram.zuoBase.p16;

/**
 * @Author qywu11
 * @Date 2023/3/31 17:39
 * @Version 1.0
 * 位运算
 * 给定两个32位整数a和b，返回a和b中较大的，要求不能用任何比较判断
 */
public class Code12_GetMax {
    // 请保证参数n，不是0就是1情况下
    // 1->0
    // 0->1
    public static int flip(int n){
        return n ^ 1;
    }

    // n是正数，返回1
    // n是负数，返回0
    public static int sign(int n){
        return flip( (n >> 31) & 1);
    }

    public static int getMax1(int a,int b){
        int c = a - b;
        int scA = sign(c); // a-b为非负，scA为1; a-b为负，scA为0
        int scB = flip(scA); // scA为1,scB为0; scA为0,scB为1
        // scA=0,scB=1; scA=1,scB=0;
        return a * scA + b * scB;
    }

    public static int getMax2(int a,int b){
        int c = a - b;
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        int difSab = sa ^ sb; // a,b符合不同，为1；一样为0
        int sameSab = flip(difSab); // a,b符合同，为1；不一样为0
        int returnA = difSab * sa + sameSab * sc;
        int returnB =  flip(returnA);
        return a * returnA + b * returnB;
    }

}
