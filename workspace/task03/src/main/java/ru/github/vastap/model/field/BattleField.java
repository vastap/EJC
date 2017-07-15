package ru.github.vastap.model.field;

import ru.github.vastap.model.players.ActionResult;
import ru.github.vastap.model.ships.Ship;
import ru.github.vastap.model.ships.ShipState;
import ru.github.vastap.model.field.actions.CheckEmptinessAction;
import ru.github.vastap.model.field.actions.FieldAction;
import ru.github.vastap.model.field.actions.FillEmptyCellsAction;
import ru.github.vastap.model.field.cell.EmptyCell;
import ru.github.vastap.model.field.cell.FieldCell;
import ru.github.vastap.model.field.cell.ShipCell;

/**
 * Представляет из себя поле боя.
 * <p>Имеет определённый размер X на Y.
 * Каждая координата [X;Y] является клеткой.
 */
public class BattleField {
	private FieldCell[][] field;
	private ActionResult lastActionResult;
	private int shipCells = 0;

	/**
	 * Создать поле боя в виде квадрата
	 *
	 * @param size Размер поля size * size
	 */
	public BattleField(int size) {
		//Т.к. массив из объектов, то это не приводит к инициализации примитивов и избегается оверхед
		field = new FieldCell[size][size];
	}

	/**
	 * Получить размер поля боя
	 * <p>Поле боя - квадрат, поэтому соответствует любой стороне.
	 *
	 * @return Размер стороны поля боя.
	 */
	public int getSize() {
		return field.length;
	}

	/**
	 * Свободна ли клетка по указанным координатам.
	 * <p>Клетка свободна, если к ней не примыкает ни одна занятая клетка + она сама не занята
	 *
	 * @param xCoord Координата по оси X
	 * @param yCoord Координата по оси Y
	 * @return True, если клетка свободна для размещения корабля
	 */
	public boolean isFree(int xCoord, int yCoord) {
		if (xCoord < 0 || yCoord < 0 || xCoord > field.length - 1 || yCoord > field.length - 1 || field[xCoord][yCoord] != null) {
			return false;
		}

		CheckEmptinessAction check = new CheckEmptinessAction(this);
		iterateAround(xCoord, yCoord, check);
		return check.isEmpty();
	}

	/**
	 * Проверить, что указанная координата свободна на поле боя
	 *
	 * @param coordinate Координата на поле боя
	 * @return True, если клетка свободна для размещения корабля
	 */
	public boolean isFree(Coordinate coordinate) {
		return isFree(coordinate.getX(), coordinate.getY());
	}

	/**
	 * Разместить корабль на поле боя
	 *
	 * @param ship Добавляемый корабль
	 * @param coordinates Координаты размещения корабля
	 */
	public void addShip(Ship ship, Coordinate[] coordinates) {
		for (Coordinate coord : coordinates) {
			field[coord.getX()][coord.getY()] = new ShipCell(coord, ship);
			shipCells++;
		}
		ship.setCoordinates(coordinates);
		ship.setState(ShipState.PLACED);
	}

	/**
	 * Получение отображения
	 * @param ownMode Режим владельца данного поля. Если true - корабли не спрятаны
	 */
	public String[] getView(boolean ownMode) {
		String[] lines = new String[field[0].length + 1];
		StringBuilder renderer = new StringBuilder();

		//Render Header
		for (int col = 0; col < field[0].length; col++) {
			renderer.append("  " + col);
		}
		renderer.append(" ");
		lines[0] = renderer.toString();

		for (int line = 0; line < getSize(); line++) {
			renderer = new StringBuilder();
			renderer.append(line);
			for (int col = 0; col < field[0].length; col++) {
				renderer.append("[");
				if (field[line][col] == null) {
					renderer.append(" ]");
				} else {
					renderer.append(field[line][col].getRenderSymbol(!ownMode));
					renderer.append("]");
				}
			}
			lines[line + 1] = renderer.toString();
		}
		return lines;
	}

	/**
	 * Обработать выстрел по полю боя
	 *
	 * @param coordinate Координата ячейки, в которую был произведён выстрел
	 */
	public void processShot(Coordinate coordinate) {
		if (field[coordinate.getX()][coordinate.getY()] == null) {
			field[coordinate.getX()][coordinate.getY()] = new EmptyCell();
			lastActionResult = ActionResult.MISSED;
			return;
		}

		char before = field[coordinate.getX()][coordinate.getY()].getRenderSymbol();
		field[coordinate.getX()][coordinate.getY()].processShot();
		if (before != field[coordinate.getX()][coordinate.getY()].getRenderSymbol()) {
			//Если это клетка с кораблём - проверим, не уничтожен ли он
			if (field[coordinate.getX()][coordinate.getY()].getClass() == ShipCell.class) {
				Ship ship = ((ShipCell) field[coordinate.getX()][coordinate.getY()]).getShip();
				shipCells--;

				if (ship.getState() == ShipState.DESTROYED) {
					lastActionResult = ActionResult.DESTROY;
					Coordinate[] coords = ship.getCoordinates();
					for (Coordinate coord : coords) {
						iterateAround(coord.getX(), coord.getY(), new FillEmptyCellsAction(this));
					}
				} else {
					lastActionResult = ActionResult.HIT;
				}
			}
		}
	}

	/**
	 * Получить ячейку поля по её координатам
	 *
	 * @param xCoord Координата по оси X
	 * @param yCoord Координата по оси Y
	 * @return Ячейка поля боя или null, если она ещё не инициализирована (пустая и по ней не стреляли)
	 */
	public FieldCell getCell(int xCoord, int yCoord) {
		return field[xCoord][yCoord];
	}

	/**
	 * Указать значение для ячейки на поле боя
	 *
	 * @param xCoord Координата по оси X
	 * @param yCoord Координата по оси Y
	 * @param cellValue Значение, которым будет инициирована клетка
	 */
	public void setCell(int xCoord, int yCoord, FieldCell cellValue) {
		field[xCoord][yCoord] = cellValue;
	}


	/**
	 * Обойти все клетки вокруг указанной клетки
	 *
	 * @param xCoord Координата исходной клетки по оси x
	 * @param yCoord Координата исходной клетки по оси y
	 * @param action Выполняемое при итерации действие
	 */
	public void iterateAround(int xCoord, int yCoord, FieldAction action) {
		if (xCoord < 0 || yCoord < 0 || xCoord > field.length - 1 || yCoord > field.length - 1) {
			//Не итерируемся, если указанная координата вне поля
			return;
		}

		for (int x = -1; x < 2; x++) {
			if (xCoord + x < 0 || xCoord + x > getSize() - 1) continue;
			for (int y = -1; y < 2; y++) {
				if (yCoord + y < 0 || yCoord + y > getSize() - 1) continue;
				action.action(xCoord + x, yCoord + y);
				if (action.isFinished()) {
					return;
				}
			}
		}
	}

	/**
	 * Получить статус последнего действия
	 *
	 * @return Статус последнего действия, выполненного на данном поле
	 */
	public ActionResult getLastActionResult() {
		return lastActionResult;
	}

	/**
	 * Получить количество клеток, которые осталось уничтожить
	 *
	 * @return Количество оставшихся палуб (т.е. кол-во клеток, которые нужно уничтожить)
	 */
	public int getShipCellsCount() {
		return this.shipCells;
	}

}