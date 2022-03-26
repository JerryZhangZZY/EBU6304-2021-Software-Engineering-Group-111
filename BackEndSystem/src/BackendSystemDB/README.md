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

####DBwriter some simple example
```java
String[] str = {"4","CA1556","456789","1","23B","vegetarian","French","Japan","ice","Shan Dong"};
DBwrite.writeline(str);
```
