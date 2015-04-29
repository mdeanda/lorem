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

	private void testBlank(String s) {
		assertNotNull(s);
		assertNotEquals("", s.trim());
	}

	@Test
	public void testCity() {
		String s = lorem.getCity();
		testBlank(s);
	}

	@Test
	public void testCountry() {
		String s = lorem.getCountry();
		testBlank(s);
	}

	@Test
	public void testEmail() {
		String s = lorem.getEmail();
		testBlank(s);
	}

	@Test
	public void testParagraphs() {
		String p1 = lorem.getParagraphs(3, 5);
		testBlank(p1);

		String p2 = lorem.getParagraphs(6, 8);
		testBlank(p2);

		assertNotEquals(p1, p2);
	}

	@Test
	public void testWords() {
		String words0 = lorem.getWords(2);
		assertNotNull(words0);
		assertNotEquals("", words0.trim());

		String words1 = lorem.getWords(4, 5);
		assertNotNull(words1);
		assertNotEquals("", words1.trim());

		assertNotEquals(words0, words1);
	}
}
