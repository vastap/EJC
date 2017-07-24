package ru.github.vastap;

/**
 * Shop for carrot buying
 */
public class CarrotShop {
	private static int countOfCarrots = 0;

	/**
	 * Increment carrot count
	 */
	public static void replenish(int carrotCount) {
		countOfCarrots = countOfCarrots + carrotCount;
	}

	/**
	 * Decrement carrot count
	 */
	public static boolean buy() {
		if (countOfCarrots > 0) {
			countOfCarrots = countOfCarrots - 1;
			System.out.println("Another carrot sold. Count after decrement: " + countOfCarrots);
			return true;
		} else {
			System.out.println("Not enought carrots =(");
			return false;
		}
	}

	/**
	 * Get available count of carrots
	 *
	 * @return
	 */
	public static int getCarrotAvailableCount() {
		return countOfCarrots;
	}
}
