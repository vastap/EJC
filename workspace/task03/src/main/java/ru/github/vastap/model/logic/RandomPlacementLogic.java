package ru.github.vastap.model.logic;

import ru.github.vastap.model.field.BattleField;
import ru.github.vastap.model.ships.Ship;
import ru.github.vastap.model.ships.ShipState;
import ru.github.vastap.model.field.Coordinate;
import ru.github.vastap.model.field.Direction;

import java.util.Iterator;
import java.util.Random;

/**
 * Расставление кораблей в случайном месте поля
 */
public class RandomPlacementLogic implements ShipPlacementLogic {

	@Override
	public void placeShips(BattleField field, Iterator<Ship> shipIterator) {
		Random rnd = new Random();

		while (shipIterator.hasNext()) {
			Ship ship = shipIterator.next();

			Direction direction = Direction.values()[rnd.nextInt(4)];
			while (ship.getState() != ShipState.PLACED) {
				Coordinate[] coordinates = new Coordinate[ship.getSize()];
				coordinates[0] = new Coordinate(rnd.nextInt(field.getSize()-1), rnd.nextInt(field.getSize()-1));
				if (!field.isFree(coordinates[0])) {
					continue;
				}

				for (int i = 1; i < coordinates.length; i++) {
					coordinates[i] = coordinates[i - 1].getNextCoordinate(direction);
					if (!field.isFree(coordinates[i])) {
						coordinates = null;
						break;
					}
				}

				if (coordinates != null) {
					field.addShip(ship, coordinates);
				}
			}
		}
	}

}
