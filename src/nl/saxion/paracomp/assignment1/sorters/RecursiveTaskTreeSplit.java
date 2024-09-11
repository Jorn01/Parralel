package nl.saxion.paracomp.assignment1.sorters;

import java.util.concurrent.RecursiveTask;

import nl.saxion.paracomp.assignment1.Utils;

public class RecursiveTaskTreeSplit extends RecursiveTask<int[]> {
    private int threshold = 1000; // Example threshold value
    private final int[] numbers;

    public RecursiveTaskTreeSplit(int[] numbers, int threshold) {
        this.numbers = numbers;
        this.threshold = threshold;
    }

    @Override
    protected int[] compute() {
        if (numbers.length < threshold) {
            Utils.bubbleSort(numbers);
            return numbers;
        }
        // Split the array into two halves
        int n = numbers.length;
        int mid = n / 2;

        int[] left = new int[mid];
        int[] right = new int[n - mid];

        // Copy the data into left and right arrays
        System.arraycopy(numbers, 0, left, 0, mid);
        System.arraycopy(numbers, mid, right, 0, n - mid);

        // Create subtasks
        RecursiveTaskTreeSplit leftTask = new RecursiveTaskTreeSplit(left, threshold);
        RecursiveTaskTreeSplit rightTask = new RecursiveTaskTreeSplit(right, threshold);

        // Fork the subtasks
        leftTask.fork();
        rightTask.fork();

        // Join the results
        int[] leftResult = leftTask.join();
        int[] rightResult = rightTask.join();

        // Merge the sorted halves
        int[] mergedArray = Utils.merge(leftResult, rightResult);

        // Copy mergedArray back into numbers
        System.arraycopy(mergedArray, 0, numbers, 0, n);

        // Check if the array is sorted
        if (!Utils.isSorted(numbers)) {
            System.err.println("The list is not sorted");
        }

        return numbers;
    }
}
