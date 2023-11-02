package com.epam.rd.autotasks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class FactorialRegularInputTesting {

    Factorial factorial = new Factorial();

    @ParameterizedTest
    @CsvSource({"7 , 5040", "12 , 479001600", "22 , 1124000727777607680000"})
    void testFactorialMyCases(String in, String expected) {
        assertEquals(expected, factorial.factorial(in));
    }
}
