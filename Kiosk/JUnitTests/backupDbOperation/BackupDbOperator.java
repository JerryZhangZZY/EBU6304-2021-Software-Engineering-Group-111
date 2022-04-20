package backupDbOperation;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * This class contains methods to recover master database
 * as well as update backup database.
 *
 * @author Zhang Zeyu
 * @author Ni Ruijie
 *
 * @date 2022/4/20
 * @version 3.0
 * Logic optimized: create folder DBbackup when pulling, delete the folder when pushing.
 *
 * @date 2022/4/7
 * @version 2.0
 */

public abstract class BackupDbOperator {

    static final String masterRoot = "DB\\";
    static final String backupRoot = "DBbackup\\";
    static final String configPath = "conf\\";
    static final String uncopy = configPath + "uncopy.txt";

    /**
     * Update backup database from master database.
     */
    public static void pull() {
        try {
            if (!Files.exists(Path.of(configPath))){
                Files.createDirectory(Path.of(configPath));
            }
            Files.writeString(Path.of(uncopy), ".md");
            if (!Files.exists(Path.of(backupRoot))){
                Files.createDirectory(Path.of(backupRoot));
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        Process p;
        String cmds = "xcopy " + masterRoot + "*.* " + backupRoot + " /s/y/exclude:" + configPath + "uncopy.txt";
        try {
            p = Runtime.getRuntime().exec(cmds);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while((line = br.readLine()) != null) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Recover master database from backup database.
     */
    public static void push() {
        Process p,d;
        String cmds = "xcopy " + backupRoot + "*.* " + masterRoot + " /s/y/exclude:" + configPath + "uncopy.txt";
        String deletecmd = "cmd /c rd "+ backupRoot +" /s/q";
        try {
            p = Runtime.getRuntime().exec(cmds);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while((line = br.readLine()) != null) {
            }
            d = Runtime.getRuntime().exec(deletecmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
