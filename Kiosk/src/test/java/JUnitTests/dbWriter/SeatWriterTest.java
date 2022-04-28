package dbWriter;

import backupDbOperation.BackupDbOperator;
import dbReader.FlightReader;
import dbReader.PlaneReader;
import dbReader.SeatReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * This is a unit test of SeatWriter.
 *
 * @author Zhang Zeyu
 *
 * @version 2.0
 * @date 2022/4/8
 */

class SeatWriterTest {

    @BeforeEach
    void backupDb() {
        BackupDbOperator.pull();
    }

    @AfterEach
    void recoverDb() {
        BackupDbOperator.push();
    }

    @RepeatedTest(10)
    void setSeat() {
        Random random = new Random();
        List<String> idFlights = new ArrayList<>();

        int index = 0;
        try {
            while(true) {
                idFlights.add(FlightReader.getIdFlight(index));
                index++;
            }
        } catch (Exception ignored) {}

        int randomIdFlightIndex = random.nextInt(idFlights.size());
        String randomIdFlight = idFlights.get(randomIdFlightIndex);
        System.out.println("Random idFlight: " + randomIdFlight);

        SeatReader seatReader = new SeatReader(randomIdFlight);

        int randomIdPlane = FlightReader.getIdPlane(FlightReader.indexOf(randomIdFlight));
        System.out.println("Random idPlane: " + randomIdPlane);
        int colNum = seatReader.getSeat(0).length;
        System.out.println("Column number: " + colNum);
        int rowNum = PlaneReader.getCapacity(PlaneReader.indexOf(randomIdPlane)) / colNum;
        System.out.println("Row number: " + rowNum);
        int randomCol = random.nextInt(colNum) + 1;
        System.out.println("Random column: " + randomCol);
        int randomRow = random.nextInt(rowNum) + 1;
        System.out.println("Random row: " + randomRow);

        if (seatReader.getSeat(randomRow)[randomCol - 1]) {
            SeatWriter.setSeat(randomIdFlight, randomRow, randomCol);
            assertFalse(seatReader.getSeat(randomRow)[randomCol - 1]);
        }

        System.out.println();
    }
}