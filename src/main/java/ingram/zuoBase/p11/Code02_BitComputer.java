package ingram.zuoBase.p11;

/**
 * @Author qywu11
 * @Date 2023/2/15 10:06
 * @Version 1.0
 */
public class Code02_BitComputer {

    public static void main(String[] args) {
        int a = 0;// 32 bit
        int[] arr = new int[10]; // 32bit * 10 -> 320bits
        // arr[0] int 0 ~ 31
        // arr[1] int 32 ~ 63
        // arr[2] int 64 ~ 95

        int i = 178; // 想取得178个bit的状态
        int numIndex = i/32;
        int bitIndex = i%32;
        // 拿到178位的状态
        int s = ( (arr[numIndex] >> (bitIndex)));
        System.out.println(s);
        // 把178位状态改成1
        arr[numIndex] = arr[numIndex] | (1 << (bitIndex));

        // 把178位状态改成0
        // arr[numIndex] = arr[numIndex] & (~ (1 << (bitIndex)));

        // 拿到178位的状态
        int bit = (arr[i/32] >> (i % 32)) & 1;

        System.out.println(bit);
        System.out.println(arr[numIndex]);
    }
}
