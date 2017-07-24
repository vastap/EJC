package ru.github.vastap.model.players;

import ru.github.vastap.model.field.Coordinate;
import ru.github.vastap.model.field.actions.SearchEnemyAction;
import ru.github.vastap.model.field.cell.EmptyCell;
import ru.github.vastap.model.field.cell.ShipCell;
import ru.github.vastap.model.logic.RandomPlacementLogic;

/**
 * Player, which controlled by a computer
 */
public class ComputerPlayer extends Player {
	private int coordinateX = 0;
	private int coordinateY = 0;

	public ComputerPlayer(int id) {
		super(id);
		setStrategy(new RandomPlacementLogic());
	}

	@Override
	public Coordinate getCoordinateChoice() {
		//Trying to strike near when last strike was succeed
		if (getEnemyField().getLastActionResult() == ActionResult.HIT) {
			SearchEnemyAction action = new SearchEnemyAction(getEnemyField());
			getEnemyField().iterateAround(coordinateX, coordinateY, action);
			return action.getCoordinate();
		}

		// Computer try select column by column
		while (true) {
			if (coordinateY == getEnemyField().getSize()) {
				coordinateY = 0;
				coordinateX++;
			}
			if (getEnemyField().getCell(coordinateX, coordinateY) != null && getEnemyField().getCell(coordinateX, coordinateY).getClass() == EmptyCell.class) {
				continue;
			}
			if (getEnemyField().getCell(coordinateX, coordinateY) != null && getEnemyField().getCell(coordinateX, coordinateY).getClass() == ShipCell.class) {
				if (getEnemyField().getCell(coordinateX, coordinateY).getRenderSymbol() == ShipCell.DESTROYED_SYMBOL) {
					continue;
				}
			}
			break;
		}
		Coordinate coordinate = new Coordinate(coordinateX, coordinateY);
		coordinateY++;
		return coordinate;
	}

	@Override
	public void finishTheGame() {
		//Computer has not resources. Do nothing
	}

}
