package dbReader;

import java.io.*;

/**
 * This class opens a given json file and converts into string.
 *
 * @author Zhang Zeyu
 * @date 2022/3/20
 * @version 1.0
 */

public abstract class JsonReader {
    public static String read(String path) {
        String jsonStr = "";
        try {
            File jsonFile = new File(path);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
