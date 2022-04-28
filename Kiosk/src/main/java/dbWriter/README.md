# Database Writer

## This package contains tools that help you write data back to database.

### Simple example of StatusWriter:

```java
StatusWriter.setTrue(PassengerFlightReader.indexOf(1));
```

### Simple example of CurrentWriter:

```java
CurrentWriter.clear();
CurrentWriter.setSeat("1A", 2);
CurrentWriter.setMeal(0, true, false, false);
```

Hint:
- The json file may be formatted to one line.
- In *IDEA*, use <kbd>Ctrl</kbd> + <kbd>Alt</kbd> + <kbd>L</kbd> to reformat json file.