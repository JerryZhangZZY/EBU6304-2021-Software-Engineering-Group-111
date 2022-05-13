package BSAdministratorDB;
/**
 * a tool to read administrator database
 *
 * @author Wang Chenyu
 * @date 2022/3/26
 * @version 1.0
 */

import com.csvreader.CsvReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class AdministratorReader {
    String path = "database/backend_administrator.csv";
    CsvReader csvReaderDB;
    private String account;
    public AdministratorReader(String account){
        this.account = account;
    }
    private ArrayList<String[]> getDatabase() throws IOException {
        csvReaderDB = new CsvReader(path,',', Charset.forName("UTF-8"));
        ArrayList<String[]> data = new ArrayList<String[]>();
        String[] record;
        while (csvReaderDB.readRecord()){
            record = csvReaderDB.getRawRecord().split(",");
            data.add(record);
        }
        return data;
    }
    public String[] getPassword() throws IOException {
        String[] record = new String[3];
        ArrayList<String[]> data = this.getDatabase();
        for(int i=0;i< data.size();i++){
            if(account.equals(data.get(i)[0])){
                record = data.get(i);
            }
        }
        return record;
    }
    public boolean exsist() throws IOException {
        ArrayList<String[]> data = this.getDatabase();
        for(int i=0;i< data.size();i++){
            if(account.equals(data.get(i)[0])){
                return true;
            }
        }
        return false;
    }
}
