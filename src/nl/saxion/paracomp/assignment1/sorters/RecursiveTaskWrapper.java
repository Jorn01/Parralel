package nl.saxion.paracomp.assignment1.sorters;

import java.util.concurrent.ForkJoinPool;

public class RecursiveTaskWrapper extends BaseSorter {

    private int DrempelWaarde;

    public RecursiveTaskWrapper(int DrempelWaarde) {
        super("RecursiveTask");
        this.DrempelWaarde = DrempelWaarde;
    }

    @Override
    public int[] sort(int[] numbers) {
        ForkJoinPool pool = new ForkJoinPool();

        RecursiveTaskTreeSplit task = new RecursiveTaskTreeSplit(numbers, DrempelWaarde);

        return pool.invoke(task);

    }

}
