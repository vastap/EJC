package ru.github.vastap;

/**
 * Algorithms implementations
 */
public class App {

	/**
	 * Bubble sort
	 *
	 * @param source Source Array to sort
	 * @return Sorted Array
	 */
	public static int[] bubbleSort(int[] source) {
		int tmp = 0;
		boolean sorted;
		do {
			sorted = true;
			for (int i = 0; i < source.length - 1; i++) {
				if (source[i] > source[i + 1]) {
					tmp = source[i];
					source[i] = source[i + 1];
					source[i + 1] = tmp;
					sorted = false;
					break;
				}
			}
		} while (!sorted);
		return source;
	}

	/**
	 * Bubble sort with XOR swap
	 *
	 * @param source Source Array to sort
	 * @return Sorted Array
	 */
	public static int[] bubbleSortWithXORSwap(int[] source) {
		boolean sorted;
		do {
			sorted = true;
			for (int i = 0; i < source.length - 1; i++) {
				if (source[i] > source[i + 1]) {
					sorted = false;
					//XOR Swap
					source[i] = source[i] ^ source[i + 1];
					source[i + 1] = source[i] ^ source[i + 1];
					source[i] = source[i] ^ source[i + 1];
					break;
				}
			}
		} while (!sorted);
		return source;
	}

	/**
	 * Insertion Sort of array
	 *
	 * @param source Source array
	 * @return sorted array
	 */
	public static int[] insertionSort(int[] source) {
		int number = 0;
		for (int i = 1; i < source.length; i++) {
			for (int z = i - 1; z >= 0; z--) {
				if (source[i] < source[z]) {
					if (z != 0 && (source[i] < source[z - 1])) continue;
					number = source[i];
					System.arraycopy(source, z, source, z + 1, i - z);
					source[z] = number;
				}
			}
		}
		return source;
	}

	/**
	 * Insertion Sort of array.
	 * <p>This is short variant from https://edunow.su/site/content/algorithms/sortirovka_massiva</p>
	 *
	 * @param source Source array
	 * @return sorted array
	 */
	public static int[] insertionSortShort(int[] source) {
		int value;
		int head;
		int tail;

		for (head = 1; head < source.length; head++) {
			value = source[head];
			for (tail = head - 1; tail >= 0 && source[tail] > value; tail--) {
				source[tail + 1] = source[tail];
			}
			source[tail + 1] = value;
		}
		return source;
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

}
