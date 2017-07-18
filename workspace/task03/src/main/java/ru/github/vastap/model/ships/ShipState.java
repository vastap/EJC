package ru.github.vastap.model.ships;

/**
 * Describes state of a ship
 */
public enum ShipState {
	/** Not exists */
	NONE,
	/** Ship was created, but not placed on a battle field */
	CREATED,
	/** Ship was placed on a battlefield */
	PLACED,
	/** Ship was destroyed */
	DESTROYED
}