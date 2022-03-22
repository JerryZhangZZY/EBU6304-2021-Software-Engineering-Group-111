package frame;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import dbReader.*;

/**
 * @version 1.0
 * @author zaitian
 * @date 3/18
 * @author wcy
 * initial version main frame
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
    ImageIcon icon1_bcak = new ImageIcon("Kiosk/src/icons/back.png");
    Image img_bcak = icon1_bcak.getImage();
    Image newimg_bcak= img_bcak.getScaledInstance(100, 80, java.awt.Image.SCALE_SMOOTH);
    ImageIcon icon_bcak = new ImageIcon(newimg_bcak);
    ImageIcon icon1_exit = new ImageIcon("Kiosk/src/icons/exit.png");
    Image img_exit = icon1_exit.getImage();
    Image newimg_exit= img_exit.getScaledInstance(80, 70, java.awt.Image.SCALE_SMOOTH);
    ImageIcon icon_exit = new ImageIcon(newimg_exit);

    /**
     * Create the frame, initializing panels.
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
        ForcedExitButton = new JButton();
        ForcedExitButton.setContentAreaFilled(false);
        ForcedExitButton.setBorderPainted(false);
        ForcedExitButton.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_ENTER)
                {
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
        centerPanel.setBounds(0, 100, 1920, 980);
        contentPane.add(centerPanel);
        centerPanel.setLayout(null);
        /*
         * bottom panel, with back button
         */
//        bottomPanel = new JPanel();
//        bottomPanel.setBackground(Color.CYAN);
//        bottomPanel.setBounds(0, 980, 1920, 100);
//        contentPane.add(bottomPanel);
//        bottomPanel.setLayout(null);

        backButton = new JButton("back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 26));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.WHITE);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setBounds(-20, 880, 240, 100);
        backButton.setIcon(icon_bcak);
        centerPanel.add(backButton);

    }

    /**
     * loading a panel into the center panel of main frame
     * note that it will take up almost whole frame
     * @param panel the panel to be loaded
     */
    public void loadPanel(JPanel panel){
        centerPanel.add(panel, 0);
    }
    public Component getLoadedPanel(){
        return centerPanel.getComponent(0);
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
