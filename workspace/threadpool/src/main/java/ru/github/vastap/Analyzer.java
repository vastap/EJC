package ru.github.vastap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * CSV files analyzer
 */
public class Analyzer {
	private final int THREADS_COUNT = 10;
	private final ExecutorService threadPool;
	private final List<Future<List<LogEntry>>> futures = new ArrayList<>();

	/**
	 * Create new analyzer with thread pool under the hood
	 */
	public Analyzer() {
		threadPool = Executors.newFixedThreadPool(THREADS_COUNT);
	}

	/**
	 * Analyze a single file
	 *
	 * @param file
	 */
	public void analyze(File file) {
		if (file.getName().endsWith(".csv")) {
			futures.add(threadPool.submit(new ParseTask(file)));
		} else {
			System.err.println("Can't analyze not a csv file: " + file.getAbsolutePath());
		}
	}

	/**
	 * Analyze a batch of files
	 *
	 * @param files
	 */
	public void analyze(File[] files) {
		for (File file : files) {
			analyze(file);
		}
	}

	/**
	 * Get Result for analyzed data
	 *
	 * @return
	 */
	public List<LogEntry> getResult() {
		List<LogEntry> entries = new ArrayList<>();
		// Collect all data in one List
		for (Future<List<LogEntry>> future : futures) {
			try {
				entries.addAll(future.get());
			} catch (InterruptedException e) {
				throw new IllegalStateException("Getting of log entries was interrupted", e);
			} catch (ExecutionException e) {
				throw new IllegalStateException("Can't get parsed results", e);
			}
		}

		LogEntry[] array = new LogEntry[entries.size()];
		entries.toArray(array);
		for (int i = 0; i < array.length; i++) {
			for (int z = i + 1; z < array.length; z++) {
				if (array[i] == null || array[z] == null) {
					continue;
				}
				//Delete dublicates and summarize time
				if (array[i].getUser().equals(array[z].getUser()) && array[i].getUrl().equals(array[z].getUrl())) {
					array[i].setTime(array[i].getTime() + array[z].getTime());
					array[z] = null;
				}
			}
		}

		List<LogEntry> list = Arrays.asList(array);
		list.sort(new Comparator<LogEntry>() {
			@Override
			public int compare(LogEntry entry1, LogEntry entry2) {
				if (entry1 == null) {
					return -1;
				}
				if (entry2 == null) {
					return 1;
				}
				return entry1.getUser().compareTo(entry2.getUser());
			}
		});
		return list;
	}

	/**
	 * Task for getting log entries from the input file
	 */
	private class ParseTask implements Callable<List<LogEntry>> {
		private File file;

		public ParseTask(File file) {
			this.file = file;
		}

		@Override
		public List<LogEntry> call() throws Exception {
			List<LogEntry> entiries = new ArrayList<>();
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				reader.readLine();
				String line;
				while ((line = reader.readLine()) != null) {
					String[] stringData = line.split(",");
					if (stringData.length != 4) {
						System.err.println("File line has a wrong format and will be skipping. ");
					}

					LogEntry entry = new LogEntry();
					entry.setId(stringData[0]);
					entry.setUrl(stringData[1]);
					entry.setTime(Long.valueOf(stringData[2]));
					entry.setUser(stringData[3]);
					entiries.add(entry);
				}
			} catch (IOException e) {
				throw new IllegalStateException("Error while reading the file " + file.getAbsolutePath());
			}
			return entiries;
		}
	}
}
