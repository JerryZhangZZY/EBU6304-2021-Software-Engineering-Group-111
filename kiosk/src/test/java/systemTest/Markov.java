import com.csvreader.CsvReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

/**
 * Reading a csv file that contains a state-transition matrix
 * @author zaitian
 *
 * @version 1.0
 * @date 4/22
 */
public class Markov extends CsvReader{

    public Markov() throws FileNotFoundException {
        super("kiosk/src/test/decision/Markov.csv");
    }

    /**
     * transfer state according to markov matrix
     * @param current current state
     * @return next state
     * @throws IOException when matrix file not found
     */
    public int nextStateOf(int current) throws IOException {
        int next;
        readHeaders();
        for (int i = 0; i <= current; i++) {
            skipRecord();
        }
        System.out.println(getRawRecord());
        String[] pf = getRawRecord().split(",");

        double dice = new Random().nextDouble();
        System.out.println(dice);
        double df = 0;
        for (next = 0; next < getColumnCount(); next++) {
            df = df + Double.parseDouble(pf[next]);
            if (dice < df)
                break;
        }
        return next;
    }
}
