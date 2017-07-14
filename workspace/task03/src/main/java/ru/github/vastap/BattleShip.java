package ru.github.vastap;

import ru.github.vastap.model.BattleField;
import ru.github.vastap.model.Coordinate;
import ru.github.vastap.model.Player.ComputerPlayer;
import ru.github.vastap.model.Player.HumanPlayer;
import ru.github.vastap.model.Player.Player;
import ru.github.vastap.model.Ship.Ship;
import ru.github.vastap.model.Ship.ShipState;

import java.util.Scanner;

/**
 * Основной класс игры "Морской бой"
 */
public class BattleShip {
	private static final int PLAYERS_COUNT = 2;
	private int currentPlayerCount;
	private Player[] players;
	private BattleField[] battleFields;

	/**
	 * Добавить игрока в игру
	 */
	private void addPlayer(Player player) {
		players[currentPlayerCount] = player;
		battleFields[currentPlayerCount] = new BattleField(10);
		addShipsToPlayer(player);
		currentPlayerCount++;
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
	 * Подготовить данные к началу игры
	 */
	public void prepareForGame() {
		players = new Player[PLAYERS_COUNT];
		battleFields = new BattleField[PLAYERS_COUNT];

		addPlayer(new ComputerPlayer("Компьютер"));
		addPlayer(new HumanPlayer("Игрок"));

		//Просим игроков разместить свои корабли
		for (int i = 0; i < players.length; i++) {
			if (players[i] == null) {
				break;
			}
			players[i].placeShips(battleFields[i]);
		}
	}

	/** Начать игру */
	public void start(){
		Scanner in = new Scanner(System.in);
		System.out.println("Для выхода введите q");
		System.out.println("Введите координаты X и Y через пробел");
		String input;
		while(true){
			battleFields[0].render();
			input = in.nextLine();
			if (input.equals("q")){
				break;
			}
			Coordinate coord = inputParser(input);
			if (coord == null){
				System.out.println("Введены неправильные координаты, попробуйте снова");
			}else{
				processPlayerAction(coord);
			}
		}

		System.out.println("Игра завершена");
	}

	/**
	 * Парсер координаты из полученной от пользователя строки
	 * @param data Введённая пользователем строка
	 * @return Координата ячейки, которую указал пользователь
	 */
	private Coordinate inputParser(String data){
		String[] str = data.split(" ");
		if (str.length != 2){
			return null;
		}
		if (str[0].length()>1 || !Character.isDigit(str[0].charAt(0))){
			return null;
		}
		if (str[1].length()>1 || !Character.isDigit(str[1].charAt(0))){
			return null;
		}
		return new Coordinate(Integer.valueOf(str[0]), Integer.valueOf(str[1]));
	}

	/**
	 * Обработать выполненное пользователем действие
	 * @param coordinate Координата, выбранная игроком
	 */
	private void processPlayerAction(Coordinate coordinate){
		System.out.println(coordinate);
		ShipState state = battleFields[0].getCell(coordinate).processShot();
		if (state != ShipState.NONE) {
			if (state == ShipState.DESTROYED) {
				System.out.println("Убит!");
				//TODO: Доделать Game Over
			}else {
				System.out.println("Ранен!");
			}
		}else{
			System.out.println("Промах!");
		}
	}
}
