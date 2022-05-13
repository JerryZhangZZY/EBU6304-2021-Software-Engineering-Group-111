package dbReader;

import com.csvreader.CsvReader;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class is a tool that can get seat status quickly.
 *
 * @author Zhang Zeyu
 * @author Liang Zhehao
 *
 * @version 3.1
 * Adapt to new database.
 * @date 2022/4/20
 *
 * @version 3.0
 * Support multiple columns.
 * @date 2022/4/19
 *
 * @version 1.1
 * Performance improved.
 * @date 2022/3/27
 *
 * @version 1.0
 * @date 2022/3/21
 */

public class SeatReader {

    int idPlane;
    String path;
    CsvReader csvReader;

    /**
     * @param idFlight the current flight id
     */
    public SeatReader(String idFlight) {
        path = "database/flightSeatStatus/" + idFlight + ".csv";
    }

    /**
     * Get a row of seat status
     * @param row the row you want to get status of
     * @return an array contains seat status of that row. true: available; false: occupied
     */
    public boolean[] getSeat(int row) {
        try {
            csvReader = new CsvReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int count = 0; count < row; count++) {
            try {
                csvReader.skipRecord();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String[] record = csvReader.getRawRecord().split(",");
        boolean[] seat = new boolean[record.length];
        for(int index = 0; index < record.length; index++)
            seat[index] = record[index].equals("O");
        csvReader.close();
        return seat;
    }
}
