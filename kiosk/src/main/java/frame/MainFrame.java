package frame;

import main.Clock;
import main.Config;
import main.State;
import main.Theme;
import panel.WelcomePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;

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
 * @author Liang Zhehao
 *
 * @version 5.1
 * Add AboutListener
 * @date 2022/5/22
 *
 * @version 5.0
 * Support AboutSatisflight.
 * @date 2022/5/21
 *
 * @version 4.2
 * Added no-operation detection
 * @date 2022/5/11
 *
 * @version 4.1
 * Remove animation config check
 * @date 2022/5/11
 *
 * @version 4.0
 * New method to refresh colors.
 * @date 2022/5/7
 *
 * @version 3.4
 * Button pressed color adapt to theme.
 * @date 2022/4/26
 *
 * @version 3.3
 * GUI changes.
 * @date 2022/4/26
 *
 * @version 3.2
 * Added function: Automatically exit in a fixed time.
 * @date 2022/4/26
 *
 * @version 3.1
 * Animation speed can be set in config.
 * @date 2022/4/24
 *
 * @version 3.0
 * Delete useless codes.
 * @date 2022/4/24
 *
 * @version 2.3
 * add new admin exit
 * @date 4/11
 *
 * @version 2.2
 * Added overall clock of the system
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
    private JLabel lblWelcome;
    private JButton btnExit;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JButton btnBack;
    private JLabel lblSatisflight;
    private WelcomePanel welcomePanel;
    private JLabel lblClock;
    private JLabel lblTimer;
    private Toolkit container;
    private AWTEventListener mouseListener;
    private int mouseListenerFlag = 0;

    private int freshTime = 8;

    /**
     * Main frame with panels initialized.
     */
    public MainFrame() {

        UIManager.put("Button.select", Theme.getButtonPressedColor());

        try {
            int animationSpeed = Integer.parseInt(Config.readConfig("animationSpeed"));
            switch (animationSpeed) {
                case -1 -> freshTime = 0;
                case 1 -> freshTime = 17;
                case 2 -> freshTime = 12;
                case 3 -> freshTime = 8;
                case 4 -> freshTime = 5;
                case 5 -> freshTime = 2;
            }
        } catch (Exception ignored) {}

        welcomePanel = new WelcomePanel();
        welcomePanel.setLocation(0, -1080);
        add(welcomePanel);

        /*
         * basic settings
         */
        setTitle("Satisflight Check-in System");
        setIconImage(new ImageIcon("kiosk/icons/satisflight.png").getImage());
        setResizable(false);
        setUndecorated(true);
        setBounds(new Rectangle(0, 0, 1920, 1080));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        /*
         * top panel, with welcome text and exit button
         */
        topPanel = new JPanel();
        topPanel.setBackground(Theme.getThemeColor());
        topPanel.setBounds(0, 0, 1920, 100);
        topPanel.setLayout(null);
        add(topPanel);

        lblWelcome = new JLabel();
        lblWelcome.setFont(new Font("Arial", Font.PLAIN, 45));
        lblWelcome.setForeground(Theme.getMinorFontColor());
        lblWelcome.setBounds(50, 20, 1000, 60);
        topPanel.add(lblWelcome);

        lblClock = new JLabel();
        lblClock.setForeground(Theme.getMinorFontColor());
        lblClock.setBounds(810, 20, 300, 60);
        lblClock.setHorizontalAlignment(SwingConstants.CENTER);
        lblClock.setFont(new Font("Arial", Font.BOLD, 30));
        Clock.setClock(lblClock);
        lblClock.setVisible(false);
        topPanel.add(lblClock);

        lblTimer = new JLabel();
        lblTimer.setForeground(Theme.getMinorFontColor());
        lblTimer.setBounds(1680, 20, 100, 60);
        lblTimer.setHorizontalAlignment(SwingConstants.TRAILING);
        lblTimer.setFont(new Font("Arial", Font.BOLD, 45));
        lblTimer.setVisible(false);
        Clock.loadTimer(lblTimer);
        topPanel.add(lblTimer);

        container = Toolkit.getDefaultToolkit();
        mouseListener = new AWTEventListener() {
            public void eventDispatched(AWTEvent e) {
                //System.out.println(e);
                Clock.stopIdleTimer();
                Clock.setIdleTimer(15000);
            }
        };

        btnExit = new JButton();
        btnExit.setRequestFocusEnabled(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setBorderPainted(false);
        btnExit.setIcon(new ImageIcon("kiosk/icons/exit.png"));
        btnExit.setBounds(1815, 12, 80, 70);
        btnExit.setFocusPainted(false);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                 * alt + exit: for developers to exit program easily and elegantly
                 * note that for ordinary users, clicking the exit just return to welcome page
                 */
                if ((e.getModifiers() & ActionEvent.ALT_MASK) != 0) {
                    callAdminConsole();
                }
                else {
                    State.setIsReady(new boolean[]{true, true, true,
                            false, false, false, false, true, true});
                    Clock.stopCheckinTimer();
                    Clock.stopIdleTimer();
                    State.setPc(0);
                }
            }
        });
        topPanel.add(btnExit);
        /*
         * main panel that holds other panels
         * use centerPanel.add(JPanel panel) to add components
         */
        centerPanel = new JPanel();
        centerPanel.setBackground(Theme.getBackgroundColor());
        centerPanel.setBounds(0, 100, 1920, 880);
        add(centerPanel);
        centerPanel.setLayout(null);
        /*
         * bottom panel, with back button
         */
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Theme.getThemeColor());
        bottomPanel.setBounds(0, 980, 1920, 100);
        add(bottomPanel);
        bottomPanel.setLayout(null);

        btnBack = new JButton();
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setBounds(25, 11, 80, 80);
        ImageIcon backIcon = new ImageIcon("kiosk/icons/back.png");
        backIcon.setImage(backIcon.getImage().getScaledInstance(80, 70, Image.SCALE_SMOOTH));
        btnBack.setIcon(backIcon);
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnBack.setEnabled(false);
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
        bottomPanel.add(btnBack);

        AboutListener aboutListener = new AboutListener();

        lblSatisflight = new JLabel("Satisflight Check-in System");
        lblSatisflight.setBounds(1350, 10, 550, 80);
        lblSatisflight.setFont(new Font("Arial", Font.PLAIN, 40));
        lblSatisflight.setForeground(Theme.getMinorFontColor());
        Image logoImage = new ImageIcon("kiosk/icons/satisflight.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        lblSatisflight.setIcon(new ImageIcon(logoImage));
        lblSatisflight.addMouseListener(aboutListener);
        bottomPanel.add(lblSatisflight);
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
        lblWelcome.setVisible(welcome);
        btnExit.setVisible(exit);
        btnBack.setVisible(back);
    }
    public void showClock(Boolean flag){
        lblClock.setVisible(flag);
    }

    /**
     * set welcome text with passenger's name when available
     */
    public void setWelcomeText(){
        showClock(true);
        lblWelcome.setText("Welcome, " + State.getPassengerName());
    }

    /**
     * reset welcome text to default when passenger's name is not available
     */
    public void resetWelcomeText() {
        lblWelcome.setText(
                "Welcome to " + Config.readConfig(("airportName")) + "!");
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
                        sleep(freshTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                centerPanel.getComponent(1).setLocation(0, 0);
                centerPanel.remove(centerPanel.getComponent(0));
                repaint();
                revalidate();
                btnBack.setEnabled(true);
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
                        sleep(freshTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                centerPanel.getComponent(1).setLocation(0, 0);
                centerPanel.remove(centerPanel.getComponent(0));
                repaint();
                revalidate();
                btnBack.setEnabled(true);
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
                    moveVertical(welcomePanel, (int)(0.053 * frame * (frame - 50)));
                    try {
                        sleep(freshTime);
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
                    moveVertical(welcomePanel, (int)(-0.053 * frame * (frame - 50)));
                    try {
                        sleep(freshTime);
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
     * calling of admin console
     */
    private void callAdminConsole() {
        new AdminConsole(this);
    }

    private void callAboutSatisflight() {
        new AboutSatisflight(this);
    }

    public void refreshColor() {
        UIManager.put("Button.select", Theme.getButtonPressedColor());
        topPanel.setBackground(Theme.getThemeColor());
        lblWelcome.setForeground(Theme.getMinorFontColor());
        lblWelcome.setForeground(Theme.getMinorFontColor());
        lblClock.setForeground(Theme.getMinorFontColor());
        lblTimer.setForeground(Theme.getMinorFontColor());
        centerPanel.setBackground(Theme.getBackgroundColor());
        bottomPanel.setBackground(Theme.getThemeColor());
        lblSatisflight.setForeground(Theme.getMinorFontColor());
    }

    public void mouseListener(){
        if(mouseListenerFlag == 0){
            long eventMask = AWTEvent.MOUSE_EVENT_MASK;
            container.addAWTEventListener(mouseListener,eventMask);
            mouseListenerFlag = 1;
        }
    }

    public void stopMouseListener(){
        if(mouseListenerFlag == 1){
            container.removeAWTEventListener(mouseListener);
            Clock.stopIdleTimer();
            mouseListenerFlag = 0;
        }
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public class AboutListener implements MouseListener {

        private int counter = 5;
        private boolean j = false;
        private Timer timer = new Timer();
        @Override
        public void mouseClicked(MouseEvent e) {
            if (!j) {
                j = true;
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        counter = 5;
                        j = false;
                    }
                };
                timer.schedule(timerTask, 2000);
            }
            counter--;
            System.out.println(counter);
            if (counter == 0) {
                counter = 5;
                callAboutSatisflight();
            }
        }
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }
}
