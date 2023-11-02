package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class QuadraticEquationTwoRootsCasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();


    private final double a;
    private final double b;
    private final double c;
    private final String expected;

    public QuadraticEquationTwoRootsCasesTesting(double a, double b, double c, String expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "testTwoRoots #{index}: ({0}+{1}+{2})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1.0, -8.0, 12.0, "6.0 2.0"},
                {5.0, 10.0, -15.0, "1.0 -3.0"},
                {1.0, 2.0, -3.0, "1.0 -3.0"},
                {1.0, -70.0, 600.0, "60.0 10.0"}
        });
    }

    @Test
    public void testTwoRootsCase() {
        String[] exp = expected.split(" ");
        String[] res = quadraticEquation.solve(a, b, c).split(" ");
        if (res.length > 1) {
            boolean allMatch = (exp[0].equals(res[0]) && exp[1].equals(res[1]))
                    || (exp[0].equals(res[1]) && exp[1].equals(res[0]));
            assertTrue(allMatch);
        } else assertEquals(expected, quadraticEquation.solve(a, b, c));
    }
}
