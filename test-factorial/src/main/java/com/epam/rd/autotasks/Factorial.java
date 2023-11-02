package com.epam.rd.autotasks;

import java.math.BigInteger;


public class Factorial {
    public String factorial(String n) {
        if (n == null || Integer.parseInt(n) < 0) {
            throw new IllegalArgumentException();
        }
        try {
            int f = Integer.parseInt(n);
            BigInteger result = BigInteger.ONE;
            for (int i = 2; i <= f; i++)
                result = result.multiply(BigInteger.valueOf(i));
            return "" + result;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException();
        }
    }
}
