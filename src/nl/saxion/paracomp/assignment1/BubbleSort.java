package nl.saxion.paracomp.assignment1;

import java.util.Scanner;

public class BubbleSort extends BaseSorter {
    @Override
    public int[] sort(int[] numbers) {
        Utils.bubbleSort(numbers);
        return numbers;
    }
}
