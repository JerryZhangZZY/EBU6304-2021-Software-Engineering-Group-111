package dbReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * This class is a tool that can help you search and get values of flight info quickly.
 *
 * @author Zhang Zeyu
 *
 * @version 2.0
 * Add getIdFlight() by index.
 * @date 2022/4/8
 *
 * @version 1.0
 * @date 2022/3/20
 */

public abstract class FlightReader {

    /**
     * Get index of given idFlight
     * @param idFlight primary key
     * @return index of that flight
     */
    public static int indexOf(String idFlight) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        int index = 0;
        while(!arr.getJSONObject(index).getString("idFlight").equals(idFlight)) { index++; }
        return index;
    }

    public static String getIdFlight(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("idFlight");
    }

    public static String getDeparture(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("departure");
    }

    public static String getArrival(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("arrival");
    }

    public static String getGate(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("gate");
    }

    /**
     * Get departure time
     * @param index got from indexOf()
     * @return only contains time, date is not included
     */
    public static String getDepartureTime(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("departureTime").substring(11);
    }

    /**
     * Get arrival time
     * @param index got from indexOf()
     * @return only contains time, date is not included
     */
    public static String getArrivalTime(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("arrivalTime").substring(11);
    }

    public static String getBoardingTime(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("boardingTime");
    }

    public static int getIdPlane(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getInteger("idPlane");
    }

    /**
     * Get departure date
     * @param index got from indexOf()
     * @return date of departure
     */
    public static String getDate(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("departureTime").substring(0, 10);
    }
}
