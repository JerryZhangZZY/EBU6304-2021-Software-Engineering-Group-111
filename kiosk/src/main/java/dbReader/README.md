# Database Reader

## This package contains tools that help you search and get data from database.

### How to use?

1. Call indexOf(primaryKey) to get index.
2. Save the index as a temp value.
3. Call getters and pass in the index as a param to get the corresponding value.

### Simple Example

#### To get simple attribute:

```java
FlightReader.getDepartureTime(FlightReader.indexOf("BA0002"));
```

#### To get multiple attributes:

```java
int tmpIndex = FlightReader.indexOf("BA0002");
FlightReader.getDepartureTime(tmpIndex);
FlightReader.getArrivalTime(tmpIndex);
```