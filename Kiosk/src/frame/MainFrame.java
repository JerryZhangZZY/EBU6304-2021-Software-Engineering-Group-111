package frame;

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
 * main frame with restored ui design and controlling methods
 *
 * @version 1.3
 * @author zaitian
 * @date 3.24
 * main frame with enhanced functions and refined coding style
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

    /**
     * Main frame with panels initialized.
     */
    public MainFrame() {
        /*
         * basic settings
         */
        setTitle("KIOSK");
        setResizable(false);
        setUndecorated(true);
        setBounds(new Rectangle(0, 0, 1920, 1080));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        /*
        top panel, with welcome text and exit button
         */
        topPanel = new JPanel();
        topPanel.setBackground(Color.ORANGE);
        topPanel.setBounds(0, 0, 1920, 100);
        contentPane.add(topPanel);
        topPanel.setLayout(null);

        welcomeLabel = new JLabel();
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        welcomeLabel.setBounds(40, 20, 1000, 60);
        topPanel.add(welcomeLabel);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 26));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.WHITE);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setIcon(new ImageIcon("Kiosk/icons/exit.png"));
        exitButton.setBounds(1700, 8, 200, 77);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                State.setIsReady(new boolean[]{true, true, true,
                        false, false, false, false, true, true});

                State.setPc(0);
            }
        });
        topPanel.add(exitButton);
        /*
        for developers to exit program easily
        note that for ordinary users, clicking the normal exit just return to welcome page
         */
        ForcedExitButton = new JButton("Forced Exit");
        ForcedExitButton.setFont(new Font("Arial", Font.PLAIN, 24));
        ForcedExitButton.setBounds(1400, 20, 180, 60);
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
        contentPane.add(centerPanel, 0);
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
//        backButton.setIcon(icon_back);
        backButton.setIcon(new ImageIcon("Kiosk/icons/back.png"));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (State.getPc()) {
                    case 2://alt ID
                    case 4://seat
                    case 5://food
                        break;
                    case 6://bill
                        State.setIsReady(false, State.getPc());
                        break;
                    case 7://pay
                }
                State.setPc(State.getPc() - 1);
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
        centerPanel.add(panel, 0);
    }
    /**
     * access the panel loaded in the center panel
     * @return the panel loaded in the center panel, if exists
     */
    public Component getLoadedPanel(){
        if(centerPanel.getComponentCount()>0)
            return centerPanel.getComponent(0);
        else
            return null;
    }
    /**
     * unload a panel from the center panel
     * nothing is down if no panel exists
     * @param panel the panel to be unloaded
     */
    public void unloadPanel(Component panel){
        if(panel != null)
            centerPanel.remove(panel);
    }
    /**
     * deciding whether components are displayed
     * @param welcome welcome text
     * @param exit exit button
     * @param back back button
     */
    public void displayComponents(Boolean welcome, Boolean exit, Boolean back){
        welcomeLabel.setVisible(welcome);
        exitButton.setVisible(exit);
        backButton.setVisible(back);
    }
    /**
     * deciding whether bars are displayed
     * @param flag top and bottom bars
     */
    public void hideBars(Boolean flag){
        if (flag){
            topPanel.setVisible(false);
            bottomPanel.setVisible(false);
            centerPanel.setBounds(0,0,1920,1080);
        }
        else {
            topPanel.setVisible(true);
            bottomPanel.setVisible(true);
            centerPanel.setBounds(0, 100, 1920, 880);
        }
    }
    public void setWelcomeText(){
        welcomeLabel.setText("Welcome, " + State.getPassengerName());
    }
    public void resetWelcomeText(int page){
        if(page == 1){
            welcomeLabel.setText("Welcome to Beijing International Airport");
        }
    }

}
