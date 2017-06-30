package ru.github.vastap.fortune;

import ru.github.vastap.model.Duck;
import ru.github.vastap.model.FlyingDuck;
import ru.github.vastap.model.StationaryDuck;

import java.util.Random;

/**
 * Представляет из себя Фортуну - выполнение действий по воле случая
 */
public class Fortuna {

	/**
	 * Получить случайный порядковый номер утки
	 * @param duckCount Число уток, из которых нужно выбрать
	 * @return Порядковый номер выбранной утки
	 */
	public static int getDuckNumber(int duckCount) {
		// На основе Math.random() См. http://proglang.su/java/91
		return 0 + (int) (Math.random() * duckCount);
	}

	/**
	 * Получить случайную утку, свойства которой будут выбраны по воле случая, т.е. случайно
	 * @return Утка со случайными свойствами
	 */
	public static Duck getRandomDuck() {
		// На основе java.util.Random
		if (new Random().nextBoolean()) {
			return new FlyingDuck();
		} else {
			return new StationaryDuck();
		}
	}

	/**
	 * Указать для утки скорость, случайным образом, в указанном диапазоне
	 * @param duck Утка, для которой будет изменена скорость
	 * @param min Минимальная скорость в утиных единицах скорости
	 * @param max Максимальная скорость в утиных единицах скорости
	 */
	public static void setDuckSpeed(Duck duck, int min, int max) {
		// На основе алгоритма XorShift
		duck.setSpeed(XorShiftRandomizer.nextInt(max) + min);
	}

}
