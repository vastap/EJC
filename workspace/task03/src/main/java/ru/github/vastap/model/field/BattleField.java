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
 * Battle Field with ships.
 * <p>Battle field has size.
 * Each coordinate of this field is a cell.
 */
public class BattleField {
	private FieldCell[][] field;
	private ActionResult lastActionResult;
	private int shipCells = 0;

	/**
	 * Create a new square field with specified size
	 *
	 * @param size The battle field size (size x size)
	 */
	public BattleField(int size) {
		//The Field Cell arrays has null references in a cell and not initialized objects|primitives.
		field = new FieldCell[size][size];
	}

	/**
	 * Get the battle field size.
	 *
	 * @return The size of each battle field side
	 */
	public int getSize() {
		return field.length;
	}

	/**
	 * Check this cell for emptiness.
	 * <p>A cell is free if all cells near by this cell is free + if this cell is free
	 *
	 * @param coordinateX coordinate on X axis
	 * @param coordinateY coordinate on Y axis
	 * @return True, if cell is free for ship placement
	 */
	public boolean isFree(int coordinateX, int coordinateY) {
		if (coordinateX < 0 || coordinateY < 0 || coordinateX > field.length - 1 || coordinateY > field.length - 1 || field[coordinateX][coordinateY] != null) {
			return false;
		}

		CheckEmptinessAction check = new CheckEmptinessAction(this);
		iterateAround(coordinateX, coordinateY, check);
		return check.isEmpty();
	}

	/**
	 * Check this cell for emptiness.
	 *
	 * @param coordinate Coordinate of a cell
	 * @return True, if cell is free for ship placement
	 */
	public boolean isFree(Coordinate coordinate) {
		return isFree(coordinate.getX(), coordinate.getY());
	}

	/**
	 * Place ship on battle field.
	 *
	 * @param ship        The ship which should be added
	 * @param coordinates Coordinates of the ship
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
	 * Get text representation of field (field View)
	 *
	 * @param ownMode True to represent field for field owner.
	 *                If this flag is true - all ships will be hide
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
	 * Process a strike in a field cell by coordinate
	 *
	 * @param coordinate Coordinate of a cell
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
			if (field[coordinate.getX()][coordinate.getY()].getClass() == ShipCell.class) {
				Ship ship = ((ShipCell) field[coordinate.getX()][coordinate.getY()]).getShip();
				shipCells--;

				if (ship.getState() == ShipState.DESTROYED) {
					lastActionResult = ActionResult.DESTROY;
					Coordinate[] coordinates = ship.getCoordinates();
					for (Coordinate deckCoordinate : coordinates) {
						iterateAround(deckCoordinate.getX(), deckCoordinate.getY(), new FillEmptyCellsAction(this));
					}
				} else {
					lastActionResult = ActionResult.HIT;
				}
			}
		}
	}

	/**
	 * Get field cell by cell coordinates
	 *
	 * @param coordinateX coordinate on X axis
	 * @param coordinateY coordinate on Y axis
	 * @return Field cell or null, if cell was not initialized (there is no strike in this cell)
	 */
	public FieldCell getCell(int coordinateX, int coordinateY) {
		return field[coordinateX][coordinateY];
	}

	/**
	 * Set cell for specified coordinates
	 *
	 * @param coordinateX coordinate on X axis
	 * @param coordinateY coordinate on Y axis
	 * @param cellValue   cell for specified coordinates
	 */
	public void setCell(int coordinateX, int coordinateY, FieldCell cellValue) {
		field[coordinateX][coordinateY] = cellValue;
	}

	/**
	 * Iterate around specified cell
	 *
	 * @param coordinateX coordinate on X axis
	 * @param coordinateY coordinate on Y axis
	 * @param action      Action for each iteration
	 */
	public void iterateAround(int coordinateX, int coordinateY, FieldAction action) {
		if (coordinateX < 0 || coordinateY < 0 || coordinateX > field.length - 1 || coordinateY > field.length - 1) {
			return;
		}

		for (int x = -1; x < 2; x++) {
			if (coordinateX + x < 0 || coordinateX + x > getSize() - 1) continue;
			for (int y = -1; y < 2; y++) {
				if (coordinateY + y < 0 || coordinateY + y > getSize() - 1) continue;
				action.action(coordinateX + x, coordinateY + y);
				if (action.isFinished()) {
					return;
				}
			}
		}
	}

	/**
	 * Get a status of last action.
	 *
	 * @return
	 */
	public ActionResult getLastActionResult() {
		return lastActionResult;
	}

	/**
	 * Get count of cells, which should be destroyed before player win
	 *
	 * @return Count of remaining ships decs
	 */
	public int getShipCellsCount() {
		return this.shipCells;
	}

}