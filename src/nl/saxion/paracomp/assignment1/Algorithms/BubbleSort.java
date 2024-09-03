package nl.saxion.paracomp.assignment1.Algorithms;

import java.util.*;

import nl.saxion.paracomp.assignment1.Aopdrachten;
import nl.saxion.paracomp.assignment1.RunWrapper;
import nl.saxion.paracomp.assignment1.Utils;

public class BubbleSort extends Aopdrachten {
    private Scanner sc;

    public BubbleSort() {
        try {
            wrapper = new RunWrapper(this, Utils.class.getMethod("bubbleSort", int[].class));
        } catch (NoSuchMethodException e) {
            System.out.println("Method not found");
        }
    }

    @Override
    public void run() {
        sc = new Scanner(System.in);
        System.out.println("Press A for auto run OR press B for manual mode");
        String choice = sc.next();
        if (choice.equals("A")) {
            System.out.println("Chose auto run");
            runAutoMode();
        } else if (choice.equals("B")) {
            System.out.println("Chose manual mode");
            System.out.println("Please enter the length of the list you want to sort this will get ran 10 times: ");
            Utils utils = new Utils();
            int length = utils.getLength();
            results.put(length, wrapper.simulateASortList(length));
        } else {
            System.out.println("You entered an invalid key");
            run();
        }
        sc.close();
        printResultsToFile();
    }
}
