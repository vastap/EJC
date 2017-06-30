package ru.github.vastap.model;

import ru.github.vastap.behavior.FlyNoWay;

/**
 * Стационарная утка. Прекрасно может подойти для флюгеля
 */
public class StationaryDuck extends Duck {

	public StationaryDuck() {
		super(new FlyNoWay());
	}

	@Override
	public int fly() {
		//Рожденный флюгелем летать не может
		return 0;
	}
}
