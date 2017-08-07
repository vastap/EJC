package ru.github.vastap;

import org.junit.Test;

public class WaitNotifyYieldTest {
	/**
	 * Yield - give way to somebody.
	 * Thread can try to tell Thread sheduler that this thread want to be Runnable, not Running.
	 */
	@Test
	public void shouldYieldToAnotherThread() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; ++i) {
					System.err.println(Thread.currentThread().getName() + " " + i);
					Thread.yield();
				}
			}
		};
		for (int i = 0; i < 4; i++) {
			new Thread(runnable).start();
		}
	}

	/**
	 * Join thread to another thread before this thread end
	 *
	 * @throws InterruptedException
	 */
	@Test
	public void shouldJoinToAThread() throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; ++i) {
					System.err.println(i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		System.err.println("start...");
		thread.start();
		thread.join();
		System.err.println("finish.");
	}

	/**
	 * Threads cooperation example with notifyAll and wait methods
	 *
	 * @throws InterruptedException
	 */
	@Test
	public void shouldNotifyAllThreads() throws InterruptedException {
		final Object key = new Object();

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; ++i) {
					System.err.println(i);
					if (i == 3) {
						// Wakeup all threads for monitor of key object
						synchronized (key) {
							key.notifyAll();
						}
					}
					try {
						Thread.sleep(101);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		System.err.println("start...");
		thread.start();
		// wait release ownership of this monitor and waits until another thread notifies
		synchronized (key) {
			key.wait();
		}
		System.err.println("finish.");
	}

}
