package ingram.DCodeRecord;

/**
 * @Author qywu11
 * @Date 2024/1/30 14:32
 * @Version 1.0
 */
public class String20240130 {
    // 344.反转字符串
    public static void reverseString(char[] s) {
        int start = 0,end = s.length -1;
        while (start < end){
            char tmp = s[start];
            s[start++] = s[end];
            s[end--] = tmp;
        }
    }

    public static void reverseString(char[] s,int start,int end) {
        while (start < end && start < s.length && end > 0){
            char tmp = s[start];
            s[start++] = s[end];
            s[end--] = tmp;
        }
    }

    // 541. 反转字符串II 每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
    public String reverseStr(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i += 2 * k) {
            reverse(arr, i, Math.min(i + k, n) - 1);
        }
        return new String(arr);
    }

    public void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
