package ingram.zuoBase.p27;

public class Problem01_PrintNoInArray {

    // 请保证arr[0..N-1]上的数字都在[1～n]之间
    public static void printNumberNoInArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            modify(arr[i], arr);
        }

        // 打印不符合规则位置
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i + 1) {
                System.out.println(i + 1);
            }
        }
    }

    public static void modify(int value, int[] arr) {
        while (arr[value - 1] != value) {
            int tmp = arr[value - 1];
            arr[value - 1] = value;
            value = tmp;
        }
    }

    public static void main(String[] args) {
        int[] test = { 3, 2, 3, 5, 6, 1, 6 };
        printNumberNoInArray(test);
    }
}