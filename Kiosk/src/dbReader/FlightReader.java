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

    public static int indexOf(String idFlight) {
        JSONObject obj = JSON.parseObject(readJsonFile("DB/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        int index = 0;
        while(!arr.getJSONObject(index).getString("idFlight").equals(idFlight)) { index++; }
        return index;
    }

    public static String getDeparture(int index) {
        JSONObject obj = JSON.parseObject(readJsonFile("DB/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("departure");
    }

    public static String getArrival(int index) {
        JSONObject obj = JSON.parseObject(readJsonFile("DB/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("arrival");
    }

    public static String getGate(int index) {
        JSONObject obj = JSON.parseObject(readJsonFile("DB/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("gate");
    }

    public static String getDepartureTime(int index) {
        JSONObject obj = JSON.parseObject(readJsonFile("DB/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("departureTime");
    }

    public static String getArrivalTime(int index) {
        JSONObject obj = JSON.parseObject(readJsonFile("DB/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("arrivalTime");
    }

    public static String getBoardingTime(int index) {
        JSONObject obj = JSON.parseObject(readJsonFile("DB/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("boardingTime");
    }

    public static int getIdPlane(int index) {
        JSONObject obj = JSON.parseObject(readJsonFile("DB/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getInteger("idPlane");
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
