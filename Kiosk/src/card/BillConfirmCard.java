package card;

import BackendSystemDB.DBwrite;
import dbReader.PassengerFlightReader;
import dbWriter.SeatWriter;
import idCardReader.IdCardReader;
import main.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class can return a panel of confirm card.
 *
 * @author Liang Zhehao
 * @date 2022/3/25
 * @version 1.0
 *
 * @author Zhang Zeyu
 * @date 2022/3/28
 * @version 1.1
 * Reuse SmallBillCard
 * and appearance improved.
 */

public class BillConfirmCard extends JPanel {

    private FlightInfoTopBarCard flightInfoTopBarCard;
    private SeatBillCard seatBillCard;
    private MealBillcard mealBillcard;
    private JLabel scan_label;
    private JLabel info_label;
    private JButton btnConfirm;
    private int judge;

    private JTextField textBill;

    public BillConfirmCard() {

        setBackground(new Color(244, 244, 244));
        setForeground(Color.WHITE);
        setLayout(null);
        setSize(1600, 880);
        /*
        Initialize flightInfoTopBarCard
         */
        flightInfoTopBarCard = new FlightInfoTopBarCard(State.getIdFlight());
        flightInfoTopBarCard.setLocation(0, 20);
        add(flightInfoTopBarCard);

        /*
        Initialize seatBillCard
         */
        String seatPre = State.getPrefSeatName()[State.getSeatPre()];
        int seatPay = State.getPrefSeatPrice()[State.getSeatPre()];
        char column = 'o';
        switch (State.getSeatColumn()) {
            case 1:
                column = 'A';
                break;
            case 2:
                column = 'B';
                break;
            case 3:
                column = 'C';
                break;
            case 4:
                column = 'D';
                break;
            case 5:
                column = 'E';
                break;
            case 6:
                column = 'F';
        }
        seatBillCard = new SeatBillCard(State.getSeatRow(), column, seatPre, seatPay);
        seatBillCard.setLocation(50, 224);
        add(seatBillCard);

        /*
        Initialize mealBillcard
         */
        boolean[] select = State.getSelectedPrefFood();
        int[] foodPay = new int[3];
        for (int i = 0; i < 3; i++) {
            if (select[i])
                foodPay[i] = State.getPrefFoodPrice()[i];
            else
                foodPay[i] = -1;
        }
        mealBillcard = new MealBillcard(State.getMeal(),
                State.getPrefFoodName()[0], State.getPrefFoodName()[1], State.getPrefFoodName()[2],
                foodPay[0], foodPay[1], foodPay[2]);
        mealBillcard.setLocation(50, 437);
        add(mealBillcard);

        SmallBillCard totalBill = new SmallBillCard(State.getBill());
        totalBill.changeTitle("Total:");
        add(totalBill);


        scan_label=new JLabel();
        info_label=new JLabel();
        info_label.setText("Scan ID to continue");
        info_label.setHorizontalAlignment(SwingConstants.CENTER);
        info_label.setBounds(1180, 550, 400, 40);
        info_label.setFont(new Font("Arial", Font.PLAIN,35));
        info_label.setForeground(Color.DARK_GRAY);
        judge = 0;
        add(info_label);

        scan_label.setBounds(1190, 150, 400, 400);
        scan_label.setIcon(new ImageIcon("Kiosk/icons/scan.png"));
        add(scan_label);



        JButton btnConfirm = new JButton("Confirm");
        btnConfirm.setFont(new Font("Arial", Font.BOLD, 35));
        btnConfirm.setBounds(1200, 760, 330, 70);
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setBackground(new Color(11, 89, 167));
        add(btnConfirm);
        btnConfirm.addActionListener(new ConfirmListener());
    }

    public class ConfirmListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
                //upload database
            String id = null;
            try {
                id = IdCardReader.readId();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (id.equals(PassengerFlightReader.getIdPassenger(PassengerFlightReader.indexOf(State.getPassengerFlight_index())))){
                info_label.setText("Authentication success");
                if(judge==0){
                    judge=1;
                }
                else {
                    if (State.getBill() != 0) {
                        State.setPc(State.getPc() + 1);
                    }
                    else
                    {
                        confirm();
                        State.setPc(State.getPc() + 2);
                    }
                }
                }
                else
                {
                    info_label.setText("Authentication error");
                }
            }
    }

    public static void confirm() {
        SeatWriter.setSeat(State.getIdFlight(), State.getSeatRow(), State.getSeatColumn());
        String column = "o";
        switch (State.getSeatColumn()) {
            case 1:
                column = "A";
                break;
            case 2:
                column = "B";
                break;
            case 3:
                column = "C";
                break;
            case 4:
                column = "D";
                break;
            case 5:
                column = "E";
                break;
            case 6:
                column = "F";
        }
        String food = "";
        if (State.getMeal() == 'a')
            food = "Standard";
        else if (State.getMeal() == 'b')
            food = "Vegetarian";
        else if (State.getMeal() == 'c')
            food = "Halal";
        String[] prefFood = new String[3];
        for (int i = 0; i < 3; i++) {
            if (!State.getSelectedPrefFood()[i])
                prefFood[i] = "NULL";
            else
                prefFood[i] = State.getPrefFoodName()[i];
        }
        DBwrite.changeline(State.getBookingNum(), State.getIdFlight(), State.getSeatRow() + column, food, prefFood[0], prefFood[1], prefFood[2]);
    }


    public List<String> getBookingNumByScanning() {
        List<String> list = new ArrayList<>();
        try {
            list.addAll(PassengerFlightReader.getBookingNumByPassengerId(IdCardReader.readId()));
            return list;
        } catch (IOException e) {
            for(int i = 0; i < 3; i++)
                list.add("F");
            return list;
        }
    }
}
