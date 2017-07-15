package ru.github.vastap.model.logic;

import ru.github.vastap.model.field.BattleField;
import ru.github.vastap.model.ships.Ship;

import java.util.Iterator;

/**
 * Описание логики размещения кораблей на поле
 */
public interface ShipPlacementLogic {

	/**
	 * Разместить корабли на поле боя.
	 * <p>Т.к. для логики может потребоваться знать о всех кораблях, то в логике используется итератор по кораблям, а не корабль
	 * @param field Поле боя
	 * @param shipIterator Итератор по кораблям
	 */
	public void placeShips(BattleField field, Iterator<Ship> shipIterator);

}
