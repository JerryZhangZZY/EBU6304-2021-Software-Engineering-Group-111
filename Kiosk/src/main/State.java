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
 */
public abstract class State {
    static private int pc = 0;
    static public int getPc(){
        return pc;
    }
    static public void setPc(int pcNext){
        pc = pcNext;
    }
}
