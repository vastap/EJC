package ru.github.vastap.model.Cell;

import ru.github.vastap.model.Coordinate;
import ru.github.vastap.model.Ship.ShipState;

/**
 * Пустая клетка, по которой был выполнен удар
 */
public class EmptyCell extends FieldCell{

	public EmptyCell(Coordinate coordinate) {
		super(coordinate);
	}

	@Override
	public char getRenderSymbol() {
		return '-';
	}

	@Override
	public ShipState processShot() {
		System.out.println("Промах");
		return ShipState.NONE;
	}

}
