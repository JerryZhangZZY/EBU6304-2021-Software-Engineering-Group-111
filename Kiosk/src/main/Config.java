package main;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * configuration file accessor
 *
 * @author zaitian
 *
 * @version 1.0
 * @date 4/8
 *
 * @version 1.1
 * create dir and file if not exits
 * @date 4/13
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
                cfg = new FileInputStream("./Conf/Config.yaml");
            } catch (FileNotFoundException e1) {
                try {
                    if (!Files.exists(Path.of("./Conf"))){
                        Files.createDirectory(Path.of("./Conf"));
                    }
                    Files.writeString(Path.of("./Conf/Config.yaml"),
                            "language: English\nidCardDrive: E\n");
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
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
}
