package dbWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class opens a given json file write in with a string.
 *
 * @author Zhang Zeyu
 * @date 2022/3/21
 * @version 1.0
 */

public abstract class JsonWriter {

    /**
     * @param path path of json file
     * @param jsonStr string to write in
     */
    public static void write(String path, String jsonStr) {
        try {
            FileWriter fw = new FileWriter(path);
            PrintWriter out = new PrintWriter(fw);
            out.write(jsonStr);
            fw.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
