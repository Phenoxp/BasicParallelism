package com.phenoxp.sorting;

import java.util.Random;

public class AppForParallelized {

    static Random random = new Random();

    public static void main(String [] args){

        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of Threads "+numberOfThreads);
        System.out.println("");


        int[] nums = createRandomArray();
        Mergesort mergeSort = new Mergesort(nums);
        Mergesort mergeSort2 = new Mergesort(nums);

        long startTime = System.currentTimeMillis();
        mergeSort.parallelMergeSort(0, nums.length-1, numberOfThreads);
        long endTime = System.currentTimeMillis();
//        mergeSort.showResult();

        System.out.println("Time taken for 200.000.000 elements in parallel: "+ (endTime - startTime));

        startTime = System.currentTimeMillis();
        mergeSort2.mergeSort(0, nums.length-1);
        endTime = System.currentTimeMillis();
        System.out.println("Time taken for 200.000.000 elements sequentially: "+ (endTime - startTime));
    }

    private static int[] createRandomArray(){
        int limit = 200000000;
        int[] a = new int[limit];
        for(int i=0; i<limit; i++){
            a[i] = random.nextInt(limit);
        }

        return a;
    }
}
