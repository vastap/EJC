package ru.github.vastap.model;

import ru.github.vastap.behavior.FlyWithWings;

/**
 * Летающая утка. Летает на ближние и дальние расстояния
 */
public class FlyingDuck extends Duck {

	public FlyingDuck() {
		super(new FlyWithWings());
	}


	@Override
	public int fly() {
		return this.getSpeed();
	}
}
