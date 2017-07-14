package ru.github.vastap.model.Player;

import ru.github.vastap.model.BattleField;
import ru.github.vastap.model.Strategy.RandomPlacementStrategy;

public class HumanPlayer extends Player {

	public HumanPlayer(String name) {
		super(name);
		setStrategy(new RandomPlacementStrategy());
	}

}
