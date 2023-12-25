package ingram.zuoBase.p13;

import io.swagger.models.auth.In;

import java.util.LinkedList;

/**
 * @Author qywu11
 * @Date 2023/2/27 10:09
 * @Version 1.0
 *
 * 窗口内最大值最小值更新结构
 *
 * 有一串数字和固定窗口，窗口大小为3，沿着数字从左向右移动，每次移动窗口中的最大值记录下来形成数组
 * 例如：31866753 第一次移动 [318]66753 最大值为8 索引值2
 * 第二次移动 3[186]6753 最大值为8 索引值2
 * 第三次移动 31[866]753 最大值为8 索引值2
 * 第四次移动 318[667]53 最大值为7 索引值5
 * ......
 * 最终得出窗口移动最大值数组：[8,8,8,7,7,7]
 *
 * 设计一个双端队列，头大尾小，窗口最大值为头部所代表的的值，扔掉数据永远不找回，有R、L两种移动方式
 *  →← [                ] →←
 *     头    大——>小     尾
 *  R往右，右侧进来一个数字，对比这个数字与左侧几个数字大小，如果比左侧数字大，需要弹出左侧比新数字较小的数
 *  如果比左侧数字小直接接到后面
 *  例如：→[3]← 新进入数字1，直接跟在后面队列更新为→[31]←； →[31]← 新进入数字8，双端队列需要依次从右边弹出扔掉1、3更新为 →[8]←
 *
 *  L往右，左侧出去一个数字，直接扔掉，双端队列更新
 *  例如：→[31]←，L向右侧移动，队列更新为→[1]←
 *
 *  扩展：如果要获取最小值，则设计的双端队列头小尾大即可
 */
public class Code07_MaxWindow {


    public static class WindowMax{
        private int[] arr;
        private int L;
        private int  R;
        private LinkedList<Integer> qMax;
        public WindowMax(int[] a){
            arr = a;
            L = -1;
            R = 0;
            qMax = new LinkedList<>();
        }

        public void addNumFromRight(){
            if(R == arr.length){
                return;
            }
            while ( !qMax.isEmpty() && qMax.peekLast() < arr[R]){
                qMax.pollLast();
            }
            qMax.addLast(R);
            R ++;
        }

        public void removeNumFromLeft(){
            if(L >= R - 1){
                return;
            }
            L ++;
            if (qMax.peekFirst() == L){
                qMax.pollFirst();
            }
        }

        public Integer getMax(){
            if(!qMax.isEmpty()){
                return arr[qMax.peekFirst()];
            }
            return null;
        }


    }

    // O(1) 每个点只进出一次
    public static int[] getMaxWindow(int[] arr,int w){
        if( arr == null || w < 1 || arr.length < w){
            return null;
        }
        LinkedList<Integer> qMax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            // 窗口 R向右移动，弹出所有小于新数字的值，因为后进入数字比前面晚弹出且数字大于等于前面数字，所以局部最大值为新加入数字
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]){
                qMax.pollLast();
            }
            // 加入新数字
            qMax.addLast(i);

            // 左侧过窗口，弹出左边首个数字
            if(w == i - qMax.peekFirst()){
                qMax.pollFirst();
            }
            // 位置大于窗口后，记录每个窗口最大数字到res中
            if(i >= w - 1){
                res[index ++] = arr[qMax.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,7,9,1,7,3,1,2};
        int[] res = getMaxWindow(arr,3);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]+",");
        }

        System.out.println("------------ res20230530 ------------ ");
        int[] res20230530 =getMaxWindow20230530(arr,3);
        for (int i = 0; i < res20230530.length; i++) {
            System.out.print(res20230530[i]+",");
        }
    }





    public static int[] getMaxWindow20230526(int[] arr,int w){
        LinkedList<Integer> qMax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        // [ 1,2,8,5,3,6]
        for (int i = 0; i < arr.length; i++) {
            while (!qMax.isEmpty() && arr[i] >= arr[qMax.peekLast()]){
                qMax.pollLast();
            }
            qMax.addLast(i);
            if(i - w == qMax.peekFirst()){
                qMax.pollFirst();
            }
            if(i >= w - 1){
                res[index ++] = arr[qMax.peekFirst()];
            }
        }

        return res;
    }

    public static int[] getMaxWindowUseClass(int[] arr,int w){

        int[] res = new int[arr.length - w + 1];
        WindowMax wm = new WindowMax(arr);
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            wm.addNumFromRight();
            if(!wm.qMax.isEmpty() && wm.qMax.peekLast() <= arr[i]){
                wm.addNumFromRight();
            }
            // 左侧过窗口，弹出左边首个数字
            if(!wm.qMax.isEmpty() && w == i - wm.qMax.peekFirst()){
                wm.removeNumFromLeft();
            }
            // 位置大于窗口后，记录每个窗口最大数字到res中
            if(!wm.qMax.isEmpty() && i >= w - 1){
                res[index ++] = wm.getMax();
            }

        }

        return res;
    }

    public static int[] getMaxWindow20230530(int[] arr,int w){
        if(null == arr || w < 1 || arr.length < w ){
            return null;
        }
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        LinkedList<Integer> qMax = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {

            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]){
                qMax.pollLast();
            }
            qMax.addLast(i);

            if(w == i - qMax.peekFirst()){
                qMax.pollFirst();
            }

            if(i >= w - 1){
                res[index ++] = arr[qMax.peekFirst()];
            }

        }
        return res;
    }

    public static int[] getMaxWin20230703(int[] arr,int w){
        if(arr == null || w < 1 || w > arr.length){
            return null;
        }

        int[] res = new int[arr.length - w];
        int index = 0;
        LinkedList<Integer> qMax = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {

            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]){
                qMax.pollLast();
            }

            qMax.addLast(i);

            if(i - w == qMax.peekFirst()){
                qMax.pollFirst();
            }

            if(i >= w - 1){
                res[index ++] = arr[qMax.peekFirst()];
            }

        }

        return res;
    }

}
