package com.epam.rd.autotasks.snail;

import java.util.Scanner;

public class Snail {
    public static void main(String[] args) {
        //Write a program that reads a,b and h (line by lyne in this order) and prints
        //the number of days for which the snail reach the top of the tree.
        //a - feet that snail travels up each day, b - feet that slides down each night, h - height of the tree
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int h = scanner.nextInt();
        int curHeight = 0;
        if (a > b && a < h) {
            curHeight = (h - a) / (a - b) + 1;
            System.out.println(curHeight);
        }
        if (a >= h) {
            System.out.println(++curHeight);
        }
        if (a <= b && a < h) {
            System.out.println("Impossible");
        }

    }
}
