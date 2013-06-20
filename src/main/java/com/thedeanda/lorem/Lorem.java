package com.thedeanda.lorem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Lorem {
	private static Lorem instance = new Lorem();
	private List<String> words = new ArrayList<String>();
	private Random random = new Random();
	private List<String> maleNames;
	private List<String> femaleNames;
	private List<String> surnames;
	private List<String> firstNames;

	public static String getPhone() {
		return instance._getPhone();
	}

	public static String getFirstName() {
		return instance.getRandom(instance.firstNames);
	}

	public static String getFirstNameMale() {
		return instance.getRandom(instance.maleNames);
	}

	public static String getFirstNameFemale() {
		return instance.getRandom(instance.femaleNames);
	}

	public static String getLastName() {
		return instance.getRandom(instance.surnames);
	}

	public static String getName() {
		return getFirstName() + " " + getLastName();
	}

	public static String getNameMale() {
		return getFirstNameMale() + " " + getLastName();
	}

	public static String getNameFemale() {
		return getFirstNameFemale() + " " + getLastName();
	}

	public static String getTitle(int count) {
		return instance._getWords(count, count, true);
	}

	public static String getTitle(int min, int max) {
		return instance._getWords(min, max, true);
	}

	public static String getWords(int count) {
		return instance._getWords(count, count, false);
	}

	public static String getWords(int min, int max) {
		return instance._getWords(min, max, false);
	}

	public static String getHtmlParagraphs(int min, int max) {
		return instance._getHtmlParagraphs(min, max);
	}

	public static String getParagraphs(int min, int max) {
		return instance._getParagraphs(min, max);
	}

	private Lorem() {
		readLorem();
		maleNames = readLines("male_names.txt");
		femaleNames = readLines("female_names.txt");
		surnames = readLines("surnames.txt");
		firstNames = new ArrayList<String>();
		firstNames.addAll(maleNames);
		firstNames.addAll(femaleNames);
	}

	private void readLorem() {
		StringBuilder lines = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(getClass()
					.getResourceAsStream("lorem.txt")));
			String line;
			while ((line = br.readLine()) != null) {
				lines.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		String[] w = lines.toString().split("\\s");
		// use set here to remove duplicates
		Set<String> words = new HashSet<String>();
		for (String word : w) {
			word = word.replaceAll(",", "");
			word = word.replaceAll("\\.", "");
			word = word.toLowerCase();
			words.add(word);
		}
		this.words.addAll(words);
	}

	private List<String> readLines(String file) {
		List<String> ret = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(getClass()
					.getResourceAsStream(file)));
			String line;
			while ((line = br.readLine()) != null) {
				ret.add(line.trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

	private int getCount(int min, int max) {
		if (min < 0)
			min = 0;
		if (max < min)
			max = min;
		int count = max != min ? random.nextInt(max - min) + min : min;
		return count;
	}

	private String _getHtmlParagraphs(int min, int max) {
		int count = getCount(min, max);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append("<p>");
			sb.append(_getParagraphs(1, 1));
			sb.append("</p>");
		}
		return sb.toString().trim();
	}

	private String _getParagraphs(int min, int max) {
		int count = getCount(min, max);
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < count; j++) {
			int sentences = random.nextInt(5) + 2; // 2 to 6
			for (int i = 0; i < sentences; i++) {
				String first = _getWords(1, 1, false);
				first = first.substring(0, 1).toUpperCase()
						+ first.substring(1);
				sb.append(first);

				sb.append(_getWords(2, 20, false));
				sb.append(".  ");
			}
			sb.append("\n");
		}
		return sb.toString().trim();
	}

	private String _getWords(int min, int max, boolean title) {
		int count = getCount(min, max);
		return instance._getWords(count, title);
	}

	private String _getWords(int count, boolean title) {
		StringBuilder sb = new StringBuilder();
		int size = words.size();
		int wordCount = 0;
		while (wordCount < count) {
			String word = words.get(random.nextInt(size));
			if (title) {
				if (wordCount == 0 || word.length() > 3) {
					word = word.substring(0, 1).toUpperCase()
							+ word.substring(1);
				}
			}
			sb.append(word);
			sb.append(" ");
			wordCount++;
		}
		return sb.toString().trim();
	}

	private String getRandom(List<String> list) {
		int size = list.size();
		return list.get(random.nextInt(size));
	}

	private String _getPhone() {
		StringBuilder sb = new StringBuilder();

		sb.append("(");
		sb.append(random.nextInt(9) + 1);
		for (int i = 0; i < 2; i++) {
			sb.append(random.nextInt(10));
		}
		sb.append(") ");

		sb.append(random.nextInt(9) + 1);
		for (int i = 0; i < 2; i++) {
			sb.append(random.nextInt(10));
		}
		sb.append("-");

		for (int i = 0; i < 4; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
}
