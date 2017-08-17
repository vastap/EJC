package ru.github.vastap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Generator for csv files
 */
public class CsvGenerator {
	private static final String[] users = new String[]{"Sam", "Nick", "John", "Mike", "Ron", "Dan", "Jack"};
	private static final String[] sites = new String[]{"http://www.bbc.com/russian/news-", "https://www.youtube.com/watch?v=",
			"https://rsport.ria.ru/chess/20170817/", "http://www.ebay.com/cln/altanbataev0/-/"};
	private final int MIN_LINES = 5;
	private final Random random = new Random();

	/**
	 * Create CSV file with generated content
	 *
	 * @return
	 */
	public File generate() {
		File csvFile = createFile();
		try (BufferedWriter bufWriter = new BufferedWriter(new FileWriter(csvFile))) {
			bufWriter.write(generateContent());
		} catch (IOException e) {
			throw new IllegalStateException("Can't write csv file", e);
		}
		return csvFile;
	}

	/**
	 * Generate a batch of files
	 *
	 * @param count Count of files for generating
	 * @return Array of generated files
	 */
	public File[] generateBatch(int count) {
		File[] generatedBatch = new File[count];
		for (int i = 0; i < count; i++) {
			generatedBatch[i] = generate();
		}
		return generatedBatch;
	}

	/**
	 * Generate ID number for user entry
	 *
	 * @return
	 */
	private String generateId() {
		return String.valueOf(random.nextInt(100));
	}

	/**
	 * Generate content of CSV File
	 *
	 * @return
	 */
	private String generateContent() {
		StringBuilder sb = new StringBuilder();
		sb.append("id,url,time,user").append("\n");
		for (int i = MIN_LINES; i < MIN_LINES + random.nextInt(5); i++) {
			sb.append(generateId()).append(",");
			sb.append(sites[random.nextInt(sites.length)]).append(random.nextInt(100)).append(",");
			sb.append(random.nextInt(60 * 1000)).append(",");
			sb.append(users[random.nextInt(sites.length)]).append(",");
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Create new empty CSV file
	 *
	 * @return
	 */
	private File createFile() {
		File csvFile = null;
		try {
			csvFile = File.createTempFile("log-", ".csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return csvFile;
	}

}
