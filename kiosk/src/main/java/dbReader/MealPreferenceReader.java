package dbReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * This class is a tool that can help you search and get values of meal preference info quickly.
 *
 * @author Liang Zhehao
 *
 * @version 3.0
 * @date 4/25
 */
public abstract class MealPreferenceReader {

    /**
     * Get preference food name
     * @param index got from FlightReader.getMealPreference()
     * @return preference food name
     */
    public static String getName(int index) {
        JSONObject jsonObject = JSON.parseObject(JsonReader.read("database/mealPreference.json"));
        JSONArray jsonArray = jsonObject.getJSONArray("meal");
        return jsonArray.getJSONObject(index).getString("name");
    }

    /**
     * Get preference food price
     * @param index got from FlightReader.getMealPreference()
     * @return preference food price
     */
    public static int getPrice(int index) {
        JSONObject jsonObject = JSON.parseObject(JsonReader.read("database/mealPreference.json"));
        JSONArray jsonArray = jsonObject.getJSONArray("meal");
        return jsonArray.getJSONObject(index).getInteger("price");
    }

    /**
     * Get preference food introduction
     * @param index got from FlightReader.getMealPreference()
     * @return preference food introduction
     */
    public static String getIntroduction(int index) {
        JSONObject jsonObject = JSON.parseObject(JsonReader.read("database/mealPreference.json"));
        JSONArray jsonArray = jsonObject.getJSONArray("meal");
        return jsonArray.getJSONObject(index).getString("introduction");
    }
}
