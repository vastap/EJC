package ru.github.vastap.fortune;

/**
 * Генератор случайных чисел на основе алгоритма XorShift.
 * <p>Подобный алгоритм используется в вычислении HashCode в Java8 и Java9<br>
 * Данный пример взят из статьи: <a href="http://www.apofig.com/2014/10/random-xorshift.html">Быстрая реализация Random - алгоритм XORShift</a>
 */
public class XorShiftRandomizer {

	/** Основа для генерации случайного числа. По аналогии с seed для java.util.Random */
	private static long rndBase;

	/**
	 * Получить случайное число
	 * @param max верхний предел для случайного числа
	 * @return Случайное число, полученное по алгоритму XorShift
	 */
	public static int nextInt(int max) {
		if (rndBase == 0L) {
			rndBase = System.currentTimeMillis();
		}

		rndBase ^= (rndBase << 21);
		rndBase ^= (rndBase >>> 35);
		rndBase ^= (rndBase << 4);
		int out = (int) rndBase % max;
		return (out < 0) ? -out : out;
	}

}
