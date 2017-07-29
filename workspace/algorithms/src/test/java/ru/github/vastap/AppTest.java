package ru.github.vastap;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

/**
 * Tests on algorightms implementations
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
	public void shouldDoSelectionSort(){
		int[] array = new int[]{5, 3, 4, 2, 6, 9, 7};
		App.selectionSort(array);
		assertEquals("[2, 3, 4, 5, 6, 7, 9]", Arrays.toString(array));
	}

	@Test
	public void shouldDoInsertionSort() {
		int[] array = new int[]{5, 4, 3, 2, 1};
		App.insertionSort(array);
		assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(array));
	}

	@Test
	public void shouldDoShuttleSort() {
		int[] array = new int[]{5, 4, 3, 2, 1};
		App.shuttleSort(array);
		assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(array));
	}

	@Test
	public void shouldDoShellSort(){
		int[] array = new int[]{5, 4, 3, 2, 1};
		App.shellSort(array);
		assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(array));
	}

	@Test
	public void shouldDoQuickSort() {
		int[] array = new int[]{5, 3, 4, 2, 6, 9, 7};
		assertEquals("[2, 3, 4, 5, 6, 7, 9]", Arrays.toString(App.quickSort(array, 0, array.length - 1)));

		array = new int[]{3, 7, 7, 7, 7, 7, 7};
		assertEquals("[3, 7, 7, 7, 7, 7, 7]", Arrays.toString(App.quickSort(array, 0, array.length - 1)));
	}

	@Test
	public void shouldDoMergeSort() {
		int[] array = new int[]{5, 3, 4, 2, 6, 9, 7, 10};
		assertEquals("[2, 3, 4, 5, 6, 7, 9, 10]", Arrays.toString(App.mergeSort(array,0,array.length-1)));
	}
}
