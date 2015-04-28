package com.thedeanda.lorem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LoremIpsum implements Lorem {
	private List<String> words = new ArrayList<String>();
	private Random random = null;
	private List<String> maleNames;
	private List<String> femaleNames;
	private List<String> surnames;
	private List<String> firstNames;
	private List<String> stateAbbr;
	private List<String> stateFull;
	private List<String> cities;

	public LoremIpsum() {
		this(new Random());
	}

	public LoremIpsum(Random random) {
		this.random = random;
		readLorem();
		maleNames = readLines("male_names.txt");
		femaleNames = readLines("female_names.txt");
		surnames = readLines("surnames.txt");
		firstNames = new ArrayList<String>();
		firstNames.addAll(maleNames);
		firstNames.addAll(femaleNames);

		cities = readLines("cities.txt");
		stateAbbr = readLines("state_abbr.txt");
		stateFull = readLines("state_full.txt");
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
					.getResourceAsStream(file), "UTF-8"));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getCity()
	 */
	@Override
	public String getCity() {
		return getRandom(cities);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return getRandom(firstNames);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getFirstNameMale()
	 */
	@Override
	public String getFirstNameMale() {
		return getRandom(maleNames);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getFirstNameFemale()
	 */
	@Override
	public String getFirstNameFemale() {
		return getRandom(femaleNames);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getLastName()
	 */
	@Override
	public String getLastName() {
		return getRandom(surnames);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getName()
	 */
	@Override
	public String getName() {
		return getFirstName() + " " + getLastName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getNameMale()
	 */
	@Override
	public String getNameMale() {
		return getFirstNameMale() + " " + getLastName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getNameFemale()
	 */
	@Override
	public String getNameFemale() {
		return getFirstNameFemale() + " " + getLastName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getTitle(int)
	 */
	@Override
	public String getTitle(int count) {
		return getWords(count, count, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getTitle(int, int)
	 */
	@Override
	public String getTitle(int min, int max) {
		return getWords(min, max, true);
	}

	private int getCount(int min, int max) {
		if (min < 0)
			min = 0;
		if (max < min)
			max = min;
		int count = max != min ? random.nextInt(max - min) + min : min;
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getHtmlParagraphs(int, int)
	 */
	@Override
	public String getHtmlParagraphs(int min, int max) {
		int count = getCount(min, max);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append("<p>");
			sb.append(getParagraphs(1, 1));
			sb.append("</p>");
		}
		return sb.toString().trim();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getParagraphs(int, int)
	 */
	@Override
	public String getParagraphs(int min, int max) {
		int count = getCount(min, max);
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < count; j++) {
			int sentences = random.nextInt(5) + 2; // 2 to 6
			for (int i = 0; i < sentences; i++) {
				String first = getWords(1, 1, false);
				first = first.substring(0, 1).toUpperCase()
						+ first.substring(1);
				sb.append(first);

				sb.append(getWords(2, 20, false));
				sb.append(".  ");
			}
			sb.append("\n");
		}
		return sb.toString().trim();
	}

	private String getWords(int min, int max, boolean title) {
		int count = getCount(min, max);
		return getWords(count, title);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getWords(int)
	 */
	@Override
	public String getWords(int count) {
		return getWords(count, count, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getWords(int, int)
	 */
	@Override
	public String getWords(int min, int max) {
		return getWords(min, max, false);
	}

	private String getWords(int count, boolean title) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getPhone()
	 */
	@Override
	public String getPhone() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getStateAbbr()
	 */
	@Override
	public String getStateAbbr() {
		return getRandom(stateAbbr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getStateFull()
	 */
	@Override
	public String getStateFull() {
		return getRandom(stateFull);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getZipCode()
	 */
	@Override
	public String getZipCode() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
}
