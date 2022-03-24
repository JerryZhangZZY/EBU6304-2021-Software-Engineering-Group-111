package main;

/**
 * @version 1.0
 * @author zaitian
 * @date 3/22
 * Designed to temporarily save data
 * before forwarding to json database
 * at the final page. Add static variables
 * as you need it. Do remember to
 * add getter/setter and avoid name-collision
 *
 * @author Zhang Zeyu
 * @date 2022/3/24
 * @version 1.1
 * Add temp vars.
 */
public abstract class State {
    private static int pc = 0;
    private static String passengerName = "passenger";
    private static String bookingNum;
    private static String idPassengerFlight;
    private static String idFlight;
    private static String seat;
    private static int seatPre; //[0, 1, 2, 3]
    private static int meal;    //[0, 1, 2]
    private static boolean mealPre1;
    private static boolean mealPre2;
    private static boolean mealPre3;

    public static String getPassengerName() { return passengerName; }
    public static int getPc() { return pc; }

    public static void setPassengerName(String passengerName) { State.passengerName = passengerName; }
    public static void setPc(int pcNext) { pc = pcNext; }
}
