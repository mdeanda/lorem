package com.thedeanda.lorem;

public interface Lorem {

	public String getCity();

	public String getEmail();

	public String getFirstName();

	public String getFirstNameMale();

	public String getFirstNameFemale();

	public String getLastName();

	public String getName();

	public String getNameMale();

	public String getNameFemale();

	public String getTitle(int count);

	public String getTitle(int min, int max);

	public String getHtmlParagraphs(int min, int max);

	public String getParagraphs(int min, int max);

	public String getWords(int count);

	public String getWords(int min, int max);

	public String getPhone();

	public String getStateAbbr();

	public String getStateFull();

	public String getZipCode();

}