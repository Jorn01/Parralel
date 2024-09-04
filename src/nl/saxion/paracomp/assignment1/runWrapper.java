package nl.saxion.paracomp.assignment1;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

public class runWrapper {
    public static ArrayList<Duration> sort(int length, BaseSorter sorter) {
        ArrayList<Duration> durations = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            durations.add(new Utils().durationOf(() -> {
                int[] numbers = Utils.arrayOfRandomInts(length);
                numbers = sorter.sort(numbers);
                System.out.println(Utils.isSorted(numbers));
            }));
        }
        return durations;
    }
}
