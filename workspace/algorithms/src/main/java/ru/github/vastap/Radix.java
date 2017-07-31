package ru.github.vastap;

public class Radix {

	/**
	 * A utility function to get maximum value in array[]
	 *
	 * @param array
	 * @return
	 */
	private static int getMax(int array[]) {
		int maxValue = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] > maxValue) {
				maxValue = array[i];
			}
		}
		return maxValue;
	}

	/**
	 * Do counting sort of array[] according to the digit represented by exp.
	 *
	 * @param array
	 * @param exp
	 */
	public static void countSort(int[] array, int exp) {
		int outputArray[] = new int[array.length];
		int count[] = new int[10];

		// Store count of occurrences in count[]
		for (int i = 0; i < array.length; i++) {
			count[(array[i] / exp) % 10]++;
		}

		// Change count[i] so that count[i] now contains
		// actual position of this digit in outputArray[]
		for (int i = 1; i < 10; i++) {
			count[i] += count[i - 1];
		}

		// Build the outputArray array
		for (int i = array.length - 1; i >= 0; i--) {
			outputArray[count[(array[i] / exp) % 10] - 1] = array[i];
			count[(array[i] / exp) % 10]--;
		}

		// Copy the outputArray array to array[], so that array[] now
		// contains sorted numbers according to curent digit
		for (int i = 0; i < array.length; i++) {
			array[i] = outputArray[i];
		}
	}

	/**
	 * Implementation of Radix Sort
	 *
	 * @param arr Array to sort
	 */
	public static void radixsort(int arr[]) {
		int maxNumber = getMax(arr);
		// Do counting sort for every digit. Note that instead
		// of passing digit number, exp is passed. exp is 10^i
		// where i is current digit number
		for (int exp = 1; maxNumber / exp > 0; exp *= 10) {
			countSort(arr, exp);
		}
	}

}