package ru.github.vastap;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Tests on algorithms implementations
 */
public class AppTest {

	@Test
	public void shouldDoBubbleSort() {
		int[] array = new int[]{5, 3, 4, 2, 6, 9, 7};
		App.bubbleSort(array);
		assertEquals("[2, 3, 4, 5, 6, 7, 9]", Arrays.toString(array));

		array = new int[]{5, 3, 4, 2, 6, 9, 7};
		App.bubbleSortWithXORSwap(array);
		assertEquals("[2, 3, 4, 5, 6, 7, 9]", Arrays.toString(array));
	}

	@Test
	public void shouldDoSelectionSort() {
		int[] array = new int[]{5, 3, 4, 2, 6, 9, 7};
		App.selectionSort(array);
		assertEquals("[2, 3, 4, 5, 6, 7, 9]", Arrays.toString(array));
	}

	@Test
	public void shouldDoInsertionSort() {
		int[] array = new int[]{5, 4, 3, 2, 1};
		App.insertionSort(array);
		assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(array));

		array = new int[]{1, 5, 4, 2};
		App.insertionSort(array);
		assertEquals("[1, 2, 4, 5]", Arrays.toString(array));
	}

	@Test
	public void shouldDoShuttleSort() {
		int[] array = new int[]{5, 4, 3, 2, 1};
		App.shuttleSort(array);
		assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(array));
	}

	@Test
	public void shouldDoShellSort() {
		int[] array = new int[]{5, 4, 3, 2, 1};
		App.shellSort(array);
		assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(array));
	}

	@Test
	public void shouldDoQuickSort() {
		int[] array = new int[]{4, 2, 6, 1, 3};
		App.quickSort(array, 0, array.length - 1);
		assertEquals("[1, 2, 3, 4, 6]", Arrays.toString(array));

		array = new int[]{5, 3, 4, 2, 6, 9, 7};
		App.quickSort(array, 0, array.length - 1);
		assertEquals("[2, 3, 4, 5, 6, 7, 9]", Arrays.toString(array));

		array = new int[]{6, 2, 6, 1, 6};
		App.quickSort(array, 0, array.length - 1);
		assertEquals("[1, 2, 6, 6, 6]", Arrays.toString(array));
	}

	@Test
	public void shouldDoMergeSort() {
		int[] array = new int[]{8, 4, 6, 2, 1, 7, 5, 3};
		App.mergeSort(array, 0, array.length);
		assertEquals("[1, 2, 3, 4, 5, 6, 7, 8]", Arrays.toString(array));

		array = new int[]{1, 5, 4, 2, 6, 8};
		App.mergeSort(array, 0, array.length);
		assertEquals("[1, 2, 4, 5, 6, 8]", Arrays.toString(array));

		array = new int[]{1, 3, 2};
		App.mergeSort(array, 0, array.length);
		assertEquals("[1, 2, 3]", Arrays.toString(array));

		array = new int[]{3, 2, 3};
		App.mergeSort(array, 0, array.length);
		assertEquals("[2, 3, 3]", Arrays.toString(array));

		array = new int[]{1, 3, 3};
		App.mergeSort(array, 0, array.length);
		assertEquals("[1, 3, 3]", Arrays.toString(array));
	}

	@Test
	public void shouldDoCountingSort() {
		int[] array = new int[]{8, 4, 2, 4, 3, 2, 2, 3};
		assertEquals("[2, 2, 2, 3, 3, 4, 4, 8]", Arrays.toString(App.countingSort(array)));
	}

	@Test
	public void shouldDoRadixSort() {
		int[] array = new int[]{10, 12, 2, 1, 33, 5};
		Radix.radixsort(array);
		assertEquals("[1, 2, 5, 10, 12, 33]", Arrays.toString(array));
	}

	@Test
	public void shouldDoBinarySearch() {
		int[] array = new int[]{5, 19, 22, 2, 10, 0};
		Radix.radixsort(array);
		assertEquals("[0, 2, 5, 10, 19, 22]", Arrays.toString(array));
		assertEquals(3, App.getIndexWithBinarySearch(array, 10));
		assertEquals(-1, App.getIndexWithBinarySearch(array, 3));
	}

	@Test
	public void shouldDoHeapSort() {
		Heap heap = new Heap(new int[]{2, 4, 6, 8, 3, 1});
		heap.sort();
		assertEquals("[1, 2, 3, 4, 6, 8]", heap.toString());
	}

}