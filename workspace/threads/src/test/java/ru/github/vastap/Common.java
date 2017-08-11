package ru.github.vastap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for common threads behavior
 */
public class Common {

	/**
	 * All child threads will be killed at main thread stop
	 */
	@Test(expected = ThreadDeath.class)
	public void shouldExecuteChildProcessWhenMainIsClose() {
		Thread mainThread = Thread.currentThread();
		Thread childThread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		childThread.start();

		// Deprecated Thread stop for killing child thread:
		mainThread.stop();
	}

	/**
	 * Main thread has thread ID = 1
	 */
	@Test
	public void shouldHaveFirstIDonMainThread() {
		assertEquals(1, Thread.currentThread().getId());
	}

	/**
	 * Main thread can be finded by ID = 1
	 */
	@Test
	public void shouldFindMainThread() {
		Thread childThread = new Thread(new Runnable() {
			@Override
			public void run() {
				Thread[] threads = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
				Thread.currentThread().getThreadGroup().enumerate(threads);
				for (Thread thread : threads) {
					if (thread.getId() == 1) {
						System.out.println(Thread.currentThread().getName() + ": his name is " + thread.getName());
						break;
					}
				}
			}
		});
		System.out.println("Group with main: " + Thread.currentThread().getThreadGroup().getName());
		System.out.println("Parent group: " + Thread.currentThread().getThreadGroup().getParent().getName());
		childThread.setName("childThread");
		childThread.start();
		// Waiting until child thread searching for main thread
		try {
			childThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ": my name is " + Thread.currentThread().getName());
	}
}
