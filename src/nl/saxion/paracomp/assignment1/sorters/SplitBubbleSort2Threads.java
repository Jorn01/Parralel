package nl.saxion.paracomp.assignment1.sorters;

import java.util.Arrays;

import nl.saxion.paracomp.assignment1.Utils;

public class SplitBubbleSort2Threads extends BaseSorter {

    public SplitBubbleSort2Threads() {
        super("splitBubble2");
    }

    @Override
    public int[] sort(int[] numbers) {
        // Split the array into two halves
        int n = numbers.length;
        int mid = n / 2;

        int[] left = new int[mid];
        int[] right = new int[n - mid];

        // Copy the data into left and right arrays
        System.arraycopy(numbers, 0, left, 0, mid);
        System.arraycopy(numbers, mid, right, 0, n - mid);

        // Sort each half
        Thread leftThread = new Thread(() -> {
            Utils.bubbleSort(left);
        });
        Thread rightThread = new Thread(() -> {
            Utils.bubbleSort(right);
        });

        leftThread.start();
        rightThread.start();

        try {
            leftThread.join();
            rightThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Merge the sorted halves
        numbers = Utils.merge(left, right);
        // Check if the array is sorted
        if (!Utils.isSorted(numbers)) {
            System.err.println("The list is not sorted");
        }
        return numbers;
    }

}
