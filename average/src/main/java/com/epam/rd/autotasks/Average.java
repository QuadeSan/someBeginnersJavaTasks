package com.epam.rd.autotasks;

import java.util.Scanner;

public class Average {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Use Scanner methods to read input
        int current = scanner.nextInt();
        int sum = current;
        int count = 0;
        while (current != 0) {
            current = scanner.nextInt();
            sum += current;
            count++;
        }
        int avg = sum/count;
        System.out.println(avg);

    }
}