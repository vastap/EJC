package ru.github.vastap.model;

import ru.github.vastap.model.Cell.EmptyCell;
import ru.github.vastap.model.Cell.FieldCell;
import ru.github.vastap.model.Cell.ShipCell;
import ru.github.vastap.model.Ship.Ship;

/**
 * Представляет из себя поле боя.
 * <p>Имеет определённый размер X на Y.
 * Каждая координата [X;Y] является клеткой.
 */
public class BattleField {
	private FieldCell[][] field;

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
	 * Определеить, свободна ли клетка
	 *
	 * @param xCoord Координата по оси X (номер строки)
	 * @param yCoord Координата по оси Y (номер столбца)
	 * @param ship   Корабль, для которого выполняется проверка
	 * @return
	 */
	public boolean isFree(int xCoord, int yCoord, Ship ship) {
		if (xCoord > field.length - 1 || xCoord < 0) {
			return false;
		}
		if (yCoord > field[xCoord].length - 1 || yCoord < 0) {
			return false;
		}
		if (field[xCoord][yCoord] != null) {
			return false;
		}
		//Базовые проверки пройдены. Теперь нужно убедиться, что нет рядом других кораблей
		if (xCoord > 0 && field[xCoord - 1][yCoord] != null) {
			return false;
		}
		if (xCoord < getSize() - 1 && field[xCoord + 1][yCoord] != null) {
			return false;
		}
		if (yCoord > 0 && field[xCoord][yCoord - 1] != null) {
			return false;
		}
		if (yCoord < getSize() - 1 && field[xCoord][yCoord + 1] != null) {
			return false;
		}
		//Проверим диагонали
		if (xCoord > 0 && yCoord > 0 && field[xCoord - 1][yCoord - 1] != null) {
			return false;
		}
		if (xCoord < getSize() - 1 && yCoord < getSize() - 1 && field[xCoord + 1][yCoord + 1] != null) {
			return false;
		}
		if (xCoord < getSize() - 1 && yCoord > 0 && field[xCoord + 1][yCoord - 1] != null) {
			return false;
		}
		if (xCoord > 0 && yCoord < getSize() - 1 && field[xCoord - 1][yCoord + 1] != null) {
			return false;
		}

		return true;
	}

	/**
	 * Разместить корабль на поле боя
	 *
	 * @param ship   Добавляемый корабль
	 * @param coords Координаты корабля
	 */
	public void addShip(Ship ship, Coordinate[] coords) {
		for (Coordinate coord : coords) {
			field[coord.getX()][coord.getY()] = new ShipCell(coord, ship);
		}
	}

	/**
	 * Вывод в консоль поля боя
	 */
	public void render() {
		//Render Header
		for (int col = 0; col < field[0].length; col++) {
			System.out.print("  ");
			System.out.print(col);
		}
		System.out.println();

		for (int line = 0; line < getSize(); line++) {
			System.out.print(line);
			for (int col = 0; col < field[0].length; col++) {
				System.out.print("[");
				if (field[line][col] == null) {
					System.out.print(" ]");
				} else {
					System.out.print(field[line][col].getRenderSymbol() + "]");
				}
			}
			System.out.println();
		}
	}

	public FieldCell getCell(Coordinate coordinate){
		if (field[coordinate.getX()][coordinate.getY()]==null){
			field[coordinate.getX()][coordinate.getY()]=new EmptyCell(coordinate);
		}
		return field[coordinate.getX()][coordinate.getY()];
	}
}
