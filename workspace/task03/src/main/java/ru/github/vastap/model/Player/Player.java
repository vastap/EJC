package ru.github.vastap.model.Player;

import ru.github.vastap.model.BattleField;
import ru.github.vastap.model.Ship.Ship;
import ru.github.vastap.model.Strategy.ShipPlacementStrategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Игрок, участвующий в игре "Морской Бой"
 */
public abstract class Player {
	private String name;
	private List<Ship> ships = new ArrayList<>();
	private ShipPlacementStrategy strategy;
	private int health = 0;

	public Player(String name){
		this.name = name;
	}

	/** Разместить корабли */
	public void placeShips(BattleField field){
		System.out.println(getName()+" размещает корабли");
		Iterator<Ship> shipIterator = getShipsIterator();
		strategy.placeShips(field, shipIterator);
	}

	/**
	 * Добавление корабля для игрока
	 * @param newShip Корабль
	 */
	public void addShip(Ship newShip){
		ships.add(newShip);
		//newShip.
		health++;
	}

	/**
	 * Получить итератор для получения кораблей из списка кораблей пользователя
	 * @return Итератор по кораблям пользователя
	 */
	protected Iterator<Ship> getShipsIterator(){
		//Возвращаем обычный iterator, т.к. не хотим давать возможность удаляь
		return ships.iterator();
	}

	/**
	 * Получить имя игрока
	 * @return
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Указать стратегию размещения кораблей
	 * @param strategy
	 */
	public void setStrategy(ShipPlacementStrategy strategy){
		this.strategy = strategy;
	}

}
