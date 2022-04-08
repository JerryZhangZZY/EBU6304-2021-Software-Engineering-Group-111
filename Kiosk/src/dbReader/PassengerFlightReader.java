package dbReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * This class is a tool that can help you search and get values of passenger's flight info quickly.
 *
 * @author Zhang Zeyu
 * @author Ni Ruijie
 *
 * @version 2.0
 * Improve booking number validation judgement logic.
 * @date 2022/4/8
 *
 * @version 1.3
 * Add method getPassengerFlight_index.
 * @date 2022/3/27
 *
 * @version 1.2
 * Add new getters.
 * @date 2022/3/25
 *
 * @version 1.1
 * Add a new getter.
 * @date 2022/3/22
 *
 * @version 1.0
 * @date 2022/3/20
 */

public abstract class PassengerFlightReader {

    /**
     * Get index of given idPassengerFlight.
     * @param idPassengerFlight primary key
     * @return index of that passengerFlight
     */
    public static int indexOf(int idPassengerFlight) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/passengerFlight.json"));
        JSONArray arr = obj.getJSONArray("passengerFlight");
        int index = 0;
        while(arr.getJSONObject(index).getInteger("idPassengerFlight") != idPassengerFlight) { index++; }
        return index;
    }

    public static String getIdPassenger(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/passengerFlight.json"));
        JSONArray arr = obj.getJSONArray("passengerFlight");
        return arr.getJSONObject(index).getString("idPassenger");
    }

    public static String getIdFlight(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/passengerFlight.json"));
        JSONArray arr = obj.getJSONArray("passengerFlight");
        return arr.getJSONObject(index).getString("idFlight");
    }

    public static String getBookingNum(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/passengerFlight.json"));
        JSONArray arr = obj.getJSONArray("passengerFlight");
        return arr.getJSONObject(index).getString("bookingNum");
    }

    public static int getCarryon(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/passengerFlight.json"));
        JSONArray arr = obj.getJSONArray("passengerFlight");
        return arr.getJSONObject(index).getInteger("carryon");
    }

    public static int getCheckin(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/passengerFlight.json"));
        JSONArray arr = obj.getJSONArray("passengerFlight");
        return arr.getJSONObject(index).getInteger("checkin");
    }

    public static int getBagDropCounter(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/passengerFlight.json"));
        JSONArray arr = obj.getJSONArray("passengerFlight");
        return arr.getJSONObject(index).getInteger("bagDropCounter");
    }

    public static boolean getStatus(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/passengerFlight.json"));
        JSONArray arr = obj.getJSONArray("passengerFlight");
        return arr.getJSONObject(index).getBoolean("status");
    }

    /**
     * Get a list of idFlight of a given bookingNum.
     * @param bookingNum the booking number of that passenger
     * @return a list that contains flight ids that bookingNum matches
     */
    public static List<String> getIdFlightByBookingNum(String bookingNum) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/passengerFlight.json"), Feature.OrderedField);
        JSONArray arr = obj.getJSONArray("passengerFlight");
        List<String> list = new ArrayList<>();
        for(int index = 0; index < arr.size(); index++) {
            if(arr.getJSONObject(index).getString("bookingNum").equals(bookingNum) && !arr.getJSONObject(index).getBoolean("status"))
                list.add(arr.getJSONObject(index).getString("idFlight"));
        }
        return list;
    }

    /**
     * Get PassengerFlight_index of given bookingNum and idFlight.
     * @param bookingNum the booking number of that passenger
     * @param idFlight the flight ID of the passenger
     * @return index of passengerFlight
     */
    public static int getPassengerFlight_index(String bookingNum, String idFlight) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/passengerFlight.json"), Feature.OrderedField);
        JSONArray arr = obj.getJSONArray("passengerFlight");
        int index = 0;
        while(!(arr.getJSONObject(index).getString("bookingNum").equals(bookingNum)&&arr.getJSONObject(index).getString("idFlight").equals(idFlight))) { index++; }
        return index;
    }

    /**
     * Get a list of bookNum of a given bookingNum.
     * @param idPassenger the id of that passenger
     * @return a linked hash set that contains book numbers that passenger matches
     */
    public static LinkedHashSet<String> getBookingNumByPassengerId(String idPassenger) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/passengerFlight.json"), Feature.OrderedField);
        JSONArray arr = obj.getJSONArray("passengerFlight");
        LinkedHashSet<String> set = new LinkedHashSet<>();
        for(int index = 0; index < arr.size(); index++) {
            if(arr.getJSONObject(index).getString("idPassenger").equals(idPassenger) && !arr.getJSONObject(index).getBoolean("status"))
                set.add(arr.getJSONObject(index).getString("bookingNum"));
        }
        return set;
    }

    /**
     * Check whether the given bookingNum is valid (exist and status = false).
     * @param bookingNum booking number
     * @return validation
     */
    public static Boolean bookingValid(String bookingNum) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/passengerFlight.json"), Feature.OrderedField);
        JSONArray arr = obj.getJSONArray("passengerFlight");
        for(int index = 0; index < arr.size(); index++) {
            if(arr.getJSONObject(index).getString("bookingNum").equals(bookingNum))
                return true;
        }
        return false;
    }

    public static String getPassengerNameByBookingNum(String bookingNum) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/passengerFlight.json"), Feature.OrderedField);
        JSONArray arr = obj.getJSONArray("passengerFlight");
        int index = 0;
        while(!arr.getJSONObject(index).getString("bookingNum").equals(bookingNum)) { index++; }
        return PassengerReader.getSurname(PassengerReader.indexOf(PassengerFlightReader.getIdPassenger(index)));
    }
}
