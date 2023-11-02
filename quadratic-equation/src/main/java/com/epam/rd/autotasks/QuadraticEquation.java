package com.epam.rd.autotasks;

import java.util.Locale;
import java.util.Scanner;

import static java.lang.Math.sqrt;

public class QuadraticEquation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double discr = (b * b) - (4 * a * c);
        if (discr > 0) {
            double x = (-b + Math.sqrt(discr)) / (2 * a);
            double x1 = (-b - Math.sqrt(discr)) / (2 * a);
            System.out.println(x + " " + x1);
        } else if (discr == 0) {
            double x = -b / (2 * a);
            System.out.println(x);
        } else if (discr < 0) {
            System.out.println("no roots");
        }

    }

}