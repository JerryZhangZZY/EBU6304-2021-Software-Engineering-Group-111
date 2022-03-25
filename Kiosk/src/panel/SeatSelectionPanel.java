package panel;

import card.SeatSelectionCard;

import javax.swing.*;
import java.awt.*;

public class SeatSelectionPanel extends JPanel {

    private SeatSelectionCard seatSelectionCard;

    public SeatSelectionPanel() {
        setBounds(320, 0, 1600, 880);
        setBackground(Color.PINK);
        setLayout(null);

        seatSelectionCard = new SeatSelectionCard();
        add(seatSelectionCard);
    }
}
