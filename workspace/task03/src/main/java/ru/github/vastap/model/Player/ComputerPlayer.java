package ru.github.vastap.model.Player;

import ru.github.vastap.model.BattleField;
import ru.github.vastap.model.Ship.Ship;
import ru.github.vastap.model.Ship.ShipState;
import ru.github.vastap.model.Strategy.RandomPlacementStrategy;

import java.util.Iterator;
import java.util.Random;

public class ComputerPlayer extends Player{

	public ComputerPlayer(String name) {
		super(name);
		setStrategy(new RandomPlacementStrategy());
	}

}
