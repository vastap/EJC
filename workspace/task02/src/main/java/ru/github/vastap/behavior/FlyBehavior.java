package ru.github.vastap.behavior;

/**
 * Представляет из себя описание умения полёта.
 * <p>"Не умеет летать" тоже может являться описанием полёта
 */
public interface FlyBehavior {

	/** Полететь */
	public void fly();
}