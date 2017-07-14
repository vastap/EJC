package ru.github.vastap.model.Ship;

/** Состояние, в котором может находится корабль */
public enum ShipState {
	/** Не существует */
	NONE,
	/** Корабль создан, но ещё не размещён на поле боя*/
	CREATED,
	/** Корабль размещён на поле боя */
	PLACED,
	/** Корабль уничтожен */
	DESTROYED;
}