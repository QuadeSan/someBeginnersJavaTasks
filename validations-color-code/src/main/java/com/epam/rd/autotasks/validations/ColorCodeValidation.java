package com.epam.rd.autotasks.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorCodeValidation {
    public static boolean validateColorCode(String color) {

        // Put your code here
        if (color != null) {
            String regex = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$";
            return Pattern.compile(regex).matcher(color).matches();
        }
        return false;
    }
}





