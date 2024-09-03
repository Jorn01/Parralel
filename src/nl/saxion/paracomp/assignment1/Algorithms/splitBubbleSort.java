package nl.saxion.paracomp.assignment1.Algorithms;

import nl.saxion.paracomp.assignment1.Aopdrachten;
import nl.saxion.paracomp.assignment1.RunWrapper;
import nl.saxion.paracomp.assignment1.Utils;

public class splitBubbleSort extends Aopdrachten {

    public splitBubbleSort() {
        try {
            wrapper = new RunWrapper(this, splitBubbleSort.class.getMethod("splitBubbleSort", int[].class));
        } catch (NoSuchMethodException e) {
            System.out.println("Method not found");
            return;
        }
    }

    public void splitBubbleSort(int[] arr) {
        int[] left = new int[arr.length / 2];
        int[] right = new int[arr.length - left.length];

        System.arraycopy(arr, 0, left, 0, left.length);
        System.arraycopy(arr, left.length, right, 0, right.length);

        Utils utils = new Utils();
        utils.bubbleSort(left);
        utils.bubbleSort(right);

        Utils.merge(left, right);
    }

    @Override
    public void run() {
        runAutoMode();
        printResultsToFile();
    }
}
