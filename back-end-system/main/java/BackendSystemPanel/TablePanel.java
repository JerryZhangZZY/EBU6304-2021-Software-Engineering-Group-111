package BackendSystemPanel;

import BackendSystemDB.DBreader;
import Backendmain.Systempointer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * the panel for show and check database
 *
 * @author Wang Chenyu
 * @date 2022/3/26
 * @version 1.0
 *
 * @author Liang Zhehao
 * @data 2022/3/29
 * @version 1.1
 * Simplify the code, remove the call to getDataBase function
 */
public class TablePanel extends JPanel {
    private final JTable table = new JTable();
    private final JScrollPane pane = new JScrollPane();
    private final JLabel page_title = new JLabel("Passenger information");
    private final JTextField airline_field = new JTextField();
    private final JTextField idPassenger_field = new JTextField();
    private final JTextField idFlight_field = new JTextField();
    private final JButton airline_button = new JButton("confirm");
    private final JButton passenger_button = new JButton("confirm");
    private final JButton flight_button = new JButton("confirm");
    private final JLabel passenger = new JLabel("idPassenger");
    private final JLabel airline = new JLabel("airline");
    private final JLabel flight = new JLabel("idflight");
    private final Border borderLine = BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true);
    private final Border errorLine = BorderFactory.createLineBorder(Color.RED, 3, true);
    private final DefaultTableModel model = new DefaultTableModel(
            new Object[][]{

            },
            new String[]{"idPassenger", "surName", "idFlight", "bookingNum", "status", "seat", "meal", "mealPre1", "mealPre2", "mealPre3", "airline"}
    );
    //    private ArrayList<String[]> data;
    private String[] data;
    private final DBreader dbReader = new DBreader();

    public TablePanel() throws IOException {
        airlineActionListener air = new airlineActionListener();
        passengerActionListener pass = new passengerActionListener();
        flightActionListener fli = new flightActionListener();
        freshActionListener fre = new freshActionListener();

        setBounds(new Rectangle(0, 0, 1920, 980));
        setBackground(new Color(244, 244, 244));
        setLayout(null);
        setSize(1920, 980);

        DBreader reader = new DBreader();
        pane.setBorder(new TitledBorder(null, "DataBase Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pane.setLocation(0, 180);
        pane.setSize(1920, 800);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setEnabled(false);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));

        table.setModel(model);
        table.setRowHeight(30);
        JTableHeader head_table = table.getTableHeader();
        head_table.setPreferredSize(new Dimension(head_table.getWidth(), 35));
        head_table.setFont(new Font("Arial", Font.PLAIN, 25));
        table.setFont(new Font("Arial", Font.PLAIN, 25));
        pane.setViewportView(table);
        add(pane);
        for (int i = 1; i < dbReader.getNumberOfLine(); i++) {
            data = dbReader.getline(i);
            if (data[4].equals("0"))
                model.addRow(data);
        }
        for (int i = 1; i < dbReader.getNumberOfLine(); i++) {
            data = dbReader.getline(i);
            if (data[4].equals("1"))
                model.addRow(data);
        }
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(35);
        table.getColumnModel().getColumn(5).setPreferredWidth(35);
        table.getColumnModel().getColumn(6).setPreferredWidth(80);
        table.getColumnModel().getColumn(7).setPreferredWidth(80);
        table.getColumnModel().getColumn(8).setPreferredWidth(80);
        table.getColumnModel().getColumn(9).setPreferredWidth(80);
        table.getColumnModel().getColumn(10).setPreferredWidth(90);
        table.getTableHeader().setReorderingAllowed(false);


        page_title.setFont(new Font("Arial", Font.BOLD, 36));
        page_title.setLocation(766, 0);
        page_title.setSize(387, 51);
        add(page_title);

        airline_field.setLocation(194, 122);
        airline_field.setSize(224, 45);
        airline_field.setFont(new Font("Arial", Font.BOLD, 36));
        airline_field.setBorder(borderLine);
        add(airline_field);

        airline_button.setBounds(445, 122, 115, 45);
        airline_button.setBorder(borderLine);
        airline_button.setFont(new Font("Arial", Font.BOLD, 20));
        airline_button.addActionListener(air);
        add(airline_button);

        passenger_button.setLocation(1095, 122);
        passenger_button.setSize(115, 45);
        passenger_button.setBorder(borderLine);
        passenger_button.setFont(new Font("Arial", Font.BOLD, 20));
        passenger_button.addActionListener(pass);
        add(passenger_button);

        idPassenger_field.setLocation(848, 122);
        idPassenger_field.setSize(224, 45);
        idPassenger_field.setBorder(borderLine);
        idPassenger_field.setFont(new Font("Arial", Font.BOLD, 36));
        add(idPassenger_field);

        idFlight_field.setLocation(1424, 122);
        idFlight_field.setSize(224, 45);
        idFlight_field.setBorder(borderLine);
        idFlight_field.setFont(new Font("Arial", Font.BOLD, 36));
        add(idFlight_field);

        flight_button.setLocation(1672, 122);
        flight_button.setSize(115, 45);
        flight_button.setBorder(borderLine);
        flight_button.setFont(new Font("Arial", Font.BOLD, 20));
        flight_button.addActionListener(fli);
        add(flight_button);

        passenger.setLocation(685, 122);
        passenger.setSize(149, 45);
        passenger.setFont(new Font("Arial", Font.BOLD, 25));
        add(passenger);

        flight.setLocation(1328, 122);
        flight.setSize(82, 45);
        flight.setFont(new Font("Arial", Font.BOLD, 25));
        add(flight);

        airline.setLocation(106, 122);
        airline.setSize(74, 45);
        airline.setFont(new Font("Arial", Font.BOLD, 25));
        add(airline);

        JButton refresh = new JButton("refresh");
        refresh.setFont(new Font("Arial", Font.BOLD, 20));
        refresh.setBorder(borderLine);
        refresh.setBounds(902, 64, 115, 45);
        refresh.addActionListener(fre);
        add(refresh);

        JButton back = new JButton("BACK");
        back.setBounds(26, 8, 127, 45);
        back.setFont(new Font("Arial", Font.BOLD, 20));
        back.setBorder(borderLine);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Systempointer.setPc(0);
                Systempointer.setName(null);
            }
        });
        add(back);
    }

    private class airlineActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (airline_field.getText().length() == 0) {
                airline_field.setBorder(errorLine);
            } else {
                try {
                    model.setRowCount(0);
                    airline_field.setBorder(borderLine);
                    String airl = airline_field.getText();

                    for (int i = 1; i < dbReader.getNumberOfLine(); i++) {
                        data = dbReader.getline(i);
                        if (airl.equals(data[10])) {
                            if (data[4].equals("0")) {
                                model.addRow(data);
                            }
                        }
                    }
                    for (int i = 1; i < dbReader.getNumberOfLine(); i++) {
                        data = dbReader.getline(i);
                        if (airl.equals(data[10])) {
                            if (data[4].equals("1")) {
                                model.addRow(data);
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private class passengerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (idPassenger_field.getText().length() == 0) {
                idPassenger_field.setBorder(errorLine);
            } else {
                try {
                    model.setRowCount(0);
                    idPassenger_field.setBorder(borderLine);
                    String passe = idPassenger_field.getText();

                    for (int i = 1; i < dbReader.getNumberOfLine(); i++) {
                        data = dbReader.getline(i);
                        if (passe.equals(data[0])) {
                            if (data[4].equals("0")) {
                                model.addRow(data);
                            }
                        }
                    }
                    for (int i = 1; i < dbReader.getNumberOfLine(); i++) {
                        data = dbReader.getline(i);
                        if (passe.equals(data[0])) {
                            if (data[4].equals("1")) {
                                model.addRow(data);
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private class flightActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (idFlight_field.getText().length() == 0) {
                idFlight_field.setBorder(errorLine);
            } else {
                try {
                    model.setRowCount(0);
                    idFlight_field.setBorder(borderLine);
                    String fli = idFlight_field.getText();

                    for (int i = 1; i < dbReader.getNumberOfLine(); i++) {
                        data = dbReader.getline(i);
                        if (fli.equals(data[2])) {
                            if (data[4].equals("0")) {
                                model.addRow(data);
                            }
                        }
                    }
                    for (int i = 1; i < dbReader.getNumberOfLine(); i++) {
                        data = dbReader.getline(i);
                        if (fli.equals(data[2])) {
                            if (data[4].equals("1")) {
                                model.addRow(data);
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private class freshActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            idFlight_field.setText("");
            idPassenger_field.setText("");
            airline_field.setText("");
            idFlight_field.setBorder(borderLine);
            idPassenger_field.setBorder(borderLine);
            airline_field.setBorder(borderLine);
            model.setRowCount(0);
            try {
                for (int i = 1; i < dbReader.getNumberOfLine(); i++) {
                    data = dbReader.getline(i);
                    if (data[4].equals("0"))
                        model.addRow(data);
                }
                for (int i = 1; i < dbReader.getNumberOfLine(); i++) {
                    data = dbReader.getline(i);
                    if (data[4].equals("1"))
                        model.addRow(data);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
