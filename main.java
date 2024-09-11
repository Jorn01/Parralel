import java.util.Scanner;

import nl.saxion.paracomp.assignment1.SortAlgorithmFactory;
import nl.saxion.paracomp.assignment1.sorters.BaseSorter;

public class main {
    public static void main(String[] args) {
        new main().program();
    }

    private void program() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the algorithm you want to run: ");
        System.out.println("""
                1. bubble
                2. treeSplitBubble
                3. RecursiveTask
                4. ForkJoin
                5. RecursiveTask""");
        String algorithm = sc.next();
        BaseSorter sorter = SortAlgorithmFactory.getSortAlgorithm(algorithm, sc);
        sorter.run();
    }
}
