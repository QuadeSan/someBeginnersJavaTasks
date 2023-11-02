package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuadraticEquationSingleRootCasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    private final double a;
    private final double b;
    private final double c;
    private final double expected;

    public QuadraticEquationSingleRootCasesTesting(double a, double b, double c, double expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "testSingleRoot #{index}: ({0}+{1}+{2})= {4}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {-4.0, 12.0, -9.0, 1.5},
                {3.0, -18.0, 27.0, 3.0},
                {1, 4.0, 4.0, -2.0},
                {1, -6.0, 9.0, 3.0}
        });
    }

    @Test
    public void testSingleRootsCase() {
        String exp = ""+expected;
        assertEquals(exp, quadraticEquation.solve(a, b, c));
    }
}