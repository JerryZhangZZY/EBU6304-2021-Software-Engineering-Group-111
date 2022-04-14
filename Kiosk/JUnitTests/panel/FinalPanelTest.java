package panel;

import BackendSystemDB.DBreader;
import backupDbOperation.BackupDbOperator;
import dbReader.PassengerFlightReader;
import dbReader.SeatReader;
import main.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a unit test of final panel
 *
 * @author Wang Chenyu
 * @author Liang Zhehao
 * @author Zhang Zeyu
 *
 * @version 2.2
 * Delete pull().
 * @date 2022/4/10
 *
 * @version 2.1
 * @date 2022/4/9
 *
 * @version 2.0
 * @date 2022/4/7
 */
public class FinalPanelTest {

    @Test
    void exitTest() {
        Random random = new Random();
        char[] food = {'a', 'b', 'c'};
        String[] column = {"A", "B", "C", "D", "E", "F"};
        String[] foodName = {"Normal", "Vegetarian", "Halal"};

        State.setIdFlight("CA0001");
        State.setSeatRow(random.nextInt(6) + 1);
        State.setSeatColumn(random.nextInt(6) + 1);
        State.setSelectedPrefFood(new boolean[]{random.nextBoolean(), random.nextBoolean(), random.nextBoolean()});
        State.setPrefFoodName(new String[]{"food1", "food2", "food3"});
        State.setBookingNum("bn0001");
        State.setMeal(food[random.nextInt(3)]);
        State.setPassengerFlight_index(0);
        FinalPanel finalPanel = new FinalPanel();
        JButton exit = finalPanel.getExit_begin();
        exit.doClick();
        assertEquals(3, State.getPc());
        System.out.println("right");
    }

    @Test
    void systemExitTest() {
        Random random = new Random();
        char[] food = {'a', 'b', 'c'};
        String[] column = {"A", "B", "C", "D", "E", "F"};
        String[] foodName = {"Normal", "Vegetarian", "Halal"};

        State.setIdFlight("CA0001");
        State.setSeatRow(random.nextInt(6) + 1);
        State.setSeatColumn(random.nextInt(6) + 1);
        State.setSelectedPrefFood(new boolean[]{random.nextBoolean(), random.nextBoolean(), random.nextBoolean()});
        State.setPrefFoodName(new String[]{"food1", "food2", "food3"});
        State.setBookingNum("bn0001");
        State.setMeal(food[random.nextInt(3)]);
        State.setPassengerFlight_index(0);
        FinalPanel finalPanel = new FinalPanel();
        JButton exit = finalPanel.getExit_system();
        exit.doClick();
        assertEquals(0, State.getPc());
        System.out.println("right");
    }

    @RepeatedTest(10)
    void testDataConfirm() {
        Random random = new Random();
        char[] food = {'a', 'b', 'c'};
        String[] column = {"A", "B", "C", "D", "E", "F"};
        String[] foodName = {"Normal", "Vegetarian", "Halal"};

        State.setIdFlight("CA0001");
        State.setSeatRow(random.nextInt(6) + 1);
        State.setSeatColumn(random.nextInt(6) + 1);
        State.setSelectedPrefFood(new boolean[]{random.nextBoolean(), random.nextBoolean(), random.nextBoolean()});
        State.setPrefFoodName(new String[]{"food1", "food2", "food3"});
        State.setBookingNum("bn0001");
        State.setMeal(food[random.nextInt(3)]);
        State.setPassengerFlight_index(0);

        FinalPanel finalPanel = new FinalPanel();

        SeatReader seatReader = new SeatReader("CA0001");
        boolean[] seat = seatReader.getSeat(State.getSeatRow());
        assertFalse(seat[State.getSeatColumn() - 1]);

        String[] data = {};
        try {
            DBreader dBreader = new DBreader();
            for (int i = 0; i < dBreader.getNumberOfLine(); i++) {
                data = dBreader.getline(i);
                if (data[2].equals(State.getIdFlight()) && data[3].equals(State.getBookingNum()))
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] finalData = data;
        String[] prefFood = new String[3];
        for (int i = 0; i < 3; i++) {
            if (State.getSelectedPrefFood()[i])
                prefFood[i] = State.getPrefFoodName()[i];
            else
                prefFood[i] = "NULL";
        }
        assertAll("backend",
                () -> assertEquals("1", finalData[4]),
                () -> assertEquals("" + State.getSeatRow() + column[State.getSeatColumn() - 1], finalData[5]),
                () -> assertEquals(foodName[(int)State.getMeal() - 97], finalData[6]),
                () -> assertEquals(prefFood[0], finalData[7]),
                () -> assertEquals(prefFood[1], finalData[8]),
                () -> assertEquals(prefFood[2], finalData[9])
        );
        assertTrue(PassengerFlightReader.getStatus(State.getPassengerFlight_index()));
    }

    @AfterEach
    void reset() {
        BackupDbOperator.push();
    }
}
