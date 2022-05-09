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
        setBackground(null);
        setSize(330, 570);
        setLayout(null);
        setOpaque(true);

        String[] titleText = {"Plane model", "Capacity", "Airline", "Plane age", ""};
        String[] contentText = new String[5];

        int planeIndex = PlaneReader.indexOf(FlightReader.getIdPlane(FlightReader.indexOf(State.getIdFlight())));
        contentText[0] = PlaneReader.getModel(planeIndex);
        contentText[1] = "" + PlaneReader.getCapacity(planeIndex);
        contentText[2] = PlaneReader.getAirline(planeIndex);
        contentText[3] = "" + PlaneReader.getPlaneAge(planeIndex);
        contentText[4] = "";
        for (int i = 0; i < 6; i++) {
            JLabel line = new JLabel();
            line.setBounds(15, i * 82 + 19, 300, 2);
            line.setOpaque(true);
            line.setBackground(Theme.getTertiaryFontColor());
            add(line);

            if (i == 5)
                break;

            JLabel title = new JLabel(titleText[i]);
            title.setFont(new Font("Arial", Font.PLAIN, 18));
            title.setForeground(Theme.getSecondaryFontColor());
            title.setHorizontalAlignment(SwingConstants.CENTER);
            title.setBounds(0, i * 82 + 29, 330, 30);
            add(title);

            JLabel content = new JLabel(contentText[i]);
            content.setFont(new Font("Arial", Font.PLAIN, 25));
            content.setForeground(Theme.getMainFontColor());
            content.setVerticalAlignment(SwingConstants.TOP);
            content.setHorizontalAlignment(SwingConstants.CENTER);
            content.setBounds(0, i * 82 + 61, 330, 30);
            add(content);

        }
    }
}
