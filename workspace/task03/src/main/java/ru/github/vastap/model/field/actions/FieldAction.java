package ru.github.vastap.model.field.actions;

import ru.github.vastap.model.field.BattleField;

/**
 * Описание действия, выполняемого при итерации по полю.
 * <p>Данные действия предназначены для использования при итерированию по полю боя в
 * {@link ru.github.vastap.model.field.BattleField#iterateAround}
 */
public abstract class FieldAction {
	private BattleField field;

	public FieldAction(BattleField field) {
		this.field = field;
	}

	/** Выполнить действие */
	public abstract void action(int xCoord, int yCoord);

	/** Завершено ли действией (это прервёт остальные итерации) */
	public abstract boolean isFinished();

	/**
	 * Получить поле боя, для которого инициировано данной действие.*
	 * @return Поле боя, для которого выполняется действие
	 */
	protected BattleField getField() {
		return this.field;
	}
}