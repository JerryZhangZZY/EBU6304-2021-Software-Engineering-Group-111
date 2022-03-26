package Backendmain;

public abstract class Systempointer {
    private static int pc = 0;
    private static String name;
    public static void setPc(int pc){
        Systempointer.pc=pc;
    }
    public static int getPc(){
        return Systempointer.pc;
    }
    public static void setName(String name){
        Systempointer.name=name;
    }
    public static String getName(){
        return Systempointer.name;
    }
}
