import nl.saxion.paracomp.assignment1.SortAlgorithmFactory;
import nl.saxion.paracomp.assignment1.sorters.BaseSorter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main().program();
    }

    private void program() {
        //TODO append the results to the files instead of overriding it.
        Scanner sc = new Scanner(System.in);
        String algorithm = "";
        while(!algorithm.equalsIgnoreCase("Q") && !algorithm.equalsIgnoreCase("Exit")) {
            System.out.println("Enter the algorithm you want to run: ");
            System.out.println("""
                    1. bubble
                    2. treeSplitBubble
                    3. RecursiveTask
                    4. ForkJoin
                    5. RecursiveTask
                    Q. Exit the program""");
            algorithm = sc.next();
            if(algorithm.equalsIgnoreCase("Q") || algorithm.equalsIgnoreCase("Exit")){
                System.out.println("Exiting the program");
                break;
            }
            BaseSorter sorter = SortAlgorithmFactory.getSortAlgorithm(algorithm, sc);
            sorter.run();
            System.out.println("Note: Running this multiple times doesnt append the results at this moment in time");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        sc.close();
    }
}
