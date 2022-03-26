package BackendSystemDB;

/**
 * a tool write data to DB
 *
 * @author Wang Chenyu
 * @date 2022/3/25
 * @version 1.0
 */
import com.csvreader.CsvWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

public abstract class DBwrite {
    public static void writeline(String[] info) {
        File f = new File("DB/backend.csv");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
            CsvWriter cwriter = new CsvWriter(writer, ',');
            cwriter.writeRecord(info, false);
            cwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
