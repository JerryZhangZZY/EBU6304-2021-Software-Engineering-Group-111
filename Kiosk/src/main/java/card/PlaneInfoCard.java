package card;

import dbReader.FlightReader;
import dbReader.PlaneReader;
import main.State;
import main.Theme;

import javax.swing.*;
import java.awt.*;

/**
 * This class can return a plane information card
 *
 * @author Liang Zhehao
 *
 * @version 4.0
 * @date 2022/5/6
 */
public class PlaneInfoCard extends JPanel {

    public PlaneInfoCard() {
        setBackground(Theme.getCardColor());
        setSize(380, 570);
        setLayout(null);
        setOpaque(true);

        String[] titleText = {"Plane model", "Capacity", "Airline", "", ""};
        String[] contentText = new String[5];

        int planeIndex = PlaneReader.indexOf(FlightReader.getIdPlane(FlightReader.indexOf(State.getIdFlight())));
        contentText[0] = PlaneReader.getModel(planeIndex);
        contentText[1] = "" + PlaneReader.getCapacity(planeIndex);
        contentText[2] = PlaneReader.getAirline(planeIndex);
        contentText[3] = "";
        contentText[4] = "";
        for (int i = 0; i < 6; i++) {
            JLabel line = new JLabel();
            line.setBounds(15, i * 102 + 29, 300, 2);
            line.setOpaque(true);
            line.setBackground(Theme.getTertiaryFontColor());
            add(line);

            if (i == 5)
                break;

            JLabel title = new JLabel(titleText[i]);
            title.setFont(new Font("Arial", Font.PLAIN, 25));
            title.setForeground(Theme.getMainFontColor());
            title.setBounds(20, i * 102 + 39, 250, 30);
            add(title);

            JLabel content = new JLabel(contentText[i]);
            content.setFont(new Font("Arial", Font.PLAIN, 18));
            content.setForeground(Theme.getSecondaryFontColor());
            content.setVerticalAlignment(SwingConstants.TOP);
            content.setBounds(20, i * 102 + 74, 290, 30);
            add(content);

        }
    }
}
