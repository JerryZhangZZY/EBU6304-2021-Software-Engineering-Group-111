package dbReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * This class is a tool that can help you search and get values of passenger info quickly.
 *
 * @author Zhang Zeyu
 * @date 2022/3/20
 * @version 1.0
 */

public abstract class PassengerReader {

    /**
     * Get index of given idPassenger
     * @param idPassenger primary key
     * @return index of that passenger
     */
    public static int indexOf(String idPassenger) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/passenger.json"));
        JSONArray arr = obj.getJSONArray("passenger");
        int index = 0;
        while(!arr.getJSONObject(index).getString("idPassenger").equals(idPassenger)) { index++; }
        return index;
    }

    public static String getSurname(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/passenger.json"));
        JSONArray arr = obj.getJSONArray("passenger");
        return arr.getJSONObject(index).getString("surname");
    }
}
