package dbWriter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import dbReader.JsonReader;

/**
 * This class is a tool that can set status to true in passengerFlight.
 *
 * @author Zhang Zeyu
 * @date 2022/3/21
 * @version 1.0
 */

public abstract class StatusWriter {
    public static void setTrue(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/passengerFlight.json"), Feature.OrderedField);
        JSONArray arr = obj.getJSONArray("passengerFlight");
        arr.getJSONObject(index).put("status", false);
        obj.put("passengerFlight", arr);
        String jsonStr = obj.toJSONString();
        JsonWriter.write("DB/passengerFlight.json", jsonStr);
    }
}
