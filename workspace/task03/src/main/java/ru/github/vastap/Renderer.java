package ru.github.vastap;

import ru.github.vastap.model.field.BattleField;

/**
 * Отрисовщик игрового поля
 */
public class Renderer {

	/**
	 * Отрисовщик полей
	 * @param enemy Поле противника
	 * @param own Собственное поле
	 */
	public static void render(BattleField enemy, BattleField own){
		String[] enemyView = enemy.getView(false);
		String[] ownView = own.getView(true);
		StringBuilder resultString;

		for (int i=0;i<enemyView.length;i++){
			resultString = new StringBuilder(enemyView[i].length()+ownView[i].length()+2);
			System.out.println( resultString.append(enemyView[i]).append("  ").append(ownView[i]).toString() );
		}
	}
}
