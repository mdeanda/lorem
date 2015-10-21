package lorem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

public class LoremTest {

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
		String s = lorem.getEmail();
		notBlank(s);
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

}
