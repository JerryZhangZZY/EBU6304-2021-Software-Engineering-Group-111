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
        String[] str = {"66","John","CA1002","879689","1","23C","vegetarian","French","Japan","ice","Shan Dong"};
        //DBwrite.writeline(str);
        String[] stri = {"74","Panda","CA1006","879456","0","24A","NULL","NULL","NULL","NULL","Shan Dong"};
        DBwrite.changeline(stri);


    }
}
