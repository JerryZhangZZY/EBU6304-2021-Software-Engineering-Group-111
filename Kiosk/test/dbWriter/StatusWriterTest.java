package dbWriter;

import backupDbOperation.BackupDbOperator;
import dbReader.PassengerFlightReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a unit test of StatusWriter.
 *
 * @author Zhang Zeyu
 *
 * @version 2.0
 * @date 2022/4/8
 */

class StatusWriterTest {

    @AfterEach
    void recoverDb() {
        BackupDbOperator.push();
    }

    @RepeatedTest(3)
    void setTrue() {
        Random random = new Random();
        int randomIndex = random.nextInt(4);
        if (!PassengerFlightReader.getStatus(randomIndex)) {
            StatusWriter.setTrue(randomIndex);
            assertTrue(PassengerFlightReader.getStatus(randomIndex));
        }
    }
}