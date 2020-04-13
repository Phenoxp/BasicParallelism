package com.phenoxp.sum;

public class ParallelSum {

    private ParallelWorker[] sums;
    private int nbreOfThreads;

    public ParallelSum(int nbreOfThreads) {
        this.sums = new ParallelWorker[nbreOfThreads];
        this.nbreOfThreads = nbreOfThreads;
    }

    public int sum(int[] nums) {
        int steps = (int) Math.ceil(nums.length * 1.0 / nbreOfThreads);

        for (int i = 0; i < nbreOfThreads; i++) {
            sums[i] = new ParallelWorker(nums, i * steps, (i + 1) * steps);
            sums[i].start();
        }
        try {
            for (ParallelWorker worker : sums)
                worker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int total = 0;
        for (ParallelWorker worker : sums)
            total += worker.getPartialSum();

        return total;
    }
}
