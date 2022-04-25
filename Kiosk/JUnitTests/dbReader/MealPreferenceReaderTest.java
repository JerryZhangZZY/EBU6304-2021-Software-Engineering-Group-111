package dbReader;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class MealPreferenceReaderTest {

    @Test
    void getName() {
        assertEquals("Extra", MealPreferenceReader.getName(0));
    }

    @Test
    void getPrice() {
        assertEquals(5, MealPreferenceReader.getPrice(0));
    }

    @Test
    void getIntroduction() {
        assertEquals("Food is not enough? How about some more!", MealPreferenceReader.getIntroduction(0));
    }
}
