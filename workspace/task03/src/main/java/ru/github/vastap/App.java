package ru.github.vastap;

/**
 * Main класс для работы с игрой: Морской бой
 */
public class App {

	public static void main(String[] args) {
		BattleShip game = new BattleShip();
		game.prepareForGame();
		game.start();

	}
}
