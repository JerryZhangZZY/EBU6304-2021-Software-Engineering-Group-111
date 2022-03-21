package dbWriter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import dbReader.JsonReader;

/**
 * This class is a tool that can set current check-in information to database.
 *
 * @author Zhang Zeyu
 * @date 2022/3/21
 * @version 1.0
 */

public abstract class CurrentWriter {
    public static void setIdPassengerFlight(int idPassengerFlight) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/currentCheckIn.json"), Feature.OrderedField);
        obj.put("idPassenger", idPassengerFlight);
        String jsonStr = obj.toJSONString();
        JsonWriter.write("DB/currentCheckIn.json", jsonStr);
    }

    public static void setSeat(String seat, int seatPre) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/currentCheckIn.json"), Feature.OrderedField);
        obj.put("seat", seat);
        obj.put("seatPre", seatPre);
        String jsonStr = obj.toJSONString();
        JsonWriter.write("DB/currentCheckIn.json", jsonStr);
    }

    public static void setMeal(int meal, boolean mealPre1, boolean mealPre2, boolean mealPre3) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/currentCheckIn.json"), Feature.OrderedField);
        obj.put("meal", meal);
        obj.put("mealPre1", mealPre1);
        obj.put("mealPre2", mealPre2);
        obj.put("mealPre3", mealPre3);
        String jsonStr = obj.toJSONString();
        JsonWriter.write("DB/currentCheckIn.json", jsonStr);
    }

    public static void clear() {
        JsonWriter.write("DB/currentCheckIn.json",
                "{\n" +
                "  \"idPassengerFlight\": null,\n" +
                "  \"seat\": null,\n" +
                "  \"seatPre\": null,\n" +
                "  \"meal\": null,\n" +
                "  \"mealPre1\": null,\n" +
                "  \"mealPre2\": null,\n" +
                "  \"mealPre3\": null\n" +
                "}");
    }
}
