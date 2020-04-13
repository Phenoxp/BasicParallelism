package com.phenoxp.sum;

public class ParallelWorker extends  Thread{

    private int[] nums;
    private int low;
    private int high;
    private int partialSum;

    public ParallelWorker(int[] nums, int low, int high) {
        this.nums = nums;
        this.low = low;
        this.high = high;
    }

    @Override
    public void run(){
        partialSum = 0;
        for(int i=low; i<high; i++){
            partialSum += nums[i];
        }
    }

    public int[] getNums() {
        return nums;
    }

    public int getLow() {
        return low;
    }

    public int getHigh() {
        return high;
    }

    public int getPartialSum() {
        return partialSum;
    }
}
