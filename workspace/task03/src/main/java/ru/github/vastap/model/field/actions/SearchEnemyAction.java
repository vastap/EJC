package ru.github.vastap.model.field.actions;

import ru.github.vastap.model.field.BattleField;
import ru.github.vastap.model.field.Coordinate;

/**
 * Action for searching enemy ship near by iterating cell.
 */
public class SearchEnemyAction extends FieldAction {
	private boolean found = false;
	private Coordinate coordinate;

	public SearchEnemyAction(BattleField field) {
		super(field);
	}

	@Override
	public void action(int coordinateX, int coordinateY) {
		if (getField().getCell(coordinateX, coordinateY) == null) {
			coordinate = new Coordinate(coordinateX, coordinateY);
			found = true;
		}
	}

	@Override
	public boolean isFinished() {
		return found;
	}

	/**
	 * Get enemy ship cell if found.
	 *
	 * @return Coordinate with intended placement of an enemy ship
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

}
