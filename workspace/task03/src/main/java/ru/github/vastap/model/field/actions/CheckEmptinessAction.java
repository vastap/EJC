package ru.github.vastap.model.field.actions;

import ru.github.vastap.model.field.BattleField;

/**
 * Check if cells are free near by cell of iteration.
 * This check can return result after iteration.
 */
public class CheckEmptinessAction extends FieldAction {
	private boolean emptiness = true;
	private boolean finished = false;

	public CheckEmptinessAction(BattleField field) {
		super(field);
	}

	@Override
	public void action(int xCoord, int yCoord) {
		if (getField().getCell(xCoord, yCoord) != null) {
			emptiness = false;
			finished = true;
		}
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

	/**
	 * Are all cells near by iterating cell free.
	 *
	 * @return True if we can place ship in iterating cell
	 */
	public boolean isEmpty() {
		return this.emptiness;
	}

}