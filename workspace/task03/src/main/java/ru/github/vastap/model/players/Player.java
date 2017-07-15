package ru.github.vastap.model.players;

import ru.github.vastap.model.field.BattleField;
import ru.github.vastap.model.field.Coordinate;
import ru.github.vastap.model.ships.Ship;
import ru.github.vastap.model.logic.ShipPlacementLogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Игрок, участвующий в игре "Морской Бой"
 */
public abstract class Player {
	private int id;
	private boolean isRenderNeeded = false;
	private ActionResult lastShotStatus;
	private BattleField enemyField;
	private ShipPlacementLogic strategy;
	private List<Ship> ships = new ArrayList<>();

	/**
	 * Создание игрока с присваиванием идентификатора
	 * @param id Идентификатор игрока
	 */
	public Player(int id){
		this.id = id;
	}

	/**
	 * Получить идентификатор игрока
	 * @return ID игрока
	 */
	public int getId(){
		return this.id;
	}

	/**
	 * Указать игроку ссылку на поле врага
	 * @param field Поле врага
	 */
	public void linkEnemyField(BattleField field){
		this.enemyField = field;
	}

	/**
	 * Получить ссылку на поле врага
	 * @return Поле врага
	 */
	protected BattleField getEnemyField(){
		return this.enemyField;
	}


	/** Разместить корабли */
	public void placeShips(BattleField field){
		Iterator<Ship> shipIterator = getShipsIterator();
		strategy.placeShips(field, shipIterator);
	}

	/**
	 * Добавление корабля для игрока
	 * @param newShip Корабль
	 */
	public void addShip(Ship newShip){
		ships.add(newShip);
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
	 * Указать стратегию размещения кораблей
	 * @param strategy Стратегия, по которой будут расположены корабли
	 */
	public void setStrategy(ShipPlacementLogic strategy){
		this.strategy = strategy;
	}

	/**
	 * Получить от игрока выбранную координату удара
	 * @return выбранная для удара координата
	 */
	public abstract Coordinate getCoordinateChoice();

	/**
	 * Нужно ли для текущего игрока отрисовывать поле
	 * @return
	 */
	public boolean isRenderNeeded(){
		return this.isRenderNeeded;
	}

	/**
	 * Указать, нужно ли отрисовывать поле для текущего игрока
	 * @param isRenderNeeded
	 */
	public void setRenderNeeded(boolean isRenderNeeded){
		this.isRenderNeeded = isRenderNeeded;
	}

	/**
	 * Уведомить игрока о том, какой результат его последнего действия
	 * @param actionStatus Статус последнего действия
	 */
	public void setLastShotStatus(ActionResult actionStatus){
		this.lastShotStatus = actionStatus;
	}

}
