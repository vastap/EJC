package ru.github.vastap.model.logic;

import ru.github.vastap.model.field.BattleField;
import ru.github.vastap.model.ships.Ship;

import java.util.Iterator;

/**
 * The logic of ship placement.
 */
public interface ShipPlacementLogic {

	/**
	 * Place ships on the battle field
	 * <p>Logic use ship iterator for safe getting player ships
	 *
	 * @param field        The own field
	 * @param shipIterator The player ships iterator
	 */
	public void placeShips(BattleField field, Iterator<Ship> shipIterator);

}
