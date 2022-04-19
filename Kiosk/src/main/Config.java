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
 *
 * @version 1.1
 * create dir and file if not exits
 * @date 4/13
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
        while(cfg == null) {
            try {
                cfg = new FileInputStream("Conf/Config.yaml.0");
            } catch (FileNotFoundException e1) {
                establishConfig();
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
    public static void establishConfig(){
        try {
            InputStream raw = null;
            while (raw == null) {
                try {
                    raw = new FileInputStream("Conf/Config.yaml");
                } catch (FileNotFoundException e1) {
                    try {
                        if (Files.notExists(Path.of("Conf"))) {
                            Files.createDirectory(Path.of("Conf"));
                        }
                        if (Files.notExists(Path.of("Conf/Config.yaml"))) {
                            Files.writeString(Path.of("Conf/Config.yaml"),
                                    "language: English\nidCardDrive: F\n");
                        }
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            Files.copy(raw, Path.of("Conf/Config.yaml.0"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String str = readConfig("language");
    }
}
