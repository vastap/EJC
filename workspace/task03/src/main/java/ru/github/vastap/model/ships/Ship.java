package ru.github.vastap.model.ships;

import ru.github.vastap.model.field.cell.FieldCell;
import ru.github.vastap.model.field.Coordinate;

/**
 * The Ship - entity of entity, which is located on a battle field.
 * <p>Ship has own coordinates, status and size (number of decs).
 * <p>Ship has status "DESTROYED" when all decs are destroyed.
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
	 * Set coordinates of the ship.
	 * @param coordinates
	 */
	public void setCoordinates(Coordinate[] coordinates) {
		if (this.coordinates != null) return;

		if (coordinates.length != health) {
			throw new IllegalArgumentException("Number of coordinates not equals number of the decks");
		}
		this.coordinates = coordinates;
	}

	/**
	 * Get coordinates of the ship.
	 * @return Ship coordinates
	 */
	public Coordinate[] getCoordinates() {
		return coordinates;
	}

	/**
	 * Get the size of the ship
	 * @return The Number of decs
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Set the status for the ship.
	 * <p>Statuses have their own order.
	 * Status can be changed only from lower to highest.
	 * For example, you can't change status from PLACED to CREATED
	 *
	 * @param state The status of the ship
	 */
	public void setState(ShipState state) {
		if (this.state.ordinal() < state.ordinal()) {
			this.state = state;
		}
	}

	/**
	 * Get a status of the ship
	 * @return
	 */
	public ShipState getState() {
		return this.state;
	}

	/**
	 * Handling of a strike on a ship
	 */
	public void hit(FieldCell cell) {
		for (Coordinate coord : coordinates) {
			if (coord.getY() == cell.getCoordinate().getY() && coord.getX() == cell.getCoordinate().getX()) {
				health--;
				if (health == 0) {
					setState(ShipState.DESTROYED);
				}
				break;
			}
		}
	}

}
