package nl.saxion.paracomp.assignment1;

import nl.saxion.paracomp.assignment1.sorters.*;

import java.util.Scanner;

public class SortAlgorithmFactory {
    public static BaseSorter getSortAlgorithm(String algorithm, Scanner sc) {
        switch (algorithm) {
            case "bubble", "1":
                return new BubbleSort();
            case "splitBubble", "2":
                return new SplitBubbleSort();
            case "splitBubble2", "3":
                return new SplitBubbleSort2Threads();
            case "treeSplitBubble", "4": {
                System.out.println("Enter the threshold value");
                int threshold = sc.nextInt();
                return new RecursiveBubbleSort(threshold);
            }
            case "RecursiveTask", "5": {
                System.out.println("Enter the threshold value");
                int threshold = sc.nextInt();
                return new RecursiveTaskWrapper(threshold);
            }
            default:
                throw new IllegalArgumentException("Invalid algorithm");
        }
    }
}
