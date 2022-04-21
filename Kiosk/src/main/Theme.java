package main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dbReader.JsonReader;

import java.awt.*;

/**
 * This class stores the current theme data.
 *
 * @author Zhang Zeyu
 *
 * @version 3.0
 * @date 2022/4/21
 */

public abstract class Theme {

    private static Color themeColor;
    private static Color backgroundColor;
    private static Color cardColor;
    private static Color mainFontColor;
    private static Color secondaryFontColor;
    private static Color tertiaryFontColor;
    private static Color minorFontColor;

    public static Color getThemeColor() {
        if (themeColor == null)
            loadColor();
        return themeColor;
    }
    public static Color getBackgroundColor() {
        if (themeColor == null)
            loadColor();
        return backgroundColor;
    }
    public static Color getCardColor() {
        if (themeColor == null)
            loadColor();
        return cardColor;
    }
    public static Color getMainFontColor() {
        if (themeColor == null)
            loadColor();
        return mainFontColor;
    }
    public static Color getSecondaryFontColor() {
        if (themeColor == null)
            loadColor();
        return secondaryFontColor;
    }
    public static Color getTertiaryFontColor() {
        if (themeColor == null)
            loadColor();
        return tertiaryFontColor;
    }
    public static Color getMinorFontColor() {
        if (themeColor == null)
            loadColor();
        return minorFontColor;
    }

    public static void loadColor() {
        try {
            JSONObject obj = JSON.parseObject(JsonReader.read("conf/theme.json"));
            JSONObject objTheme = obj.getJSONObject(Config.readConfig("theme"));

            int r, g, b;

            r = Integer.parseInt(objTheme.getString("themeColor").split(",")[0]);
            g = Integer.parseInt(objTheme.getString("themeColor").split(",")[1]);
            b = Integer.parseInt(objTheme.getString("themeColor").split(",")[2]);
            themeColor = new Color(r, g, b);

            r = Integer.parseInt(objTheme.getString("backgroundColor").split(",")[0]);
            g = Integer.parseInt(objTheme.getString("backgroundColor").split(",")[1]);
            b = Integer.parseInt(objTheme.getString("backgroundColor").split(",")[2]);
            backgroundColor = new Color(r, g, b);

            r = Integer.parseInt(objTheme.getString("cardColor").split(",")[0]);
            g = Integer.parseInt(objTheme.getString("cardColor").split(",")[1]);
            b = Integer.parseInt(objTheme.getString("cardColor").split(",")[2]);
            cardColor = new Color(r, g, b);

            r = Integer.parseInt(objTheme.getString("mainFontColor").split(",")[0]);
            g = Integer.parseInt(objTheme.getString("mainFontColor").split(",")[1]);
            b = Integer.parseInt(objTheme.getString("mainFontColor").split(",")[2]);
            mainFontColor = new Color(r, g, b);

            r = Integer.parseInt(objTheme.getString("secondaryFontColor").split(",")[0]);
            g = Integer.parseInt(objTheme.getString("secondaryFontColor").split(",")[1]);
            b = Integer.parseInt(objTheme.getString("secondaryFontColor").split(",")[2]);
            secondaryFontColor = new Color(r, g, b);

            r = Integer.parseInt(objTheme.getString("tertiaryFontColor").split(",")[0]);
            g = Integer.parseInt(objTheme.getString("tertiaryFontColor").split(",")[1]);
            b = Integer.parseInt(objTheme.getString("tertiaryFontColor").split(",")[2]);
            tertiaryFontColor = new Color(r, g, b);

            r = Integer.parseInt(objTheme.getString("minorFontColor").split(",")[0]);
            g = Integer.parseInt(objTheme.getString("minorFontColor").split(",")[1]);
            b = Integer.parseInt(objTheme.getString("minorFontColor").split(",")[2]);
            minorFontColor = new Color(r, g, b);
        } catch (Exception e) {
            System.out.println("Theme load failed!");
            themeColor = new Color(11, 89, 167);
            backgroundColor = new Color(244, 244, 244);
            cardColor = new Color(255, 255, 255);
            mainFontColor = new Color(64, 64, 64);
            secondaryFontColor = new Color(128, 128, 128);
            tertiaryFontColor = new Color(192, 192, 192);
            minorFontColor = new Color(255, 255, 255);
        }
    }
}
