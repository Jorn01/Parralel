import nl.saxion.paracomp.assignment1.sorters.*;

public class TestAlles {
    public static void main(String[] args) {
        new TestAlles().run();
    }

    private void run() {
        BaseSorter sorter;
        sorter = new BubbleSort();
        sorter.runAutoMode();
        sorter.printResultsToFile(true);

        sorter = new SplitBubbleSort();
        sorter.runAutoMode();
        sorter.printResultsToFile(true);

        sorter = new SplitBubbleSort2Threads();
        sorter.runAutoMode();
        sorter.printResultsToFile(true);

        sorter = new RecursiveBubbleSort(2400);
        sorter.runAutoMode();
        sorter.printResultsToFile(true);

        sorter = new RecursiveTaskWrapper(2400);
        sorter.runAutoMode();
        sorter.printResultsToFile(true);
    }
}
