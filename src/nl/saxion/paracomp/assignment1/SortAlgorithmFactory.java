package nl.saxion.paracomp.assignment1;

import java.util.Scanner;

public class SortAlgorithmFactory {
    public static BaseSorter getSortAlgorithm(String algorithm) {
        switch (algorithm) {
            case "bubble":
                return new BubbleSort();
            case "splitBubble":
                return new SplitBubbleSort();
            case "splitBubble2":
                return new SplitBubbleSort2Threads();
            case "treeSplitBubble": {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter the threshold value");
                int threshold = sc.nextInt();
                sc.close();
                return new ThreadPoolBubbleSort(threshold);
            }
            case "RecursiveTask": {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter the threshold value");
                int threads = sc.nextInt();
                sc.close();
                return new RecursiveTaskTreeSplit(threads);
            }
            default:
                throw new IllegalArgumentException("Invalid algorithm");
        }
    }
}
