package com.thedeanda.lorem;

import java.util.Date;

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
 */
public interface Lorem {

	public String getCity();

	public String getCountry();

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

	public String getUrl();

	public String getWords(int count);

	public String getWords(int min, int max);

	public String getPhone();

	public String getStateAbbr();

	public String getStateFull();

	public String getZipCode();

	public Date getFutureDate();

	public Date getDate();

	public Date getPastDate();

}