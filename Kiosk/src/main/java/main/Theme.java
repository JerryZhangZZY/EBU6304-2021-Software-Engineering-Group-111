package main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dbReader.JsonReader;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class stores the current theme data.
 *
 * @author Zhang Zeyu
 *
 * @version 3.2
 * Add 3 auto-generate colors
 * and modified built-in themes.
 * @date 2022/4/26
 *
 * @version 3.1
 * Auto generate theme.json
 * @date 2022/4/22
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
    private static Color buttonPressedColor;
    private static Color altButtonPressedColor;
    private static Color cardUnavailableColor;

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
    
    public static Color getButtonPressedColor() {
        if (themeColor == null)
            loadColor();
        return buttonPressedColor;
    }

    public static Color getAltButtonPressedColor() {
        if (themeColor == null)
            loadColor();
        return altButtonPressedColor;
    }

    public static Color getCardUnavailableColor() {
        if (themeColor == null)
            loadColor();
        return cardUnavailableColor;
    }

    public static void loadColor() {
        File file = new File("conf/theme.json");
        if (!file.exists()) {
            try {
                file.createNewFile();
                String strDefaultTheme = """
                        {
                           "Cobalt": {
                             "themeColor": "11,89,167",
                             "backgroundColor": "244,244,244",
                             "cardColor": "255,255,255",
                             "mainFontColor": "64,64,64",
                             "secondaryFontColor": "128,128,128",
                             "tertiaryFontColor": "192,192,192",
                             "minorFontColor": "255,255,255"
                           },
                           "Onyx": {
                             "themeColor": "13,17,23",
                             "backgroundColor": "22,27,34",
                             "cardColor": "18,23,29",
                             "mainFontColor": "201,209,217",
                             "secondaryFontColor": "137,145,153",
                             "tertiaryFontColor": "73,81,89",
                             "minorFontColor": "201,209,217"
                           },
                           "Tiber": {
                             "themeColor": "24,77,71",
                             "backgroundColor": "240,250,244",
                             "cardColor": "250,255,254",
                             "mainFontColor": "44,64,44",
                             "secondaryFontColor": "108,128,108",
                             "tertiaryFontColor": "172,192,172",
                             "minorFontColor": "250,255,254"
                           },
                           "Anchor": {
                             "themeColor": "72,88,104",
                             "backgroundColor": "240,242,244",
                             "cardColor": "221,226,231",
                             "mainFontColor": "47,47,79",
                             "secondaryFontColor": "86,105,124",
                             "tertiaryFontColor": "164,178,192",
                             "minorFontColor": "244,244,255"
                           },
                           "Almond": {
                             "themeColor": "255,160,122",
                             "backgroundColor": "253,245,230",
                             "cardColor": "255,250,240",
                             "mainFontColor": "88,44,12",
                             "secondaryFontColor": "132,66,18",
                             "tertiaryFontColor": "245,186,96",
                             "minorFontColor": "255,250,240"
                           },
                           "Tomato": {
                             "themeColor": "245,89,61",
                             "backgroundColor": "255,252,251",
                             "cardColor": "253,220,215",
                             "mainFontColor": "84,15,4",
                             "secondaryFontColor": "200,37,10",
                             "tertiaryFontColor": "247,129,109",
                             "minorFontColor": "255,255,255"
                           },
                           "Maroon": {
                             "themeColor": "165,42,42",
                             "backgroundColor": "244,244,244",
                             "cardColor": "255,255,255",
                             "mainFontColor": "64,10,10",
                             "secondaryFontColor": "128,128,128",
                             "tertiaryFontColor": "192,192,192",
                             "minorFontColor": "255,255,255"
                           }
                        }""";
                FileWriter fileWriter = new FileWriter(file.getPath(), false);
                fileWriter.write(strDefaultTheme);
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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

            /*
            Generate button pressed color
             */
            int rPressed, gPressed, bPressed;
            /*
            card lighter than background
             */
            if (cardColor.getRed() + cardColor.getGreen() + cardColor.getBlue()
                    > backgroundColor.getRed() + backgroundColor.getGreen() + backgroundColor.getBlue()) {
                rPressed = (int) (themeColor.getRed() * 0.8);
                gPressed = (int) (themeColor.getGreen() * 0.8);
                bPressed = (int) (themeColor.getBlue() * 0.8);
            }
            /*
            card darker than background
             */
            else {
                rPressed = (themeColor.getRed() + backgroundColor.getRed()) / 2;
                gPressed = (themeColor.getGreen() + backgroundColor.getGreen()) / 2;
                bPressed = (themeColor.getBlue() + backgroundColor.getBlue()) / 2;
            }
            buttonPressedColor = new Color(rPressed, gPressed, bPressed);

            /*
            Generate alter button pressed color
             */
            int rAltPressed, gAltPressed, bAltPressed;
            /*
            light background
             */
            if (backgroundColor.getRed() + backgroundColor.getGreen() + backgroundColor.getBlue() > 384) {
                rAltPressed = (int) (backgroundColor.getRed() * 0.8);
                gAltPressed = (int) (backgroundColor.getGreen() * 0.8);
                bAltPressed = (int) (backgroundColor.getBlue() * 0.8);
            }
            /*
            dark background
             */
            else {
                rAltPressed = (themeColor.getRed() + backgroundColor.getRed()) / 2;
                gAltPressed = (themeColor.getGreen() + backgroundColor.getGreen()) / 2;
                bAltPressed = (themeColor.getBlue() + backgroundColor.getBlue()) / 2;
            }
            altButtonPressedColor = new Color(rAltPressed, gAltPressed, bAltPressed);

            /*
            Generate unavailable card color
             */
            int rGray, gGray, bGray;
            /*
            light background
             */
            if (backgroundColor.getRed() + backgroundColor.getGreen() + backgroundColor.getBlue() > 384) {
                rGray = Math.max(Theme.getBackgroundColor().getRed() - 5, 0);
                gGray = Math.max(Theme.getBackgroundColor().getGreen() - 5, 0);
                bGray = Math.max(Theme.getBackgroundColor().getBlue() - 5, 0);
            }
            /*
            dark background
             */
            else {
                rGray = Math.min(Theme.getBackgroundColor().getRed() + 5, 255);
                gGray = Math.min(Theme.getBackgroundColor().getGreen() + 5, 255);
                bGray = Math.min(Theme.getBackgroundColor().getBlue() + 5, 255);
            }
            cardUnavailableColor = new Color(rGray, gGray, bGray);

        } catch (Exception e) {
            System.out.println("Theme load failed!");
            themeColor = new Color(11, 89, 167);
            backgroundColor = new Color(244, 244, 244);
            cardColor = new Color(255, 255, 255);
            mainFontColor = new Color(64, 64, 64);
            secondaryFontColor = new Color(128, 128, 128);
            tertiaryFontColor = new Color(192, 192, 192);
            minorFontColor = new Color(255, 255, 255);
            buttonPressedColor = new Color(8, 71, 133);
            altButtonPressedColor = new Color(195, 195, 195);
            cardUnavailableColor = new Color(239, 239,239);
        }
    }
}
