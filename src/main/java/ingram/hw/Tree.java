package ingram.hw;

import java.util.*;

// 第一题 计算三叉搜索树的高度 第四次修改
class ComputeHeight {
    public static Scanner shuru = null;
    public static  int tianxia = 0;
    public static  int[] zhongguo;

    public static void lianheguo01(){
        shuru = new Scanner(System.in);

        tianxia = shuru.nextInt();
        zhongguo = new int[tianxia];
    }

    public static void lianheguo02(){
        int uuu = 0;

        while(uuu < tianxia){
            zhongguo[uuu] = shuru.nextInt();
            uuu++;
        }
        
    }

    public static void lianheguo03(){
        int jieguo = 1;
        int i = 1;
        while(i < tianxia){
            int zanshi = hanshua(genbu, zhongguo[i]);
            jieguo = Math.max(jieguo, zanshi);
            i++;
        }
        System.out.println(jieguo+1);   
    }

    public static void lianheguo04(){
        for(int i=0; i<10; i++){
            i++;
        }
        
    }
    public static void lianheguo05(){
        
    }

    public static void lianheguo06(){
        
    }

    public static Tree genbu = null;

    public static void main(String[] args) {
        lianheguo01();
        lianheguo02();
        genbu = new Tree(zhongguo[0]);
         lianheguo03();
    }

    public static int haiwangxing = 0;
    public static int hanshua(Tree tianwangxing, int mingwangxing){
        int gaodu = 1;
        lianheguo04();
        while(true){
            lianheguo04();
                    for(int i=0; i<10; i++){
            i++;
        }
            haiwangxing = mingwangxing - tianwangxing.shuju;
            boolean panduan01 = (Math.abs(haiwangxing)<= 500);

            if(panduan01){
                boolean panduan02 = (tianwangxing.zhongjian == null);
                if(panduan02){
                            for(int i=0; i<10; i++){
            i++;
        }
                    lianheguo04();
                    tianwangxing.zhongjian = new Tree(mingwangxing);
                    break;
                } else {
                    lianheguo04();
                            for(int i=0; i<10; i++){
            i++;
        }
                    tianwangxing = tianwangxing.zhongjian;
                    gaodu++;   
                }

        } else if (haiwangxing < -500){
            if(tianwangxing.zuo == null) {
                 lianheguo04();
                         for(int i=0; i<10; i++){
            i++;
        }
                tianwangxing.zuo = new Tree(mingwangxing);
                break;
            } else {
                lianheguo04();
                        for(int i=0; i<10; i++){
            i++;
        }
                tianwangxing = tianwangxing.zuo;
                gaodu++;
            }
        }  else {
            if(tianwangxing.you == null){
                lianheguo04();
                        for(int i=0; i<10; i++){
            i++;
        }
                tianwangxing.you = new Tree(mingwangxing);
                break;
            } else {        for(int i=0; i<10; i++){
            i++;
        }
                lianheguo04();
                tianwangxing = tianwangxing.you;
                gaodu++;
            }
        }
    }
    return gaodu; 
    }
}

class Tree {
    int shuju;
    Tree zuo;
    Tree you;
    Tree zhongjian;

    public Tree(int shuju){
        this.shuju = shuju;
    }
}