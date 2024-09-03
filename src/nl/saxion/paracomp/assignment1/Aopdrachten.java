package nl.saxion.paracomp.assignment1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Aopdrachten Abstract class that contains the run method that will be
 * implemented by the algorithms this will handle the running of the algorithms
 * and the output of the results
 */
public abstract class Aopdrachten implements Runnable {

    protected RunWrapper wrapper;

    protected Map<Integer, ArrayList<Duration>> results = new HashMap<>();

    public abstract void run();

    protected void runAutoMode() {
        results.put(25000, wrapper.simulateASortList(25000));
        results.put(50000, wrapper.simulateASortList(50000));
        results.put(100000, wrapper.simulateASortList(100000));
        results.put(200000, wrapper.simulateASortList(200000));
        results.put(400000, wrapper.simulateASortList(400000));
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

        File file = new File("output.json");
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, json);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
