package panel;

import main.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @version 1.0
 * @date 3/24
 * @author zaitian
 */
public class WelcomePanel extends JPanel {
    JLabel background;
    JLabel welcomeLabel;
    JLabel hintLabel;
    JPanel mask;

    public WelcomePanel(){
        setBounds(new Rectangle(0, 0, 1920, 1080));
        setLayout(null);

        background = new JLabel(new ImageIcon("Kiosk/icons/bg.png"));
        background.setBounds(0, 0, 1920, 1080);
        add(background);

        welcomeLabel = new JLabel("Welcome to Beijing International Airport!");
        welcomeLabel.setBounds(360, 100,1200,200);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 64));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel, 0);

        hintLabel = new JLabel("Click anywhere to start your check-in");
        hintLabel.setBounds(360, 100, 1200, 1600);
        hintLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        hintLabel.setForeground(Color.white);
        hintLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(hintLabel, 0);

        mask = new JPanel();
        mask.setBounds(0, 0, 1920, 1080);
        mask.setOpaque(false);
        add(mask, 0);

        mask.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                System.out.println("clicked");
                State.setPc(State.getPc()+1);
            }
        });
    }
}
