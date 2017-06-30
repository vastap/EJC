package ru.github.vastap.model;

import ru.github.vastap.behavior.FlyBehavior;

/**
 * Абстрактная утка, представляющая из себя всё, что можно назвать уткой.
 */
public abstract class Duck {
	private FlyBehavior flyBehavior;
	private int flightSpeed;
	private static int MAX_SPEED=100;
	private static int MIN_SPEED=10;

	public Duck(FlyBehavior behavior){
		this.flyBehavior = behavior;
	}

	/**
	 * Указать скорость полёта укти
	 * @param speed Скорость в утиных единицах скорости
	 */
	public void setSpeed(int speed) {
		this.flightSpeed = speed;
	}

	/**
	 * Получить скорость полёта утки
	 * @return Скорость в утиных единицах скорости
	 */
	public int getSpeed() {
		return this.flightSpeed;
	}

	/**
	 * Получить максимально возможную скорость для уток.
	 * <p>Утки быстрее перемещаться, т.к. такие нагрузки несовместимы с жизнь.
	 * @return Максимально возможная для уток скорость
	 */
	public static int getMaxSpeed(){
		return MAX_SPEED;
	}

	/**
	 * Получить минимальную скорость для уток.
	 * <p>Медленнее они не могут перемещаться из-за действия третих сил.
	 * @return Минимально возможная для уток скорость
	 */
	public static int getMinSpeed(){
		return MIN_SPEED;
	}

	/**
	 * Выполнить полёт.
	 * <p>При скорости м/с перемещение осуществляется на метры.
	 * <p>Так как скорость в утиных единицах скорости, то и перемещение на утиные единицы
	 * @return Количество утиных единиц полёта
	 */
	public abstract int fly();

}
