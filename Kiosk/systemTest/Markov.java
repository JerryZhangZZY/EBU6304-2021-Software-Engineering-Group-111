import com.csvreader.CsvReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class Markov extends CsvReader{

    public Markov() throws FileNotFoundException {
        super("testing-resource/Markov.csv");
    }

    public int nextStateOf(int current) throws IOException {
        int next;
        readHeaders();
        for (int i = 0; i < current; i++) {
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

    public static void main(String[] args) throws IOException {
        int[] n023 = {0,0,0,0};
        for (int i = 0; i < 10000; i++) {
            Markov markov = new Markov();
            int next = markov.nextStateOf(2);
            if (next==0) n023[0]++;
            else if (next==2) n023[1]++;
            else if (next==3) n023[2]++;
            else n023[3]++;
        }
        for (int n : n023) {
            System.out.println(n);
        }
    }
}
