package backupDbOperation;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

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

    static String masterRoot;
    static String backupRoot;
    static String os;
    static String cmds;
    static String specialCmd;
    static String deletecmd;

    /**
     * Update backup database from master database.
     */
    public static void pull() {
        getOS();
        if(os.startsWith("Windows")){
            masterRoot = "DB\\";
            backupRoot = "DBbackup\\";
            cmds = "xcopy " + masterRoot + "*.* " + backupRoot + " /s/y";
        }
        else if(os.startsWith("Linux")){
            masterRoot = "/DB";
            backupRoot = "/DBbackup";
            cmds = "cp -r " + masterRoot + " " + backupRoot;
        }
        else if(os.startsWith("Mac OS")){
            masterRoot = "/DB";
            backupRoot = "/DBbackup";
            cmds = "cp -r " + masterRoot + " " + backupRoot;
            specialCmd = "sudo mount -uw /";
        }
        try {
            if (!Files.exists(Path.of(backupRoot))){
                Files.createDirectory(Path.of(backupRoot));
            }
            if(os.startsWith("Mac OS")){
                Runtime.getRuntime().exec(specialCmd);
            }
            Runtime.getRuntime().exec(cmds);
            Thread.sleep(100);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Recover master database from backup database.
     */
    public static void push() {
        getOS();
        if(os.startsWith("Windows")){
            masterRoot = "DB\\";
            backupRoot = "DBbackup\\";
            cmds = "xcopy " + backupRoot + "*.* " + masterRoot + " /s/y";
            deletecmd = "cmd /c rd "+ backupRoot +" /s/q";
        }
        else if(os.startsWith("Linux")){
            masterRoot = "/DB/";
            backupRoot = "/DBbackup/";
            cmds = "cp -r " + backupRoot + " " + masterRoot;
            deletecmd = "sudo rm -rf "+ backupRoot;
        }
        else if(os.startsWith("Mac OS")){
            masterRoot = "/DB/";
            backupRoot = "/DBbackup/";
            cmds = "cp -r " + backupRoot + " " + masterRoot;
            deletecmd = "sudo rm -rf "+ backupRoot;
            specialCmd = "sudo mount -uw /";
        }
        try {
            if(os.startsWith("Mac OS")){
                Runtime.getRuntime().exec(specialCmd);
            }
            Runtime.getRuntime().exec(cmds);
            Thread.sleep(100);
            Runtime.getRuntime().exec(deletecmd);
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getOS() {
        Properties pros = System.getProperties();
        os = (String) pros.get("os.name");
        System.out.println(os);
    }
}
