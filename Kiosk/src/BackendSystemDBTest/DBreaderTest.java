package BackendSystemDBTest;
/**
 * a tool to test DBreader
 *
 * @author Wang Chenyu
 * @date 2022/3/25
 * @version 1.0
 */

import BackendSystemDB.DBreader;

import java.io.IOException;
import java.util.ArrayList;

public class DBreaderTest {
    public static void main(String[] args) throws IOException {
        DBreader reader = new DBreader();
        String[] a= reader.getheadline();
//        for(int i =0;i<10;i++){
//            System.out.println(a[i]);
//        }
        String[] tmp=reader.getline(2);
        //System.out.println(tmp[0]);
        ArrayList<String[]> data = reader.getDataBase();
        System.out.print(data.get(4)[9]);
        //System.out.println(reader.getNumberOfLine());
        //ArrayList<String[]> airline = reader.getSpecialAirline("AIR China");
        //System.out.println(airline.get(0)[0]);

    }
}
