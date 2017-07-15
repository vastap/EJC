package ru.github.vastap.model.players;

import ru.github.vastap.Utils;
import ru.github.vastap.model.field.Coordinate;
import ru.github.vastap.model.logic.RandomPlacementLogic;

import java.util.Scanner;

/**
 * Игрок, которым управляет человек
 */
public class HumanPlayer extends Player {
	private Scanner in = new Scanner(System.in);

	public HumanPlayer(int id) {
		super(id);
		setStrategy(new RandomPlacementLogic());
		setRenderNeeded(true);
	}

	@Override
	public Coordinate getCoordinateChoice() {
		String input;
		Coordinate coordinate;
		System.out.println("Введите координаты X и Y через пробел. Для выхода введите q");

		while(true){
			input = in.nextLine();
			coordinate = Utils.inputParser(input);
			if (input.equals("q")){
				System.out.println("Игра будет завершена. Жаль с вами расставаться.");
				System.exit(0);
			}
			if (coordinate == null){
				System.out.println("Введены неправильные координаты, попробуйте снова");
				continue;
			}
			break;
		}
		return coordinate;
	}

}