package dbReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * This class is a tool that can help you search and get values of plane info quickly.
 *
 * @author Zhang Zeyu
 * @author Liang Zhehao
 *
 * @version 4.0
 * add getPlaneAge
 * @date 2022/5/9
 *
 * @version 3.0
 * add getSeatModel
 * @date 2022/4/20
 *
 * @version 1.0
 * @date 2022/3/20
 */

public abstract class PlaneReader {

    /**
     * Get index of given idPlane
     * @param idPlane primary key
     * @return index of that plane
     */
    public static int indexOf(int idPlane) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/plane.json"));
        JSONArray arr = obj.getJSONArray("plane");
        int index = 0;
        while(arr.getJSONObject(index).getInteger("idPlane") != idPlane) { index++; }
        return index;
    }

    public static String getModel(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/plane.json"));
        JSONArray arr = obj.getJSONArray("plane");
        return arr.getJSONObject(index).getString("model");
    }

    public static int getCapacity(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/plane.json"));
        JSONArray arr = obj.getJSONArray("plane");
        return arr.getJSONObject(index).getInteger("capacity");
    }

    public static String getAirline(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/plane.json"));
        JSONArray arr = obj.getJSONArray("plane");
        return arr.getJSONObject(index).getString("airline");
    }

    /**
     * The number of digits indicates the number of seating segments,
     * Each number represents the number of seats in each segment.
     * @param index got from indexOf()
     * @return seat model
     */
    public static int getSeatModel(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/plane.json"));
        JSONArray arr = obj.getJSONArray("plane");
        return arr.getJSONObject(index).getInteger("seatModel");
    }

    /**
     * Get column number
     * @param index got from indexOf()
     * @return column number
     */
    public static String getColumnNum(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/plane.json"));
        JSONArray arr = obj.getJSONArray("plane");
        return arr.getJSONObject(index).getString("columnNum");
    }

    /**
     * Get plane age
     * @param index got from indexOf()
     * @return plane age
     */
    public static int getPlaneAge(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("database/plane.json"));
        JSONArray arr = obj.getJSONArray("plane");
        return arr.getJSONObject(index).getInteger("planeAge");
    }

    public static String getBackrestAngle(int index){
        JSONObject obj = JSON.parseObject(JsonReader.read("database/plane.json"));
        JSONArray arr = obj.getJSONArray("plane");
        return arr.getJSONObject(index).getString("backrestAngle");
    }
}
