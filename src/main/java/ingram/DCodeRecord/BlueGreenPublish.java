package ingram.DCodeRecord;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author qywu11
 * @Date 2024/1/30 14:31
 * @Version 1.0
 * 蓝绿发布
 */
public class BlueGreenPublish {


    public static String selectColor(String[] colorArr, int[] weightArr) {
        int length = colorArr.length;
        boolean sameWeight = true;
        int totalWeight = 0;
        for (int i = 0; i < length; i++) {
            int weight = weightArr[i];
            totalWeight += weight;
            if (sameWeight && totalWeight != weight * (i + 1)) {
                sameWeight = false;
            }
        }
        if (totalWeight > 0 && !sameWeight) {
            int offset = ThreadLocalRandom.current().nextInt(totalWeight);
            System.out.println("offset:" + offset);
            for (int i = 0; i < length; i++) {
                if (offset < weightArr[i]) {
                    return colorArr[i];
                }
            }
        }
        return colorArr[ThreadLocalRandom.current().nextInt(length)];
    }

    //测试代码
    public static void main(String[] args) {
        String[] colorArr = new String[]{"GREEN","BLUE"};
        int[] weightArr = new int[] {10,50};
        for(int i = 0; i < 20; i ++) {
            System.out.println(selectColor(colorArr, weightArr));
        }
    }
}
