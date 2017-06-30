package ru.github.vastap;

import ru.github.vastap.model.Player;

/**
 * Hello world!
 */
public class App {

	public static void main(String[] args) {
		Play casino = new Play(5, 10, new Player());
		casino.begin();
	}

}
