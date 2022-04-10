package idCardReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import main.Config;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * This class simulates a card reader.
 *
 * @author Zhang Zeyu
 * @author Wang Zaitian
 *
 * @version 2.0
 * enable choose-drive-by-config
 * @date 2022/4/8
 *
 * @version 1.0
 * @date 2022/3/23
 */

public abstract class IdCardReader {

    //set drive letter
    private static final String driveLetter = Config.readConfig("idCardDrive");

    public static String readId() throws IOException {
        JSONObject obj = JSON.parseObject(IdCardReader.read(driveLetter + "://ID info.json"));
        return obj.getString("id");
    }
    public static String readName() throws IOException {
        JSONObject obj = JSON.parseObject(IdCardReader.read(driveLetter + "://ID info.json"));
        return obj.getString("surname");
    }

    public static String read(String path) throws IOException {
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
    }
}
