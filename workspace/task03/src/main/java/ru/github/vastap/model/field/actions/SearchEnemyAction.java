package ru.github.vastap.model.field.actions;

import ru.github.vastap.model.field.BattleField;
import ru.github.vastap.model.field.Coordinate;

/**
 * Действие для поиска вражеского корабля при помощи итерации вокруг клетки
 */
public class SearchEnemyAction extends FieldAction {
	private boolean found = false;
	private Coordinate coordinate;

	public SearchEnemyAction(BattleField field) {
		super(field);
	}

	@Override
	public void action(int xCoord, int yCoord) {
		if (getField().getCell(xCoord, yCoord) == null) {
			coordinate = new Coordinate(xCoord, yCoord);
			found = true;
		}
	}

	@Override
	public boolean isFinished() {
		return found;
	}

	/**
	 * Получить найденную координату
	 * @return Координата с предполагаемым расположением вражеского корабля
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

}
