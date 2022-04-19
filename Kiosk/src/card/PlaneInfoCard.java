package card;

import javax.swing.*;
import java.awt.*;

/**
 * This class can return a panel of Plane info card.
 *
 * @author Liang Zhehao
 *
 * @version 3.0
 * @date 2022/4/20
 *
 * @version 2.0
 * @date 2022/4/11
 */
public class PlaneInfoCard extends JPanel {

    /**
     *
     * @param model plane model
     * @param capacity plane capacity
     * @param airline airline of plane
     */
    public PlaneInfoCard(String model, int capacity, String airline) {
        setBackground(Color.WHITE);
        setSize(600,280);
        setLayout(null);

        JLabel lblModel = new JLabel("Model :");
        lblModel.setForeground(Color.DARK_GRAY);
        lblModel.setFont(new Font("Arial", Font.BOLD, 35));
        lblModel.setBounds(40, 20, 122, 57);
        add(lblModel);

        JLabel modelName = new JLabel(model);
        modelName.setForeground(Color.GRAY);
        modelName.setFont(new Font("Eras Bold ITC", Font.BOLD, 35));
        modelName.setBounds(172, 20, 470, 57);
        add(modelName);

        JLabel lblCap = new JLabel("Capacity :");
        lblCap.setForeground(Color.DARK_GRAY);
        lblCap.setFont(new Font("Arial", Font.BOLD, 35));
        lblCap.setBounds(40, 80, 166, 57);
        add(lblCap);

        JLabel capacityNumber = new JLabel("" + capacity);
        capacityNumber.setForeground(Color.GRAY);
        capacityNumber.setFont(new Font("Eras Bold ITC", Font.BOLD, 35));
        capacityNumber.setBounds(216, 80, 109, 57);
        add(capacityNumber);

        JLabel lblAirline = new JLabel("Airline :");
        lblAirline.setForeground(Color.DARK_GRAY);
        lblAirline.setFont(new Font("Arial", Font.BOLD, 35));
        lblAirline.setBounds(40, 140, 129, 57);
        add(lblAirline);

        JLabel airlineName = new JLabel(airline);
        airlineName.setForeground(Color.GRAY);
        airlineName.setFont(new Font("Eras Bold ITC", Font.BOLD, 35));
        airlineName.setBounds(179, 140, 414, 57);
        add(airlineName);

    }
}
