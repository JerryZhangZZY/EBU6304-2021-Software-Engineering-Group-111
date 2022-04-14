package backupDbOperation;

/**
 * Run this class manually to recover master database from backup database.
 *
 * @author Zhang Zeyu
 *
 * @version 2.0
 * @date 2022/4/7
 */

public class BackupDbPushRunner {
    public static void main(String[] args) {
        BackupDbOperator.push();
    }
}
