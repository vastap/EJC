package ru.github.vastap;

/**
 * Main класс для работы с игрой: Морской бой
 */
public class App {

	public static void main(String[] args) {
		BattleShipGame game = new BattleShipGame();
		game.prepareForGame();
		game.start();
	}
}
