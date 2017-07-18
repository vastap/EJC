package ru.github.vastap;

import ru.github.vastap.model.field.BattleField;

/**
 * Static Renderer for battle field
 */
public class Renderer {

	/**
	 * Render battle fields
	 *
	 * @param enemy Field of the enemy
	 * @param own   Own field
	 */
	public static void render(BattleField enemy, BattleField own) {
		String[] enemyView = enemy.getView(false);
		String[] ownView = own.getView(true);
		StringBuilder resultString;

		for (int i = 0; i < enemyView.length; i++) {
			resultString = new StringBuilder(enemyView[i].length() + ownView[i].length() + 2);
			System.out.println(resultString.append(enemyView[i]).append("  ").append(ownView[i]).toString());
		}
	}

}
