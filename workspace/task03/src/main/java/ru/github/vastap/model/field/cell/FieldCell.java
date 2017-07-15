package ru.github.vastap.model.field.cell;

import ru.github.vastap.model.field.Coordinate;

/**
 * Представляет клетку на поле боя.
 * <p>Клетка содержит координату, в которой она расположена на поле боя.
 * Отвечает за то, каким образом клетка должна быть отрисована и за обработку попадания по клетке.
 */
public abstract class FieldCell {
	private Coordinate coordinate;

	/**
	 * Создание заполненной ячейки на поле боя
	 *
	 * @param coordinate Координата, по которой будет создана ячейка
	 */
	public FieldCell(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * Получить способ, которым нужно отрисовать клетку
	 *
	 * @param hide Нужно ли прятать корабль и отрисовать его пустотой
	 * @return Символ для отображения на поле боя
	 */
	public abstract char getRenderSymbol(boolean hide);

	/**
	 * Получить способ, которым нужно отрисовать клетку
	 * @return Символ, отображающий данную клетку
	 */
	public char getRenderSymbol() {
		return getRenderSymbol(true);
	}

	/**
	 * Обработкать выстрел по клетке
	 * Возвращает статус корабля
	 */
	public abstract void processShot();

	/** Получить координату ячейки */
	public Coordinate getCoordinate() {
		return coordinate;
	}

}
