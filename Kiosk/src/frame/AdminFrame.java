package frame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;

public class AdminFrame extends JFrame {
    JPanel contentPane = new JPanel();

    public AdminFrame(){
        setBounds(new Rectangle(720, 360, 480, 270));
        setSize(480, 270);
        setUndecorated(true);
        setAlwaysOnTop(true);
        contentPane.setBackground(new Color(15, 15, 15));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JTextArea terminal = new JTextArea();
//        terminal.setBounds(10, 10, 700, 340);
        terminal.setBounds(new Rectangle(10, 10, 460, 250));
        terminal.setBackground(new Color(16, 16,16));
        terminal.setForeground(Color.white);
        terminal.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        terminal.setFont(new Font("Consolas", Font.PLAIN, 16));
        contentPane.add(terminal);

        terminal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    System.out.println(e.getKeyCode());
                    String[] commands = terminal.getText().split("\n");
                    String command = commands[commands.length-1];
                    if (command.equals("exeo")){
                        System.exit(0);
                    }
                    else if (command.equals("resumo")){
                        setVisible(false);
                    }
                }
            }
        });
        setVisible(true);
    }
}
