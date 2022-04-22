package panel;

import main.Clock;
import main.Config;
import main.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * the panel that displays when kiosk is vaccant
 * @author zaitian
 * @author Zhang Zeyu
 * @author Ni Ruijie
 *
 * @version 2.0
 * Added clock
 * @date 2022/4/11
 *
 * @version 1.1
 * Change font.
 * @date 2022/3/26
 *
 * @version 1.0
 * @date 3/24
 */
public class WelcomePanel extends JPanel {
    JLabel background;
    JLabel welcomeLabel;
    JLabel hintLabel;
    /** mask over other components, to be clicked**/
    JButton mask;
    JLabel time;

    public WelcomePanel(){
        setBounds(new Rectangle(0, 0, 1920, 1080));
        setLayout(null);
        /*
        background img
         */
        background = new JLabel(new ImageIcon("Kiosk/icons/bg.png"));
        background.setBounds(0, 0, 1920, 1080);
        add(background);
        /*
        a big welcome
         */
        welcomeLabel = new JLabel(
                "Welcome to " + Config.readConfig(("airportName")) + "!");
        welcomeLabel.setBounds(210, 150,1500,200);
        welcomeLabel.setFont(new Font("Calibri", Font.BOLD, 80));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel, 0);
        /*
        hint for continuing
         */
        hintLabel = new JLabel("click anywhere to start your check-in");
        hintLabel.setBounds(360, 100, 1200, 1600);
        hintLabel.setFont(new Font("Calibri", Font.PLAIN, 36));
        hintLabel.setForeground(Color.lightGray);
        hintLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(hintLabel, 0);
        /*
        clock at home page
         */
        time = new JLabel();
        time.setBounds(760, 20, 400, 30);
        time.setFont(new Font("Default", Font.PLAIN, 30));
        Clock.setClock(time);
        time.setHorizontalAlignment(SwingConstants.CENTER);
        add(time,0);
        /*
        mask to click and move on
         */
        mask = new JButton();
        mask.setIcon(new ImageIcon("Kiosk/icons/mask"));
        mask.setBounds(0, 0, 1920, 1080);
        mask.setContentAreaFilled(false);
        mask.setOpaque(false);
        mask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                State.setPc(1);
            }
        });
        remove(this.mask);
        add(mask);
    }
    /**
     * mask getter
     * @return mask that lies over other components
     */
    public JButton getMask(){
        return mask;
    }
}
