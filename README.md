# Satisflight - An Airport Check-in System


![Version](https://img.shields.io/badge/Version-5.0-green)
![](https://img.shields.io/github/repo-size/JerryZhangZZY/EBU6304-2021-Software-Engineering-Group-111)
[![QMPlus Hub](https://img.shields.io/badge/QMPlus%20Hub-here-orange)](https://hub.qmplus.qmul.ac.uk/group/ebu6304-2022-software-engin-35)
[![Gitee Mirror](https://img.shields.io/badge/Gitee%20Mirror-here-red)](https://gitee.com/jerryzhangzzy/EBU6304-2021-Software-Engineering-Group-111)

### *version 5.0 - Oriental*

## Update Notes

### New Features

- Time span is displayed at flight info
- Configurable image printer
- An Easter Egg
- More themes
- More to be discovered ...

### Resolved Problems

- Unexpected launching when config names are invalid
- Removing selectability of some text

---

## Overview

Satisflight is a smart flight check-in software system, featuring a kiosk software which offers passengers self-service check-in, so they can choose seats and print boarding passes in no time. The software is equipped with a back-end management system that enables airport and airlines staffs to view check-in status and get better prepared for passengers' boarding.

## Main Features

- Multiple ways of identification available when check-in
- Checking-in for all sequential flights in one trip
- A QR code providing digital boarding pass
- Free-choice of seat and customized meal
- Printing boarding pass and baggage tag
- Providing information to airline staff
- Fast in response and easy of use
- Fancy and smooth animation
- Flexible user configuration
- A variety of themes
- More to be discovered ...

## Using Instruction

### Environment Requirements

|                     | Minimal                      | Recommended               |
|---------------------|------------------------------|---------------------------|
| Operating System    | Ubuntu 19/macOS 12/Windows 7 | Windows 10                |
| Processor           | Intel(R) Core(TM) i3-530     | Intel(R) Core(TM) i5-9300 |
| RAM                 | 1GB                          | 4GB                       |
| Storage             | 128MB                        | 32GB                      |
| Runtime Environment | Java 10                      | Java 10                   |

### How To Use

#### Windows

1. To use kiosk software, go to `sbin` directory and run `start-kiosk.bat` by double-click.
2. To use back-end system, go to `sbin` directory and run `start-backend.bat` by double-click.

#### Linux

1. To use kiosk software, run:
```shell
cd sbin
chmod 777 start-kiosk.sh
./start-kiosk.sh
```
2. To use back-end system, run:
```shell
cd sbin
chmod 777 start-backend.sh
./start-backend.sh
```

### Hints

#### User Configuration

Since release version 2.0: Xichang, we have enabled user configuration, so that users can check and modify some software settings.

To edit configuration files, go to `conf` directory and open `config.yaml` with your text editor.
Do follow the format provided by existing entries.
However, improper change may lead to unexpected problems, so we do recommend consulting technique support if you are not an expert.
A more user-friendly and complexity-hidden configuration editor might be added in later release.

For detailed usage, vide `conf/config.yaml` comments.

#### External Equipment as Authentication

The Kiosk software makes use of external equipment as authentication method.
It requires scanning the ID document when confirming check-in and also uses it as an alternative way to retrieve flights.
A USB drive with passenger's identification information that plays the role of de facto identification document during these operations.
**In other words, when you need to use an ID document, insert a USB flash disk that contains a passenger-information-populated json file.**
(Copying the file into your computer's hard drive is equivalent.)
The default drive name is "F", but you can choose whatever you like by [editing configuration file](#User-Configuration).

A sample passenger ID document file is provided as "ID info.json" under software's home directory, alongside with JAR files.
Copy them into your USB flash disk, or manually view the database and use other identification.

#### Database Reset

Now that our early-version databases are limit in size, you may find all flights already checked-in.
To continue using the software, resetting the database may be necessary.
To reset the databases to initial state, for Windows run:
```shell
sbin\reset-database.bat
```
or, for Linux:
```shell
cd sbin
chmod 777 reset-database.sh
./reset-database.sh
```

#### Admin Exit

A special exit is provided with administrator, namely airport stuff.
When passengers click the exit button, the kiosk software returns to welcome page.
While admins are empowered to *literally* exit the programme.
To call this function, press `Alt` when clicking the exit button, then a black-backgrounded terminal will pop up.
Enter `resumo` to close this terminal and resume checking-in.
Enter `exeo` to close and therefore exit the whole programme.

#### Theme Customization

Since release 3.0: Taiyuan, we have enabled theme customization.
Go to `theme` entry in `conf/config.yaml` file to change it.
Available options currently includes the following:

- Cobalt
- Onyx
- Tiber
- Anchor
- Almond
- Tomato
- Maroon

If you want to design your onw theme, go to `theme.json` under `conf` directory to set colors for different parts of the software GUI.

### Extensions

#### Exit Timer

Timer extension enables auto-exit if one single check-in last too long or if there is no more flights to check in.
The software can lock its screen and return to welcome page.
This will help save energy and protect users' privacy.

To use exit timer extension, go to `config.yaml`, and set `checkinTimer` (120 second for entire check-in process) and/or `idleTimer` (4 seconds if no more flights) as `enable`.
Set `disable` if you do not want to use them.
The two entries are independent.

#### Check-in Time Constraint

This extension constrains the time period when users can check in their flights.
Set `enableCheckInLeadingTime` as `true` so that a flight can be checked in only if it departs in more than 30 minutes and no more than 24 hours.
Say, a flight departs at 10:40 on April 29th, and it is only available from 10:40 on April 28th to 10:10 on April 29th.

#### Auto Dark Theme

When the kiosk software is working at night, the theme will automatically turn dark.
This can improve user experience.
Set `enableAutoDarkTheme` as `true` to activate this extension.

---

## Development team

**Group Members**
- Wang Zaitian [*@ZaitianWang*](https://github.com/ZaitianWang)
- Li Chunlin [*@CuSO5*](https://github.com/CuSO5)
- Wang Chenyu [*@wcyfrank*](https://github.com/wcyfrank)
- Ni Ruijie [*@zhuantomato*](https://github.com/zhuantomato)
- Liang Zhehao [*@BHobbes*](https://github.com/BHobbes)
- Zhang Zeyu [*@JerryZhangZZY*](https://github.com/JerryZhangZZY)
