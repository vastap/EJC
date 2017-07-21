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
		assertEquals("[2, 3, 4, 5, 6, 7, 9]", Arrays.toString(App.bubbleSort(array)));
		assertEquals("[2, 3, 4, 5, 6, 7, 9]", Arrays.toString(App.bubbleSortWithXORSwap(array)));
	}

	@Test
	public void shouldDoInsertionSort() {
		int[] array = new int[]{5, 3, 4, 2, 6, 9, 7};
		assertEquals("[2, 3, 4, 5, 6, 7, 9]", Arrays.toString(App.insertionSort(array)));
		assertEquals("[2, 3, 4, 5, 6, 7, 9]", Arrays.toString(App.insertionSortShort(array)));
	}

	@Test
	public void shouldDoQuickSort() {
		int[] array = new int[]{5, 3, 4, 2, 6, 9, 7};
		assertEquals("[2, 3, 4, 5, 6, 7, 9]", Arrays.toString(App.quickSort(array, 0, array.length - 1)));

		array = new int[]{3, 7, 7, 7, 7, 7, 7};
		assertEquals("[3, 7, 7, 7, 7, 7, 7]", Arrays.toString(App.quickSort(array, 0, array.length - 1)));
	}
}
