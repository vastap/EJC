package task_02.fortune;

import java.util.Random;

import task_02.behavior.FlyNoWay;
import task_02.behavior.FlyWithWings;
import task_02.model.Duck;

/** Представляет из себя Фортуну - выполнение действий по воле случая */
public class Fortuna {

	/** Получить утку. Свойства утки будут выбраны по воле случая, т.е. случайно */
	public static Duck getDuck() {
		// На основе java.util.Random
		if (new Random().nextBoolean()) {
			return new Duck(new FlyNoWay());
		} else {
			return new Duck(new FlyWithWings());
		}
	}

	/**
	 * Выбрать для полёта утки случайную скорость
	 * 
	 * @param min Минимальная скорость, в утиных единицах скорости
	 * @param max Максимальная скорость, в утиных единицах скорости
	 */
	public static void setDuckSpeed(Duck duck, int min, int max) {
		// На основе алгоритма XorShift
		duck.setSpeed(new XorShiftRandom().nextInt(max) + min);
	}

	/**
	 * Получить случайный порядковый номер утки
	 * 
	 * @param duckCount Число уток
	 * @return
	 */
	public static int getDuckNumber(int duckCount) {
		// На основе Math.random() См. http://proglang.su/java/91
		return 0 + (int) (Math.random() * duckCount);
	}

}
