package nl.saxion.paracomp.assignment1.sorters;

import java.util.Scanner;

import nl.saxion.paracomp.assignment1.Utils;

public class BubbleSort extends BaseSorter {
    public BubbleSort() {
        super("bubble");
    }

    @Override
    public int[] sort(int[] numbers) {
        Utils.bubbleSort(numbers);
        return numbers;
    }
}
