package ru.github.vastap;

import java.util.Arrays;

/**
 * Algorithms implementations
 */
public class App {

	/**
	 * XOR Swap
	 *
	 * @param array Array to be processed
	 * @param from  First element for swap
	 * @param to    Second element to swap
	 */
	private static void xorSwap(int[] array, int from, int to) {
		array[from] = array[from] ^ array[to];
		array[to] = array[from] ^ array[to];
		array[from] = array[from] ^ array[to];
	}

	/**
	 * Bubble sort
	 *
	 * @param source Source Array to sort
	 */
	public static void bubbleSort(int[] source) {
		System.out.println("Bubble Sort: " + Arrays.toString(source));
		boolean isSorted;
		do {
			isSorted = true;
			for (int i = 0; i < source.length - 1; i++) {
				if (source[i] > source[i + 1]) {
					int tmp = source[i];
					source[i] = source[i + 1];
					source[i + 1] = tmp;
					isSorted = false;
					break;
				}
			}
		} while (!isSorted);
	}

	/**
	 * Bubble sort with XOR swap
	 *
	 * @param source Source Array to sort
	 * @return Sorted Array
	 */
	public static void bubbleSortWithXORSwap(int[] source) {
		System.out.println("Bubble Sort with XOR: " + Arrays.toString(source));
		boolean isSorted;
		do {
			isSorted = true;
			for (int i = 0; i < source.length - 1; i++) {
				if (source[i] > source[i + 1]) {
					isSorted = false;
					xorSwap(source, i, i + 1);
					break;
				}
			}
		} while (!isSorted);
	}

	/**
	 * Selection sort implementation
	 *
	 * @param source Source Array for sorting
	 */
	public static void selectionSort(int[] source) {
		System.out.println("Selection Sort: " + Arrays.toString(source));
		for (int i = 0; i < source.length; i++) {
			int minIndex = i;
			for (int min = i + 1; min < source.length; min++) {
				if (source[min] < source[minIndex]) {
					minIndex = min;
				}
			}
			if (i != minIndex) {
				xorSwap(source, i, minIndex);
			}
		}
	}

	/**
	 * Insertion Sort implementation1
	 *
	 * @param source Source Array
	 */
	public static void insertionSort(int[] source) {
		System.out.println("Insertion Sort: " + Arrays.toString(source));
		// Take out value by value, make a "hole" on this place
		for (int holePosition = 0; holePosition < source.length; holePosition++) {
			int takenValue = source[holePosition];
			//Move cursor while we have an element before hole and this element bigger than taken value
			int cursor;
			for (cursor = holePosition - 1; cursor >= 0 && takenValue < source[cursor]; cursor--) {
				source[cursor + 1] = source[cursor];
			}
			source[cursor + 1] = takenValue;
		}
	}

	/**
	 * Shuttle Sort implementation
	 *
	 * @param source Source Array for sorting
	 */
	public static void shuttleSort(int[] source) {
		System.out.println("Shuttle Sort: " + Arrays.toString(source));
		if (source.length <= 1) {
			return;
		}
		// Iterate over all
		for (int i = 1; i < source.length; i++) {
			for (int cursor = i - 1; cursor >= 0 && (source[cursor] > source[cursor + 1]); cursor--) {
				xorSwap(source, cursor, cursor + 1);
			}
		}
	}

	/**
	 * Shell sort implementation
	 *
	 * @param source
	 */
	public static void shellSort(int[] source) {
		System.out.println("Shell Sort: " + Arrays.toString(source));
		// Calculate the gap
		int gap = source.length / 2;

		while (gap >= 1) {
			for (int i = 0; i < source.length; i++) {
				// Move cursor not from gap value and iterate backward
				for (int cursor = i - gap; cursor >= 0 && (source[cursor] > source[cursor + gap]); cursor -= gap) {
					xorSwap(source, cursor, cursor + gap);
				}
			}
			// Decrease the gap value
			gap = gap / 2;
		}
	}

	/**
	 * Implementation of quick sort
	 *
	 * @param source      Source Array for sorting
	 * @param leftBorder  left border of sorting part of array
	 * @param rightBorder right border of sorting part of array
	 */
	public static void quickSort(int[] source, int leftBorder, int rightBorder) {
		System.out.println("Quick Sort: " + Arrays.toString(source));
		int leftMarker = leftBorder;
		int rightMarker = rightBorder;
		int pivot = source[(leftMarker + rightMarker) / 2];
		do {
			// Move left marker to the right until element less than pivot element
			while (source[leftMarker] < pivot) {
				leftMarker++;
			}
			// Move right marker to the left until element larger than pivot element
			while (source[rightMarker] > pivot) {
				rightMarker--;
			}
			// If <= then we done all swaps and reach last step
			if (leftMarker <= rightMarker) {
				// Left marker will be less than right only if we have to swap elements
				if (leftMarker < rightMarker) {
					int tmp = source[leftMarker];
					source[leftMarker] = source[rightMarker];
					source[rightMarker] = tmp;
				}
				// Move markers on one position to form new bounds
				leftMarker++;
				rightMarker--;
			}
		} while (leftMarker <= rightMarker);
		// If left marker less than end border
		if (leftMarker < rightBorder) {
			quickSort(source, leftMarker, rightBorder);
		}
		// If right marker bigger than start border
		if (leftBorder < rightMarker) {
			quickSort(source, leftBorder, rightMarker);
		}
	}

	/**
	 * Implementation of Merge Sort
	 *
	 * @param source Source Array to sort
	 * @param left   Start from this element
	 * @param right  Finish at this element
	 * @return Sorted Array
	 */
	public static void mergeSort(int[] source, int left, int right) {
		int delimiter = left + ((right - left) / 2) + 1;
		// Recursive merge for parts bigger than 2 elements
		if (delimiter > 0 && right > left + 1) {
			mergeSort(source, left, delimiter - 1);
			mergeSort(source, delimiter, right);
		}

		// Compare and merge
		int[] buffer = new int[right - left + 1];
		int cursor = left;
		for (int i = 0; i < buffer.length; i++) {
			if (delimiter > right || source[cursor] < source[delimiter]) {
				buffer[i] = source[cursor];
				cursor++;
			} else {
				buffer[i] = source[delimiter];
				delimiter++;
			}
		}
		System.arraycopy(buffer, 0, source, left, buffer.length);
	}

	/**
	 * Implementation of counting sort
	 *
	 * @param theArray
	 * @return
	 */
	public static int[] countingSort(int theArray[]) {
		// for each value in the source array increment count in indexes array
		int indexes[] = new int[9 + 1];
		for (int i = 0; i < theArray.length; i++) {
			indexes[theArray[i]]++;
		}
		// populate the final sorted array
		int[] sortedArray = new int[theArray.length];
		int currentSortedIndex = 0;
		// for each index in indexes
		for (int index = 0; index < indexes.length; index++) {
			// for the number of times the item occurs
			int count = indexes[index];
			for (int i = 0; i < count; i++) {
				sortedArray[currentSortedIndex] = index;
				currentSortedIndex++;
			}
		}
		return sortedArray;
	}

	/**
	 * Binary search implementation
	 * <p>Like java.util.Arrays.binarySearch and Collections.binarySearch
	 *
	 * @param array Sorted array
	 * @return index of element
	 */
	public static int getIndexWithBinarySearch(int[] array, int value) {
		int left = 0;
		int right = array.length;

		while (right >= left) {
			int middle = (left + right) / 2;
			if (array[middle] == value) {
				return middle;
			}
			if (array[middle] < value) {
				left = middle + 1;
			}
			if (array[middle] > value) {
				right = middle - 1;
			}
		}
		return -1;
	}

}