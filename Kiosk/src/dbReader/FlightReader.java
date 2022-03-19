package dbReader;

/**
 * This class is a tool that can help you search and get values of flight info quickly.
 *
 * @author Zhang Zeyu
 * @date 2022/3/20
 * @version 1.0
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public abstract class FlightReader {

    public int indexOf(String idFlight) {
        JSONObject jobj = JSON.parseObject(readJsonFile("DB/flight.json"));
        JSONArray arr = jobj.getJSONArray("flight");
        int index = 0;
        while(arr.getJSONObject(index).getString("idFlight").equals(idFlight)) { index++; }
        return index;
    }

    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
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
