package nl.saxion.paracomp.assignment1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RunWrapper {
    private final Utils utils;
    private final ArrayList<Duration> results;
    private final Method sortFunction;
    private final Object instance;

    /**
     * Simulate a sort function on a list of random integers
     * 
     * @param sortFunction The sort function to run
     */
    public RunWrapper(Object instance, Method sortFunction) {
        this.sortFunction = sortFunction;
        this.results = new ArrayList<>();
        this.utils = new Utils();
        this.instance = instance;
    }

    /**
     * Simulate a sort function on a list of random integers
     * 
     * @param length
     * @return A map of the results
     * 
     */
    public ArrayList<Duration> simulateASortList(int length) {
        Instant start = Instant.now();
        for (int i = 0; i < 10; i++) {
            results.add(utils.durationOf(() -> {
                int[] unsortedList = Utils.arrayOfRandomInts(length);
                try {
                    sortFunction.invoke(instance, (Object) unsortedList);
                    System.out.println(Arrays.toString(unsortedList));
                    if (!Utils.isSorted(unsortedList)) {
                        System.err.println("The list is not sorted");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("An error occurred while running the sort function");
                }
            }));
        }
        System.out.println("Finished 10 runs of " + length + " after : " + Duration.between(start, Instant.now()));
        return results;
    }

}
