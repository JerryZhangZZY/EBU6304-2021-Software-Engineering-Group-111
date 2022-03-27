package BackendSystemDB;

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
 */
import com.csvreader.CsvWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public abstract class DBwrite {
    public static void writeline(String[] info) {
        File f = new File("DB/backend.csv");
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
    public static void  changeline(String[] info){
        try {
            DBreader temp = new DBreader();
            ArrayList<String[]> data = new ArrayList<>();
            ArrayList<String[]> data_new = new ArrayList<>();
            data = temp.getDataBase();
            for(int i = 0; i<data.size();i++){
                if(data.get(i)[0].equals(info[0])){
                    data_new.add(info);
                }
                else
                    data_new.add(data.get(i));
            }
            CsvWriter csvWriter = new CsvWriter("DB/backend.csv");
            for (String[] strings : data_new)
                csvWriter.writeRecord(strings);
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
