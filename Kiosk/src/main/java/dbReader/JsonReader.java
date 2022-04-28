package dbReader;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * This class opens a given json file and converts into string.
 *
 * @author Zhang Zeyu
 * @date 2022/3/20
 * @version 1.0
 */

public abstract class JsonReader {
    public static String read(String path) {
        try {
            File jsonFile = new File(path);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
            int ch = 0;
            StringBuilder sb = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
