package ru.github.vastap;

import java.util.Deque;

/**
 * Class for recursion exercises.
 */
public class Recursion {

	/**
	 * Return all natural numbers from 1 to before specified number
	 *
	 * @param number        The number to which we want to count
	 * @param stringBuilder The result container
	 */
	public static void getAllnaturalBefore(int number, StringBuilder stringBuilder) {
		// Exit condition
		if (number == 1) {
			stringBuilder.append("1");
			return;
		}
		// Recursion step / Recursion condition
		getAllnaturalBefore(number - 1, stringBuilder);
		stringBuilder.append(" ").append(number);
	}

	/**
	 * Get factorial - the product of all positive integers less than or equal to n.
	 *
	 * @param number Number for calculating the factorial
	 * @return The factorial of the specified number
	 */
	public static int getFactorial(int number) {
		if (number == 1) {
			return 1;
		}
		return number * getFactorial(number - 1);
	}

	/**
	 * Calculating fibonacci number
	 *
	 * @return
	 */
	public static int getFibonacci(int number) {
		if (number < 2) {
			return number;
		}
		return getFibonacci(number - 1) + getFibonacci(number - 2);
	}

	/**
	 * Hanoy Tower algorithm.
	 * <p>Move from left tower to right tower
	 *
	 * @param left   Left Tower Stack
	 * @param center Center Tower Stack
	 * @param right  Right Tower Stack
	 * @param count  Count of
	 */
	public static void hanoyTower(Deque<Integer> left, Deque<Integer> center, Deque<Integer> right, int count) {
		if (count > 0) {
			hanoyTower(left, right, center, count - 1);  // Move n-1 from left to center as a "help" tower
			int biggest = left.pop();                            // the last one is biggest
			right.push(biggest);                                // move biggest to the right
			hanoyTower(center, left, right, count - 1);  // move from center (help tower) to the right tower
		}
	}
}
