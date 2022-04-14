# Satisflight - An Airport Check-in System


![Version](https://img.shields.io/badge/Version-2.0-green)
![](https://img.shields.io/github/repo-size/JerryZhangZZY/EBU6304-2021-Software-Engineering-Group-111)
[![QMPlus Hub](https://img.shields.io/badge/QMPlus%20Hub-here-orange)](https://hub.qmplus.qmul.ac.uk/group/ebu6304-2022-software-engin-35)
[![Gitee Mirror](https://img.shields.io/badge/Gitee%20Mirror-here-red)](https://gitee.com/jerryzhangzzy/EBU6304-2021-Software-Engineering-Group-111)

---

### *version 2.0 - Xichang*

## Update Notes

### New Features

- Fancy and smooth animation
- User configuration enabled
- A clock to suggest time
- An exit for admin
- More choice when choosing flights
- More informative view when choosing seats
- A QR code providing digital boarding pass
- More to be discovered ...

### Resolved Problems

- No printer output when using JAR
- Chaotic input warning

---

## Overview

Satisflight is a smart flight check-in software system, featuring a kiosk software which offers passengers self-service check-in, so they can choose seats and print boarding passes in no time. The software is equipped with a back-end management system that enables airport and airlines staffs to view check-in status and get better prepared for passengers' boarding.

## Main Features

- Multiple ways of identification available when check-in
- Checking-in for all sequential flights in one trip
- Free-choice of seat and customized meal
- Printing boarding pass and baggage tag
- Providing information to airline staff 
- Fast in response and easy of use
- More to be discovered ...

## Using Instruction

### Environment Requirements

|    | Minimal                  | Recommended               |
|----|--------------------------|---------------------------|
|Operating System| Ubuntu 19 or Windows 7   | macOS 12 or Windows 10    |
|Processor| Intel(R) Core(TM) i3-530 | Intel(R) Core(TM) i5-9300 |
|RAM| 1GB                       | 4GB                       |
|Storage| 128MB                 | 32GB                      |
|Runtime Environment| Java 10   | Java 10                   |

### How To Use

#### Windows

1. To use kiosk software, run:
```shell
sbin\start-kiosk.bat
```
2. To use back-end system, run:
```shell
sbin\start-backend.bat
```

#### Linux

1. To use kiosk software, run:
```shell
cd sbin
./start-kiosk.sh
```
2. To use back-end system, run:
```shell
cd sbin
./start-backend.sh
```

### Hints

#### User Configuration

Since release version 2.0: Xichang, we have enabled user configuration, so that users can check and modify some software settings. 

To edit configuration files, go to `Conf` directory and open `Config.yaml` with your text editor. 
Do follow the format provided by existing entries. 
However, improper change may lead to unexpected problems, so we do recommend consulting technique support if you are not an expert. 
A more user-friendly and complexity-hidden configuration editor might be added in later release.

#### External Equipment as Authentication

The Kiosk software makes use of external equipment as authentication method. 
It requires scanning the ID document when confirming check-in and also uses it as an alternative way to retrieve flights. 
A USB drive with passenger's identification information that plays the role of de facto identification document during these operations. 
**In other words, when you need to use an ID document, insert a USB flash disk that contains a passenger-information-populated json file.**
(Copying the file into your computer's hard drive is equivalent.)
The default drive name is "E", but you can choose whatever you like by [editing configuration file](#User-Configuration).

#### Database Reset

Now that our early-version databases are limit in size, you may find all flights already checked-in. 
To continue using the software, resetting the database may be necessary.
Run

- [ ] reset script

to reset the databases to initial state

---

## Development team

**Group Members**
- Wang Zaitian [*@ZaitianWang*](https://github.com/ZaitianWang)
- Li Chunlin [*@CuSO5*](https://github.com/CuSO5)
- Wang Chenyu [*@wcyfrank*](https://github.com/wcyfrank)
- Ni Ruijie [*@zhuantomato*](https://github.com/zhuantomato)
- Liang Zhehao [*@BHobbes*](https://github.com/BHobbes)
- Zhang Zeyu [*@JerryZhangZZY*](https://github.com/JerryZhangZZY)
