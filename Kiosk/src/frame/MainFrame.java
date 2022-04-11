package frame;

import main.State;
import panel.WelcomePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.*;

import static java.lang.Thread.sleep;

/**
 * main frame that holds panels for different pages
 * only one panel is loaded at a time
 * a panel must be unloaded before loading another one
 *
 * @author zaitian
 * @author wcy
 * @author Zhang Zeyu
 * @author Ni Ruijie
 *
 * @version 2.2
 * Added overall timer of the system
 * @date 2022/4/11
 *
 * @version 2.1
 * Add animation methods and move welcome panel to here.
 * @date 2022/4/11
 *
 * @version 2.0
 * Add Satisflight logo.
 * @date 2022/4/1
 *
 * @version 1.4
 * appearance improvement
 * @date 2022/3/27
 *
 * @version 1.3
 * main frame with enhanced functions and refined coding style
 * @date 3.24
 *
 * @version 1.2
 * main frame with restored ui design and controlling methods
 * @date 3/22
 *
 * @version 1.1
 * main frame with icon added and db_reader embedded
 * @date 3/21
 *
 * @version 1.0
 * initial version main frame
 * @date 3/18
 */

public class MainFrame extends JFrame {

    private JPanel topPanel;
    private JLabel welcomeLabel;
    private JButton exitButton;
    private JButton forcedExitButton;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JButton backButton;
    private JLabel satisflightLabel;
    private WelcomePanel welcomePanel;
    private JLabel time;

    private final int TIME_INTERVAL = 8;

    /**
     * Main frame with panels initialized.
     */
    public MainFrame() {

        welcomePanel = new WelcomePanel();
        welcomePanel.setLocation(0, -1080);
        add(welcomePanel);

        /*
         * basic settings
         */
        setTitle("Satisflight - Kiosk");
        setResizable(false);
        setUndecorated(true);
        setBounds(new Rectangle(0, 0, 1920, 1080));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        /*
         * top panel, with welcome text and exit button
         */
        topPanel = new JPanel();
        topPanel.setBackground(new Color(11, 89, 167));
        topPanel.setBounds(0, 0, 1920, 100);
        add(topPanel);
        topPanel.setLayout(null);

        welcomeLabel = new JLabel();
        welcomeLabel.setFont(new Font("Calibre", Font.PLAIN, 45));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBounds(60, 20, 1000, 60);
        topPanel.add(welcomeLabel);

        time = new JLabel();
        time.setForeground(Color.WHITE);
        time.setBounds(940, 20, 500, 60);
        time.setFont(new Font("Arial", Font.BOLD, 30));
        topPanel.add(time);

        exitButton = new JButton();
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setIcon(new ImageIcon("Kiosk/icons/exit.png"));
        exitButton.setBounds(1815, 12, 80, 70);
//        exitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                State.setIsReady(new boolean[]{true, true, true,
//                        false, false, false, false, true, true});
//
//                State.setPc(0);
//            }
//        });
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.isAltDown()) {
                    AdminFrame adminFrame = new AdminFrame();
                }
                else {
                    State.setIsReady(new boolean[]{true, true, true,
                            false, false, false, false, true, true});
                    State.setPc(0);
                }
            }
        });
        topPanel.add(exitButton);
        /*
         * for developers to exit program easily
         * note that for ordinary users, clicking the normal exit just return to welcome page
         */
        forcedExitButton = new JButton("Forced Exit");
        forcedExitButton.setFont(new Font("Arial", Font.PLAIN, 24));
        forcedExitButton.setBounds(1600, 20, 180, 60);
        forcedExitButton.setBackground(new Color(11, 89, 167));
        forcedExitButton.setForeground(Color.DARK_GRAY);
        forcedExitButton.setBorder(null);
        forcedExitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        forcedExitButton.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_ENTER){
                    System.exit(0);
                }
            }
            });
        topPanel.add(forcedExitButton);
        /*
         * main panel that holds other panels
         * use centerPanel.add(JPanel panel) to add components
         */
        centerPanel = new JPanel();
        centerPanel.setBackground(new Color(244, 244, 244));
        centerPanel.setBounds(0, 100, 1920, 880);
        add(centerPanel);
        centerPanel.setLayout(null);
        /*
         * bottom panel, with back button
         */
        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(11, 89, 167));
        bottomPanel.setBounds(0, 980, 1920, 100);
        add(bottomPanel);
        bottomPanel.setLayout(null);

        backButton = new JButton();
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setBounds(25, 11, 80, 80);
        ImageIcon backIcon = new ImageIcon("Kiosk/icons/back.png");
        backIcon.setImage(backIcon.getImage().getScaledInstance(80, 70, Image.SCALE_SMOOTH));
        backButton.setIcon(backIcon);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backButton.setEnabled(false);
                switch (State.getPc()) {
                    case 2://alt ID
                        break;
                    case 4://seat
                        break;
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

        satisflightLabel = new JLabel("Satisflight Check-in System");
        satisflightLabel.setBounds(1350, 10, 550, 80);
        satisflightLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        satisflightLabel.setForeground(Color.WHITE);
        satisflightLabel.setIcon(new ImageIcon("Kiosk/icons/satisflight.png"));
        bottomPanel.add(satisflightLabel);
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
     * nothing is done if no panel exists
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
//            centerPanel.setBounds(0,0,1920,1080);
        }
        else {
            topPanel.setVisible(true);
            bottomPanel.setVisible(true);
//            centerPanel.setBounds(0, 100, 1920, 880);
        }
    }
    /**
     * set welcome text with passenger's name when available
     */
    public void setWelcomeText(){
        this.setTimer(time);
        welcomeLabel.setText("Welcome, " + State.getPassengerName());
    }

    /**
     * reset welcome text to default when passenger's name is not available
     */
    public void resetWelcomeText() {
        welcomeLabel.setText("Welcome to Beijing International Airport");
    }

    /**
     * Move component vertically.
     * @param component thing you want to move
     * @param distance how many pixels you want it to move
     */
    public void moveVertical(Component component, int distance) {
        component.setLocation(component.getX(), component.getY() + distance);
    }

    /**
     * Animation of switching to a latter panel.
     * @param nextPanel new panel to switch to
     */
    public void scrollDown(JPanel nextPanel) {
        nextPanel.setLocation(0, 880);
        centerPanel.add(nextPanel);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int frame = 0; frame < 50; frame++) {
                    moveVertical(centerPanel.getComponent(0), (int)(0.043 * frame * (frame - 50)));
                    moveVertical(centerPanel.getComponent(1), (int)(0.043 * frame * (frame - 50)));
                    try {
                        Thread.sleep(TIME_INTERVAL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                centerPanel.getComponent(1).setLocation(0, 0);
                centerPanel.remove(centerPanel.getComponent(0));
                repaint();
                revalidate();
                backButton.setEnabled(true);
            }
        });
        thread.start();
    }

    /**
     * Animation of switching to a previous panel.
     * @param nextPanel new panel to switch to
     */
    public void scrollUp(JPanel nextPanel) {
        nextPanel.setLocation(0, -880);
        centerPanel.add(nextPanel);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int frame = 0; frame < 50; frame++) {
                    moveVertical(centerPanel.getComponent(0), (int)(-0.043 * frame * (frame - 50)));
                    moveVertical(centerPanel.getComponent(1), (int)(-0.043 * frame * (frame - 50)));
                    try {
                        Thread.sleep(TIME_INTERVAL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                centerPanel.getComponent(1).setLocation(0, 0);
                centerPanel.remove(centerPanel.getComponent(0));
                repaint();
                revalidate();
                backButton.setEnabled(true);
            }
        });
        thread.start();
    }

    /**
    * Animation of unlocking screen.
    */
    public void unlockScreen() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int frame = 0; frame < 50; frame++) {
                    moveVertical(welcomePanel, (int)(0.052 * frame * (frame - 50)));
                    try {
                        Thread.sleep(TIME_INTERVAL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                welcomePanel.setLocation(0, -1080);
                repaint();
                revalidate();
            }
        });
        thread.start();
    }

    /**
     * Animation of locking screen.
     */
    public void lockScreen() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int frame = 0; frame < 50; frame++) {
                    moveVertical(welcomePanel, (int)(-0.052 * frame * (frame - 50)));
                    try {
                        Thread.sleep(TIME_INTERVAL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                welcomePanel.setLocation(0, 0);
                repaint();
                revalidate();
            }
        });
        thread.start();
    }

    /**
     * Set the timer
     * @param time panel of the timer
     */
    private void setTimer(JLabel time) {
        final JLabel varTime = time;
        Timer timeAction = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long timemillis = System.currentTimeMillis();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                varTime.setText(df.format(new Date(timemillis)));
            }
        });
        timeAction.start();
    }
}
