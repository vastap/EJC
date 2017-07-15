package ru.github.vastap.model.ships;

/** Описывает состояние, в котором находится корабль */
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