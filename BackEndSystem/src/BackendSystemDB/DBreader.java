package BackendSystemDB;

import com.csvreader.CsvReader;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * a tool read data from DB
 *
 * @author Wang Chenyu
 * @date 2022/3/25
 * @version 1.0
 *
 * @author Liang Zhehao
 * @date 2022/3/28
 * @version 1.1
 * Modify getNumberOfLine function
 */
public class DBreader {
    private String path = "DB/backend.csv";
    private String idPassenger;
    private CsvReader csvReaderhead;
    private CsvReader csvReaderDB;
    private CsvReader csvReaderNL;
    int row;

    public String[] getheadline() throws IOException {
        try {
            csvReaderhead = new CsvReader(path, ',', Charset.forName("UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        csvReaderhead.readHeaders();
        String[] head = csvReaderhead.getHeaders();
        csvReaderhead.close();
        return head;
    }

    public String[] getline(int temp_row) throws IOException {
        CsvReader csvReaderline = new CsvReader(path, ',', Charset.forName("UTF-8"));
        String[] record;
        for (int count = -1; count < temp_row; count++) {
            try {
                csvReaderline.readRecord();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        record = csvReaderline.getRawRecord().split(",");
        csvReaderline.close();
        return record;
    }

//    public ArrayList<String[]> getDataBase() throws IOException {
//        csvReaderDB = new CsvReader(path, ',', Charset.forName("UTF-8"));
//        ArrayList<String[]> data = new ArrayList<String[]>();
//        String[] head = this.getheadline();
//        data.add(head);
//        csvReaderDB.readRecord();
//        int i = 1;
//        while (csvReaderDB.readRecord()) {
//            String[] temp = this.getline(i);
//            data.add(temp);
//            i++;
//        }
//        csvReaderDB.close();
//        return data;
//    }

//    public ArrayList<String[]> getSpecialAirline(String airline) throws IOException {
//        ArrayList<String[]> data = new ArrayList<String[]>();
//        ArrayList<String[]> database = this.getDataBase();
//        String[] head = this.getheadline();
//        data.add(head);
//        int i = this.getNumberOfLine();
//        for (int j = 1; j < i + 1; j++) {
//            if (airline.equals(database.get(i)[9])) {
//                data.add(database.get(i));
//            }
//        }
//        return data;
//    }

    public int getNumberOfLine() throws IOException {
        csvReaderNL = new CsvReader(path);
        int i = 0;
        while (csvReaderNL.readRecord()) {
            i++;
        }
        csvReaderNL.close();
        return i;
    }
}
