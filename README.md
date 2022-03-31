# Satisflight - An Airport Check-in System


![Version](https://img.shields.io/badge/Version-1.0-green)
![](https://img.shields.io/github/repo-size/JerryZhangZZY/EBU6304-2021-Software-Engineering-Group-111)
[![QMPlus Hub](https://img.shields.io/badge/QMPlus%20Hub-here-orange)](https://hub.qmplus.qmul.ac.uk/group/ebu6304-2022-software-engin-35)
[![Gitee Mirror](https://img.shields.io/badge/Gitee%20Mirror-here-red)](https://gitee.com/jerryzhangzzy/EBU6304-2021-Software-Engineering-Group-111)

### *version 1.0 - Jiuquan*

## Overview

Satisflight is a smart flight check-in software system, featuring a kiosk software which offers passengers self-service check-in, so they can choose seats and print boarding passes in no time. The software is equipped with a back-end management system that enables airport and airlines staffs to view check-in status and get better prepared for passengers' boarding.

## Main Features

- Multiple ways of identification available when check-in
- Checking-in for all sequential flights in one trip
- Free-choice of seat and customized meal
- Fast in response and easy of use
- More to be discovered ...

## Using Instruction

### Environment Requirements

|    |Minimal|Recommended|
|----|-------|-----------|
|Operating System|Ubuntu 20 or Windows 10| Ubuntu 20 or Windows 10|
|Processor|Intel(R) Core(TM) i3-530|Intel(R) Core(TM) i5-9300|
|RAM|4GB|16GB|
|Storage|128MB|32GB|
|Runtime Environment|Java 10|Java 10|

### Usage Example

#### Windows

1. Click `WIN` + `R`
2. Run `cmd`
3. To use kiosk software, run `sbin/start-kiosk.bat`
4. To use back-end system, run `sbin/start-backend.bat`

#### Linux

1. Change directory to where Satisflight is held using `cd`
2. To use kiosk software, run `java "-Dsun.java2d.uiScale=1.0" -jar Kiosk.jar`
3. To use back-end system, run `java "-Dsun.java2d.uiScale=1.0" -jar BackEndSystem.jar`

## Development team

**Group Members**
- Wang Zaitian [*@ZaitianWang*](https://github.com/ZaitianWang)
- Li Chunlin [*@CuSO5*](https://github.com/CuSO5)
- Wang Chenyu [*@wcyfrank*](https://github.com/wcyfrank)
- Ni Ruijie [*@zhuantomato*](https://github.com/zhuantomato)
- Liang Zhehao [*@BHobbes*](https://github.com/BHobbes)
- Zhang Zeyu [*@JerryZhangZZY*](https://github.com/JerryZhangZZY)
