package ru.github.vastap.model.ships;

import ru.github.vastap.model.field.cell.FieldCell;
import ru.github.vastap.model.field.Coordinate;

/**
 * Корабль - сущность, размещаемая на поле боя.
 * <p>Имеет свои координаты, свой статус, размер (количество палуб).
 * <p>При уничтожении всех палуб корабль считается уничтоженным
 */
public class Ship {
	private ShipState state;
	private Coordinate[] coordinates;
	private int size;
	private int health;

	public Ship(int size) {
		this.size = size;
		health = size;
		state = ShipState.CREATED;
	}

	/**
	 * Указать координаты корабля
	 * @param coordinates Координаты корабля
	 */
	public void setCoordinates(Coordinate[] coordinates){
		if ( this.coordinates != null) return;

		if (coordinates.length != health){
			throw new IllegalArgumentException("Длинна корабля не совпадает с количеством переданных координат");
		}
		this.coordinates = coordinates;
	}

	/**
	 * Получить занимаемые кораблём координаты
	 * @return Координаты корабля
	 */
	public Coordinate[] getCoordinates() {
		return coordinates;
	}

	/**
	 * Получить размер корабля
	 * @return размер корабля (кол-во палуб)
	 */
	public int getSize() {
		return size;
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
