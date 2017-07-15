package ru.github.vastap.model.field.actions;

import ru.github.vastap.model.field.BattleField;

/**
 * Проверка на свободность клеток вокруг клетки, по которой выполняется итерации.
 * После итерации у данной проверки можно получить результат.
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
	 * Свободна ли клетка для размещения корабля
	 * @return True если во время всех итераций все клетки вокруг указанной клетки так же пустые
	 */
	public boolean isEmpty() {
		return this.emptiness;
	}

}