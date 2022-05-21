package frame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;

/**
 * small window for admin to exit the programme
 * @author zaitian
 *
 * @version 1.0
 * @date 4/11
 *
 * @version 1.1
 * improve interaction
 * @date 4/13
 */
public class AdminConsole extends JFrame {
    JPanel contentPane = new JPanel();

    public AdminConsole(JFrame parent){
        setIconImage(new ImageIcon("kiosk/icons/satisflight.png").getImage());
        setBounds(new Rectangle(720, 360, 480, 270));
        setSize(480, 270);
        setUndecorated(true);
        setAlwaysOnTop(true);
        contentPane.setBackground(new Color(15, 15, 15));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextArea terminal = new JTextArea();
        terminal.setBounds(new Rectangle(10, 10, 460, 250));
        terminal.setBackground(new Color(16, 16,16));
        terminal.setForeground(Color.white);
        terminal.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        terminal.setFont(new Font("Consolas", Font.PLAIN, 16));
        terminal.setText("To resume checking-in, enter \"resumo\"\n");
        contentPane.add(terminal);

        parent.setEnabled(false);

        terminal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    String[] commands = terminal.getText().split("\n");
                    String command = commands[commands.length-1];
                    if (command.equals("exeo")){
                        System.exit(0);
                    }
                    else if (command.equals("resumo")){
                        parent.setEnabled(true);
                        setVisible(false);
                    }
                }
            }
        });
        setVisible(true);
    }
}
