package com.epam.rd.autotasks.meetautocode;

import java.util.Scanner;

public class ElectronicWatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int seconds = scanner.nextInt();
        int secondsInDay = 86400;
        int secondsInHour = 3600;
        int secondsInMinute = 60;
        int hours = (seconds % secondsInDay) / secondsInHour;
        int minutes = ((seconds % secondsInHour) % secondsInDay ) / secondsInMinute;
        int secondsLeft = ((seconds % secondsInDay) % secondsInHour) % secondsInMinute;
        System.out.println(hours+":"+String.format("%02d:%02d", minutes, secondsLeft));

    }
}
