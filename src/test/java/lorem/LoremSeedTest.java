package lorem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

public class LoremSeedTest {

	private Lorem lorem;

	@Before
	public void init() {
		lorem = LoremIpsum.getInstance(99l);
	}

	@Test
	public void testSeededPseudoRandom() {
		assertEquals("Atlantis", lorem.getCity());
		assertEquals("ignota mus", lorem.getWords(2));
		assertEquals("Suriname", lorem.getCountry());
		assertEquals("(424) 909-8919", lorem.getPhone());
	}

}
