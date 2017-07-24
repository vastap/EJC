package ru.github.vastap.model.players;

import ru.github.vastap.Utils;
import ru.github.vastap.model.field.Coordinate;
import ru.github.vastap.model.logic.RandomPlacementLogic;

import java.util.Scanner;

/**
 * Player which controlled by human
 */
public class HumanPlayer extends Player {
	private Scanner userInput = new Scanner(System.in);

	public HumanPlayer(int id) {
		super(id);
		setStrategy(new RandomPlacementLogic());
		setRenderNeeded(true);
	}

	@Override
	public Coordinate getCoordinateChoice() {
		String inputLine;
		Coordinate coordinate;
		System.out.println("Choose coordinate of X and Y with space delimiter. Enter q for exit");

		while(true){
			inputLine = userInput.nextLine();
			coordinate = Utils.inputParser(inputLine);
			if (inputLine.equals("q")){
				System.out.println("The game will be finished. Sorry to part with you.");
				break;
			}
			if (coordinate == null){
				System.out.println("Coordinate string has wrong format. Please try again.");
				continue;
			}
			break;
		}
		return coordinate;
	}

	@Override
	public void finishTheGame() {
		this.userInput.close();
	}


}