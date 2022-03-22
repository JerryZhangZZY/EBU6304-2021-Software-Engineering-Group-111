package frame;

import dbReader.PassengerFlightReader;
import dbReader.PassengerReader;
import main.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @version 1.0
 * @author zaitian
 * @date 3/18
 * initial version main frame
 *
 * @version 1.1
 * @author wcy
 * @date 3/21
 * main frame with icon added and db_reader embedded
 *
 * @version 1.2
 * @author zaitian
 * @date 3/22
 * main frame with restored ui design controlling methods
 */

public class MainFrame extends JFrame {

    private JPanel contentPane;
    private JPanel topPanel;
    private JLabel welcomeLabel;
    private JButton exitButton;
    private JButton ForcedExitButton;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JButton backButton;
    ImageIcon icon1_back = new ImageIcon("Kiosk/icons/back.png");
    Image img_back = icon1_back.getImage();
    Image newImg_back = img_back.getScaledInstance(100, 80, java.awt.Image.SCALE_SMOOTH);
    ImageIcon icon_back = new ImageIcon(newImg_back);
    ImageIcon icon1_exit = new ImageIcon("Kiosk/icons/exit.png");
    Image img_exit = icon1_exit.getImage();
    Image newImg_exit = img_exit.getScaledInstance(80, 70, java.awt.Image.SCALE_SMOOTH);
    ImageIcon icon_exit = new ImageIcon(newImg_exit);

    /**
     * Main frame with panels initialized.
     */
    public MainFrame(int idPassengerFlight) {
        /*
         * basic settings
         */
        setTitle("KIOSK");
        setResizable(false);
        setUndecorated(true);
        setBounds(new Rectangle(0, 0, 1920, 1080));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        /*
        top panel, with welcome text and exit button
         */
        topPanel = new JPanel();
        topPanel.setBackground(Color.ORANGE);
        topPanel.setBounds(0, 0, 1920, 100);
        contentPane.add(topPanel);
        topPanel.setLayout(null);
        //TODO move to a control method
        String idPassenger = PassengerFlightReader.getIdPassenger(PassengerFlightReader.indexOf(idPassengerFlight));
        String surname = PassengerReader.getSurname(PassengerReader.indexOf(idPassenger));
        welcomeLabel = new JLabel("Welcome, "+surname+"!");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        welcomeLabel.setBounds(40, 20, 400, 60);
        topPanel.add(welcomeLabel);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 26));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.WHITE);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setIcon(icon_exit);
        exitButton.setBounds(1700, 8, 200, 77);
        topPanel.add(exitButton);
        /*
        for developers to exit program easily
        note that for ordinary users, clicking the normal exit just return to welcome page
         */
        ForcedExitButton = new JButton("Forced Exit");
        ForcedExitButton.setFont(new Font("Arial", Font.PLAIN, 24));
        ForcedExitButton.setBounds(1400, 20, 180, 60);
//        ForcedExitButton.setContentAreaFilled(false);
//        ForcedExitButton.setBorderPainted(false);
        ForcedExitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        ForcedExitButton.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_ENTER){
                    System.exit(0);
                }
            }
            });
        topPanel.add(ForcedExitButton);
        /*
        main panel that holds other panels
        use centerPanel.add(JPanel panel) to add components
         */
        centerPanel = new JPanel();
        centerPanel.setBackground(new Color(244, 244, 244));
        centerPanel.setBounds(0, 100, 1920, 880);
        contentPane.add(centerPanel);
        centerPanel.setLayout(null);
        /*
         * bottom panel, with back button
         */
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.CYAN);
        bottomPanel.setBounds(0, 980, 1920, 100);
        contentPane.add(bottomPanel);
        bottomPanel.setLayout(null);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 26));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.WHITE);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setBounds(40, 20, 300, 60);
        backButton.setIcon(icon_back);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (State.getPc()){
                    case 2://alt ID
                    case 4://seat
                    case 5://food
                    case 6://bill
                    case 7://pay
                    {
                        State.setPc(State.getPc() - 1);
                        break;
                    }
                }
            }
        });
        bottomPanel.add(backButton);

    }

    /**
     * loading a panel into the center panel of main frame
     * note that it will take up almost whole frame
     * @param panel the panel to be loaded
     */
    public void loadPanel(JPanel panel){
//        centerPanel.add(panel);
        centerPanel.add(panel, 0);
    }
    public Component getLoadedPanel(){
        if(centerPanel.getComponentCount()>0)
            return centerPanel.getComponent(0);
        else
            return null;
    }
    public void unloadPanel(Component panel){
        centerPanel.remove(panel);
    }

    /**
     * deciding whether components are displayed
     * @param welcome welcome text
     * @param exit exit button
     * @param back back button
     */
    public void displayComponents(Boolean welcome, Boolean exit, Boolean back){
        if(!welcome){
            welcomeLabel.setVisible(false);
        }
        if(!exit){
            exitButton.setVisible(false);
        }
        if(!back){
            backButton.setVisible(false);
        }
    }
}
