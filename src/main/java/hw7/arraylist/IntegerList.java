package hw7.arraylist;

import hw7.exceptions.EmptyArgumentException;
import hw7.exceptions.InvalidCellNumberException;
import hw7.exceptions.NonExistentElementException;

import java.util.Arrays;

public class IntegerList implements IntegerListInterface {
    private Integer[] array;
    private int capacity;
    private int size = 0;

    public IntegerList(int capacity) {
        this.capacity = capacity;
        array = new Integer[capacity];
    }

    @Override
    public Integer add(Integer item) {
        return add(size, item);
    }

    private void grow() {
        capacity += (capacity / 2 + 1);
        Integer[] temp = new Integer[capacity];
        System.arraycopy(array, 0, temp, 0, size);
        array = temp;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        if (capacity <= size) {
            grow();
        }
        int i;
        for (i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[i] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        array[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                index = i;
            }
        }
        if (index == -1) {
            throw new NonExistentElementException("Нет такого элемента");
        } else return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer temp = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return temp;
    }

    @Override
    public boolean contains(Integer item) {
        quickSort(array, 0, array.length - 1);
        return binarySearch(item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i > -1; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return array[index];
    }

    @Override
    public boolean equals(IntegerListInterface otherList) {
        if (otherList == null) {
            throw new EmptyArgumentException("Пустой список");
        }
        if (this.size != otherList.size()) return false;
        for (int i = 0; i < size; i++) {
            if (!(array[i].equals(otherList.get(i)))) return false;
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(array, size);
    }

    private boolean binarySearch(int element) {
        int min = 0;
        int max = size - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (element == array[mid]) {
                return true;
            }
            if (element < array[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    public static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private void validateIndex(int index) {
        if (index > size || index < 0) {
            throw new InvalidCellNumberException("Такой ячейки нет в массиве");
        }
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new EmptyArgumentException("Введена пустая строка");
        }
    }
}