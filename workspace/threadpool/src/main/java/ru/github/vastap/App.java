package ru.github.vastap;

import java.io.File;

/**
 * Program for analyzing csv files with next information: id, url, time, user
 * Create result file without url dublicates, but summarize time, spending in equals url
 */
public class App {

	public static void main(String[] args) {
		CsvGenerator dataGenerator = new CsvGenerator();
		File[] csvFiles = dataGenerator.generateBatch(15);

		Analyzer analyzer = new Analyzer();
		analyzer.analyze(csvFiles);

		File resultFile = ResultReporter.saveData(analyzer.getResult());
		System.out.println(resultFile.getAbsolutePath());
		for (File file : csvFiles) {
			file.delete();
		}
	}

}
