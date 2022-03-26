package BSAdministratorDBTest;


import BSAdministratorDB.AdministratorReader;

import java.io.IOException;

/**
 * the test for administratorDB reader
 *
 * @author Wang Chenyu
 * @date 2022/3/26
 * @version 1.0
 */
public class AdministratorDBTest {
    public static void main(String[] args) throws IOException {
        AdministratorReader reader = new AdministratorReader("565656");
        String[] temp = reader.getPassword();
//        System.out.println(temp[0]);
//        System.out.println(temp[1]);
//        System.out.println(temp[2]);
//        if(reader.exsist()){
//            System.out.println("rookie");
//        }
    }

}
