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
    private JPanel bottomPanel;
    private JButton backButton;
    private JPanel centerPanel;

    /**
     * Create the frame, initializing panels.
     */
    public MainFrame() {
        /*
         * basic settings
         */
        setTitle("KIOSK");
        setResizable(false);
        //setSize(1600, 900);
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        /*
        top panel, with welcome text and exit button
         */
        topPanel = new JPanel();
        topPanel.setBackground(Color.ORANGE);
        topPanel.setBounds(0, 0, 1280, 80);
        contentPane.add(topPanel);
        topPanel.setLayout(null);

        welcomeLabel = new JLabel("Welcome, iKun!");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        welcomeLabel.setBounds(40, 10, 400, 60);
        topPanel.add(welcomeLabel);

        exitButton = new JButton("Exit (icon)");
        exitButton.setBounds(1060, 10, 180, 60);
        topPanel.add(exitButton);
        /*
         * bottom panel, with back button
         */
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.CYAN);
        bottomPanel.setBounds(0, 612, 1280, 80);
        contentPane.add(bottomPanel);
        bottomPanel.setLayout(null);

        backButton = new JButton("Back (icon)");
        backButton.setBounds(0, 10, 180, 60);
        bottomPanel.add(backButton);

        /*
        main panel that holds other panels
        use centerPanel.add(JPanel panel) to add components
         */
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.LIGHT_GRAY);
        centerPanel.setBounds(0, 80, 1280, 530);
        contentPane.add(centerPanel);
        centerPanel.setLayout(null);

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
