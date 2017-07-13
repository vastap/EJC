package ru.github.vastap;

import ru.github.vastap.model.Player;

/**
 * Главный класс утиного казино
 */
public class App {

	public static void main(String[] args) {
		Play casino = new Play(5, 10, new Player());
		casino.begin();
	}

}
