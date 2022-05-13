package panel;

import main.Clock;
import main.Config;
import main.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * the panel that displays when kiosk is vaccant
 * @author zaitian
 * @author Zhang Zeyu
 * @author Ni Ruijie
 *
 * @version 3.0
 * Better way of setting background image.
 * @date 2022/4/23
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
    JLabel lblWelcome;
    JLabel lblHint;
    /** mask over other components, to be clicked**/
    JButton btnMask;
    JLabel lblTime;

    public WelcomePanel(){
        setBounds(new Rectangle(0, 0, 1920, 1080));
        setLayout(null);
        /*
        a big welcome
         */
        lblWelcome = new JLabel(
                "Welcome to " + Config.readConfig(("airportName")) + "!");
        lblWelcome.setBounds(210, 150,1500,200);
        lblWelcome.setFont(new Font("Calibri", Font.BOLD, 80));
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblWelcome, 0);
        /*
        hint for continuing
         */
        lblHint = new JLabel("click anywhere to start your check-in");
        lblHint.setBounds(360, 100, 1200, 1600);
        lblHint.setFont(new Font("Calibri", Font.PLAIN, 36));
        lblHint.setForeground(Color.lightGray);
        lblHint.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblHint, 0);
        /*
        clock at home page
         */
        lblTime = new JLabel();
        lblTime.setBounds(760, 20, 400, 30);
        lblTime.setFont(new Font("Default", Font.PLAIN, 30));
        Clock.setClock(lblTime);
        lblTime.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTime,0);
        /*
        mask to click and move on
         */
        btnMask = new JButton();
        btnMask.setBounds(0, 0, 1920, 1080);
        btnMask.setContentAreaFilled(false);
        btnMask.setOpaque(false);
        btnMask.setBorderPainted(false);
        btnMask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                State.setPc(1);
            }
        });
        remove(this.btnMask);
        add(btnMask);
    }
    /**
     * mask getter
     * @return mask that lies over other components
     */
    public JButton getBtnMask(){
        return btnMask;
    }

    /*
    set background
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("kiosk/icons/bg.png").getImage(), 0, 0,this.getWidth(), this.getHeight(), this);
    }
}
