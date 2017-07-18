package ru.github.vastap.model.field.actions;

import ru.github.vastap.model.field.BattleField;

/**
 * Describe action performed with iterating cell.
 * <p>This action intended for use with battle field iterator
 * {@link ru.github.vastap.model.field.BattleField#iterateAround}
 */
public abstract class FieldAction {
	private BattleField field;

	public FieldAction(BattleField field) {
		this.field = field;
	}

	/**
	 * Make an action
	 */
	public abstract void action(int coordinateX, int coordinateY);

	/**
	 * Check if action was finished.
	 * @return Return true to prevent next iterations
	 */
	public abstract boolean isFinished();

	/**
	 * Inner method for getting battle field.
	 *
	 * @return Battle field for make an action.
	 */
	protected BattleField getField() {
		return this.field;
	}

}