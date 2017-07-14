package ru.github.vastap.model.Strategy;

import ru.github.vastap.model.BattleField;
import ru.github.vastap.model.Ship.Ship;
import ru.github.vastap.model.Ship.ShipState;

import java.util.Iterator;
import java.util.Random;

/**
 * Расставление кораблей в случайном месте поля
 */
public class RandomPlacementStrategy implements ShipPlacementStrategy{

	@Override
	public void placeShips(BattleField field, Iterator<Ship> shipIterator) {
		Random rnd = new Random();

		while(shipIterator.hasNext()){
			Ship ship = shipIterator.next();
			while (ship.getState() != ShipState.PLACED){
				int xCoord = rnd.nextInt(field.getSize());
				int yCoord = rnd.nextInt(field.getSize());
				ship.allocate(xCoord, yCoord, field);
			}
		}
	}
}
