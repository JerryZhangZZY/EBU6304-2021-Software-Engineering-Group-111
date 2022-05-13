package dbReader;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a unit test of SeatReader.
 *
 * @author Zhang Zeyu
 *
 * @version 3.0
 * Sync to new SeatReader.
 * @date 2022/4/19
 *
 * @version 2.0
 * @date 2022/4/8
 */

class SeatReaderTest {

    @Test
    void getSeat() {
        assertEquals("[true, true, false, true, true, true]", Arrays.toString(new SeatReader("AC0001").getSeat(1)));
        assertEquals("[true, true, true, true, true, true]", Arrays.toString(new SeatReader("CA0001").getSeat(2)));
        assertEquals("[false, false, false, true, true, true, true, true, true, true, true]", Arrays.toString(new SeatReader("LH7718").getSeat(4)));
    }
}