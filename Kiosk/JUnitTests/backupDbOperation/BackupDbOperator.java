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

    /**
     * Update backup database from master database.
     */
    public static void pull() {
        String cmds = "xcopy " + masterRoot + "*.* " + backupRoot + " /s/y";
        try {
            if (!Files.exists(Path.of(backupRoot))){
                Files.createDirectory(Path.of(backupRoot));
            }
            Runtime.getRuntime().exec(cmds);
            Thread.sleep(50);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Recover master database from backup database.
     */
    public static void push() {
        String cmds = "xcopy " + backupRoot + "*.* " + masterRoot + " /s/y";
        String deletecmd = "cmd /c rd "+ backupRoot +" /s/q";
        try {
            Runtime.getRuntime().exec(cmds);
            Thread.sleep(50);
            Runtime.getRuntime().exec(deletecmd);
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
