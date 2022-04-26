package main;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The class is a tool to set a clock or stop it.
 *
 * @author Ni Ruijie
 * @author Zhang Zeyu
 *
 * @version 3.1
 * Change date format.
 * @date 2022/4/26
 *
 * @version 3.0
 * Added 3 methods to achieve automatically exiting.
 * @date 2022/4/26
 *
 * @version 2.0
 * Created 2 functions setClock & stopClock
 * @date 2022/4/11
 */
public abstract class Clock {
    static Timer clockAction;
    static Timer timerAction;
    static int limitTime = 120;
    static int tempLimitTime;
    static JLabel tempTimer;
    static int flag = 0;
    /**
     * Set the clock
     * @param clock panel of the clock
     */
    public static void setClock(JLabel clock) {
        final JLabel varClock = clock;
        clockAction = new Timer();
        clockAction.schedule(new TimerTask() {
            @Override
            public void run() {
                long timemillis = System.currentTimeMillis();
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
                varClock.setText(df.format(new Date(timemillis)));
            }
        },0,1000);
    }

    /**
     * Load the panel from MainFrame to this class.
     * @param timer panel of the timer
     */
    public static void loadTimer(JLabel timer){
        tempTimer = timer;
    }

    /**
     * Set the Timer which can make the system exit to welcome page in a fixed time.
     */
    public static void setTimer() {
        tempLimitTime = limitTime;
        final JLabel varTimer = tempTimer;
        timerAction = new Timer();
        flag = 1;
        timerAction.schedule(new TimerTask() {
            @Override
            public void run() {
                varTimer.setText(String.valueOf(tempLimitTime--));
                tempTimer.setVisible(true);
                if(tempLimitTime ==-1){
                    stopTimer();
                    State.setPc(0);
                }
            }
        },0,1000);
    }

    /**
     * Stop the Timer, and reset it.
     */
    public static void stopTimer(){
        if(flag==1){
            timerAction.cancel();
            tempLimitTime = limitTime;
            tempTimer.setVisible(false);
            flag = 0;
        }
        else{
            tempTimer.setVisible(false);
        }
    }

}
