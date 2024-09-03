import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {
        try {
            // Sample unsorted array
            int[] unsortedList = { 5, 3, 8, 4, 2 };

            // Get the bubbleSort method
            Method method = Test.class.getMethod("bubbleSort", int[].class);

            // Invoke the method
            method.invoke(null, (Object) unsortedList);

            // Check if the array is sorted
            if (!isSorted(unsortedList)) {
                System.err.println("The list is not sorted");
            } else {
                System.out.println("The list is sorted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public static boolean isSorted(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
