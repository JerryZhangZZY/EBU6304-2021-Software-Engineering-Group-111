package BackendSystemDBTest;
/**
 * a tool test DBwriter
 *
 * @author Wang Chenyu
 * @date 2022/3/25
 * @version 1.0
 */
import BackendSystemDB.DBreader;
import BackendSystemDB.DBwrite;

import java.io.IOException;
import java.util.ArrayList;

public class DBwriteTest {
    public static void main(String[] args) throws IOException {
        String[] str = {"4","CA1556","456789","1","23B","vegetarian","French","Japan","ice","Shan Dong"};
        DBwrite.writeline(str);

    }
}
