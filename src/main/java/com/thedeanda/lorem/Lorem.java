package com.thedeanda.lorem;

public interface Lorem {

	public abstract String getCity();

	public abstract String getFirstName();

	public abstract String getFirstNameMale();

	public abstract String getFirstNameFemale();

	public abstract String getLastName();

	public abstract String getName();

	public abstract String getNameMale();

	public abstract String getNameFemale();

	public abstract String getTitle(int count);

	public abstract String getTitle(int min, int max);

	public abstract String getHtmlParagraphs(int min, int max);

	public abstract String getParagraphs(int min, int max);

	public abstract String getWords(int count);

	public abstract String getWords(int min, int max);

	public abstract String getPhone();

	public abstract String getStateAbbr();

	public abstract String getStateFull();

	public abstract String getZipCode();

}