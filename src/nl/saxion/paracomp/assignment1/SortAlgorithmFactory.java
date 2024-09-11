package nl.saxion.paracomp.assignment1;

import java.util.Scanner;

import nl.saxion.paracomp.assignment1.sorters.BaseSorter;
import nl.saxion.paracomp.assignment1.sorters.BubbleSort;
import nl.saxion.paracomp.assignment1.sorters.RecursiveTaskWrapper;
import nl.saxion.paracomp.assignment1.sorters.SplitBubbleSort;
import nl.saxion.paracomp.assignment1.sorters.SplitBubbleSort2Threads;
import nl.saxion.paracomp.assignment1.sorters.ThreadPoolBubbleSort;

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
                return new ThreadPoolBubbleSort(threshold);
            }
            case "RecursiveTask", "5": {
                System.out.println("Enter the threshold value");
                int threads = sc.nextInt();
                return new RecursiveTaskWrapper(threads);
            }
            default:
                throw new IllegalArgumentException("Invalid algorithm");
        }
    }
}
