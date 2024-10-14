import nl.saxion.paracomp.assignment1.runWrapper;
import nl.saxion.paracomp.assignment1.sorters.BaseSorter;
import nl.saxion.paracomp.assignment1.sorters.RecursiveBubbleSort;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        new Test().run();
    }

    private void run(){
        HashMap<Integer, ArrayList<Duration>> results = new HashMap<>();
        BaseSorter sorter;

        for (int i = 100; i < 1000; i = i + 50) {
            sorter = new RecursiveBubbleSort(i) {
                @Override
                public void runAutoMode() {
                    results.put(400000, runWrapper.sort(400000, this, false));
                }
            };
            sorter.runAutoMode();
            results.put(i, sorter.getResults().get(400000));
            System.out.println("Finished sorting with threshold " + i);
        }


        try (FileWriter csvWriter = new FileWriter("Threshold.csv")) {
            // Write the CSV header
            csvWriter.append("Threshold,Average Duration (ms)\n");

            // Iterate through the HashMap
            for (Integer key : results.keySet()) {
                csvWriter.append(key.toString());
                csvWriter.append(",");

                // Get the list of durations and calculate the average in milliseconds
                ArrayList<Duration> durations = results.get(key);
                if(durations != null) {
                    csvWriter.append(Long.toString(durations.stream().map(Duration::toMillis).reduce(0L, Long::sum) / durations.size()));
                }
                csvWriter.append("\n");  // Newline after each row
            }

            // Flush and close the writer
            csvWriter.flush();
            System.out.println("CSV file was created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
