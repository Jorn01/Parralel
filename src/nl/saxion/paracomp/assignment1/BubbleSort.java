package nl.saxion.paracomp.assignment1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class BubbleSort implements Runnable {
    private Scanner sc;

    private final Utils utils = new Utils();

    private final Map<Integer, ArrayList<Duration>> results = new HashMap<>();

    private int getLength() {
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid number: ");
            sc.next();
        }
        int length = sc.nextInt();
        sc.close();
        return length;
    }
    private void simulateASortList(int length) {
        Instant start = Instant.now();
        results.put(length, new ArrayList<>());
        for (int i = 0; i < 10; i++) {
            results.get(length).add(utils.durationOf(() -> {
                int[] unsortedList = Utils.arrayOfRandomInts(length);
                Utils.bubbleSort(unsortedList);
            }));
        }
        System.out.println("Finished 10 runs of " + length + " after : " + Duration.between(start, Instant.now()));
    }
    private void runAutoMode(){
        simulateASortList(25000);
        simulateASortList(50000);
        simulateASortList(100000);
        simulateASortList(200000);
        simulateASortList(400000);
    }

    @Override
    public void run() {
        sc = new Scanner(System.in);
        System.out.println("Press A for auto run OR press B for manual mode");
        String choice = sc.next();
        if(choice.equals("A")){
            System.out.println("Chose auto run");
            runAutoMode();
        } else if(choice.equals("B")) {
            // Run manual mode
        } else {
            System.out.println("You entered an invalid key");
            run();
        }
        printResultsToFile();
    }

    public String formatDuration(Duration duration) {
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        long millis = duration.toMillisPart();

        return String.format("%02d:%02d.%03d", minutes, seconds, millis);
    }
    private void printResultsToFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode json = objectMapper.createObjectNode();
        for (Integer key : results.keySet()) {
            ArrayNode parsedResults = objectMapper.createArrayNode();

            results.get(key).stream()
                    .map(this::formatDuration)
                    .forEach(parsedResults::add);

            json.set(String.valueOf(key), parsedResults);
        }

        File file = new File("output.json");
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, json);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
