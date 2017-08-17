package ru.github.vastap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AppTest {
	private File testFile;

	@Before
	public void prepareData() {
		CsvGenerator dataGenerator = new CsvGenerator();
		testFile = dataGenerator.generate();
	}

	@After
	public void clearData() {
		testFile.delete();
	}

	@Test
	public void shouldGenerateCsvFile() throws InterruptedException {
		assertTrue(testFile.exists());
	}

	@Test
	public void shouldReadCsvFile() {
		Analyzer analyzer = new Analyzer();
		analyzer.analyze(testFile);
		List<LogEntry> entries = analyzer.getResult();
		assertTrue(entries.get(0).getUser() != null);
	}

}
