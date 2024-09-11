package nl.saxion.paracomp.assignment1.sorters;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import nl.saxion.paracomp.assignment1.Utils;
import nl.saxion.paracomp.assignment1.runWrapper;

public abstract class BaseSorter implements Runnable {
    protected final HashMap<Integer, ArrayList<Duration>> results = new HashMap<>();
    private final String fileName;

    public abstract int[] sort(int[] numbers);

    public BaseSorter(String fileName) {
        this.fileName = fileName;
    }

    protected void runAutoMode() {
        results.put(25000, runWrapper.sort(25000, this));
        results.put(50000, runWrapper.sort(50000, this));
        // results.put(100000, runWrapper.sort(100000, this));
        // results.put(200000, runWrapper.sort(200000, this));
        // results.put(400000, runWrapper.sort(400000, this));
    }

    private String formatDuration(Duration duration) {
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        long millis = duration.toMillisPart();

        return String.format("%02d:%02d.%03d", minutes, seconds, millis);
    }

    protected void printResultsToFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode json = objectMapper.createObjectNode();
        for (Integer key : results.keySet()) {
            ArrayNode parsedResults = objectMapper.createArrayNode();

            results.get(key).stream().map(this::formatDuration).forEach(parsedResults::add);

            json.set(String.valueOf(key), parsedResults);
        }

        File file = new File(fileName + ".json");
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, json);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press A for auto run OR press B for manual mode");
        String choice = sc.next();
        if (choice.equals("A")) {
            System.out.println("Chose auto run");
            runAutoMode();
        } else if (choice.equals("B")) {
            System.out.println("Chose manual mode");
            System.out.println("Please enter the length of the list you want to sort this will get ran 10 times: ");
            int length = new Utils().getLength();
            results.put(length, runWrapper.sort(length, this));
        } else {
            System.out.println("You entered an invalid key");
            run();
        }
        sc.close();
        printResultsToFile();
    }
}
