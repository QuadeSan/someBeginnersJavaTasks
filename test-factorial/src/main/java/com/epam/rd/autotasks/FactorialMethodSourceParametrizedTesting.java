package com.epam.rd.autotasks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

class FactorialMethodSourceParametrizedTesting {

    Factorial factorial = new Factorial();

    @ParameterizedTest
    @MethodSource("testCases")
    void testFactorial(String in, String expected) {
        try {
            int res = Integer.parseInt(in);
            if (res >= 0) {
                assertEquals(factorial.factorial(in),expected);
            } else {
                assertThrows(IllegalArgumentException.class, () -> factorial.factorial(in));
            }
        } catch (NumberFormatException ex) {
            assertThrows(IllegalArgumentException.class, () -> factorial.factorial(in));
        }
    }

    public static Stream<Arguments> testCases() {

        return Stream.of(
                Arguments.of("1", "1"),
                Arguments.of("2", "2"),
                Arguments.of("5", "120")
        );
    }

}
