package ru.github.vastap.model.Ship;

import ru.github.vastap.model.BattleField;
import ru.github.vastap.model.Cell.FieldCell;
import ru.github.vastap.model.Coordinate;
import ru.github.vastap.model.Direction;

public class Ship {
	private ShipState state = ShipState.CREATED;
	/** Идентификатор корабля на поле боя, позывной */
	private String ID;
	/** Занимаемые кораблём клетки */
	private Coordinate[] coordinates;
	/** Количество жизней (Длинная корабля - попадания) */
	private int health;

	public Ship(int size) {
		coordinates = new Coordinate[size];
		health = size;
	}

	/**
	 * Получить размер корабля
	 * @return размер корабля (кол-во палуб)
	 */
	public int getSize() {
		return coordinates.length;
	}

	/**
	 * Указать статус корабля.
	 * <p>Статусы имеют свою последовательность.
	 * Менять можно только на следующий в цепочке статус.
	 * Например, нельзя поменять с PLACED обратно на CREATED
	 *
	 * @param state Статус корабля
	 */
	public void setState(ShipState state) {
		if (this.state.ordinal() < state.ordinal()) {
			this.state = state;
		}
	}

	/**
	 * Получить статус корабля
	 * @return Статус корабля
	 */
	public ShipState getState() {
		return this.state;
	}

	/**
	 * Выполнить попытку разместить корабль по указанным координатам
	 *
	 * @param direction Направление
	 * @param xCoord    Координаты по оси X
	 * @param yCoord    Координаты по оси Y
	 * @param field     Поле боя
	 * @return Набор занятых координат, если есть возможность разместить корабль
	 */
	private Coordinate[] tryToAllocate(Direction direction, int xCoord, int yCoord, BattleField field) {
		Coordinate[] coords = new Coordinate[getSize()];
		coords[0] = new Coordinate(xCoord, yCoord);
		for (int i = 1; i < getSize(); i++) {
			coords[i] = coords[i - 1].getNextCoordinate(direction);
		}
		//Проверяем все координаты на предмет того, что их можно занять
		for (Coordinate coord : coords) {
			if (!field.isFree(coord.getX(), coord.getY(), this)) {
				return null;
			}
		}
		return coords;
	}

	/** Разместить корабль на поле боя вокруг указанной координаты */
	public void allocate(int xCoord, int yCoord, BattleField field) {
		//Если ячейка уже занята - не сможем аллоцировать корабль
		if (!field.isFree(xCoord, yCoord, this)) {
			return;
		}
		//Ищем свободные координаты
		Coordinate[] coords = null;
		//TODO: Сделать направления рандомными
		for (Direction direction : Direction.values()) {
			coords = tryToAllocate(direction, xCoord, yCoord, field);
			if (coords != null) {
				break;
			}
		}
		if (coords == null) {
			return;
		}

		field.addShip(this, coords);
		this.coordinates = coords;
		setState(ShipState.PLACED);
	}

	/** Обработка удара по кораблю */
	public void hit(FieldCell cell){
		for (Coordinate coord : coordinates){
			if (coord.getY() == cell.getCoordinate().getY() && coord.getX() == cell.getCoordinate().getX()){
				health--;
				if (health==0){
					setState( ShipState.DESTROYED );
				}
				break;
			}
		}
	}
}
