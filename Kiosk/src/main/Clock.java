package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class is a tool to set a clock or stop it.
 * @author Ni Ruijie
 *
 * @version 1.0
 * Created 2 functions setClock & stopClock
 * @date 2022/4/11
 */
public abstract class Clock {
    static Timer timeAction;
    /**
     * Set the timer
     * @param time panel of the timer
     */
    public static void setClock(JLabel time) {
        final JLabel varTime = time;
        timeAction = new Timer(0, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long timemillis = System.currentTimeMillis();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                varTime.setText(df.format(new Date(timemillis)));
            }
        });
        timeAction.start();
    }

    public static void stopClock(){
        timeAction.stop();
    }

}
