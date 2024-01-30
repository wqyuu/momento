package com.iflytek.biims.bhv.compute.service.impl;

class heapSortA {
    public static int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public static void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        } 
    }

    public static void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        } 
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void heapSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        // 9 2 7 1 3 5 8 0
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr,i);
        }
        int heapSize = arr.length;
        // 根与最后节点交换，并截取size-- 0 2 7 1 3 5 8 | 9
        swap(arr,0,--heapSize);
        while (heapSize>0){
            // 根节点交换到下面，把大数交换上来 8 2 3 4 5 1 0
            heapFiy(arr,0,heapSize);
            // 根与最后节点交换，并截取size-- 0 2 3 4 5 1 | 8
            swap(arr,0,--heapSize);
        }
    }

    public static void heapInsert(int[]arr , int index){
        // 当前数大于父位置数
        while(arr[index] > arr[(index - 1)/2]){
            swap(arr,index,(index - 1)/2);
            index = (index - 1)/2;
        }
    }


    /**
     *
     * @param index 父节点
     * @param arr
     * @param heapSize
     */
    public static void heapFiy(int[] arr,int index,int heapSize){

        int left = index*2 + 1;

        while (left < heapSize){ // 下方还有孩子，左孩子不越界，代表右孩子也不会越界，下标比右小

            // 左右孩子取最大
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left]?left + 1:left;
            // 较大孩子，父，取最大
            largest = arr[largest] > arr[index] ? largest : index;
            // 父和孩子一样
            if(largest == index){
                break;
            }
            // swap 较大孩子和父做交换
            swap(arr,largest,index);
            index = largest;
            // 继续往下走
            left = index * 2 + 1;
        }
    }






    public static void heapSortA(int[] arr){

        for (int i = 0; i < arr.length; i++) {
            heapInsertA(arr,i);
        }
        int heapSize = arr.length;
        swap(arr,0,--heapSize);
        while (heapSize>0){
            heapF(arr,0,heapSize);
            swap(arr,0,--heapSize);
        }
    }

    public static void heapInsertA(int[] arr,int index){
        while (arr[index] > arr[(index - 1) / 2]){
            swap(arr,index,(index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapF(int[] arr,int index,int heapSize){
        int left = index * 2 + 1;
        while (left < heapSize){
            int right = (left+1) < heapSize && arr[left+1] > arr[left]?left+1:left;
            right = arr[right] > arr[index]?right:index;

            if(right == index){
                break;
            }

            swap(arr,right,index);
            index = right;
            // 继续往下走
            left = index * 2 + 1;
        }
    }




















}
