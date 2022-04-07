package backupDbOperation;

/**
 * Run this class manually to update backup database from master database.
 *
 * @author Zhang Zeyu
 *
 * @version 2.0
 * @date 2022/4/7
 */

public class BackupDbPullRunner {
    public static void main(String[] args) {
        BackupDbOperator.pull();
    }
}
