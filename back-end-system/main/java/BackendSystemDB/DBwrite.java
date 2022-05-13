package BackendSystemDB;

import com.csvreader.CsvWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * a tool write data to DB
 *
 * @author Wang Chenyu
 * @date 2022/3/25
 * @version 1.0
 *
 * @author Wang Chenyu
 * @date 2022/3/27
 * @version 1.1
 *
 * @author Liang Zhehao
 * @data 2022/3/28
 * @version 1.2
 * Modify changeline function
 */
public abstract class DBwrite {
    public static void writeline(String[] info) {
        File f = new File("database/backend.csv");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
            CsvWriter cwriter = new CsvWriter(writer, ',');
            cwriter.writeRecord(info, false);
            cwriter.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeline(String bookingNum, String idFlight, String seat,
                                  String meal, String mealPre1, String mealPre2, String mealPre3) {
        try {
            DBreader reader = new DBreader();
            CsvWriter csvWriter = new CsvWriter("database/backend_new.csv");

            String[] data;

            for (int i = 0; i < reader.getNumberOfLine(); i++) {
                data = reader.getline(i);
                if (data[3].equals(bookingNum) && data[2].equals(idFlight)) {
                    data[4] = "1";
                    data[5] = seat;
                    data[6] = meal;
                    data[7] = mealPre1;
                    data[8] = mealPre2;
                    data[9] = mealPre3;
                }
                 csvWriter.writeRecord(data);
            }
            csvWriter.close();
            File file = new File("database/backend.csv");
            if (file.isFile() && file.exists()) {
                file.delete();
            } else {
                System.out.println("失败！");
            }
            File oldFile = new File("database/backend_new.csv");
            File newFile = new File("database/backend.csv");
            oldFile.renameTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
