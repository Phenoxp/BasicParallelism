package com.phenoxp.sorting;

import java.util.Random;

public class Application {

    public static void main(String [] args){
        Random random = new Random();

        int[] nums = new int[30];

        for(int j=0; j<nums.length; j++)
            nums[j] = random.nextInt(1000)-500;

        //O(n log n)
        Mergesort mergeSort = new Mergesort(nums);
        mergeSort.mergeSort(0, nums.length - 1);
        mergeSort.showResult();
    }
}
