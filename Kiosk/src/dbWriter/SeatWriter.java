package dbWriter;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a tool that can write seat status quickly.
 *
 * @author Zhang Zeyu
 * @date 2022/3/27
 * @version 1.0
 */

public abstract class SeatWriter {
    public static void setSeat(String idFlight, int row, int column) {
        String path = "DB/flightSeatStatus/" + idFlight + ".csv";
        try {
            CsvReader csvReader = new CsvReader(path);
            CsvWriter csvWriter = new CsvWriter(path);
            List<String[]> list = new ArrayList<>();

            for (int currentRow = 1; csvReader.readRecord(); currentRow++) {
                if (currentRow == row) {
                    String[] record = csvReader.getValues();
                    record[column-1] = "X";
                    list.add(record);
                } else {
                    list.add(csvReader.getValues());
                }
            }
            csvReader.close();

            for (String[] strings : list)
                csvWriter.writeRecord(strings);

            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
