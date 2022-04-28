package BackendSystemDB;

import dbReader.*;

import java.io.*;
import java.util.Random;

public class DBGenerator {
    static String idPassenger;
    static String surName;
    static String idFlight;
    static String bookingNum;
    static String airline;
    static String seat;
    static String meal;
    static String mealPre1;
    static String mealPre2;
    static String mealPre3;
    static int index = 0;
    static String separator = ",";
    static String[] randomSeats = {"A","B","C","D","E","F","G","H","J","K","L"};
    static String[] randomMeals = {"Normal","Vegetarian","Halal"};
    static int[] seatForm1 = {0,1,2,8,9,10};
    static int[] seatForm2 = {0,2,8,10};
    static String[] randomNames = {
            "Aaron",
            "Abel",
            "Abraham",
            "Adam",
            "Adrian",
            "Aidan",
            "Alva",
            "Alex",
            "Alexander",
            "Alan",
            "Albert",
            "Alfred",
            "Andrew",
            "Andy",
            "Angus",
            "Anthony",
            "Apollo",
            "Arnold",
            "Arthur",
            "August",
            "Austin",
            "Ben",
            "Benjamin",
            "Bert",
            "Benson",
            "Bill",
            "Billy",
            "Blake",
            "Bob",
            "Bobby",
            "Brad",
            "Brandon",
            "Brant",
            "Brent",
            "Brian",
            "Brown",
            "Bruce",
            "Caleb",
            "Cameron",
            "Carl",
            "Carlos",
            "Cary",
            "Caspar",
            "Cecil",
            "Charles",
            "Cheney",
            "Chris",
            "Christian",
            "Christopher",
            "Clark",
            "Cliff",
            "Cody",
            "Cole",
            "Colin",
            "Cosmo",
            "Daniel",
            "Denny",
            "Darwin",
            "David",
            "Dennis",
            "Derek",
            "Dick",
            "Donald",
            "Douglas",
            "Duke",
            "Dylan",
            "Eddie",
            "Edgar",
            "Edison",
            "Edmund",
            "Edward",
            "Edwin",
            "Elijah",
            "Elliott",
            "Elvis",
            "Eric",
            "Ethan",
            "Eugene",
            "Evan",
            "Enterprise",
            "Ford",
            "Francis",
            "Frank",
            "Franklin",
            "Fred",
            "Gabriel",
            "Gaby",
            "Garfield",
            "Gary",
            "Gavin",
            "Geoffrey",
            "George",
            "Gino",
            "Glen",
            "Glendon",
            "Hank",
            "Hardy",
            "Harrison",
            "Harry",
            "Hayden",
            "Henry",
            "Hilton",
            "Hugo",
            "Hunk",
            "Howard",
            "Henry",
            "Ian",
            "Ignativs",
            "Ivan",
            "Isaac",
            "Isaiah",
            "Jack",
            "Jackson",
            "Jacob",
            "James",
            "Jason",
            "Jay",
            "Jeffery",
            "Jerome",
            "Jerry",
            "Jesse",
            "Jim",
            "Jimmy"
    };

    public static void main(String[] args) throws Exception {
        Random r = new Random();
        //第一步：设置输出的文件路径
        //如果该目录下不存在该文件，则文件会被创建到指定目录下。如果该目录有同名文件，那么该文件将被覆盖。
        File writeFile = new File("DB/backend.csv");

        try {
            //第二步：通过BufferedReader类创建一个使用默认大小输出缓冲区的缓冲字符输出流
            BufferedWriter writeText = new BufferedWriter(new FileWriter(writeFile));

            writeText.write("idPassenger,surName,idFlight,bookingNum,status,seat,meal,mealPre1,mealPre2,mealPre3,airline");
            for (int i = 0; i < 16; i++) {
                getInfo();
                writeText.newLine();    //换行
                //调用write的方法将字符串写到流中
                writeText.write(idPassenger + separator + surName + separator + idFlight + separator + bookingNum + separator + 0 + separator + "NULL" + separator + "NULL" + separator + "NULL" + separator + "NULL" + separator + "NULL" + separator + airline);
                index++;
            }

            for (int i = 0; i < 100; i++){
                randomInfo(r.nextInt(5));
                writeText.newLine();
                writeText.write(idPassenger + separator + surName + separator + idFlight + separator + bookingNum + separator + 1 + separator + seat + separator + meal + separator + mealPre1 + separator + mealPre2 + separator + mealPre3 + separator + airline);
            }
            //使用缓冲区的刷新方法将数据刷到目的地中
            writeText.flush();
            //关闭缓冲区，缓冲区没有调用系统底层资源，真正调用底层资源的是FileWriter对象，缓冲区仅仅是一个提高效率的作用
            //因此，此处的close()方法关闭的是被缓存的流对象
            writeText.close();
        } catch (FileNotFoundException e) {
            System.out.println("没有找到指定文件");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文件读写出错");
        }
    }

    public static void getInfo() {
        idPassenger = PassengerFlightReader.getIdPassenger(index);
        surName = PassengerReader.getSurname(PassengerReader.indexOf(idPassenger));
        idFlight = PassengerFlightReader.getIdFlight(index);
        bookingNum = PassengerFlightReader.getBookingNum(index);
        airline = PlaneReader.getAirline(PlaneReader.indexOf(FlightReader.getIdPlane(FlightReader.indexOf(idFlight))));
    }

    public static void randomInfo(int randomNum) {
        Random r = new Random();
        idPassenger = "123"+(r.nextInt(900)+100);
        surName = randomNames[r.nextInt(120)];
        bookingNum = "bn"+(r.nextInt(9000)+1000);
        getRandomMeal();
        switch (randomNum){
            case 0 -> {
                idFlight = "CA"+(r.nextInt(8000)+1000);
                seat = r.nextInt(12)+1+randomSeats[seatForm1[r.nextInt(6)]];
                airline = "AIR CHINA";
            }
            case 1 -> {
                idFlight = "AC"+(r.nextInt(8000)+1000);
                seat = r.nextInt(20)+1+randomSeats[seatForm1[r.nextInt(6)]];
                airline = "AIR CANADA";
            }
            case 2 -> {
                idFlight = "UA"+(r.nextInt(8000)+1000);
                seat = r.nextInt(32)+1+randomSeats[seatForm1[r.nextInt(6)]];
                airline = "UNITED AIRLINES";
            }
            case 3 -> {
                idFlight = "LH"+(r.nextInt(8000)+1000);
                seat = r.nextInt(77)+1+randomSeats[r.nextInt(11)];
                airline = "LUFTHANSA";
            }
            case 4 -> {
                idFlight = "MU"+(r.nextInt(8000)+1000);
                seat = r.nextInt(15)+1+randomSeats[seatForm2[r.nextInt(4)]];
                airline = "CHINA EASTERN";
            }
        }
    }

    public static void getRandomMeal() {
        Random r = new Random();
        meal = randomMeals[r.nextInt(3)];
        if(r.nextBoolean()){
            mealPre1 = MealPreferenceReader.getName(r.nextInt(16));
        }else{
            mealPre1 = "NULL";
        }
        if(r.nextBoolean()){
            mealPre2 = MealPreferenceReader.getName(r.nextInt(16));
        }else{
            mealPre2 = "NULL";
        }
        if(r.nextBoolean()){
            mealPre3 = MealPreferenceReader.getName(r.nextInt(16));
        }else{
            mealPre3 = "NULL";
        }
    }
}
