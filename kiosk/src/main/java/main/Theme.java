package main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dbReader.JsonReader;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 * This class stores the current theme data.
 *
 * @author Zhang Zeyu
 *
 * @version 5.2
 * Recover 'Anchor' and fix name: 'Platinum'
 * @date 2022/5/30
 *
 * @version 5.1
 * Delete 'Almond' and add 'Platium'.
 * @date 2022/5/20
 *
 * @version 5.0
 * Update theme library.
 * @date 2022/5/13
 *
 * @version 4.0
 * Auto dark theme.
 * @date 2022/5/7
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

    private static boolean isDark = false;

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

    private static Color darkThemeColor;
    private static Color darkBackgroundColor;
    private static Color darkCardColor;
    private static Color darkMainFontColor;
    private static Color darkSecondaryFontColor;
    private static Color darkTertiaryFontColor;
    private static Color darkMinorFontColor;
    private static Color darkButtonPressedColor;
    private static Color darkAltButtonPressedColor;
    private static Color darkCardUnavailableColor;

    public static Color getThemeColor() {
        if (themeColor == null)
            loadColor();
        return isDark ? darkThemeColor : themeColor;
    }
    public static Color getBackgroundColor() {
        if (themeColor == null)
            loadColor();
        return isDark ? darkBackgroundColor : backgroundColor;
    }
    public static Color getCardColor() {
        if (themeColor == null)
            loadColor();
        return isDark ? darkCardColor : cardColor;
    }
    public static Color getMainFontColor() {
        if (themeColor == null)
            loadColor();
        return isDark ? darkMainFontColor : mainFontColor;
    }
    public static Color getSecondaryFontColor() {
        if (themeColor == null)
            loadColor();
        return isDark ? darkSecondaryFontColor : secondaryFontColor;
    }
    public static Color getTertiaryFontColor() {
        if (themeColor == null)
            loadColor();
        return isDark ? darkTertiaryFontColor : tertiaryFontColor;
    }
    public static Color getMinorFontColor() {
        if (themeColor == null)
            loadColor();
        return isDark ? darkMinorFontColor : minorFontColor;
    }
    
    public static Color getButtonPressedColor() {
        if (themeColor == null)
            loadColor();
        return isDark ? darkButtonPressedColor : buttonPressedColor;
    }

    public static Color getAltButtonPressedColor() {
        if (themeColor == null)
            loadColor();
        return isDark ? darkAltButtonPressedColor : altButtonPressedColor;
    }

    public static Color getCardUnavailableColor() {
        if (themeColor == null)
            loadColor();
        return isDark ? darkCardUnavailableColor : cardUnavailableColor;
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
                           "Jungle": {
                             "themeColor": "23,133,105",
                             "backgroundColor": "245,245,245",
                             "cardColor": "255,255,255",
                             "mainFontColor": "51,51,51",
                             "secondaryFontColor": "42,84,71",
                             "tertiaryFontColor": "179,185,192",
                             "minorFontColor": "255,255,255"
                           },
                           "Tyrian": {
                             "themeColor": "95,5,78",
                             "backgroundColor": "238,238,238",
                             "cardColor": "255,255,255",
                             "mainFontColor": "69,69,69",
                             "secondaryFontColor": "95,5,78",
                             "tertiaryFontColor": "192,192,192",
                             "minorFontColor": "255,255,255"
                           },
                           "Platinum": {
                             "themeColor": "36,41,47",
                             "backgroundColor": "243,244,246",
                             "cardColor": "255,255,255",
                             "mainFontColor": "36,41,47",
                             "secondaryFontColor": "87,96,106",
                             "tertiaryFontColor": "196,202,208",
                             "minorFontColor": "255,255,255"
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
                rGray = Math.max(backgroundColor.getRed() - 5, 0);
                gGray = Math.max(backgroundColor.getGreen() - 5, 0);
                bGray = Math.max(backgroundColor.getBlue() - 5, 0);
            }
            /*
            dark background
             */
            else {
                rGray = Math.min(backgroundColor.getRed() + 5, 255);
                gGray = Math.min(backgroundColor.getGreen() + 5, 255);
                bGray = Math.min(backgroundColor.getBlue() + 5, 255);
            }
            cardUnavailableColor = new Color(rGray, gGray, bGray);

            /*
            if enabled auto dark theme
             */
            if (Boolean.parseBoolean(Config.readConfig("enableAutoDarkTheme"))) {
                JSONObject objDarkTheme = obj.getJSONObject(Config.readConfig("darkTheme"));

                r = Integer.parseInt(objDarkTheme.getString("themeColor").split(",")[0]);
                g = Integer.parseInt(objDarkTheme.getString("themeColor").split(",")[1]);
                b = Integer.parseInt(objDarkTheme.getString("themeColor").split(",")[2]);
                darkThemeColor = new Color(r, g, b);

                r = Integer.parseInt(objDarkTheme.getString("backgroundColor").split(",")[0]);
                g = Integer.parseInt(objDarkTheme.getString("backgroundColor").split(",")[1]);
                b = Integer.parseInt(objDarkTheme.getString("backgroundColor").split(",")[2]);
                darkBackgroundColor = new Color(r, g, b);

                r = Integer.parseInt(objDarkTheme.getString("cardColor").split(",")[0]);
                g = Integer.parseInt(objDarkTheme.getString("cardColor").split(",")[1]);
                b = Integer.parseInt(objDarkTheme.getString("cardColor").split(",")[2]);
                darkCardColor = new Color(r, g, b);

                r = Integer.parseInt(objDarkTheme.getString("mainFontColor").split(",")[0]);
                g = Integer.parseInt(objDarkTheme.getString("mainFontColor").split(",")[1]);
                b = Integer.parseInt(objDarkTheme.getString("mainFontColor").split(",")[2]);
                darkMainFontColor = new Color(r, g, b);

                r = Integer.parseInt(objDarkTheme.getString("secondaryFontColor").split(",")[0]);
                g = Integer.parseInt(objDarkTheme.getString("secondaryFontColor").split(",")[1]);
                b = Integer.parseInt(objDarkTheme.getString("secondaryFontColor").split(",")[2]);
                darkSecondaryFontColor = new Color(r, g, b);

                r = Integer.parseInt(objDarkTheme.getString("tertiaryFontColor").split(",")[0]);
                g = Integer.parseInt(objDarkTheme.getString("tertiaryFontColor").split(",")[1]);
                b = Integer.parseInt(objDarkTheme.getString("tertiaryFontColor").split(",")[2]);
                darkTertiaryFontColor = new Color(r, g, b);

                r = Integer.parseInt(objDarkTheme.getString("minorFontColor").split(",")[0]);
                g = Integer.parseInt(objDarkTheme.getString("minorFontColor").split(",")[1]);
                b = Integer.parseInt(objDarkTheme.getString("minorFontColor").split(",")[2]);
                darkMinorFontColor = new Color(r, g, b);

                if (darkCardColor.getRed() + darkCardColor.getGreen() + darkCardColor.getBlue()
                        > darkBackgroundColor.getRed() + darkBackgroundColor.getGreen() + darkBackgroundColor.getBlue()) {
                    rPressed = (int) (darkThemeColor.getRed() * 0.8);
                    gPressed = (int) (darkThemeColor.getGreen() * 0.8);
                    bPressed = (int) (darkThemeColor.getBlue() * 0.8);
                }
                else {
                    rPressed = (darkThemeColor.getRed() + darkBackgroundColor.getRed()) / 2;
                    gPressed = (darkThemeColor.getGreen() + darkBackgroundColor.getGreen()) / 2;
                    bPressed = (darkThemeColor.getBlue() + darkBackgroundColor.getBlue()) / 2;
                }
                darkButtonPressedColor = new Color(rPressed, gPressed, bPressed);

                if (darkBackgroundColor.getRed() + darkBackgroundColor.getGreen() + darkBackgroundColor.getBlue() > 384) {
                    rAltPressed = (int) (darkBackgroundColor.getRed() * 0.8);
                    gAltPressed = (int) (darkBackgroundColor.getGreen() * 0.8);
                    bAltPressed = (int) (darkBackgroundColor.getBlue() * 0.8);
                }
                else {
                    rAltPressed = (darkThemeColor.getRed() + darkBackgroundColor.getRed()) / 2;
                    gAltPressed = (darkThemeColor.getGreen() + darkBackgroundColor.getGreen()) / 2;
                    bAltPressed = (darkThemeColor.getBlue() + darkBackgroundColor.getBlue()) / 2;
                }
                darkAltButtonPressedColor = new Color(rAltPressed, gAltPressed, bAltPressed);

                if (darkBackgroundColor.getRed() + darkBackgroundColor.getGreen() + darkBackgroundColor.getBlue() > 384) {
                    rGray = Math.max(darkBackgroundColor.getRed() - 5, 0);
                    gGray = Math.max(darkBackgroundColor.getGreen() - 5, 0);
                    bGray = Math.max(darkBackgroundColor.getBlue() - 5, 0);
                }
                else {
                    rGray = Math.min(darkBackgroundColor.getRed() + 5, 255);
                    gGray = Math.min(darkBackgroundColor.getGreen() + 5, 255);
                    bGray = Math.min(darkBackgroundColor.getBlue() + 5, 255);
                }
                darkCardUnavailableColor = new Color(rGray, gGray, bGray);
            }
        } catch (Exception e) {
            System.out.println("Theme load failed!");

            /*
            default light theme colors
             */
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

            /*
            default dark theme colors
             */
            darkThemeColor = new Color(13, 17, 23);
            darkBackgroundColor = new Color(22, 27, 34);
            darkCardColor = new Color(18, 23, 29);
            darkMainFontColor = new Color(201, 209, 217);
            darkSecondaryFontColor = new Color(137, 145, 153);
            darkTertiaryFontColor = new Color(73, 81, 89);
            darkMinorFontColor = new Color(201, 209, 217);
            darkButtonPressedColor = new Color(17, 22, 28);
            darkAltButtonPressedColor = new Color(17, 22, 28);
            darkCardUnavailableColor = new Color(27, 32, 39);
        }
    }

    /**
    @return if this function enabled.
     */
    public static boolean autoDarkTheme() {
        boolean enableAutoDarkTheme = Boolean.parseBoolean(Config.readConfig("enableAutoDarkTheme"));
        if (enableAutoDarkTheme) {
            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            /*
            dark theme activates from 19:00 to 5:00 next morning
             */
            isDark = hour >= 19 || hour < 5;
        }
        return enableAutoDarkTheme;
    }
}
