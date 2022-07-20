package com.wqy.momento.ingram;

import java.util.PriorityQueue;

/**
 * @Author qywu11
 * @Date 2022/7/18 9:03
 * @Version 1.0
 * K 范围内相对有序； 元素移动最远不超过k
 */
public class SortArrayDistanceLessK {

    public static void sortArrayDistanceLessK(int [] arr,int k){
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index < Math.min(arr.length,k); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++,index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()){
            arr[i++] = heap.poll();
        }
    }

    public static void main(String[] args) {
        int [] arr = new int[]{1,2,6,9,5,7,3};
        sortArrayDistanceLessK(arr,2);
        for (int i:arr){
            System.out.println(i);
        }
    }
}
