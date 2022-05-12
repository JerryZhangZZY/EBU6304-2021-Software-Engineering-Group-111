package main;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;

/**
 * configuration file accessor
 *
 * @author zaitian
 * @author Zhang Zeyu
 * @author Ni Ruijie
 *
 * @version 4.4
 * Rename timers and improve reformat.
 * @date 2022/5/12
 *
 * @version 4.3
 * New method modifyConfigTemp().
 * @date 2022/5/12
 *
 * @version 4.2
 * Added config inspection
 * @date 5/10
 *
 * @version 4.1
 * Adjust attributes for auto dark theme function.
 * @date 2022/5/7
 *
 * @version 4.0
 * Boost performance of generating default config.
 * Add a new attribute.
 * @date 2022/5/6
 *
 * @version 3.4
 * Added limit time config, and allowed managers to enable/disable timers.
 * @date 2022/4/27
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
     * block the software if config fails
     */
    private static void checkConfig() {
        int inspection = inspectConfig();
        if (inspection > 0) {
            System.out.println("Unable to load configuration: " +
                    "Invalid entry found in conf/config.yaml with index " + inspection);
            System.exit(inspection);
        }
    }

    /**
     * inspect config file and try to find a defect
     * @return the first entry that fails, starts with 1
     */
    private static int inspectConfig() {
        Object[] values = config.values().toArray();
        for (int i = 1; i <= values.length; i++) {
            String value = values[i-1].toString();
            switch (i) {
                case 1 -> { //language
                    if (!value.equals("English"))
                        return i;
                }
                case 2 -> { //drive
                    if(     //not windows
                            (!String.valueOf(value.charAt(0)).matches("[A-Z]")
                                    || !value.endsWith("://")
                                    || !(value.length()==4))
                            && !value.startsWith("/volumes"))   //nor mac
                        return i;
                }
                case 3 -> { //airport name
                    if (value.isBlank())
                        return i;
                }
                case 4, 12 -> { //enable check-in leading time,
                    if (!value.equals("false") && !value.equals("true"))
                        return i;
                }
                case 5, 6, 8 -> {  //start, stop leading time, timer exit time
                    if (!value.matches("[0-9]+"))
                        return i;
                }
                case 7, 9 -> {  //timer, backstage
                    if (!value.equals("enable") && !value.equals("disable"))
                        return i;
                }
                case 10, 11 -> { //theme, dark theme
                    boolean match = false;
                    for (String s : new String[]{"Cobalt", "Onyx",
                            "Tiber", "Anchor","Almond", "Tomato", "Maroon"}
                    ) {
                        if (value.equals(s)) {
                            match = true;
                            break;
                        }
                    }
                    if (!match)
                        return i;
                }
                case 13 -> {    //anime speed
                    if (!value.equals("-1") && !(value.matches("[12345]")))
                        return i;
                }
            }
        }
        return 0;
    }

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
        checkConfig();
    }

    /**
     * temporarily modify configs in hashmap
     */
    public static void modifyConfigTemp(String name, String value) {
        config.replace(name, value);
    }

    /**
     * reset configuration to default
     */
    private static void establishConfig() throws IOException {
        String strDefaultConfig = """
                # USER CONFIGURATION FILE
                            
                # --------------< GENERAL SETTINGS >--------------
                                    
                # Supported language: English.
                language: English
                                    
                # USB drive that holds ID document
                # Recommended: E://, F://, G:// (Windows); /volumes/DRIVE NAME/ (Mac OS)
                idCardDrive: F://
                
                # --------------< AIRPORT SETTINGS >--------------
                                    
                # You can set the airport name here.
                airportName: Beijing International Airport
                                    
                # Enable/disable the check-in leading time function.
                enableCheckInLeadingTime: false
                # Check-in starts ... (hours) before departure.
                startCheckInLeadingTime: 24
                # Check-in stops ... (minutes) before departure.
                stopCheckInLeadingTime: 30
                                    
                # --------------< TIMER SETTINGS >--------------
                
                # Enable/disable check-in timer.
                checkinTimer: enable
                # Set the limit time (seconds) for check-in timer
                checkinTimeLimit: 120
                                    
                # Enable/disable idle timer.
                idleTimer: enable
                
                # -------------< APPEARANCE SETTINGS >------------
                                    
                # Select a theme from the theme library.
                theme: Anchor
                
                # Select the dark theme from the theme library.
                darkTheme: Onyx
                # Enable/disable auto dark theme
                enableAutoDarkTheme: true
                                    
                # Animation speed (1-5, -1 to disable), default: 3.
                animationSpeed: 3
                """;
        FileWriter fileWriter = new FileWriter(String.valueOf(filePath), false);
        fileWriter.write(strDefaultConfig);
        fileWriter.close();
    }
}
