import java.util.Scanner;

import nl.saxion.paracomp.assignment1.BaseSorter;
import nl.saxion.paracomp.assignment1.SortAlgorithmFactory;

public class main {
    public static void main(String[] args) {
        new main().program();
    }

    private void program() {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Select the sorting algorithm you want to use: bubble, splitBubble, splitBubble2, treeSplitBubble");
        String algorithm = sc.next();
        sc.close();
        BaseSorter sorter = SortAlgorithmFactory.getSortAlgorithm(algorithm);
        sorter.run();
    }
}
