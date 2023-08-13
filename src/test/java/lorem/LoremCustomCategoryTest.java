package lorem;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import com.thedeanda.lorem.exceptions.CategoryNotExist;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

public class LoremCustomCategoryTest {

    private Lorem lorem;

    private final String CATEGORY_VALUE = "nouns";

    @Before
    public void init() {
        Map<String, String> customCategInput = new HashMap<String, String>();
        customCategInput.put(CATEGORY_VALUE, "C:\\dev\\MyStuff\\open-source-contributions\\lorem\\src\\test\\resources\\nouns.txt");
        lorem = new LoremIpsum(customCategInput);
    }

    @Test
    public void testCustomValue() {
        try {
            String val = lorem.getCustomValue(CATEGORY_VALUE);
            assertNotNull(val);
            assertNotEquals("", val.trim());
        }catch (CategoryNotExist ex){

        }
    }

    @Test(expected = CategoryNotExist.class)
    public void testException() throws CategoryNotExist {
        lorem.getCustomValue("countries");
    }

    @Test
    public void testWithSeed() throws CategoryNotExist {
        Map<String, String> customCategInput = new HashMap<String, String>();
        customCategInput.put(CATEGORY_VALUE, "C:\\dev\\MyStuff\\open-source-contributions\\lorem\\src\\test\\resources\\nouns.txt");
        Lorem lorem = new LoremIpsum(new Random(99l), customCategInput);
        assertEquals("development", lorem.getCustomValue(CATEGORY_VALUE));
    }
}
