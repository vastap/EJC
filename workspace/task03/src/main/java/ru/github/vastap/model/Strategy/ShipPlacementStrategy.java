package ru.github.vastap.model.Strategy;

import ru.github.vastap.model.BattleField;
import ru.github.vastap.model.Ship.Ship;

import java.util.Iterator;

/**
 * Стратегия размещения кораблей
 */
public interface ShipPlacementStrategy {

	public void placeShips(BattleField field, Iterator<Ship> shipIterator);

}
