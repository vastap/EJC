package ru.github.vastap.model.Cell;

import ru.github.vastap.model.Coordinate;
import ru.github.vastap.model.Ship.Ship;
import ru.github.vastap.model.Ship.ShipState;

/**
 * Клетка с кораблём
 */
public class ShipCell extends FieldCell {
	/** Корабль, размещённый в данной клетке */
	private Ship ship;
	/** Символ, который будет отрисован при рендеринге */
	private char symbol;

	public ShipCell(Coordinate coordinate, Ship ship) {
		super(coordinate);
		this.ship = ship;
		symbol = 'O';
		//TODO: После доделок закрыть поле
	}

	@Override
	public char getRenderSymbol() {
		return symbol;
	}

	@Override
	public ShipState processShot() {
		if (symbol != 'X'){
			ship.hit(this);
			symbol = 'X';
		}
		return ship.getState();
	}

}
