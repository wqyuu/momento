package ingram.zuoBase.p21;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author qywu11
 * @Date 2023/4/20 16:49
 * @Version 1.0
 * 两个集合，让两个集合平均，进行多次magic。求最多次数；magic就是移动集合元素到另一个集合里去
 * 注意：1.集合中无重复值 2.两个集合都有数字
 * 贪心，业务题
 */
public class Problem03_MagicOP {

    public static int maxOps(int[] arr1,int[] arr2){
        double sum1 = 0;
        for (int i = 0; i < arr1.length; i++) {
            sum1 += (double) arr1[i];
        }
        double sum2 = 0;
        for (int i = 0; i < arr2.length; i++) {
            sum2 += (double) arr2[i];
        }

        if(avg(sum1,arr1.length) == avg(sum2,arr2.length)){
            return 0;
        }

        // 平均值不相等
        int[] arrMore = null;
        int[] arrLess = null;
        double sumMore = 0;
        double sumLess = 0;
        if(avg(sum1,arr1.length) > avg(sum2,arr2.length)){
            arrMore = arr1;
            sumMore = sum1;
            arrLess = arr2;
            sumLess = sum2;
        }else {
            arrMore = arr2;
            sumMore = sum2;
            arrLess = arr1;
            sumLess = sum1;
        }
        Arrays.sort(arrMore);
        HashSet<Integer> setLess = new HashSet<>();
        for (int num:arrLess){
            setLess.add(num);
        }
        int moreSize = arrMore.length; // 平均值大的集合还剩几个数
        int lessSize = arrMore.length; // 平均值小的集合还剩几个数
        int ops = 0; // 操作多少次，为了操作更多次，所以挪符合规则下最小的数
        for (int i = 0; i < arrMore.length; i++) { // 小->大
            double cur = (double) arrMore[i];
            if(cur < avg(sumMore,moreSize) && cur > avg(sumLess,lessSize)
                    && !setLess.contains(arrMore[i])){
                sumMore -= cur;
                moreSize --;
                sumLess += cur;
                lessSize ++;
                setLess.add(arrMore[i]);
                ops ++;
            }
        }
        return ops;
    }

    public static double avg(double sum,int size){
        return sum / (double) (size);
    }
}
