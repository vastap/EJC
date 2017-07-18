package ru.github.vastap.model.field.cell;

import ru.github.vastap.Utils;
import ru.github.vastap.model.ships.Ship;
import ru.github.vastap.model.ships.ShipState;
import ru.github.vastap.model.field.Coordinate;

/**
 * The cell with a deck of a ship.
 */
public class ShipCell extends FieldCell {
	public static final char DESTROYED_SYMBOL = 'X';
	public static final char SHIP_SYMBOL = 'O';
	private Ship ship;
	private char symbol;

	public ShipCell(Coordinate coordinate, Ship ship) {
		super(coordinate);
		this.ship = ship;
		if ("true".equals(Utils.getProperties().getProperty("debug.mode"))) {
			symbol = SHIP_SYMBOL;
		} else {
			symbol = ' ';
		}
	}

	@Override
	public char getRenderSymbol(boolean hide) {
		if (!hide) {
			if (symbol != ShipCell.DESTROYED_SYMBOL) {
				return ShipCell.SHIP_SYMBOL;
			}
		}
		return symbol;
	}

	@Override
	public void processShot() {
		if (ship.getState() == ShipState.DESTROYED) {
			System.out.println("The ship was already destroyed. Please, try to find another cell.");
			return;
		}
		if (symbol == DESTROYED_SYMBOL) {
			System.out.println("This cell was already under fire. Ship is still alive. Try to choose cells near by this cell.");
			return;
		}

		ship.hit(this);
		symbol = DESTROYED_SYMBOL;
		if (ship.getState() == ShipState.DESTROYED) {
			System.out.println("Destroyed!");
		} else {
			System.out.println("Hit!");
		}
	}

	/**
	 * Get a ship from this cell.
	 * @return Get a ship which is owner of the deck from this cell.
	 */
	public Ship getShip() {
		return this.ship;
	}

}
