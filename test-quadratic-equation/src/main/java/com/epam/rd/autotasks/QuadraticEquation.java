package com.epam.rd.autotasks;

public class QuadraticEquation {
    public String solve(double a, double b, double c) {
        if (a == 0) {
            throw new IllegalArgumentException();
        }
        StringBuilder res = new StringBuilder();
        double discr = (b * b) - (4 * a * c);
        if (discr > 0) {
            double x = (-b + Math.sqrt(discr)) / (2 * a);
            double x1 = (-b - Math.sqrt(discr)) / (2 * a);
            res.append(x).append(" ").append(x1);
            return res.toString();
        } else if (discr == 0) {
            double x = -b / (2 * a);
            res.append(x);
            return res.toString();
        } else {
            res.append("no roots");
            return res.toString();
        }
    }

}