package ru.github.vastap;

import org.joda.time.DateTime;

/**
 * Task01 - Ручная компиляция через javac и запуск через java
 * <p>Описание в README.md, Lesson1.
 * Чтобы посмотреть, где лежат либы, скачанные Maven'ом: mvn help:evaluate -Dexpression=settings.localRepository
 * Стоит помнить, что Maven идеей может использоваться другой, с другим репозиторием.
 */
public class Task01 {

	public static void main(String args[]) {
		DateTime dt = new DateTime();
		System.out.println("Hello, world. " + dt);
	}

}
