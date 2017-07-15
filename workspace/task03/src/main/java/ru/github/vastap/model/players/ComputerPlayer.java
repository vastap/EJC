package ru.github.vastap.model.players;

import ru.github.vastap.model.field.Coordinate;
import ru.github.vastap.model.field.actions.SearchEnemyAction;
import ru.github.vastap.model.field.cell.EmptyCell;
import ru.github.vastap.model.field.cell.ShipCell;
import ru.github.vastap.model.logic.RandomPlacementLogic;

/**
 * Игрок, которым управляет компьютер
 */
public class ComputerPlayer extends Player{
	private int xCoord = 0;
	private int yCoord = 0;

	public ComputerPlayer(int id){
		super(id);
		setStrategy(new RandomPlacementLogic());
	}

	@Override
	public Coordinate getCoordinateChoice() {
		//Если узнаёт, что попал в прошлый раз - пытается попасть рядом
		if (getEnemyField().getLastActionResult() == ActionResult.HIT){
			SearchEnemyAction action = new SearchEnemyAction(getEnemyField());
			getEnemyField().iterateAround(xCoord,yCoord,action);
			return action.getCoordinate();
		}
		//Данная реализация компьютера - упорная. Обстреливает столбец за столбцом
		while (true){
			if (yCoord == getEnemyField().getSize()) {
				yCoord=0;
				xCoord++;
			}
			if (getEnemyField().getCell(xCoord, yCoord) != null && getEnemyField().getCell(xCoord, yCoord).getClass() == EmptyCell.class) {
				continue;
			}
			if (getEnemyField().getCell(xCoord, yCoord) != null && getEnemyField().getCell(xCoord, yCoord).getClass() == ShipCell.class) {
				if (getEnemyField().getCell(xCoord, yCoord).getRenderSymbol() == ShipCell.DESTROYED_SYMBOL){
					continue;
				}
			}
			break;
		}
		Coordinate coord = new Coordinate(xCoord, yCoord);
		yCoord++;
		return coord;
	}

}
