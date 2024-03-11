package ingram.hw;

import java.util.*;

// 第二题 CPU算力分配 第五次修改
public class CPUDisput {
    private static int hanshu(int[] zanshi, int temp) {
        int jishuqi = -1;
        int zzz = 0;
        while(zzz < zanshi.length){
            if (zanshi[zzz] == temp) {
                jishuqi = zzz;
            }
            zzz++;
        }

        return jishuqi;
    }

    public static Scanner shuru = null;
    public static String[] one = null;
    public static String[] two = null;
    public static String[] three = null;

    public static void gaoshan01(){
        one = shuru.nextLine().split(" ");
        two = shuru.nextLine().split(" ");
    }

    public static void gaoshan02(){
        shuru = new Scanner(System.in);
        gaoshan01();
        three = shuru.nextLine().split(" ");
    }

    public static  int[] first;
    public static int[] second;

    public static void gaoshan03(){
        first = new int[Integer.parseInt(one[0])];
        second = new int[Integer.parseInt(one[1])];
    }

    public static void gaoshan04(){
        int zzz = 0;

        while(zzz < first.length){
            first[zzz] = Integer.parseInt(two[zzz]);
            zzz++;

        }
    }

    public static void gaoshan05(){
        for (int zzz = 0; zzz < second.length; zzz++) {
            second[zzz] = Integer.parseInt(three[zzz]);
        }

    }

    public static void gaoshan06(){
        int zzz = 0;

        while( zzz < first.length - 1){
            int xxx = zzz + 1;
            for (; xxx < first.length; xxx++) {
                if (first[zzz] > first[xxx]) {
                    int naxienian = first[zzz];
                    first[zzz] = first[xxx];
                    first[xxx] = naxienian;
                }
            }
            zzz++;
        }

    }

    public static void gaoshan07(){
        int zzz = 0;

        while(zzz < second.length - 1){
            int uuu = zzz + 1;
            for (; uuu < second.length; uuu++) {
                if (second[zzz] > second[uuu]) {
                    int zanshizanshi = second[zzz];
                    second[zzz] = second[uuu];
                    second[uuu] = zanshizanshi;
                }
            }
            zzz++;
        }

    
    }

    public static void gaoshan08(){
        
        for (int i = 0; i < first.length; i++) {
            shandong += first[i];
        }

        for (int i = 0; i < second.length; i++) {
            shanxi += second[i];
        }
    }

    public static void gaoshan09(){
        if (shandong > shanxi) {
            int nashihou = (shandong - shanxi) / 2;
            for (int suiyue = 0; suiyue < first.length; suiyue++) {
                int temosss = hanshu(second, first[suiyue] - nashihou);
                if (temosss != -1) {
                    System.out.print(first[suiyue] + " ");
                    System.out.print(second[temosss]);
                    break;
                }
            }
        }
    }

    public static void gaoshan10(){
        if (shandong < shanxi) {
            int zanhi001 = (shanxi - shandong) / 2;
            for (int hahaha = 0; hahaha < second.length; hahaha++) {
                int zanshide = hanshu(first, second[hahaha] - zanhi001);
                if (zanshide != -1) {
                    System.out.print(first[zanshide] + " ");
                    System.out.print(second[hahaha]);
                    break;
                }
            }
        }
    }

    public static int shandong = 0;
    public static int shanxi = 0;
    public static void main(String[] args) {
        gaoshan02();

        gaoshan03();

        gaoshan04();

        gaoshan05();

        gaoshan06();

        gaoshan07();


        shandong = 0;
        shanxi = 0;

        gaoshan08();

        gaoshan09();

        gaoshan10();




    }
}