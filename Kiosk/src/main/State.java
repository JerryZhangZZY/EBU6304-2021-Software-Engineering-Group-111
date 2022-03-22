package main;

public abstract class State {
    static private int pc = 0;
    static public int getPc(){
        return pc;
    }
    static public void setPc(int pcNext){
        pc = pcNext;
    }
}
