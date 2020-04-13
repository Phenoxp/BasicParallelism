package com.phenoxp.sum;

import java.util.Random;

public class App {

    public static void main(String[] args){
        Random random = new Random();
        int processors = Runtime.getRuntime().availableProcessors();

        SequentialSum sequentialSum = new SequentialSum();
        ParallelSum parallelSum = new ParallelSum(processors);
        int[] nums = new int[1000000000];

        for(int i=0; i<nums.length; i++){
            nums[i] = random.nextInt(100);
        }

        long start = System.currentTimeMillis();
        System.out.println(sequentialSum.sum(nums));
        long end = System.currentTimeMillis();
        System.out.println("Sequantial Duration: "+(end - start)+ " ms");

        start = System.currentTimeMillis();
        System.out.println(parallelSum.sum(nums));
        end = System.currentTimeMillis();
        System.out.println("Parallel Duration: "+(end - start)+ " ms");
    }
}
