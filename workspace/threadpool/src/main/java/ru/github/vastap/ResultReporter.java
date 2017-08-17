package ru.github.vastap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Reporter for saving result data
 */
public class ResultReporter {

	/**
	 * Save Data to File
	 *
	 * @param data
	 * @return File which stores result data
	 */
	public static File saveData(List<LogEntry> data) {
		File outputFile = null;
		try {
			outputFile = File.createTempFile("result-", ".csv");
		} catch (IOException e) {
			throw new IllegalStateException("Can't create file for result set");
		}

		StringBuilder outputTextBuilder = new StringBuilder();
		outputTextBuilder.append("url,time,user\n");
		for (LogEntry entry : data) {
			outputTextBuilder.append(entry.getUrl()).append(",");
			outputTextBuilder.append(entry.getTime()).append(",");
			outputTextBuilder.append(entry.getUser()).append("\n");
		}

		try (BufferedWriter output = new BufferedWriter(new FileWriter(outputFile))) {
			output.write(outputTextBuilder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return outputFile;
	}

}
