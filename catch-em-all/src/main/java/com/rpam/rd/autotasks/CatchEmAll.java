package com.rpam.rd.autotasks;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CatchEmAll {

    //You may set another exception in this field;
    static Exception exception = new FileNotFoundException();

    public static void riskyMethod() throws Exception {
        throw exception;
    }

    public static void main(String[] args) throws Exception {

        try {
            riskyMethod();
        } catch (NumberFormatException | ArithmeticException e1) {
            System.err.println(e1.getMessage());
        } catch (FileNotFoundException e2) {
            throw new IllegalArgumentException("Resource is missing", e2);
        } catch (IOException e3) {
            throw new IllegalArgumentException("Resource error", e3);
        }
    }
}
