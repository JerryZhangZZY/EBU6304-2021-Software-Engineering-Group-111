package BackendSystemFrame;
/**
 * the main frame of the backend system
 *
 * @author Wang Chenyu
 * @date 2022/3/26
 * @version 1.0
 */
import Backendmain.Systempointer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackendMainFrame extends JFrame {
    JLabel title = new JLabel();
    JButton exit_button1 = new JButton("Exit");
    JPanel title_Panel = new JPanel();
    JPanel  background_Panel = new JPanel();
    JPanel mainPanel = new JPanel();
    public BackendMainFrame(){

        setTitle("BACKEND_SYSTEM");
        setResizable(false);
        setUndecorated(true);
        setBounds(new Rectangle(0, 0, 1920, 1080));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        background_Panel.setLayout(null);
        setContentPane(background_Panel);

        title_Panel = new JPanel();
        title_Panel.setBackground(Color.ORANGE);
        title_Panel.setBounds(0, 0, 1920, 100);
        background_Panel.add(title_Panel);
        title_Panel.setLayout(null);

        exit_button1.setFont(new Font("Arial", Font.PLAIN, 26));
        exit_button1.setForeground(Color.WHITE);
        exit_button1.setBackground(Color.WHITE);
        exit_button1.setContentAreaFilled(false);
        exit_button1.setBorderPainted(false);
        exit_button1.setBounds(1700, 8, 200, 77);
        title_Panel.add(exit_button1);
        exit_button1.addActionListener(new action1());



        title.setText("Hello! Welcome to use backend system!");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Eras Bold ITC", Font.BOLD, 37));
        title.setBounds(40, 20, 1000, 60);
        title_Panel.add(title);

        mainPanel.setBackground(new Color(244, 244, 244));
        mainPanel.setBounds(0, 100, 1920, 980);
        background_Panel.add(mainPanel, 0);
        mainPanel.setLayout(null);

    }

    public void loadPanel(JPanel panel){
        mainPanel.add(panel, 0);
    }

    public void unloadPanel(Component panel){
        if(panel != null)
            mainPanel.remove(panel);
    }

    public Component getLoadedPanel(){
        if(mainPanel.getComponentCount()>0)
            return mainPanel.getComponent(0);
        else
            return null;
    }

    public void setWelcomeText(){
        title.setText("Welcome, " + Systempointer.getName()+"!");
    }

    public void resetWelcomeText(){
            title.setText("Hello! Welcome to use backend system!");
    }

    private class action1 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }
}

