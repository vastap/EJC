package ru.github.vastap.model.field.cell;

import ru.github.vastap.model.field.Coordinate;

/**
 * Cell on a battle field.
 * <p>Each cell has coordinate on a battle field.
 * Response for result of a strike in a cell.
 */
public abstract class FieldCell {
	private Coordinate coordinate;

	/**
	 * Create filled cell on a battle field
	 *
	 * @param coordinate Coordinate of the battle field cell
	 */
	public FieldCell(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * Get cell render symbol.
	 *
	 * @param hide A requirement to hide a cell and render empty cell
	 * @return Symbol to render
	 */
	public abstract char getRenderSymbol(boolean hide);

	/**
	 * Get cell render symbol
	 *
	 * @return Символ, отображающий данную клетку
	 */
	public char getRenderSymbol() {
		return getRenderSymbol(true);
	}

	/**
	 * Process the strike in a battle field cell.
	 */
	public abstract void processShot();

	/**
	 * Get coordinates of the cell
	 * @return
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

}