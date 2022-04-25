package main;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The class is a tool to set a clock or stop it.
 * @author Ni Ruijie
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
     * Set the timer
     * @param clock panel of the timer
     */
    public static void setClock(JLabel clock) {
        final JLabel varClock = clock;
        clockAction = new Timer();
        clockAction.schedule(new TimerTask() {
            @Override
            public void run() {
                long timemillis = System.currentTimeMillis();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                varClock.setText(df.format(new Date(timemillis)));
            }
        },0,1000);
    }

    public static void loadTimer(JLabel timer){
        tempTimer = timer;
    }

    public static void setTimer() {
        tempLimitTime = limitTime;
        final JLabel varTimer = tempTimer;
        timerAction = new Timer();
        flag = 1;
        timerAction.schedule(new TimerTask() {
            @Override
            public void run() {
                varTimer.setText("Time remaining: "+(tempLimitTime--));
                tempTimer.setVisible(true);
                if(tempLimitTime ==-1){
                    stopTimer();
                    State.setPc(0);
                }
            }
        },0,1000);
    }


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
