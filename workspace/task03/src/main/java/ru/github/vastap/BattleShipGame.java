package ru.github.vastap;

import ru.github.vastap.model.field.BattleField;
import ru.github.vastap.model.field.Coordinate;
import ru.github.vastap.model.players.ComputerPlayer;
import ru.github.vastap.model.players.HumanPlayer;
import ru.github.vastap.model.players.Player;
import ru.github.vastap.model.players.ActionResult;
import ru.github.vastap.model.ships.Ship;

/**
 * The Battle Ship game.
 * <p>Players are place their ships on battle field and trying to destroy all ships of their enemies.
 */
public class BattleShipGame {
	private static final int NUMBER_OF_PLAYERS = 2;
	private Player[] players = new Player[NUMBER_OF_PLAYERS];
	private BattleField[] battleFields = new BattleField[NUMBER_OF_PLAYERS];

	/**
	 * The player ID generator
	 */
	private static class PlayerIdSequence {
		private static int id = 0;

		public static int getId() {
			id++;
			return id;
		}
	}

	/**
	 * Prepare players data for game (ships, fields, etc).
	 */
	public void prepareForGame() {
		addPlayer(new ComputerPlayer(PlayerIdSequence.getId()));
		addPlayer(new HumanPlayer(PlayerIdSequence.getId()));

		//Delegate placing ships to players
		for (int i = 0; i < players.length; i++) {
			if (players[i] == null) {
				break;
			}
			players[i].placeShips(battleFields[i]);
			System.out.println("Ships are placed for player " + players[i].getId());
		}
	}

	/**
	 * Add new player to the game
	 */
	private void addPlayer(Player player) {
		int index = player.getId() - 1;

		battleFields[index] = new BattleField(Integer.valueOf(Utils.getProperties().getProperty("field.size")));
		players[index] = player;
		players[index].linkEnemyField(battleFields[index]);
		addShipsToPlayer(player);
	}

	/**
	 * Add new ship to the player. Player should know about his ship.
	 */
	private void addShipsToPlayer(Player player) {
		for (int size = 4; size > 0; size--) {
			for (int count = 0; count < 5 - size; count++) {
				player.addShip(new Ship(size));
			}
		}
	}

	/**
	 * Start new game
	 */
	public void start() {
		Coordinate coordinate;
		boolean shouldSkipNext = false;

		gameLoop:
		while (true) {
			for (Player player : players) {
				if (shouldSkipNext) {
					shouldSkipNext = false;
					continue;
				}

				if (player.isRenderNeeded()) {
					Renderer.render(battleFields[getEnemyId(player) - 1], battleFields[(player).getId() - 1]);
				}

				coordinate = player.getCoordinateChoice();
				if (coordinate == null) {
					System.out.printf("Player %d left the game \n", player.getId());
					break gameLoop;
				} else {
					System.out.printf("Player %d chose coordinate %d:%d \n", player.getId(), coordinate.getX(), coordinate.getY());
				}

				battleFields[getEnemyId(player) - 1].processShot(coordinate);
				if (battleFields[getEnemyId(player) - 1].getLastActionResult() == ActionResult.HIT || battleFields[getEnemyId(player) - 1].getLastActionResult() == ActionResult.DESTROY) {
					shouldSkipNext = true;
				}
				if (battleFields[getEnemyId(player) - 1].getShipCellsCount() == 0) {
					System.out.println("Winner: Player " + player.getId());
					break gameLoop;
				}
			}
		}

		for (Player player : players) {
			player.finishTheGame();
		}

		System.out.println("The battle is over");
	}

	/**
	 * Get an enemy ID for the player.
	 * <p>Choose an enemy ID in a circle.
	 */
	private int getEnemyId(Player player) {
		int id = player.getId() + 1;
		if (id == battleFields.length + 1) {
			id = 1;
		}
		return id;
	}

}