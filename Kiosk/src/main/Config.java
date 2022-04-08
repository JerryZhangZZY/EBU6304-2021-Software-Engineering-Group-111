package main;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;

/**
 * configuration file accessor
 *
 * @author zaitian
 *
 * @version 1.0
 * @date 4/8
 */
public abstract class Config {
    /**
     * retrieve configuration
     * @param name tag name
     * @return tag value
     */
    public static String readConfig(String name) {

        Yaml yaml = new Yaml();
        InputStream cfg = null;
        try {
            cfg = new FileInputStream("Kiosk/config/Config.yaml");
        }
        catch (FileNotFoundException e1) {
            try {
                Files.createFile(Path.of("Kiosk/config/Config.yaml"));
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        LinkedHashMap data = yaml.load(cfg);
        return data.get(name).toString();
    }

    /**
     * reset configuration to default
     */
    public static void reset(){

    }

    /**
     * modify configuration file
     * @param name tag name
     * @param value tag value
     */
    public static void writeConfig(String name, String value){

    }
//    public static void main(String[] args) throws FileNotFoundException {
//        System.out.println((loadConfig("idCardDrive")));
//    }
}
