package BackendSystemPanel;

import BSAdministratorDB.AdministratorReader;
import Backendmain.Systempointer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * the panel for log in
 *
 * @author Wang Chenyu
 * @date 2022/3/26
 * @version 1.0
 */
public class LogINPanel extends JPanel{
    JLabel account = new JLabel("Account");
    JLabel password = new JLabel("Password");
    JTextField account_field = new JTextField();
    JTextField password_field = new JTextField();
    JButton confirm = new JButton("confirm");
    String[] data =new String[2];
    boolean lp;
    Border borderLine = BorderFactory.createLineBorder(Color.DARK_GRAY,2,true);
    Border errorLine = BorderFactory.createLineBorder(Color.RED,3,true);
    public LogINPanel(){
        ConfirmListener action_detect = new ConfirmListener();
        setBounds(new Rectangle(0, 0, 1920, 980));
        setBackground(new Color(244, 244, 244));
        setLayout(null);
        setSize(1920, 980);

        account.setLocation(859, 129);
        account.setSize(202, 74);
        account.setFont(new Font("Helvetica", Font.BOLD, 50));
        add(account);

        password.setLocation(838, 353);
        password.setSize(243, 74);
        password.setFont(new Font("Helvetica", Font.BOLD, 50));
        add(password);

        account_field.setLocation(654, 230);
        account_field.setSize(611, 60);
        account_field.setBorder(borderLine);
        account_field.setFont(new Font("Helvetica", Font.BOLD, 36));
        add(account_field);

        password_field.setLocation(654, 440);
        password_field.setSize(611, 60);
        password_field.setBorder(borderLine);
        password_field.setFont(new Font("Helvetica", Font.BOLD, 36));
        add(password_field);

        confirm.setLocation(874, 594);
        confirm.setSize(171, 52);
        confirm.setFont(new Font("Arial", Font.BOLD, 27));
        confirm.setBackground(new Color(244,244,244));
        confirm.setBorder(borderLine);
        confirm.addActionListener(action_detect);
        add(confirm);
    }
    private class ConfirmListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
           if(account_field.getText().length()==0&&password_field.getText().length() == 0){
               account_field.setBorder(errorLine);
               password_field.setBorder(errorLine);
           }
           else if(account_field.getText().length()==0){
               account_field.setBorder(errorLine);
               password_field.setBorder(borderLine);
           }
           else if(password_field.getText().length() == 0){
               account_field.setBorder(borderLine);
               password_field.setBorder(errorLine);
           }
           else
           {
               account_field.setBorder(borderLine);
               password_field.setBorder(borderLine);
               String account_num = account_field.getText();
               AdministratorReader temp = new AdministratorReader(account_num);
               try {
                   data = temp.getPassword();
                   lp=temp.exsist();
               } catch (IOException e) {
                   e.printStackTrace();
               }
                   if(lp){
                       String password_num = password_field.getText();
                       if (password_num.equals(data[1])) {
                           Systempointer.setName(data[2]);
                           Systempointer.setPc(1);
                           account_field.setText("");
                           password_field.setText("");
                       } else {
                           password_field.setBorder(errorLine);
                           JOptionPane.showMessageDialog(null,"Password Error!","Password",JOptionPane.ERROR_MESSAGE);
                       }
                    }
                    else
                    {
                        account_field.setBorder(errorLine);
                        JOptionPane.showMessageDialog(null,"Account Error!","Account",JOptionPane.ERROR_MESSAGE);
                    }
           }

        }
    }

}
