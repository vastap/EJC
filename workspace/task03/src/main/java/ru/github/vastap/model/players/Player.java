package ru.github.vastap.model.players;

import ru.github.vastap.model.field.BattleField;
import ru.github.vastap.model.field.Coordinate;
import ru.github.vastap.model.ships.Ship;
import ru.github.vastap.model.logic.ShipPlacementLogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract player, which play the Battle ship game.
 */
public abstract class Player {
	private int id;
	private boolean isRenderNeeded = false;
	private BattleField enemyField;
	private ShipPlacementLogic strategy;
	private List<Ship> ships = new ArrayList<>();

	/**
	 * Create new player with ID.
	 *
	 * @param id The player id
	 */
	public Player(int id) {
		this.id = id;
	}

	/**
	 * Get the player ID.
	 *
	 * @return The Id of the player
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Set the enemy field for the player.
	 *
	 * @param field The field of an enemy
	 */
	public void linkEnemyField(BattleField field) {
		this.enemyField = field;
	}

	/**
	 * Get the field of an enemy.
	 * @return
	 */
	protected BattleField getEnemyField() {
		return this.enemyField;
	}

	/**
	 * Place ships of the player on the own field.
	 */
	public void placeShips(BattleField field) {
		Iterator<Ship> shipIterator = getShipsIterator();
		strategy.placeShips(field, shipIterator);
	}

	/**
	 * Add a new ship to the player.
	 *
	 * @param newShip New ship for adding to the player
	 */
	public void addShip(Ship newShip) {
		ships.add(newShip);
	}

	/**
	 * Get Iterator for getting a ship from a ship list.
	 *
	 * @return Iterator for iterating over all player ships
	 */
	protected Iterator<Ship> getShipsIterator() {
		//Simple iterator prevents ship remove
		return ships.iterator();
	}

	/**
	 * Set the ship placement strategy for this player
	 *
	 * @param strategy Strategy with description of ship placement logic
	 */
	public void setStrategy(ShipPlacementLogic strategy) {
		this.strategy = strategy;
	}

	/**
	 * Request a coordinate for the next strike
	 *
	 * @return Coordinate for the next strike
	 */
	public abstract Coordinate getCoordinateChoice();

	/**
	 * Requirement for field rendering for this player.
	 * For example, computer does not need a field rendering.
	 *
	 * @return True if player need a field rendering
	 */
	public boolean isRenderNeeded() {
		return this.isRenderNeeded;
	}

	/**
	 * Set requirement of field rendering
	 *
	 * @param isRenderNeeded True if player need a field rendering.
	 */
	public void setRenderNeeded(boolean isRenderNeeded) {
		this.isRenderNeeded = isRenderNeeded;
	}

	/**
	 * Finish this game (close resources, etc)
	 */
	public abstract void finishTheGame();

}
