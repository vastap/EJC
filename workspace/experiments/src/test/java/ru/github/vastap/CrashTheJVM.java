package ru.github.vastap;

import org.junit.Ignore;
import org.junit.Test;
import sun.misc.Unsafe;
import java.lang.reflect.Field;

/**
 * Test of JVM crash behavior to distinguish a JVM error and JVM crash
 */
public class CrashTheJVM {
	private static final Unsafe UNSAFE = createUnsafe();

	private static Unsafe createUnsafe() {
		try {
			Field uf = Unsafe.class.getDeclaredField("theUnsafe");
			uf.setAccessible(true);
			return (Unsafe) uf.get(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ignored test which crash the JVM.
	 * <p>Unchecked exceptions don't crash the JVM.
	 * Even for OutOfMemory JVM has a sufficient reserve of memory for error handling.
	 * For making dump we must have running process.
	 * We can't get dump from code the due to restriction on required library rt.jar
	 */
	@Ignore
	@Test
	public void shouldCrashTheJVM() {
		UNSAFE.getByte(0);
	}

}
