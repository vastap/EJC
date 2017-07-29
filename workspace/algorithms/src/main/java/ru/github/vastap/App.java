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
		System.out.println("swap " + array[from] + " and " + array[to]);
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

	public static int[] quickSort(int[] source, int first, int last) {
		int leftMarker = first;
		int rightMarker = last;
		int pivot = source[(first + last) / 2];
		int tmp;
		// While left marker less than right marker (or equals)
		do {
			// Move left marker to the right until element less than pivot element
			while (source[leftMarker] < pivot) {
				leftMarker++;
			}
			// Move right marker to the left until element larger than pivot element
			while (source[rightMarker] > pivot) {
				rightMarker--;
			}
			// When left marker is stand before right marker (or in the same position)
			if (leftMarker <= rightMarker) {
				// If left marker stand before right marker - swap elements
				if (leftMarker < rightMarker) {
					tmp = source[leftMarker];
					source[leftMarker] = source[rightMarker];
					source[rightMarker] = tmp;
				}
				// Move markers on one position
				leftMarker++;
				rightMarker--;
			}
		} while (leftMarker <= rightMarker);
		// If left marker less than end border
		if (leftMarker < last) {
			// Sort again. Part, that begins from leftMarker to the end border
			quickSort(source, leftMarker, last);
		}
		// If right marker bigger than start border
		if (first < rightMarker) {
			// Sort again. Part, that begins from start border to the right marker
			quickSort(source, first, rightMarker);
		}
		return source;
	}

	/**
	 * Implementation of Merge Sort
	 *
	 * @param source Source Array to sort
	 * @param left   Start from this element
	 * @param right  Finish at this element
	 * @return Sorted Array
	 */
	public static int[] mergeSort(int[] source, int left, int right) {
		if (left == right) return source;

		System.out.print("> ");
		for (int i = left; i <= right; i++) {
			System.out.print(source[i]);
			System.out.print("|");
		}
		System.out.println();

		//Calculate delimiter - the beginning of a new part
		int delimiter = left + ((right - left) / 2 + (right - left) % 2);
		if (delimiter != right) {
			mergeSort(source, left, delimiter - 1);
			mergeSort(source, delimiter, right);
		}

		System.out.println("Delim: " + delimiter);
		while (left < right) {
			if (delimiter == source.length) {
				break;
			}
			System.out.println(source[left] + "~" + source[delimiter]);
			if (source[left] > source[delimiter]) {
				int tmp = source[left];
				xorSwap(source, left, delimiter);
				left++;
			} else {
				left++;
			}
		}

//
// for (int i = 0; i < (delimiter - left); i++) {
//			if (delimiter == source.length) {
//				break;
//			}
//			//Swap elements
//			if (source[left] > source[delimiter]) {
//				xorSwap(source, left, delimiter);
//
//				left++;
//			} else {
//				delimiter++;
//			}
//		}
//
		System.out.println("After merge");
		for (int i = 0; i < source.length; i++) {
			System.out.print(source[i]);
			System.out.print("|");
		}
		System.out.println();

		return source;
	}
}
