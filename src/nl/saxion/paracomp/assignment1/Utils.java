package nl.saxion.paracomp.assignment1;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class Utils {

  /**
   * In-place bubble sort of an array with integers.
   *
   * @param numbers Integer array to sort
   */
  public static void bubbleSort(int[] numbers) {
    for (int i = 0; i < numbers.length; i++)
      for (int j = 1; j < numbers.length - i; j++) {
        if (numbers[j - 1] > numbers[j]) {
          int swap = numbers[j - 1];
          numbers[j - 1] = numbers[j];
          numbers[j] = swap;
        }
      }
  }

  /**
   * Checks if array is sorted
   *
   * @param numbers Integer array
   * @return true if array is sorted, otherwise false
   */
  public static boolean isSorted(int[] numbers) {
    for (int i = 0; i < numbers.length - 1; i++) {
      if (numbers[i] > numbers[i + 1])
        return false;
    }
    return true;
  }

  /**
   * Generate array with random integers.
   *
   * @param length Desired length of the array
   * @return An array with random integers
   */
  public static int[] arrayOfRandomInts(int length) {
    return new Random().ints(length).toArray();
  }

  /**
   * Merge two sorted integer arrays into one.
   *
   * @param numbers1 First sorted array with integers
   * @param numbers2 Second sorted array with integers
   * @return A new sorted array that holds the integers of the first and second array
   */
  public static int[] merge(int[] numbers1, int[] numbers2) {
    // run with assertions enabled to perform these checks
    assert isSorted(numbers1) : "numbers1 not sorted";
    assert isSorted(numbers2) : "numbers1 not sorted";
    int result[] = new int[numbers1.length + numbers2.length];

    int index1 = 0, index2 = 0, indexResult = 0;

    // copy the smallest number from first or second array, until one of them is exhausted
    while (index1 < numbers1.length && index2 < numbers2.length) {
      if (numbers1[index1] <= numbers2[index2])
        result[indexResult++] = numbers1[index1++];
      else
        result[indexResult++] = numbers2[index2++];
    }

    // copy remaining numbers from first array to result, if any
    for (int i = index1; i < numbers1.length; i++)
      result[indexResult++] = numbers1[i];

    // copy remaining numbers from second array to result, if any
    for (int i = index2; i < numbers2.length; i++)
      result[indexResult++] = numbers2[i];

    assert isSorted(result) : "result is not sorted";

    return result;
  }

  /**
   * Measure execution duration of some code.
   * The code can also be specified with a lambda expression.
   * @param code A runnable object
   * @return The execution duration
   */
  public Duration durationOf(Runnable code) {
    Instant start = Instant.now();
    code.run();
    return Duration.between(start, Instant.now());
  }

}

