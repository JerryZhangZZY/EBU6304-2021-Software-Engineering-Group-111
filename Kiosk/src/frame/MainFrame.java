package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @version 1.0
 * @author zaitian
 * @date 3/18
 * initial version main frame
 */

public class MainFrame extends JFrame {

    private JPanel contentPane;
    private JPanel topPanel;
    private JLabel welcomeLabel;
    private JButton exitButton;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JButton backButton;

    /**
     * Create the frame, initializing panels.
     */
    public MainFrame() {
        /*
         * basic settings
         */
        setTitle("KIOSK");
        setResizable(false);
        setUndecorated(true);
        //setSize(1600, 900);
        //setSize(1280, 720);
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

        welcomeLabel = new JLabel("Welcome, iKun!");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        welcomeLabel.setBounds(40, 20, 400, 60);
        topPanel.add(welcomeLabel);

        exitButton = new JButton("Exit (icon)");
        exitButton.setBounds(1660, 20, 180, 60);
        topPanel.add(exitButton);

        /*
        for developers to exit program easily
        note that for ordinary users, clicking the normal exit just return to welcome page
         */
        JButton ForcedExitButton = new JButton("Forced Exit");
        ForcedExitButton.setFont(new Font("Arial", Font.PLAIN, 24));
        ForcedExitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        ForcedExitButton.setBounds(1400, 20, 180, 60);
        topPanel.add(ForcedExitButton);
        /*
        main panel that holds other panels
        use centerPanel.add(JPanel panel) to add components
         */
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.LIGHT_GRAY);
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

        backButton = new JButton("Back (icon)");
        backButton.setBounds(40, 20, 180, 60);
        bottomPanel.add(backButton);



    }

    /**
     * loading a panel into the center panel of main frame
     * note that it will take up almost whole frame
     * @param panel the panel to be loaded
     */
    public void loadPanel(JPanel panel){
        centerPanel.add(panel);
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
