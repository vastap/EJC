package ru.github.vastap.tasks;

import ru.github.vastap.CarrotShop;

/**
 * Rabbit customer, which buy carrots
 */
public class RabbitCustomer implements Runnable {

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Customer was stopped");
			}
			CarrotShop.buy();
		}
	}

}
