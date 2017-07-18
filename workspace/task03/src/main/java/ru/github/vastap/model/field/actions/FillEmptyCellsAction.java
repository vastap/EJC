package ru.github.vastap.model.field.actions;

import ru.github.vastap.model.field.BattleField;
import ru.github.vastap.model.field.cell.EmptyCell;

/**
 * Action for filling empty cells near by iterating cell (for example, when ship was destroyed).
 */
public class FillEmptyCellsAction extends FieldAction {

	public FillEmptyCellsAction(BattleField field) {
		super(field);
	}

	@Override
	public void action(int coordinateX, int coordinateY) {
		if (getField().getCell(coordinateX, coordinateY) == null) {
			getField().setCell(coordinateX, coordinateY, new EmptyCell());
		}
	}

	@Override
	public boolean isFinished() {
		return false;
	}

}