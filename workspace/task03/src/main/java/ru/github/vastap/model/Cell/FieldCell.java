package ru.github.vastap.model.Cell;

import ru.github.vastap.model.Coordinate;
import ru.github.vastap.model.Ship.ShipState;

/**
 * Клетка на поле боя.
 * Клетка содержит координату, в которой она расположена на поле боя.
 */
public abstract class FieldCell {
	Coordinate coordinate;

	/**
	 * Создание заполненной ячейки на поле боя
	 * @param coordinate Координата, по которой будет создана ячейка
	 */
	public FieldCell(Coordinate coordinate){
		this.coordinate = coordinate;
	}

	/**
	 * Получить способ, которым нужно отрисовать клетку
	 * @return Символ для отображения на поле боя
	 */
	public abstract char getRenderSymbol();

	/** Обработкать выстрел по клетке
	 *  Возвращает статус корабля
	 */
	public abstract ShipState processShot();

	/** Получить координату ячейки */
	public Coordinate getCoordinate() {
		return coordinate;
	}
}
