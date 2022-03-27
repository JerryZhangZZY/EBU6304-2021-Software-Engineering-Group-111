# Bankend_Database Reader

## This package contains tools that help you get data from database and Write data to database

### How to use?

####DBreader some simple example
```java
DBreader reader = new DBreader();
String[] tmp=reader.getline(2);
ArrayList<String[]> data = reader.getDataBase();
ArrayList<String[]> airline = reader.getSpecialAirline("AIR China");
```
####DBwriter variable type all are String, if a customer haven't boarded statues put 0 and seat meal special_meal put it as NULL. you must write attribute idPassenger surName idFlight bookingNum status and airline
####DBwriter some simple example
```java
String[] str = {"66","John","CA1002","879689","1","23C","vegetarian","French","Japan","ice","Shan Dong"};
DBwrite.writeline(str);
String[] stri = {"74","Panda","CA1006","879456","0","NULL","NULL","NULL","NULL","NULL","Shan Dong"};
DBwrite.writeline(stri);
String[] stri = {"74","Panda","CA1006","879456","0","24A","NULL","NULL","NULL","NULL","Shan Dong"};
DBwrite.changeline(stri);
```
