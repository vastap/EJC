package ru.github.vastap.Zlatopolsky;

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
		if (Objects.isNull(sourceString)){
			throw new IllegalArgumentException("Не передана исходная строка");
		}
		char[] newString = new char[sourceString.length() / 2 + sourceString.length() % 2];
		int position=0;

		for (int i=0; i<sourceString.length();i+=2){
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
		if (Objects.isNull(sourceString)){
			throw new IllegalArgumentException("Не передана исходная строка");
		}
		if (sourceString.contains(" ") ){
			throw new IllegalArgumentException("Упражнение 78 требует слово, а не строку");
		}

		String lowerCased = sourceString.toLowerCase();
		for (int i=0;i<lowerCased.length();i++){
			if (lowerCased.charAt(i) != lowerCased.charAt(lowerCased.length()-1-i)){
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
		if (Objects.isNull(sourceString)){
			throw new IllegalArgumentException("Не передана исходная строка");
		}

		String processedString = sourceString.replace(" ", "").toLowerCase();
		for (int i=0;i<processedString.length();i++){
			if (processedString.charAt(i) != processedString.charAt(processedString.length()-1-i)){
				return false;
			}
		}
		return true;
	}
}
