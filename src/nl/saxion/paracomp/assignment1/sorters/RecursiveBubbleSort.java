package nl.saxion.paracomp.assignment1.sorters;

import nl.saxion.paracomp.assignment1.Utils;

public class RecursiveBubbleSort extends BaseSorter {
    private int DrempelWaarde = 1000;

    public RecursiveBubbleSort(int DrempelWaarde) {
        super("treeSplitBubble");
        this.DrempelWaarde = DrempelWaarde;
    }

    @Override
    public int[] sort(int[] numbers) {
        if (numbers.length < DrempelWaarde) {
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

        // Sort each half
        Thread leftThread = new Thread(() -> {
            sort(left);
        });
        Thread rightThread = new Thread(() -> {
            sort(right);
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
        int[] mergedArray = Utils.merge(left, right);

        // Copy mergedArray back into numbers
        System.arraycopy(mergedArray, 0, numbers, 0, n);

        // Check if the array is sorted
        if (!Utils.isSorted(numbers)) {
            System.err.println("The list is not sorted");
        }

        return numbers;
    }
}
