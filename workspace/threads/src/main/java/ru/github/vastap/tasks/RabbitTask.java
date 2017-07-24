package ru.github.vastap.tasks;

import ru.github.vastap.RabbitApp;

/**
 * Runnable with task for Rabbit to eat.
 */
public class RabbitTask implements Runnable {

	@Override
	public void run() {
		while (RabbitApp.shouldEat) {
			try {
				//Must chew
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (RabbitApp.shouldEat) {
				System.out.println("Rabbit eat");
			}
		}
	}

}
