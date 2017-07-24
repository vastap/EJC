package ru.github.vastap.zlatopolskyTask;

import java.util.Objects;

/**
 * Класс реализаций задач из главы 9 - Строки символов
 */
public class Chapter9 {

	/**
	 * Задача 9.43:
	 * <p>Дано слово s1. Получить слово s2, образованное нечетными буквами слова s1.
	 */
	public static String exercise43(String sourceString) {
		if (Objects.isNull(sourceString)) {
			throw new IllegalArgumentException("Не передана исходная строка");
		}
		char[] newString = new char[sourceString.length() / 2 + sourceString.length() % 2];
		int position = 0;

		for (int i = 0; i < sourceString.length(); i += 2) {
			newString[position] = sourceString.charAt(i);
			position++;
		}
		return String.valueOf(newString);
	}

	/**
	 * Задача 9.78:
	 * <p>Дано слово. Проверить, является ли оно "перевертышем".
	 * Перевертышем называется слово, читаемое одинаково как с начала, так и с конца).
	 */
	public static boolean exercise78(String sourceString) {
		if (Objects.isNull(sourceString)) {
			throw new IllegalArgumentException("Не передана исходная строка");
		}
		if (sourceString.contains(" ")) {
			throw new IllegalArgumentException("Упражнение 78 требует слово, а не строку");
		}

		String lowerCased = sourceString.toLowerCase();
		for (int i = 0; i < lowerCased.length(); i++) {
			if (lowerCased.charAt(i) != lowerCased.charAt(lowerCased.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Задача 9.116:
	 * <p>Проверить, является ли "перевертышем" (см. задачу 9.78) символьная строка после удаления из нее всех пробелов
	 */
	public static boolean exercise116(String sourceString) {
		if (Objects.isNull(sourceString)) {
			throw new IllegalArgumentException("Не передана исходная строка");
		}

		String processedString = sourceString.replace(" ", "").toLowerCase();
		for (int i = 0; i < processedString.length(); i++) {
			if (processedString.charAt(i) != processedString.charAt(processedString.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Задача 9.153:
	 * <p>Найти наибольшее количество идущих подряд одинаковых символов
	 *
	 * @param value Исходная строка
	 * @return Наибольшее количество идущих подряд одинаковых символов
	 */
	public static int exercise153(String value) {
		char[] chars = value.toCharArray();
		int sameCharCount = 1;
		int maxSameCharCount = 1;
		for (int i = 1; i < chars.length; i++) {
			if (chars[i - 1] == chars[i]) {
				sameCharCount++;
				if (sameCharCount > maxSameCharCount) {
					maxSameCharCount = sameCharCount;
				}
			} else {
				sameCharCount = 1;
			}
		}
		return maxSameCharCount;
	}

	/**
	 * Задача 9.154:
	 * <p>Дано слово. Определить, сколько различных букв в нем
	 *
	 * @param value Исходная строка
	 * @return Количество уникальных букв
	 */
	public static int exercise154(String value) {
		//Методы Arrays не подходят, т.к. массив должен быть отсортирован. Сортировать каждое добавление - дорого
		//Поэтому просто используем копию массива чаров и int счётчик
		char[] chars = value.toCharArray();
		char[] uniqueChars = new char[chars.length];
		int count = 0;

		for (char symbol : chars) {
			boolean isUnique = true;
			for (char unique : uniqueChars) {
				if (unique == symbol) {
					isUnique = false;
					break;
				}
			}
			if (isUnique && Character.isLetter(symbol)) {
				uniqueChars[count] = symbol;
				count++;
			}
		}
		return count;
	}

}