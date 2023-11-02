package com.epam.rd.autotasks.sequence;

import java.util.Scanner;

public class FindMaxInSeq {
    public static int max() {

        // Put your code here
        Scanner scanner = new Scanner(System.in);
        int max = scanner.nextInt();
        int current = scanner.nextInt();
        for (int i = 0; current != 0; i++) {
            max = Math.max(current, max);
            current = scanner.nextInt();
        }
        return max;
    }

    public static void main(String[] args) {

        System.out.println("Test your code here!\n");

        // Get a result of your code

        System.out.println(max());
    }
}
