package ru.github.vastap;

import org.junit.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Various Test cases for java language aspects
 */
public class AppTest {
	private static final String AVERAGE_MAP_KEY = "Stan";

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

	@Test
	public void shouldRemoveSuccessfullyTheSecondLastListElementWithIterator() {
		List<String> list = new ArrayList<>();
		Collections.addAll(list, "Murzik", "Barsik", "Pushok", "Boris");
		// Cursor - is a pointer to the next element
		// hasNext - return true if iterator cursor not equals to collection size
		// remove - delete element, decrement size, set cursor on last return position
		Iterator<String> it = list.iterator();
		System.err.println("cursor point on: " + 1);
		for (int i = 1; i <= 4; i++) {
			if (it.hasNext()) {
				String obj = it.next();
				System.err.println("cursor point on: " + (int) (i + 1));
				//remove element
				if (i == list.size() - 1) {
					System.err.println("delete element " + i);
					list.remove(obj);
					System.err.println("size = " + list.size()); //Size is decremeneted
					System.err.println("cursor point on: " + i);//Cursor is decremented
				}
			}
		}
	}

	@Test(expected = ConcurrentModificationException.class)
	public void shouldThrowExceptionOnTheSecondFromTheEndElementRemove() {
		List<String> list = new ArrayList<>();
		Collections.addAll(list, "Murzik", "Barsik", "Pushok", "Boris");
		Iterator<String> it = list.iterator();
		for (int i = 1; i <= 4; i++) {
			if (it.hasNext()) {
				String obj = it.next();
				if (i == list.size() - 2) {
					list.remove(obj);
				}
			}
		}
	}

	@Test
	public void shouldCatchStackOverflowError() {
		// Endless
		class Endless {
			public void cycle() {
				new Endless().cycle();
			}
		}
		try {
			new Endless().cycle();
		} catch (java.lang.StackOverflowError e) {
			System.err.println("Overflow!");
		}
	}

	@Test
	public void shouldCatchOutOfMemoryError() {
		try {
			double[] megaArray = new double[Integer.MAX_VALUE];
		} catch (java.lang.OutOfMemoryError e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void shouldSortItemsInPriorityQueue() {
		PriorityQueue queue = new PriorityQueue();
		queue.offer(10);
		queue.offer(1);
		queue.offer(5);
		assertEquals(1, queue.poll());

		queue = new PriorityQueue(Collections.reverseOrder());
		queue.offer(10);
		queue.offer(1);
		queue.offer(5);
		assertEquals(10, queue.poll());
	}

	@Test
	public void shouldGetAverageFromMapWithStream() {
		Map<String, Integer> unsortedMap = getUnsortedMap();
		List<Map.Entry> list = unsortedMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toList());
		Map.Entry averageEntry = list.get(list.size() / 2);
		assertEquals(AVERAGE_MAP_KEY, averageEntry.getKey());
	}

	@Test
	public void shouldGetAverageFromMapWithArray() {
		Map<String, Integer> unsortedMap = getUnsortedMap();
		Map.Entry<String, Integer>[] entries = new Map.Entry[unsortedMap.size()];
		unsortedMap.entrySet().toArray(entries);
		Arrays.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return Integer.compare(o1.getValue(), o2.getValue());
			}
		});
		assertEquals(AVERAGE_MAP_KEY, entries[entries.length / 2].getKey());
	}

	@Test
	public void shouldGetAverageFromMapWithSet() {
		Map<String, Integer> unsortedMap = getUnsortedMap();
		List<Map.Entry<String, Integer>> entries = new ArrayList(unsortedMap.entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return Integer.compare(o1.getValue(), o2.getValue());
			}
		});
		assertEquals(AVERAGE_MAP_KEY, entries.get(entries.size() / 2).getKey());
	}

	private static Map<String, Integer> getUnsortedMap() {
		Map<String, Integer> unsortedMap = new HashMap<>();
		unsortedMap.put("Max", 10);
		unsortedMap.put("John", 20);
		unsortedMap.put("Mike", 5);
		unsortedMap.put(AVERAGE_MAP_KEY, 15);
		unsortedMap.put("Dan", 30);
		return unsortedMap;
	}

}
