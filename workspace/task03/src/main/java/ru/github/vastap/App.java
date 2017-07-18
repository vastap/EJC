package ru.github.vastap;

/**
 * The Main class for Sea Battle game.
 */
public class App {

	public static void main(String[] args) {
		BattleShipGame game = new BattleShipGame();
		game.prepareForGame();
		game.start();
	}

}
