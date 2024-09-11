package nl.saxion.paracomp.assignment1;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.Scanner;

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
  public static boolean isSorted(int[] array) {
    for (int i = 0; i < array.length - 1; i++) {
      if (array[i] > array[i + 1]) {
        System.out.println(array[i] + " > " + array[i + 1] + " at index " + i);
        return false;
      }
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
   * @return A new sorted array that holds the integers of the first and second
   *         array
   */
  public static int[] merge(int[] arr1, int[] arr2) {
    int n1 = arr1.length;
    int n2 = arr2.length;
    int[] mergedArray = new int[n1 + n2];

    int i = 0, j = 0, k = 0;

    // Traverse both arrays and insert the smaller element into the merged array
    while (i < n1 && j < n2) {
      if (arr1[i] <= arr2[j]) {
        mergedArray[k] = arr1[i];
        i++;
      } else {
        mergedArray[k] = arr2[j];
        j++;
      }
      k++;
    }

    // Copy remaining elements of arr1, if any
    while (i < n1) {
      mergedArray[k] = arr1[i];
      i++;
      k++;
    }

    // Copy remaining elements of arr2, if any
    while (j < n2) {
      mergedArray[k] = arr2[j];
      j++;
      k++;
    }

    return mergedArray;
  }

  /**
   * Measure execution duration of some code.
   * The code can also be specified with a lambda expression.
   * 
   * @param code A runnable object
   * @return The execution duration
   */
  public Duration durationOf(Runnable code) {
    Instant start = Instant.now();
    code.run();
    return Duration.between(start, Instant.now());
  }

  public int getLength(Scanner sc) {
    while (!sc.hasNextInt()) {
      System.out.println("Please enter a valid number: ");
      sc.next();
    }
    return sc.nextInt();
  }

}
