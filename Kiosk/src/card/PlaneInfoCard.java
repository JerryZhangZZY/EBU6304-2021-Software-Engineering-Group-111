package card;

import javax.swing.*;
import java.awt.*;

/**
 * This class can return a panel of Plane info card.
 *
 * @author Liang Zhehao
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
        setSize(1480,230);
        setLayout(null);

        JLabel lblModel = new JLabel("Model :");
        lblModel.setForeground(Color.DARK_GRAY);
        lblModel.setFont(new Font("Arial", Font.BOLD, 35));
        lblModel.setBounds(16, 113, 122, 57);
        add(lblModel);

        JLabel modelName = new JLabel(model);
        modelName.setForeground(Color.GRAY);
        modelName.setFont(new Font("Eras Bold ITC", Font.BOLD, 35));
        modelName.setBounds(148, 113, 470, 57);
        add(modelName);

        JLabel lblCap = new JLabel("Capacity :");
        lblCap.setForeground(Color.DARK_GRAY);
        lblCap.setFont(new Font("Arial", Font.BOLD, 35));
        lblCap.setBounds(964, 163, 166, 57);
        add(lblCap);

        JLabel capacityNumber = new JLabel("" + capacity);
        capacityNumber.setForeground(Color.GRAY);
        capacityNumber.setFont(new Font("Eras Bold ITC", Font.BOLD, 35));
        capacityNumber.setBounds(1140, 163, 109, 57);
        add(capacityNumber);

        JLabel lblAirline = new JLabel("Airline :");
        lblAirline.setForeground(Color.DARK_GRAY);
        lblAirline.setFont(new Font("Arial", Font.BOLD, 35));
        lblAirline.setBounds(968, 113, 129, 57);
        add(lblAirline);

        JLabel airlineName = new JLabel(airline);
        airlineName.setForeground(Color.GRAY);
        airlineName.setFont(new Font("Eras Bold ITC", Font.BOLD, 35));
        airlineName.setBounds(1107, 113, 414, 57);
        add(airlineName);

        JLabel lblRow = new JLabel("Row :");
        lblRow.setForeground(Color.DARK_GRAY);
        lblRow.setFont(new Font("Arial", Font.BOLD, 35));
        lblRow.setBounds(16, 163, 96, 57);
        add(lblRow);

        JLabel lblColumn = new JLabel("Column :");
        lblColumn.setForeground(Color.DARK_GRAY);
        lblColumn.setFont(new Font("Arial", Font.BOLD, 35));
        lblColumn.setBounds(457, 163, 153, 57);
        add(lblColumn);

        JLabel columnNum = new JLabel("6");
        columnNum.setForeground(Color.GRAY);
        columnNum.setFont(new Font("Eras Bold ITC", Font.BOLD, 35));
        columnNum.setBounds(620, 163, 109, 57);
        add(columnNum);

        JLabel rowNum = new JLabel("" + (capacity / 6));
        rowNum.setForeground(Color.GRAY);
        rowNum.setFont(new Font("Eras Bold ITC", Font.BOLD, 35));
        rowNum.setBounds(122, 163, 109, 57);
        add(rowNum);

        JLabel cutline2 = new JLabel("Selected");
        cutline2.setForeground(Color.DARK_GRAY);
        cutline2.setFont(new Font("Arial", Font.BOLD, 25));
        cutline2.setBounds(740, 40, 160, 50);
        add(cutline2);

        ImageIcon iconSelected = new ImageIcon("Kiosk/icons/chosen.png");
        iconSelected.setImage(iconSelected.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH));
        JLabel selected = new JLabel();
        selected.setIcon(iconSelected);
        selected.setBounds(655, 20, 75, 75);
        add(selected);

        JLabel cutline1 = new JLabel("Available");
        cutline1.setForeground(Color.DARK_GRAY);
        cutline1.setFont(new Font("Arial", Font.BOLD, 25));
        cutline1.setBounds(370, 40, 160, 50);
        add(cutline1);

        ImageIcon iconAvail = new ImageIcon("Kiosk/icons/avail.png");
        iconAvail.setImage(iconAvail.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH));
        JLabel avail = new JLabel();
        avail.setIcon(iconAvail);
        avail.setBounds(285, 20, 75, 75);
        add(avail);

        JLabel cutline3 = new JLabel("Occupied");
        cutline3.setForeground(Color.DARK_GRAY);
        cutline3.setFont(new Font("Arial", Font.BOLD, 25));
        cutline3.setBounds(1110, 40, 160, 50);
        add(cutline3);

        ImageIcon iconOccu = new ImageIcon("Kiosk/icons/occu.png");
        iconOccu.setImage(iconOccu.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH));
        JLabel occu = new JLabel();
        occu.setIcon(iconOccu);
        occu.setBounds(1025, 20, 75, 75);
        add(occu);
    }
}
