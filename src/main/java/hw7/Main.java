package hw7;

import java.util.Arrays;
import java.util.Random;

/* Домашнее задание №8  - Алгоритмы (Integer)*/

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Integer[] integers = new Integer[100_000];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = random.nextInt();
        }
        Integer[] integers1 = Arrays.copyOf(integers, integers.length);
        Integer[] integers2 = Arrays.copyOf(integers, integers.length);

        long start = System.currentTimeMillis();
        sortBubble(integers);
        System.out.println(System.currentTimeMillis() - start);

        long start1 = System.currentTimeMillis();
        sortSelection(integers1);
        System.out.println(System.currentTimeMillis() - start1);

        long start2 = System.currentTimeMillis();
        sortInsertion(integers2);
        System.out.println(System.currentTimeMillis() - start2);

    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }


}
