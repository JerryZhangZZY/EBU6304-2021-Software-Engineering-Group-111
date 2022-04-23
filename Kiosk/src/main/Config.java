package main;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * configuration file accessor
 *
 * @author zaitian
 * @author Zhang Zeyu
 *
 * @version 3.3
 * Add animation speed config.
 * @date 2022/4/24
 *
 * @version 3.2
 * Automatically call loadConfig().
 * @date 2022/4/20
 *
 * @version 3.1
 * Add 3 leading time configs.
 * @date 2022/4/20
 *
 * @version 3.0
 * redesign code structure and function call
 * @date 4/19
 *
 * @version 2.1
 * create dir and file if not exits
 * @date 4/13
 *
 * @version 2.0
 * @date 4/8
 */
public abstract class Config {

    final static String dirName = "conf";
    final static String fileName = dirName + "/config.yaml";
    final static Path dirPath = Path.of(dirName);
    final static Path filePath = Path.of(fileName);

    static Yaml yaml = new Yaml();
    static LinkedHashMap config = null;
    /**
     * retrieve configuration
     * @param name tag name
     * @return tag value
     */
    public static String readConfig(String name) {
        if (config == null) {
            loadConfig();
        }
        return config.get(name).toString();
    }
    /**
     * read file to config variable
     */
    public static void loadConfig() {
        InputStream cfg = null;
        while(cfg == null) {
            try {
                cfg = new FileInputStream(fileName);
            } catch (FileNotFoundException e1) {
                try {
                    if (!Files.exists(dirPath)){
                        Files.createDirectory(dirPath);
                    }
                    establishConfig();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        config = yaml.load(cfg);
        try {
            cfg.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * reset configuration to default
     */
    public static void establishConfig() throws IOException{
        Files.writeString(filePath, "# USER CONFIGURATION FILE");

        addComment("--------------< GENERAL SETTINGS >--------------");

        addComment("Supported language: English.");
        addConfig("language", "English");

        addComment("USB drive that holds ID document, recommended: E, F, G.");
        addConfig("idCardDrive", "F");

        addComment("--------------< AIRPORT SETTINGS >--------------");

        addComment("You can set the airport name here.");
        addConfig("airportName", "Beijing International Airport");

        addComment("Enable the check-in leading time function.");
        addConfig("enableCheckInLeadingTime", "false");

        addComment("Check-in starts ... (hours) before departure.");
        addConfig("startCheckInLeadingTime", "24");

        addComment("Check-in stops ... (minutes) before departure.");
        addConfig("stopCheckInLeadingTime", "30");

        addComment("-------------< APPEARANCE SETTINGS >------------");

        addComment("Please select a theme from the theme library.");
        addConfig("theme", "Tiber");

        addComment("Animation speed (1-5, -1 to disable), default: 3.");
        addConfig("animationSpeed", "3");
    }
    /**
     * modify configuration file
     * @param name tag name
     * @param value tag value
     */
    public static void addConfig(String name, String value){
        try {
            List<String> oldConf = Files.readAllLines(filePath);
            String newConf = "";
            for (String s : oldConf) {
                if (!s.startsWith(name)) {                 //if duplicate
                    newConf = newConf.concat(s + "\n");    //discard old
                }
            }
            newConf = newConf.concat(name + ": " + value + "\n");
            Files.writeString(filePath, newConf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addComment(String comment){
        try {
            List<String> oldConf = Files.readAllLines(filePath);
            String newConf = "";
            for (String s : oldConf) {
                newConf = newConf.concat(s + "\n");
            }
            newConf = newConf.concat("\n# " + comment + "\n");
            Files.writeString(filePath, newConf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
