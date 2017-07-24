package ru.github.vastap.tasks;

import ru.github.vastap.CarrotShop;

/**
 * Increase carrot count in the carrot shop
 */
public class CarrotSupply implements Runnable {

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Supplier was stopped");
			}
			CarrotShop.replenish(5);
		}
	}

}
