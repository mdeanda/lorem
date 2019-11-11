package lorem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import java.time.Duration;
import java.time.LocalDateTime;

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
public class LoremTest {
	private static final int LOOP_TIMES = 5000;
	private Lorem lorem;

	@Before
	public void init() {
		lorem = LoremIpsum.getInstance();
	}

	private void notBlank(String s) {
		assertNotNull(s);
		assertNotEquals("", s.trim());
	}

	@Test
	public void testCity() {
		String s = lorem.getCity();
		notBlank(s);
	}

	@Test
	public void testCountry() {
		String s = lorem.getCountry();
		notBlank(s);
	}

	@Test
	public void testEmail() {
		for (int i = 0; i < LOOP_TIMES; i++) {
			String s = lorem.getEmail();
			notBlank(s);
			assertFalse(s, s.contains(" "));
		}
	}

	@Test
	public void testNameMale() {
		String s = lorem.getNameMale();
		notBlank(s);
	}

	@Test
	public void testNameFemale() {
		String s = lorem.getNameFemale();
		notBlank(s);
	}

	@Test
	public void testParagraphs() {
		String p1 = lorem.getParagraphs(3, 5);
		notBlank(p1);

		String p2 = lorem.getParagraphs(6, 8);
		notBlank(p2);

		assertNotEquals(p1, p2);
	}

	@Test
	public void testState() {
		String s = lorem.getStateAbbr();
		notBlank(s);

		s = lorem.getStateFull();
		notBlank(s);
	}

	@Test
	public void testWords() {
		String words0 = lorem.getWords(2);
		assertNotNull(words0);
		assertNotEquals("", words0.trim());

		String words1 = lorem.getWords(4, 5);
		assertNotNull(words1);
		assertNotEquals("", words1.trim());
		String[] wordsplit = words1.split(" ");
		assertNotNull(wordsplit);
		assertTrue(wordsplit.length >= 4);
		assertTrue(wordsplit.length <= 4);

		assertNotEquals(words0, words1);
	}

	@Test
	public void testZip() {
		String s = lorem.getZipCode();
		notBlank(s);
	}

	@Test
	public void testPriorDate() {
		LocalDateTime now = LocalDateTime.now().plusSeconds(1);
		LocalDateTime later = now.minusMinutes(65);
		for (int i=0; i<100; i++) {
			LocalDateTime fd = lorem.getPriorDate(Duration.ofHours(1));
			assertTrue(now.isAfter(fd));
			assertTrue(later.isBefore(fd));
		}
	}

	@Test
	public void testFutureDate() {
		LocalDateTime now = LocalDateTime.now().minusSeconds(1);
		LocalDateTime later = now.plusMinutes(65);
		for (int i=0; i<100; i++) {
			LocalDateTime fd = lorem.getFutureDate(Duration.ofHours(1));
			assertTrue(now.isBefore(fd));
			assertTrue(later.isAfter(fd));
		}
	}

}
