package commonTests;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тесты для проверки различных утверждений и особенности поведения, которые хотелось бы визуализировать.
 */
public class CommonTest {

	/**
	 * В большинстве случаев, codePoint могут совпадать в code (int представлением char).
	 * Большинство случаев: стандартные символы (кириллица, латинские буквы, стандартные символы).
	 */
	@Test
	public void commonCasesShouldHaveSameValues() {
		//Кириллица
		String cyrillicString = "Йокогама";
		assertEquals(8, cyrillicString.codePoints().count());
		assertEquals(8, cyrillicString.chars().count());
		//Символы латинского алфавита
		String latinString = "Quick";
		assertEquals(5, latinString.codePoints().count());
		assertEquals(5, latinString.chars().count());
		//Составные символы
		String stringWithCompositeSymbol = "ññ";
		assertEquals(3, stringWithCompositeSymbol.codePoints().count());
		assertEquals(3, stringWithCompositeSymbol.chars().count());
		//Особенная писменность других языков
		String hindiString = "प्रोग्रामिंग";
		assertEquals(12, hindiString.codePoints().count());
		assertEquals(12, hindiString.chars().count());
	}

	@Test
	public void emojiHasDifferentValues() {
		String emojiString = "👻";
		//На один emojiString приходится 2 чара (т.е. не влезает в 16 бит)
		assertEquals(1, emojiString.codePoints().count());
		assertEquals(2, emojiString.chars().count());
	}

	@Test
	public void shouldCompareStringWithinStringPool() {
		String literalString = "test";
		String anotherLiteralString = "test";
		//Литеральные строки равны, т.к. это один и тот же объект, одна и та же ссылка в пуле
		assertTrue(literalString == anotherLiteralString);
		//Объект - новый String объект, его в пуле нет
		String constructedString = new String("test");
		//Добавляем строку в пул, получаем reference на тот String, который в пуле
		String pooled = constructedString.intern();

		assertTrue(literalString != constructedString);
		assertTrue(literalString == pooled);
		//intern возвращает новый reference на объект из пула, старый reference по прежнему не в пуле!
		assertTrue(pooled != constructedString);
	}

	@Test
	public void shouldCompareStringAsCharIntValues(){
		String firstString = "Java Core";
		String secondString = "Java Core Epam";
		String thirdString = "Java core Epam";
		assertEquals(-5, firstString.compareTo(secondString));
		assertEquals(-32, secondString.compareTo(thirdString));
	}

}
