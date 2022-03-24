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

    public static int getPc() { return pc; }
    public static String getPassengerName() { return passengerName; }
    public static String getBookingNum() { return bookingNum; }
    public static String getIdPassengerFlight() { return idPassengerFlight; }
    public static String getIdFlight() { return idFlight; }
    public static String getSeat() { return seat; }
    public static int getSeatPre() { return seatPre; }
    public static int getMeal() { return meal; }
    public static boolean isMealPre1() { return mealPre1; }
    public static boolean isMealPre2() { return mealPre2; }
    public static boolean isMealPre3() { return mealPre3; }

    public static void setPc(int pcNext) { pc = pcNext; }
    public static void setPassengerName(String passengerName) { State.passengerName = passengerName; }
    public static void setBookingNum(String bookingNum) { State.bookingNum = bookingNum; }
    public static void setIdPassengerFlight(String idPassengerFlight) { State.idPassengerFlight = idPassengerFlight; }
    public static void setIdFlight(String idFlight) { State.idFlight = idFlight; }
    public static void setSeat(String seat) { State.seat = seat; }
    public static void setSeatPre(int seatPre) { State.seatPre = seatPre; }
    public static void setMeal(int meal) { State.meal = meal; }
    public static void setMealPre1(boolean mealPre1) { State.mealPre1 = mealPre1; }
    public static void setMealPre2(boolean mealPre2) { State.mealPre1 = mealPre2; }
    public static void setMealPre3(boolean mealPre3) { State.mealPre1 = mealPre3; }
}
