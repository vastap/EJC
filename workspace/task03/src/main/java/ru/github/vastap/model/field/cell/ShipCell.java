package ru.github.vastap.model.field.cell;

import ru.github.vastap.Utils;
import ru.github.vastap.model.ships.Ship;
import ru.github.vastap.model.ships.ShipState;
import ru.github.vastap.model.field.Coordinate;

/**
 * Клетка, в которой размещена палуба корабля
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
			System.out.println("Корабль был ранее уничтожен. Точно-точно. Не надо снова стрелять сюда.");
			return;
		}
		if (symbol == DESTROYED_SYMBOL) {
			System.out.println("Вы уже стреляли сюда. Корабль ещё жив, попробуйте соседние клетки.");
			return;
		}

		ship.hit(this);
		symbol = DESTROYED_SYMBOL;
		if (ship.getState() == ShipState.DESTROYED) {
			System.out.println("Убит!");
		} else {
			System.out.println("Ранен!");
		}
	}

	/**
	 * Получить корабль, который расположен в данной клетке
	 * @return Корабль, палуба которого расположена в данной клетке
	 */
	public Ship getShip() {
		return this.ship;
	}

}
