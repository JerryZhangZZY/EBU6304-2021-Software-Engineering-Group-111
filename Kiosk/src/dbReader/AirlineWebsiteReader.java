package dbReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public abstract class AirlineWebsiteReader {
    public static int indexOf(String airline) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/airline_website.json"));
        JSONArray arr = obj.getJSONArray("airline_website");
        int index = 0;
        while(!arr.getJSONObject(index).getString("airline").equals(airline)) { index++; }
        return index;
    }

    public static String getWebsite(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/airline_website.json"));
        JSONArray arr = obj.getJSONArray("airline_website");
        return arr.getJSONObject(index).getString("website");
    }
}
