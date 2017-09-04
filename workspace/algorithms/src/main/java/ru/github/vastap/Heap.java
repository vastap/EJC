package ru.github.vastap;

import java.util.Arrays;

/**
 * Heap Structure representation
 */
public class Heap {
	private final int[] array;

	public Heap(int[] source) {
		this.array = source;
		for (int i = (this.array.length - 1) / 2; i >= 0; i--) {
			heapify(i, array.length - 1);
		}
	}

	/**
	 * Perform Heapify of inner storage
	 *
	 * @param index from index
	 * @param size  size of the heap
	 */
	private void heapify(int index, int size) {
		int leftIndex = 2 * index + 1;
		int rightIndex = 2 * index + 2;
		int heapSize = size;

		int largestIndex = index;
		if (leftIndex <= heapSize && array[leftIndex] > array[largestIndex]) {
			largestIndex = leftIndex;
		}
		if (rightIndex <= heapSize && array[rightIndex] > array[largestIndex]) {
			largestIndex = rightIndex;
		}
		if (largestIndex != index) {
			swap(index, largestIndex);
			heapify(largestIndex, heapSize);
		}
	}

	/**
	 * Perform heap sort of inner storage
	 */
	public void sort() {
		for (int i = this.array.length - 1; i > 0; i--) {
			swap(0, i);
			heapify(0, i - 1);
		}
	}

	/**
	 * Swap elements
	 *
	 * @param left  Left element for swap
	 * @param right Right element for swap
	 */
	private void swap(int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}

	@Override
	public String toString() {
		return Arrays.toString(array);
	}

}