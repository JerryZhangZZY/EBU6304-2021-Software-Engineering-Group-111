package dbReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/**
 * This class is a tool that can help you get the website by airline's name quickly.
 *
 * @author Ni Ruijie
 * @date 2022/4/13
 * @version 1.0
 */
public abstract class AirlineWebsiteReader {
    /**
     * Get index of given airline
     * @param airline primary key
     * @return index of that airline
     */
    public static int indexOf(String airline) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/airline_website.json"));
        JSONArray arr = obj.getJSONArray("airline_website");
        int index = 0;
        while(!arr.getJSONObject(index).getString("airline").equals(airline)) { index++; }
        return index;
    }
    /**
     * Get website of the airline
     * @param index
     * @return website link
     */
    public static String getWebsite(int index) {
        JSONObject obj = JSON.parseObject(JsonReader.read("DB/airline_website.json"));
        JSONArray arr = obj.getJSONArray("airline_website");
        return arr.getJSONObject(index).getString("website");
    }
}
