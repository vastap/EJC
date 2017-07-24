package ru.github.vastap;

import ru.github.vastap.tasks.RabbitTask;

import java.util.Scanner;

/**
 * The main class for rabbit farm
 */
public class RabbitApp {
	public static volatile boolean shouldEat = true;

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		Thread rabbitThread = new Thread(new RabbitTask());
		rabbitThread.start();

		System.out.println("Please, enter something");
		while (!userInput.hasNext()) {
		}
		RabbitApp.shouldEat = false;
		System.out.println("Rabbits can't eat anymore");
	}

}
