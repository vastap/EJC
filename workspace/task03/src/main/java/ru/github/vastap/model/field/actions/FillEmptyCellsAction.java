package ru.github.vastap.model.field.actions;

import ru.github.vastap.model.field.BattleField;
import ru.github.vastap.model.field.cell.EmptyCell;

/**
 * Заполнить при итерации ячейки пустыми ячейками (не содержащим кораблей)
 */
public class FillEmptyCellsAction extends FieldAction {

	public FillEmptyCellsAction(BattleField field) {
		super(field);
	}

	@Override
	public void action(int xCoord, int yCoord) {
		if (getField().getCell(xCoord, yCoord) == null) {
			getField().setCell(xCoord, yCoord, new EmptyCell());
		}
	}

	@Override
	public boolean isFinished() {
		return false;
	}

}