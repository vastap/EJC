package task_02.model;

/**
 * Представляет из себя игрока, который участвует в игре.
 * <p>Имеет какое-то количество денег, из которых делает ставки.
 * @author veselroger
 *
 */
public class Player {
	/** Деньги в долларах */
	private int money = 500;

	/**
	 * Снять сумму со счёта игрока
	 * @param value Сумма в долларах
	 */
	public void withdraw(int value) {
		this.money = this.money - value;
	}
	
	/**
	 * Зачислить сумму игроку на счёт
	 * @param value Сумма в долларах
	 */
	public void deposit(int value) {
		this.money = this.money - value;
	}
}
