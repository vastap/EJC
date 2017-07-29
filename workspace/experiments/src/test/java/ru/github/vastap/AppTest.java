package ru.github.vastap;

import org.junit.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Various Test cases for java language aspects
 */
public class AppTest {

	/**
	 * <p>Arrays are not immutable and are objects.
	 * So, array can be modified if someone has access to it.
	 * But we always can give a copy of array.
	 * <p>WARNING: Is valid ONLY for primitives and immutable primitive wrapper classes!
	 */
	@Test
	public void shouldGiveSafeCopyOfArray() {
		int[] array = new int[]{1, 2, 3};
		int[] arrayClone = array.clone();
		int[] arrayCopy = Arrays.copyOf(array, array.length);

		arrayClone[0] = arrayClone[0] + 1;
		arrayCopy[0] = arrayCopy[0] + 1;
		assertTrue(array[0] == 1);
	}

	/**
	 * Atomic types have ability to change their value.
	 * We have "shallow" copy when copy or clone arrays.
	 */
	@Test
	public void shouldGiveAlterableCopies() {
		AtomicInteger[] array = new AtomicInteger[1];
		array[0] = new AtomicInteger(1);
		assertEquals(1, array[0].get());

		AtomicInteger[] clone = array.clone();
		clone[0].set(2);
		assertEquals(2, array[0].get());

		AtomicInteger[] copy = Arrays.copyOf(array, array.length);
		copy[0].set(3);
		assertEquals(3, array[0].get());
	}

	/**
	 * <p>We should use unmodifiableList to provide view for our ArrayList.
	 * Everyone can read, but they cant add/delete/replace elements.
	 * WARNING: Everyone still can modify elements by calling methods of these elements.
	 * <p>"View" is not a copy, its a wrapper
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void shouldPreventArrayListModification() {
		List<Integer> sourceList = new ArrayList<>();
		sourceList.add(5);
		// Not a copy! All read methods will be delegated to the list.
		// All write methods will throw exception
		List<Integer> thirdPartyCopy = Collections.unmodifiableList(sourceList);
		assertTrue(thirdPartyCopy.get(0) == 5);
		thirdPartyCopy.set(0, 6);
	}

	/**
	 * ArrayList increase capacity in one and a half times
	 * newCapacity = oldCapacity + (oldCapacity >> 1)
	 * >>1 = split in half, <<1 = multiply by two
	 */
	@Test
	public void shouldIncreaseCapacityInOneAndAHalfTimes() {
		int capacity = 4;
		ArrayList<Integer> list = new ArrayList<>(capacity);
		for (int i = 0; i < capacity + 1; i++) {
			list.add(i);
		}
		assertEquals(Math.round(capacity * 1.5), getArrayListSizeByReflection(list));
	}

	/**
	 * Get ArrayList size as size of underlying array
	 *
	 * @param list ArrayList for checking size
	 * @return Size of array
	 */
	private int getArrayListSizeByReflection(ArrayList list) {
		Field field;
		try {
			field = list.getClass().getDeclaredField("elementData");
			field.setAccessible(true);
			if (field.getType().isArray()) {
				return Array.getLength(field.get(list));
			}
		} catch (NoSuchFieldException e) {
			throw new IllegalStateException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
		throw new IllegalStateException("Can't read field elementData for this ArrayList object");
	}

}
