package ru.github.vastap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for some experiments with hiding and overriding
 */
public class HidingTest {

	public class Parent {
		public String field = "parent";

		public String getField() {
			return field;
		}

		public String getFieldExt() {
			return field;
		}
	}

	public class Child extends Parent {
		public String field = "child";

		public String getFieldExt() {
			return field;
		}
	}

	@Test
	public void shouldReturnTypeByField() {
		Parent asParent = new Child();
		Child asChild = new Child();
		assertEquals("parent", asParent.field);
		assertEquals("child", asChild.field);
	}

	@Test
	public void shouldReturnTypeFromParent() {
		Parent asParent = new Child();
		Child asChild = new Child();
		assertEquals("parent", asParent.getField());
		assertEquals("parent", asChild.getField());
	}

	@Test
	public void shouldReturnTypeFromObject() {
		Parent asParent = new Child();
		Child asChild = new Child();
		assertEquals("child", asParent.getFieldExt());
		assertEquals("child", asChild.getFieldExt());
	}

}
