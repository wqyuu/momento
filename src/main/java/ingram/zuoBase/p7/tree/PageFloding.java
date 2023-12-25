package ingram.zuoBase.p7.tree;

/**
 * @Author qywu11
 * @Date 2022/12/7 15:29
 * @Version 1.0
 * 微软原题： 折纸请把纸条竖着放在桌⼦上，然后从纸条的下边向上⽅对折，压出折痕后再展开。
 * 此时有1条折痕，突起的⽅向指向纸条的背⾯，这条折痕叫做“下”折痕 ；
 * 突起的⽅向指向纸条正⾯的折痕叫做“上”折痕。
 * 如果每次都从下边向上⽅ 对折，对折N次。
 * 请从上到下计算出所有折痕的⽅向。
 * 给定折的次数n,请返回从上到下的折痕的数组，若为下折痕则对应元素为"down",若为上折痕则为"up".
 * 例如:N=1时，打印: down N=2时，打印: down down up。

 */
public class PageFloding {

    static void printAllFlods(int N){
        process(1,N,true);
    }

    static void process(int i,int N,boolean down){
        if(i > N){
            return;
        }
        process(i+1,N,true);
        System.out.print(down?"凹":"凸");
        process(i+1,N,false);
    }
    // 1  1 2 1 1 2 1 2 2

    public static void main(String[] args) {
        printAllFlods(5);
    }


}
