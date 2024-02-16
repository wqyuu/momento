package ingram.forceRecursion;

/**
 * @Author qywu11
 * @Date 2023/1/9 15:50
 * @Version 1.0
 * 打印字符串的全部子序列
 */
public class PrintAllSubSquence {

    public static void main(String[] args) {

        printAllSubSquence("abc");
    }

    public static void printAllSubSquence(String str) {
        char[] arr = str.toCharArray();
        process(arr,0);
    }

    // 当前来到的i位置，要和不要两条路
    // 之前的结果是arr
    public static void process(char[] arr, int i) {
        if (i == arr.length) {
            System.out.println(String.valueOf(arr));
            return;
        }
        process(arr, i + 1); // 要当前字符
        char tmp = arr[i];
        arr[i] = 0;
        process(arr,i + 1); // 不要当前字符，先把arr[i]设置为0，用tmp保存
        arr[i] = tmp;
    }



}
