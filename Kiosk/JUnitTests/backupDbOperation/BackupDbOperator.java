package backupDbOperation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * This class contains methods to recover master database
 * as well as update backup database.
 *
 * @author Zhang Zeyu
 *
 * @version 2.0
 * @date 2022/4/7
 */

public abstract class BackupDbOperator {

    static final String[] files = {
            "backend.csv",
            "backend_administrator.csv",
            "flight.json",
            "passenger.json",
            "passengerFlight.json",
            "plane.json",
            "airline_website.json",
            "planes/plane1.csv",
            "planes/plane2.csv",
            "planes/plane3.csv"};
    static final String masterRoot = "DB/";
    static final String backupRoot = "DBbackup/";

    /**
     * Update backup database from master database.
     */
    public static void pull() {
        for (String file : files) {
            try {
                Files.copy(Path.of(masterRoot + file), Path.of(backupRoot + file), REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Recover master database from backup database.
     */
    public static void push() {
        for (String file : files) {
            try {
                Files.copy(Path.of(backupRoot + file), Path.of(masterRoot + file), REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
