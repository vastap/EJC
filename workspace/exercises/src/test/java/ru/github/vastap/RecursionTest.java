package ru.github.vastap;


import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.assertEquals;

/**
 * Test for recursion
 */
public class RecursionTest {

	@Test
	public void shouldReturnNaturalNumbersBeforeSpecified() {
		StringBuilder string = new StringBuilder(5);
		Recursion.getAllnaturalBefore(5, string);
		assertEquals("1 2 3 4 5", string.toString());
	}

	@Test
	public void shouldReturnFactorial() {
		int result = Recursion.getFactorial(5);
		int expected = 5 * 4 * 3 * 2 * 1;
		assertEquals(expected, result);
	}

	@Test
	public void shouldReturnFibonacci() {
		int result = Recursion.getFibonacci(6);
		assertEquals(8, result);
	}

	@Test
	public void shouldMoveFromOneHanoyTowerToAnother() {
		int count = 7;
		Deque<Integer> left = new ArrayDeque<Integer>();
		Deque<Integer> center = new ArrayDeque<Integer>();
		Deque<Integer> right = new ArrayDeque<Integer>();
		for (int i = 1; i <= count; i++) {
			left.push(i);
		}
		System.out.println("Left tower: " + left);
		// Move from left to right tower
		Recursion.hanoyTower(left, center, right, left.size());
		System.out.println("Right tower: " + right);
	}
}
