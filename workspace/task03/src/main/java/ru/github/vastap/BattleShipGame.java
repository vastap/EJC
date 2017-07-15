package ru.github.vastap;

import ru.github.vastap.model.field.BattleField;
import ru.github.vastap.model.field.Coordinate;
import ru.github.vastap.model.players.ComputerPlayer;
import ru.github.vastap.model.players.HumanPlayer;
import ru.github.vastap.model.players.Player;
import ru.github.vastap.model.players.ActionResult;
import ru.github.vastap.model.ships.Ship;

/**
 * Основной класс игры "Морской бой"
 */
public class BattleShipGame {
	private static final int NUMBER_OF_PLAYERS = 2;
	private Player[] players = new Player[NUMBER_OF_PLAYERS];
	private BattleField[] battleFields = new BattleField[NUMBER_OF_PLAYERS];

	/**
	 * Генератор ID игроков
	 */
	private static class PlayerIdSequence {
		private static int id = 0;

		public static int getId() {
			id++;
			return id;
		}
	}

	/**
	 * Подготовить данные к началу игры
	 */
	public void prepareForGame() {
		addPlayer(new ComputerPlayer(PlayerIdSequence.getId()));
		addPlayer(new HumanPlayer(PlayerIdSequence.getId()));

		//Делегируем размещение кораблей игрокам
		for (int i = 0; i < players.length; i++) {
			if (players[i] == null) {
				break;
			}
			players[i].placeShips(battleFields[i]);
			System.out.println("Корабли размещены для игрока " + players[i].getId());
		}
	}

	/**
	 * Добавить игрока в игру
	 */
	private void addPlayer(Player player) {
		int index = player.getId() - 1;

		battleFields[index] = new BattleField(Integer.valueOf(Utils.getProperties().getProperty("field.size")));
		players[index] = player;
		players[index].linkEnemyField(battleFields[index]);
		addShipsToPlayer(player);
	}

	/**
	 * Выдать игроку корабли. Игрок должен знать, какие у него есть корабли
	 */
	private void addShipsToPlayer(Player player) {
		for (int size = 4; size > 0; size--) {
			for (int count = 0; count < 5 - size; count++) {
				player.addShip(new Ship(size));
			}
		}
	}


	/**
	 * Начать игру
	 */
	public void start() {
		Coordinate coord;
		boolean shouldSkipNext = false;

		gameLoop:
		while (true) {
			for (Player player : players) {
				if (shouldSkipNext) {
					shouldSkipNext = false;
					continue;
				}
				//Отрисовка поля
				if (player.isRenderNeeded()) {
					Renderer.render(battleFields[getEnemyId(player) - 1], battleFields[(player).getId()-1]);

					//battleFields[getEnemyId(players) - 1].getView();
				}
				coord = player.getCoordinateChoice();
				System.out.flush();
				System.out.printf("Игрок %d выбрал координаты %d:%d \n", player.getId(), coord.getX(), coord.getY());

				battleFields[getEnemyId(player) - 1].processShot(coord);
				if (battleFields[getEnemyId(player) - 1].getLastActionResult() == ActionResult.HIT || battleFields[getEnemyId(player) - 1].getLastActionResult() == ActionResult.DESTROY){
					shouldSkipNext = true;
				}

				if (battleFields[getEnemyId(player) - 1].getShipCellsCount() == 0) {
					System.out.println("Победил игра " + player.getId());
					break gameLoop;
				}
			}
		}
		System.out.println("Игра завершена");
	}

	/**
	 * Получить ID врага для указанного игрока
	 * <p>Враг выбирается как ID следующего игока, выбирается по кругу.
	 */
	private int getEnemyId(Player player) {
		int id = player.getId() + 1;
		if (id == battleFields.length + 1) {
			id = 1;
		}
		return id;
	}

}