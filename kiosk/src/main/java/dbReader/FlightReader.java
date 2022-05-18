package dbReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is a tool that can help you search and get values of flight info quickly.
 *
 * @author Zhang Zeyu
 * @author Liang Zhehao
 * @author zaitian
 *
 * @version 3.2
 * add arrival date reader and rename dept date reader
 * @date 5/18
 *
 * @version 3.1
 * Add getFoodName() and getFoodPrice()
 * @date 2022/4/23
 *
 * @version 3.0
 * Add getDepartureDateTime().
 * @date 2022/4/20
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
        JSONObject obj = JSON.parseObject(JsonReader.read("database/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        int index = 0;
        while(!arr.getJSONObject(index).getString("idFlight").equals(idFlight)) { index++; }
        return index;
    }

    public static String getIdFlight(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("idFlight");
    }

    public static String getDeparture(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("departure");
    }

    public static String getArrival(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("arrival");
    }

    public static String getGate(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("gate");
    }

    /**
     * Get departure time
     * @param index got from indexOf()
     * @return only contains time, date is not included
     */
    public static String getDepartureTime(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("departureTime").substring(11);
    }

    /**
     * Get arrival time
     * @param index got from indexOf()
     * @return only contains time, date is not included
     */
    public static String getArrivalTime(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("arrivalTime").substring(11);
    }

    public static String getBoardingTime(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("boardingTime");
    }

    public static int getIdPlane(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getInteger("idPlane");
    }

    /**
     * Get departure date
     * @param index got from indexOf()
     * @return date of departure
     */
    public static String getDepartureDate(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        return arr.getJSONObject(index).getString("departureTime").substring(0, 10);
    }

    /**
     * read the date of arrival
     * @param index got from indexOf()
     * @return date of arrival
     */
    public static String getArrivalDate(int index) {
        JSONObject object = JSON.parseObject(JsonReader.read("database/flight.json"));
        JSONArray array = object.getJSONArray("flight");
        return array.getJSONObject(index).getString("arrivalTime").substring(0, 10);
    }
    /**
     * Get full departure time in Date.
     * @param index got from indexOf()
     * @return departure datetime in Date
     */
    public static Date getDepartureDateTime(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return simpleDateFormat.parse(arr.getJSONObject(index).getString("departureTime"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static int[] getMealPreference(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/flight.json"));
        JSONArray arr = obj.getJSONArray("flight");
        int[] foodIndex = new int[3];
        JSONArray foodArray = arr.getJSONObject(index).getJSONArray("mealPreference");
        for (int i = 0; i < 3; i++) {
            foodIndex[i] = foodArray.getInteger(i);
        }
        return foodIndex;
    }

}
