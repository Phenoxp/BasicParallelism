package com.phenoxp.sorting;

public class Mergesort {

    private int[] nums;
    private int[] tempArray;

    public Mergesort(int[] nums) {
        this.nums = nums;
        tempArray = new int[nums.length];
    }

    public void parallelMergeSort(int low, int high, int nbreOfThreads) {

        if (nbreOfThreads <= 1) {
            mergeSort(low, high);
            return;
        }

        int middleIndex = (low + high) / 2;

        Thread leftSorter = mergeSortParallel(low, middleIndex, nbreOfThreads);
        Thread rightSorter = mergeSortParallel(middleIndex + 1, high, nbreOfThreads);

        leftSorter.start();
        rightSorter.start();

        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        merge(low, middleIndex, high);
    }

    private Thread mergeSortParallel(int low, int high, int nbreOfThreads) {
        return new Thread() {
            @Override
            public void run() {
                parallelMergeSort(low, high, nbreOfThreads / 2);
            }
        };
    }

    public void mergeSort(int low, int high) {
        if (low >= high) {
            return;
        }

        int middle = (low + high) / 2;

        mergeSort(low, middle);
        mergeSort(middle + 1, high);
        merge(low, middle, high);
    }

    public void showResult() {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public boolean isSorted() {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private void merge(int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            tempArray[i] = nums[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        while ((i <= middle) && (j <= high)) {
            if (tempArray[i] <= tempArray[j]) {
                nums[k] = tempArray[i];
                i++;
            } else {
                nums[k] = tempArray[j];
                j++;
            }
            k++;
        }

        while (i <= middle) {
            nums[k] = tempArray[i];
            i++;
            k++;
        }

        while (j <= high) {
            nums[k] = tempArray[j];
            j++;
            k++;
        }
    }
}
