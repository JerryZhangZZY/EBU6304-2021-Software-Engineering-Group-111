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
 * @version 4.0
 * Rename timers and reformat codes.
 * @date 2022/5/12
 *
 * @version 3.3
 * Added limit time config, and allowed managers to enable/disable timers.
 * @date 2022/4/27
 *
 * @version 3.2
 * All backstage timers have been integrated into Clock.java.
 * Backstage timer can be invoked by using setBackstageTimer(long limitTime),
 * and stop it by using stopBackstageTimer().
 * New function which can disable the Timer has been added
 * @date 2022/4/27
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
    static Timer checkinTimerAction;
    static Timer idleTimerAction;
    static int checkinTimeLimit;
    static JLabel tempTimer;
    static int flag1 = 0;
    static int flag2 = 0;

    /**
     * Set the clock
     *
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
        }, 0, 1);
    }

    /**
     * Load the panel from MainFrame to this class.
     * Deploy configurations.
     *
     * @param timer panel of the timer
     */
    public static void loadTimer(JLabel timer) {
        if (Config.readConfig("checkinTimer").equals("disable"))
            disableCheckinTimer();
        if (Config.readConfig("idleTimer").equals("disable"))
            disableIdleTimer();
        checkinTimeLimit = Integer.parseInt(Config.readConfig("checkinTimeLimit"));
        tempTimer = timer;
    }

    /**
     * Set the Timer which can make the system exit to welcome page in a fixed time.
     */
    public static void setCheckinTimer() {
        final JLabel varTimer = tempTimer;
        if (flag1 == 0) {
            long timeSeconds = System.currentTimeMillis() / 1000;
            long tempMillisTime = checkinTimeLimit + timeSeconds;
            checkinTimerAction = new Timer();
            flag1 = 1;
            checkinTimerAction.schedule(new TimerTask() {
                @Override
                public void run() {
                    long nowTime = System.currentTimeMillis() / 1000;
                    varTimer.setText(String.valueOf(tempMillisTime - nowTime));
                    tempTimer.setVisible(true);
                    if (tempMillisTime - nowTime == -1) {
                        stopCheckinTimer();
                        State.setPc(0);
                        State.setIsReady(new boolean[]{true, true, true, false, false, false, false, true, true});
                    }
                }
            }, 0, 1);
        }
    }

    /**
     * Stop check-in timer and reset it.
     */
    public static void stopCheckinTimer() {
        if (flag1 == 1) {
            checkinTimerAction.cancel();
            tempTimer.setVisible(false);
            flag1 = 0;
        } else {
            tempTimer.setVisible(false);
        }
    }

    /**
     * Invoke an idle timer with limit time
     *
     * @param limitTime return to welcome page at limitTime (millisecond)
     */
    public static void setIdleTimer(long limitTime) {
        if (flag2 == 0) {
            idleTimerAction = new Timer();
            flag2 = 1;
            idleTimerAction.schedule(new TimerTask() {
                @Override
                public void run() {
                    stopIdleTimer();
                    stopCheckinTimer();
                    State.setPc(0);
                    State.setIsReady(new boolean[]{true, true, true, false, false, false, false, true, true});
                }
            }, limitTime);
        }
    }

    /**
     * Stop idle timer.
     */
    public static void stopIdleTimer() {
        if (flag2 == 1) {
            idleTimerAction.cancel();
            flag2 = 0;
        }
    }

    /**
     * Disable check-in timer.
     */
    public static void disableCheckinTimer() {
        flag1 = 2;
    }

    /**
     * Disable idle timer.
     */
    public static void disableIdleTimer() {
        flag2 = 2;
    }
}
