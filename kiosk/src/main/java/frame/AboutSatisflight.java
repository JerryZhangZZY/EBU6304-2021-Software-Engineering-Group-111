package frame;

import main.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.io.Serial;
import java.net.URISyntaxException;

/**
 * This frame is an Easter Egg that show info about the software
 *
 * @author Zhang Zeyu
 *
 * @version 5.0
 * @date 2022/5/20
 */

public class AboutSatisflight extends JFrame {

    private final int width = 730;
    private final int height = 440;
    private final String repoUrl = "https://github.com/JerryZhangZZY/EBU6304-2021-Software-Engineering-Group-111";

    private JButton btnExit;
    private JLabel lblTitle;
    private JLabel lblSeparator1;
    private JLabel lblSeparator2;
    private JLabel lblSeparator3;
    private JLabel lblLogo;
    private JLabel lblHeadline;
    private JLabel lblVersion;
    private JTextArea taIntro;
    private JLabel lblVisitRepo;
    private JLabel lblRepo;
    private JTextArea taDeveloper1;
    private JTextArea taDeveloper2;

    private String title, headline, version, intro, visitRepo, repo, dev1, dev2;
    private int repoTextWidth;
    private void localize() {
        String language = Config.readConfig("language");
        if (language.equals("English")) {
            title = "About Satisflight";
            headline = "<HTML><B>Satisflight</B> Oriental</HTML>";
            version = "Version 5.0";
            intro = "Satisflight is an open source group project of Software Engineering course. The project started from 15 March 2022 and took 2.5 months to finish development.";
            visitRepo = "Visit                       to get more information.";
            repo = "GitHub Repo";
            dev1 = """
                Developer Group:
                · Zhang Zeyu
                · Wang Zaitian
                · Liang Zhehao
                """;
            dev2 = """
                
                · Ni Ruijie
                · Wang Chenyu
                · Li Chunlin
                """;
            repoTextWidth = 150;
        }
        else if (language.equals("Chinese")) {
            title = "关于Satisflight";
            headline = "<HTML><B>Satisflight</B> Oriental</HTML>";
            version = "Version 5.0";
            intro = "Satisflight 是一款为软件工程课大作业而开发的开源软件。本项目于2022年3月15日开工，历时两个半月的时间完成开发。";
            visitRepo = "访问                   以获取更多信息。";
            repo = "GitHub仓库";
            dev1 = """
                开发团队:
                · Zhang Zeyu
                · Wang Zaitian
                · Liang Zhehao
                """;
            dev2 = """
                
                · Ni Ruijie
                · Wang Chenyu
                · Li Chunlin
                """;
            repoTextWidth = 105;
        }

    }
    public AboutSatisflight(JFrame frameParent) {
        frameParent.setEnabled(false);

        localize();

        setIconImage(new ImageIcon("kiosk/icons/satisflight.png").getImage());
        setBounds((1920 - width - 4) / 2, (1080 - height - 4) / 2, width + 4, height + 4);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setShape(new RoundRectangle2D.Float(0, 0, width + 4, height + 4, 32, 32));

        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(222, 222, 222));
        add(contentPane);

        RoundedPanel mainPanel = new RoundedPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(2, 2, width, height);
        mainPanel.setBackground(Color.WHITE);
        contentPane.add(mainPanel);

        JPanel topBarPanel = new JPanel();
        topBarPanel.setLayout(null);
        topBarPanel.setBounds(0, 0, width, 47);
        topBarPanel.setBackground(new Color(249, 247, 248));
        mainPanel.add(topBarPanel);

        btnExit = new JButton();
        btnExit.setBounds(12, 12, 20, 20);
        btnExit.setIcon(new ImageIcon("kiosk/icons/aboutExit.png"));
        btnExit.setRolloverIcon(new ImageIcon("kiosk/icons/aboutExitSelected.png"));
        btnExit.setPressedIcon(new ImageIcon("kiosk/icons/aboutExitPressed.png"));
        btnExit.setContentAreaFilled(false);
        btnExit.setBorderPainted(false);
        btnExit.setFocusPainted(false);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameParent.setEnabled(true);
                setVisible(false);
            }
        });
        topBarPanel.add(btnExit);

        lblTitle = new JLabel();
        lblTitle.setBounds(40, 0, width - 80, 44);
        lblTitle.setText(title);
        lblTitle.setFont(new Font("Helvetica", Font.PLAIN, 24));
        lblTitle.setForeground(new Color(71, 71, 71));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setVerticalAlignment(SwingConstants.CENTER);
        topBarPanel.add(lblTitle);

        lblSeparator1 = new JLabel();
        lblSeparator1.setBounds(0, 44, width, 1);
        lblSeparator1.setOpaque(true);
        lblSeparator1.setBackground(new Color(222, 222, 222));
        topBarPanel.add(lblSeparator1);

        lblSeparator2 = new JLabel();
        lblSeparator2.setBounds(0, 45, width, 1);
        lblSeparator2.setOpaque(true);
        lblSeparator2.setBackground(new Color(230, 230, 230));
        topBarPanel.add(lblSeparator2);

        lblSeparator3 = new JLabel();
        lblSeparator3.setBounds(0, 46, width, 1);
        lblSeparator3.setOpaque(true);
        lblSeparator3.setBackground(new Color(245, 245, 245));
        topBarPanel.add(lblSeparator3);

        lblLogo = new JLabel();
        lblLogo.setBounds(50, 130, 200, 200);
        lblLogo.setIcon(new ImageIcon("kiosk/icons/satisflight.png"));
        mainPanel.add(lblLogo);

        lblHeadline = new JLabel();
        lblHeadline.setText(headline);
        lblHeadline.setFont(new Font("Helvetica", Font.PLAIN, 35));
        lblHeadline.setForeground(new Color(51, 51, 51));
        lblHeadline.setBounds(300, 70, 500, 45);
        mainPanel.add(lblHeadline);

        lblVersion = new JLabel();
        lblVersion.setText(version);
        lblVersion.setFont(new Font("Helvetica", Font.PLAIN, 20));
        lblVersion.setForeground(new Color(51, 51, 51));
        lblVersion.setBounds(300, 110, 200, 20);
        mainPanel.add(lblVersion);

        taIntro = new JTextArea();
        taIntro.setText(intro);
        taIntro.setFont(new Font("Helvetica", Font.PLAIN, 18));
        taIntro.setForeground(new Color(51, 51, 51));
        taIntro.setBounds(300, 150, 400, 120);
        taIntro.setLineWrap(true);
        taIntro.setWrapStyleWord(true);
        taIntro.setEditable(false);
        taIntro.setOpaque(false);
        mainPanel.add(taIntro);

        lblVisitRepo = new JLabel();
        lblVisitRepo.setText(visitRepo);
        lblVisitRepo.setFont(new Font("Helvetica", Font.PLAIN, 18));
        lblVisitRepo.setForeground(new Color(51, 51, 51));
        lblVisitRepo.setBounds(300, 264, 400, 22);
        mainPanel.add(lblVisitRepo);

        lblRepo = new JLabel();
        lblRepo.setText(repo);
        lblRepo.setFont(new Font("Helvetica", Font.PLAIN, 18));
        lblRepo.setForeground(new Color(20, 124, 229));
        lblRepo.setBounds(338, 264, repoTextWidth, 22);
        lblRepo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setExtendedState(Frame.ICONIFIED);
                try {
                    Desktop.getDesktop().browse(new java.net.URI(repoUrl));
                } catch (IOException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        lblRepo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                lblRepo.setText("<HTML><U>" + repo + "</U></HTML>");
            }
        });
        lblRepo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                lblRepo.setText(repo);
            }
        });
        mainPanel.add(lblRepo);

        taDeveloper1 = new JTextArea();
        taDeveloper1.setText(dev1);
        taDeveloper1.setFont(new Font("Helvetica", Font.PLAIN, 18));
        taDeveloper1.setForeground(new Color(51, 51, 51));
        taDeveloper1.setBounds(300, 304, 400, 400);
        taDeveloper1.setLineWrap(true);
        taDeveloper1.setWrapStyleWord(true);
        taDeveloper1.setEditable(false);
        taDeveloper1.setOpaque(false);
        mainPanel.add(taDeveloper1);

        taDeveloper2 = new JTextArea();
        taDeveloper2.setText(dev2);
        taDeveloper2.setFont(new Font("Helvetica", Font.PLAIN, 18));
        taDeveloper2.setForeground(new Color(51, 51, 51));
        taDeveloper2.setBounds(436, 304, 400, 400);
        taDeveloper2.setLineWrap(true);
        taDeveloper2.setWrapStyleWord(true);
        taDeveloper2.setEditable(false);
        taDeveloper2.setOpaque(false);
        mainPanel.add(taDeveloper2);

        setVisible(true);
    }

    public static class RoundedPanel extends JPanel {
        @Serial
        private static final long serialVersionUID = 1L;

        public RoundedPanel() {
            super();
            setOpaque(true);
            int fieldWeight = getSize().width;
            int fieldHeight = getSize().height;
            setSize(fieldWeight + 5, fieldHeight + 5);
        }

        @Override
        public void paint(Graphics g) {
            int fieldX = 0;
            int fieldY = 0;
            int fieldWeight = getSize().width;
            int fieldHeight = getSize().height;
            RoundRectangle2D rect = new RoundRectangle2D.Double(fieldX, fieldY, fieldWeight, fieldHeight, 30, 30);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setClip(rect);
            super.paint(g);
        }
    }
}