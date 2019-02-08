package com.thedeanda.lorem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Miguel De Anda
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 * @author mdeanda
 * 
 */
public class LoremIpsum implements Lorem {
	/*
	 * this command was useful:
	 * 
	 * cat lorem.txt | sed -e 's/[,;.]//g' | sed -e 's/ /\n/g' | sed -e \
	 * 'y/ABCDEFGHIJKLMNOPQRSTUVWXYZ/abcdefghijklmnopqrstuvwxyz/' | sort | \
	 * uniq > lorem.txt.2
	 */
	private static LoremIpsum instance;

	private List<String> words = new ArrayList<String>();
	private Random random = null;
	private List<String> maleNames;
	private List<String> femaleNames;
	private List<String> surnames;
	private List<String> firstNames;
	private List<String> stateAbbr;
	private List<String> stateFull;
	private List<String> cities;
	private List<String> countries;

	private String[] URL_HOSTS = new String[] { "https://www.google.com/#q=%s",
			"http://www.bing.com/search?q=%s",
			"https://search.yahoo.com/search?p=%s",
			"https://duckduckgo.com/?q=%s" };

	public static LoremIpsum getInstance() {
		if (instance == null) {
			synchronized (LoremIpsum.class) {
				if (instance == null) {
					instance = new LoremIpsum(new Random());
				}
			}
		}
		return instance;
	}

	public LoremIpsum() {
		this(new Random());
	}
	
	public LoremIpsum(Long seed) {
		this(seed == null ? new Random() : new Random(seed));
	}

	public LoremIpsum(Random random) {
		this.random = random;
		
		words = readLines("lorem.txt");
		maleNames = readLines("male_names.txt");
		femaleNames = readLines("female_names.txt");
		surnames = readLines("surnames.txt");
		firstNames = new ArrayList<String>();
		firstNames.addAll(maleNames);
		firstNames.addAll(femaleNames);

		cities = readLines("cities.txt");
		stateAbbr = readLines("state_abbr.txt");
		stateFull = readLines("state_full.txt");
		countries = readLines("countries.txt");
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
	 * @see com.thedeanda.lorem.Lorem#getCountry()
	 */
	@Override
	public String getCountry() {
		return getRandom(countries);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getEmail()
	 */
	@Override
	public String getEmail() {
		StringBuilder sb = new StringBuilder();

		sb.append(getFirstName().toLowerCase());
		sb.append(".");
		sb.append(getLastName().toLowerCase());
		sb.append("@example.com");

		return sb.toString().replace(' ', '.');
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thedeanda.lorem.Lorem#getUrl()
	 */
	@Override
	public String getUrl() {
		StringBuilder sb = new StringBuilder();
		int hostId = random.nextInt(URL_HOSTS.length);
		String host = String.format(URL_HOSTS[hostId], getWords(1));
		sb.append(host);
		return sb.toString();
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

	@Override
	public Date getFutureDate() {
		Date now = new Date();
		long offset = (long) random.nextFloat() * (Long.MAX_VALUE - now.getTime());
		return new Date(now.getTime() + offset);
	}

	@Override
	public Date getDate() {
		return new Date((long) random.nextFloat() * Long.MAX_VALUE);
	}

	@Override
	public Date getPastDate() {
		return new Date((long)(random.nextFloat() * (new Date().getTime())));
	}
}
