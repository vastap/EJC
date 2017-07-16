package ru.github.vastap.zlatopolskyTask;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Chapter9Test {

	/**
	 * Проверка задачи 9.43:
	 * <p>Должны получить слово из нечётных букв указанного слова.
	 */
	@Test
	public void shouldReturnOddLetters() {
		assertEquals("1", Chapter9.exercise43("12"));
		assertEquals("13", Chapter9.exercise43("1234"));
		assertEquals("135", Chapter9.exercise43("12345"));
		assertEquals("135", Chapter9.exercise43("123456"));
		assertEquals("", Chapter9.exercise43(""));
	}

	/**
	 * Проверка задачи 9.116:
	 * <p>Должны получить true, если строка является палиндромом
	 */
	@Test
	public void shouldReturnTrueIfSentenceIsPalindrome() {
		assertTrue(Chapter9.exercise116("АРГЕНТИНА МАНИТ НЕГРА"));
		assertFalse(Chapter9.exercise116("ПОТ КАК ПОТОП"));
		assertTrue(Chapter9.exercise116("А РОЗА УПАЛА НА ЛАПУ АЗОРА"));
	}

	/**
	 * Проверка задачи 9.78:
	 * <p>Должны получить true, если слово является палиндромом
	 */
	@Test
	public void shouldReturnTrueIfWordIsPalindrome() {
		assertTrue(Chapter9.exercise78("довод"));
		assertFalse(Chapter9.exercise78("доводы"));
	}

	@Test
	public void shouldReturnMaxCountOfTheSameCharsSequence() {
		String line = "abccddeeeff";
		assertEquals(3, Chapter9.exercise153(line));
	}

	@Test
	public void shouldReturnMaxCountOfUniqueChars() {
		String line = "abbccddeefghiijjkl";
		assertEquals(12, Chapter9.exercise154(line));
	}

}
