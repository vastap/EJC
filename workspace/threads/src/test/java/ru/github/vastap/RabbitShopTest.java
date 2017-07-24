package ru.github.vastap;

import org.junit.Test;
import ru.github.vastap.tasks.CarrotSupply;
import ru.github.vastap.tasks.RabbitCustomer;
import static junit.framework.TestCase.assertFalse;

public class RabbitShopTest {

	@Test
	public void shouldByTest() throws InterruptedException {
		ThreadGroup threads = new ThreadGroup("rabbitsFamily");
		Thread rabbitCustomer = new Thread(threads, new RabbitCustomer());
		Thread anotherRabbitCustomer = new Thread(threads, new RabbitCustomer());
		Thread suplier = new Thread(threads, new CarrotSupply());

		rabbitCustomer.start();
		anotherRabbitCustomer.start();
		suplier.start();

		Thread.currentThread().sleep(5000);
		threads.interrupt();
		assertFalse(CarrotShop.getCarrotAvailableCount() < 0);
	}
}
