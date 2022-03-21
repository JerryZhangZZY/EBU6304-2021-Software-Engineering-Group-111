package card;
/**
 * This class can return a panel of a flight info card.
 *
 * @author Wang Chenyu
 * @date 2022/3/19
 * @version 1.0
 */
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.AbstractAction;




public class seatInfoCard extends JPanel {
    private int[] avail_seat = new int[6];
    private int row;
    private int temp_row = -1;
    private int temp_column = -1;
    //button init
    JButton btnNewButton = new JButton("");
    JButton btnNewButton2 = new JButton("");
    JButton btnNewButton3 = new JButton("");
    JButton btnNewButton4 = new JButton("");
    JButton btnNewButton5 = new JButton("");
    JButton btnNewButton6 = new JButton("");
    JTextField row_num= new JTextField();
    //icon loading
    ImageIcon icon1_empty = new ImageIcon("Kiosk/src/icons/avail.png");
    Image img_empty = icon1_empty.getImage();
    Image newimg_empty = img_empty.getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH);
    ImageIcon icon_empty = new ImageIcon(newimg_empty);
    ImageIcon icon1_occu = new ImageIcon("Kiosk/src/icons/occu.png");
    Image img_occu = icon1_occu.getImage();
    Image newimg_occu = img_occu.getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH);
    ImageIcon icon_occu = new ImageIcon(newimg_occu);
    ImageIcon icon1_chonse = new ImageIcon("Kiosk/src/icons/chosen.png");
    Image img_chonse = icon1_chonse.getImage();
    Image newimg_chonse = img_chonse.getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH);
    ImageIcon icon_chonse = new ImageIcon(newimg_chonse);

    public seatInfoCard(int[] avail_seat, int row) {

        for (int i = 0; i < 6; i++) {
            this.avail_seat[i] = avail_seat[i];
        }
        this.row = row;
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBackground(Color.WHITE);
        setLayout(null);
        setSize(1465, 235);
        //add bound icon
        JLabel right_label = new JLabel();
        right_label.setSize(75, 262);
        JLabel left_label = new JLabel();
        left_label.setSize(147, 206);
        right_label.setIcon(new ImageIcon("Kiosk/src/icons/rightbound.png"));
        left_label.setIcon(new ImageIcon("Kiosk/src/icons/leftbound.png"));
        add(right_label);
        add(left_label);
        right_label.setLocation(1380, -19);
        left_label.setLocation(65, 10);
        //add mid_line
        JLabel mright_label = new JLabel();
        mright_label.setSize(118, 199);
        JLabel mleft_label = new JLabel();
        mleft_label.setSize(118, 168);
        mright_label.setIcon(new ImageIcon("Kiosk/src/icons/mid.png"));
        mleft_label.setIcon(new ImageIcon("Kiosk/src/icons/mid.png"));
        add(mright_label);
        add(mleft_label);
        mright_label.setLocation(771, 10);
        mleft_label.setLocation(654, 25);
        //row number
        row_num.setFont(new Font("Arial", Font.PLAIN, 26));
        row_num.setText(String.valueOf(row));
        row_num.setBounds(8, 70, 41, 50);
        add(row_num);
        //seat label
        JLabel lblNewLabel = new JLabel("A");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 26));
        lblNewLabel.setBounds(180, 190, 19, 26);
        add(lblNewLabel);


        JLabel lblB = new JLabel("B");
        lblB.setFont(new Font("Arial", Font.PLAIN, 26));
        lblB.setBounds(356, 190, 19, 26);
        add(lblB);

        JLabel lblC = new JLabel("C");
        lblC.setFont(new Font("Arial", Font.PLAIN, 26));
        lblC.setBounds(515, 190, 19, 26);
        add(lblC);

        JLabel lblD = new JLabel("D");
        lblD.setFont(new Font("Arial", Font.PLAIN, 26));
        lblD.setBounds(954, 190, 19, 26);
        add(lblD);

        JLabel lblE = new JLabel("E");
        lblE.setFont(new Font("Arial", Font.PLAIN, 26));
        lblE.setBounds(1128, 190, 19, 26);
        add(lblE);

        JLabel lblF = new JLabel("F");
        lblF.setFont(new Font("Arial", Font.PLAIN, 26));
        lblF.setBounds(1292, 190, 19, 26);
        add(lblF);
        addSeatIcon(avail_seat, row);
    }

    //seat   btnNewButton.setIcon(icon);
    public void addSeatIcon(int[] avail, int row) {
        SimpleListener ourListener = new SimpleListener();
        for (int i = 0; i < 6; i++) {
            if (i == 0) {
                btnNewButton.setForeground(Color.WHITE);
                btnNewButton.setBackground(Color.WHITE);
                btnNewButton.setBounds(85, -19, 221, 220);
                btnNewButton.setContentAreaFilled(false);
                btnNewButton.setBorderPainted(false);
                add(btnNewButton);
                if (avail[i] == 1) {
                    btnNewButton.setIcon(icon_occu);
                } else {
                    btnNewButton.setIcon(icon_empty);
                    btnNewButton.addActionListener(ourListener);
                }

            }
            if (i == 1) {

                btnNewButton2.setForeground(Color.WHITE);
                btnNewButton2.setBackground(Color.WHITE);
                btnNewButton2.setBounds(252, -19, 221, 220);
                btnNewButton2.setContentAreaFilled(false);
                btnNewButton2.setBorderPainted(false);
                add(btnNewButton2);
                if (avail[i] == 1) {
                    btnNewButton2.setIcon(icon_occu);
                } else {
                    btnNewButton2.setIcon(icon_empty);
                    btnNewButton2.addActionListener(ourListener);
                }
            }
            if (i == 2) {

                btnNewButton3.setForeground(Color.WHITE);
                btnNewButton3.setBackground(Color.WHITE);
                btnNewButton3.setBounds(415, -19, 221, 220);
                btnNewButton3.setContentAreaFilled(false);
                btnNewButton3.setBorderPainted(false);
                add(btnNewButton3);
                if (avail[i] == 1) {
                    btnNewButton3.setIcon(icon_occu);

                } else {
                    btnNewButton3.setIcon(icon_empty);
                    btnNewButton3.addActionListener(ourListener);
                }
            }
            if (i == 3) {

                btnNewButton4.setForeground(Color.WHITE);
                btnNewButton4.setBackground(Color.WHITE);
                btnNewButton4.setBounds(851, -19, 221, 220);
                btnNewButton4.setContentAreaFilled(false);
                btnNewButton4.setBorderPainted(false);
                add(btnNewButton4);
                if (avail[i] == 1) {
                    btnNewButton4.setIcon(icon_occu);

                } else {
                    btnNewButton4.setIcon(icon_empty);
                    btnNewButton4.addActionListener(ourListener);
                }
            }
            if (i == 4) {

                btnNewButton5.setForeground(Color.WHITE);
                btnNewButton5.setBackground(Color.WHITE);
                btnNewButton5.setBounds(1023, -19, 221, 220);
                btnNewButton5.setContentAreaFilled(false);
                btnNewButton5.setBorderPainted(false);
                add(btnNewButton5);
                if (avail[i] == 1) {
                    btnNewButton5.setIcon(icon_occu);
                } else {
                    btnNewButton5.setIcon(icon_empty);
                    btnNewButton5.addActionListener(ourListener);
                }
            }
            if (i == 5) {
                btnNewButton6.setForeground(Color.WHITE);
                btnNewButton6.setBackground(Color.WHITE);
                btnNewButton6.setBounds(1188, -19, 221, 220);
                btnNewButton6.setContentAreaFilled(false);
                btnNewButton6.setBorderPainted(false);
                add(btnNewButton6);
                if (avail[i] == 1) {
                    btnNewButton6.setIcon(icon_occu);
                } else {
                    btnNewButton6.setIcon(icon_empty);
                    btnNewButton6.addActionListener(ourListener);
                }
            }
        }

    }


    public void setTemp_row(int temp_row) {
        this.temp_row = temp_row;
    }

    public void setTemp_column(int temp_column) {
        this.temp_column = temp_column;
    }

    public void setAvail_seat(int[] avail_seat){
        for(int i=0;i<6;i++){
            this.avail_seat[i]=avail_seat[i];
        }
    }

    public void setRow(int row){
        this.row=row;
        row_num.setText(String.valueOf(row));
    }

    public int getTemp_row(){
        return temp_row;
    }

    public int getTemp_column(){
        return temp_column;
    }

    private class SimpleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnNewButton) {
                if (temp_column == -1 && temp_row == -1) {
                    setTemp_column(0);
                    setTemp_row(row);
                    btnNewButton.setIcon(icon_chonse);
                } else {
                    if (temp_row == row && temp_column == 0) {
                        setTemp_column(-1);
                        setTemp_row(-1);
                        btnNewButton.setIcon(icon_empty);
                    } else if (temp_row == row && temp_column == 1) {
                        setTemp_column(0);
                        btnNewButton2.setIcon(icon_empty);
                        btnNewButton.setIcon(icon_chonse);
                    } else if (temp_row == row && temp_column == 2) {
                        setTemp_column(0);
                        btnNewButton3.setIcon(icon_empty);
                        btnNewButton.setIcon(icon_chonse);
                    } else if (temp_row == row && temp_column == 3) {
                        setTemp_column(0);
                        btnNewButton4.setIcon(icon_empty);
                        btnNewButton.setIcon(icon_chonse);
                    } else if (temp_row == row && temp_column == 4) {
                        setTemp_column(0);
                        btnNewButton5.setIcon(icon_empty);
                        btnNewButton.setIcon(icon_chonse);
                    } else if (temp_row == row && temp_column == 5) {
                        setTemp_column(0);
                        btnNewButton6.setIcon(icon_empty);
                        btnNewButton.setIcon(icon_chonse);
                    } else {
                        setTemp_row(row);
                        setTemp_column(0);
                        btnNewButton.setIcon(icon_chonse);
                    }

                }

            }
            else if (e.getSource() == btnNewButton2) {
                if (temp_column == -1 && temp_row == -1) {
                    setTemp_column(1);
                    setTemp_row(row);
                    btnNewButton2.setIcon(icon_chonse);
                }
                else {
                    if (temp_row == row && temp_column == 0) {
                        setTemp_column(1);
                        btnNewButton.setIcon(icon_empty);
                        btnNewButton2.setIcon(icon_chonse);
                    } else if (temp_row == row && temp_column == 1) {
                        setTemp_column(-1);
                        setTemp_row(-1);
                        btnNewButton2.setIcon(icon_empty);
                    } else if (temp_row == row && temp_column == 2) {
                        setTemp_column(1);
                        btnNewButton3.setIcon(icon_empty);
                        btnNewButton2.setIcon(icon_chonse);
                    } else if (temp_row == row && temp_column == 3) {
                        setTemp_column(1);
                        btnNewButton4.setIcon(icon_empty);
                        btnNewButton2.setIcon(icon_chonse);
                    } else if (temp_row == row && temp_column == 4) {
                        setTemp_column(1);
                        btnNewButton5.setIcon(icon_empty);
                        btnNewButton2.setIcon(icon_chonse);
                    } else if (temp_row == row && temp_column == 5) {
                        setTemp_column(1);
                        btnNewButton6.setIcon(icon_empty);
                        btnNewButton2.setIcon(icon_chonse);
                    } else {
                        setTemp_row(row);
                        setTemp_column(1);
                        btnNewButton2.setIcon(icon_chonse);
                    }

                }
            }
            else if (e.getSource() == btnNewButton3) {
                if (temp_column == -1 && temp_row == -1) {
                    setTemp_column(2);
                    setTemp_row(row);
                    btnNewButton3.setIcon(icon_chonse);
                } else {
                    if (temp_row == row && temp_column == 0) {
                        setTemp_column(2);
                        btnNewButton.setIcon(icon_empty);
                        btnNewButton3.setIcon(icon_chonse);
                    }
                    else if (temp_row == row && temp_column == 1) {
                        setTemp_column(2);
                        btnNewButton2.setIcon(icon_empty);
                        btnNewButton3.setIcon(icon_chonse);
                    }
                    else if (temp_row == row && temp_column == 2) {
                        setTemp_column(-1);
                        setTemp_row(-1);
                        btnNewButton3.setIcon(icon_empty);
                    }
                    else if (temp_row == row && temp_column == 3) {
                        setTemp_column(2);
                        btnNewButton4.setIcon(icon_empty);
                        btnNewButton3.setIcon(icon_chonse);
                    } else if (temp_row == row && temp_column == 4) {
                        setTemp_column(2);
                        btnNewButton5.setIcon(icon_empty);
                        btnNewButton3.setIcon(icon_chonse);
                    } else if (temp_row == row && temp_column == 5) {
                        setTemp_column(2);
                        btnNewButton6.setIcon(icon_empty);
                        btnNewButton3.setIcon(icon_chonse);
                    } else {
                        setTemp_row(row);
                        setTemp_column(2);
                        btnNewButton3.setIcon(icon_chonse);
                    }

                }
            }
            else if (e.getSource() == btnNewButton4) {
                if (temp_column == -1 && temp_row == -1) {
                    setTemp_column(3);
                    setTemp_row(row);
                    btnNewButton4.setIcon(icon_chonse);
                } else {
                    if (temp_row == row && temp_column == 0) {
                        setTemp_column(3);
                        btnNewButton.setIcon(icon_empty);
                        btnNewButton4.setIcon(icon_chonse);
                    }
                    else if (temp_row == row && temp_column == 1) {
                        setTemp_column(3);
                        btnNewButton2.setIcon(icon_empty);
                        btnNewButton4.setIcon(icon_chonse);
                    }
                    else if (temp_row == row && temp_column == 2) {
                        setTemp_column(3);
                        btnNewButton3.setIcon(icon_empty);
                    }
                    else if (temp_row == row && temp_column == 3) {
                        setTemp_column(-1);
                        setTemp_row(-1);
                        btnNewButton4.setIcon(icon_empty);
                    } else if (temp_row == row && temp_column == 4) {
                        setTemp_column(3);
                        btnNewButton5.setIcon(icon_empty);
                        btnNewButton4.setIcon(icon_chonse);
                    } else if (temp_row == row && temp_column == 5) {
                        setTemp_column(3);
                        btnNewButton6.setIcon(icon_empty);
                        btnNewButton4.setIcon(icon_chonse);
                    } else {
                        setTemp_row(row);
                        setTemp_column(3);
                        btnNewButton4.setIcon(icon_chonse);
                    }

                }
            }
            else if (e.getSource() == btnNewButton5) {
                if (temp_column == -1 && temp_row == -1) {
                    setTemp_column(4);
                    setTemp_row(row);
                    btnNewButton5.setIcon(icon_chonse);
                } else {
                    if (temp_row == row && temp_column == 0) {
                        setTemp_column(4);
                        btnNewButton.setIcon(icon_empty);
                        btnNewButton5.setIcon(icon_chonse);
                    }
                    else if (temp_row == row && temp_column == 1) {
                        setTemp_column(4);
                        btnNewButton2.setIcon(icon_empty);
                        btnNewButton5.setIcon(icon_chonse);
                    }
                    else if (temp_row == row && temp_column == 2) {
                        setTemp_column(4);
                        btnNewButton3.setIcon(icon_empty);
                        btnNewButton5.setIcon(icon_chonse);
                    }
                    else if (temp_row == row && temp_column == 3) {
                        setTemp_column(4);
                        btnNewButton4.setIcon(icon_empty);
                        btnNewButton5.setIcon(icon_chonse);
                    } else if (temp_row == row && temp_column == 4) {
                        setTemp_column(-1);
                        setTemp_row(-1);
                        btnNewButton5.setIcon(icon_empty);
                    } else if (temp_row == row && temp_column == 5) {
                        setTemp_column(4);
                        btnNewButton6.setIcon(icon_empty);
                        btnNewButton5.setIcon(icon_chonse);
                    } else {
                        setTemp_row(row);
                        setTemp_column(4);
                        btnNewButton5.setIcon(icon_chonse);
                    }

                }
            }
            else if (e.getSource() == btnNewButton6) {
                if (temp_column == -1 && temp_row == -1) {
                    setTemp_column(5);
                    setTemp_row(row);
                    btnNewButton6.setIcon(icon_chonse);
                } else {
                    if (temp_row == row && temp_column == 0) {
                        setTemp_column(5);
                        btnNewButton.setIcon(icon_empty);
                        btnNewButton5.setIcon(icon_chonse);
                    }
                    else if (temp_row == row && temp_column == 1) {
                        setTemp_column(5);
                        btnNewButton2.setIcon(icon_empty);
                        btnNewButton6.setIcon(icon_chonse);
                    }
                    else if (temp_row == row && temp_column == 2) {
                        setTemp_column(5);
                        btnNewButton3.setIcon(icon_empty);
                        btnNewButton6.setIcon(icon_chonse);
                    }
                    else if (temp_row == row && temp_column == 3) {
                        setTemp_column(5);
                        btnNewButton4.setIcon(icon_empty);
                        btnNewButton6.setIcon(icon_chonse);
                    } else if (temp_row == row && temp_column == 4) {
                        setTemp_column(5);
                        btnNewButton5.setIcon(icon_empty);
                        btnNewButton6.setIcon(icon_chonse);
                    } else if (temp_row == row && temp_column == 5) {
                        setTemp_column(-1);
                        setTemp_row(-1);
                        btnNewButton6.setIcon(icon_empty);
                    } else {
                        setTemp_row(row);
                        setTemp_column(5);
                        btnNewButton5.setIcon(icon_chonse);
                    }

                }
            }
        }
    }
}









