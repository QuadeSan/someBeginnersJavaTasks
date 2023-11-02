package com.epam.rd.autotasks.words;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class StringUtil {
    public static int countEqualIgnoreCaseAndSpaces(String[] words, String sample) {
//        throw new UnsupportedOperationException();
        if (words == null || sample == null) {
            return 0;
        }
        int result = 0;
        for (String word : words) {
            if (word.strip().equalsIgnoreCase(sample.strip())) {
                result++;
            }
        }
        return result;
    }

    public static String[] splitWords(String text) {
//        throw new UnsupportedOperationException();
        String regex = "[,.;:?!]";
        if (text == null || text.replaceAll(regex, "").isBlank()) {
            return null;
        }
        String[] splitedText = text.replaceAll("\\s",",").split("[,.;:?!]");
        String[] res = new String[splitedText.length];
        int j = 0;
        for (String s : splitedText) {
            String resStr = s.trim();
            if (resStr.length() > 0) {
                res[j++] = resStr;
            }
        }
        return Arrays.copyOf(res,j);
    }

    public static String convertPath(String path, boolean toWin) {
//        throw new UnsupportedOperationException();
        if (path == null || path.isBlank()) {
            return null;
        }
        String regexC = "^[A-Za-z]:[a-zA-Z0-9\\s\\\\_.]*[a-zA-Z0-9\\s_.]?$";
        String regexDot = "^[.]{0,2}[a-zA-Z0-9\\s\\\\_.]*[a-zA-Z0-9\\s_.]?$";
        String regexU = "^[~/]?[a-zA-Z0-9\\s_/.]*[a-zA-Z0-9/\\s_.]$";
        String regexDotU = "^[.]{0,2}[a-zA-Z0-9\\s_/.]*[a-zA-Z0-9/\\s_.]$";

        Pattern winPat = Pattern.compile(regexC);
        Pattern uniPat = Pattern.compile(regexU);
        Pattern winDot = Pattern.compile(regexDot);
        Pattern uniDot = Pattern.compile(regexDotU);
        if (toWin) {
            if (winPat.matcher(path).matches() || winDot.matcher(path).matches())  {
                return path;
            }
            if (uniPat.matcher(path).matches() || uniDot.matcher(path).matches()) {
                return path.replaceAll("~","C:\\\\User")
                        .replaceAll("^/","C:\\\\")
                        .replaceAll("/","\\\\");
            }
        }
        if (uniPat.matcher(path).matches() || uniDot.matcher(path).matches())  {
            return path;
        }
        if (winPat.matcher(path).matches() || winDot.matcher(path).matches()) {
            return path.replaceAll("C:\\\\User","~")
                    .replaceAll("C:\\\\","/")
                    .replaceAll("\\\\","/");
        }
        if (path.equals("~") &&toWin) return "C:\\User";
        if (path.equals("~")) return path;
        return null;
    }

    public static String joinWords(String[] words) {
//        throw new UnsupportedOperationException();
        StringJoiner result = new StringJoiner(", ", "[", "]");
        if (words == null || Arrays.equals(words, new String[words.length])) {
            return null;
        }
        for (String word : words) {
            if (!word.isBlank()) {
                result.add(word);
            }
        }
        int blanck = 0;
        for (String word : words) {
            if (word.isBlank()) blanck++;
        }
        if (blanck == words.length) {
            return null;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println("Test 1: countEqualIgnoreCaseAndSpaces");
        String[] words = new String[]{" WordS    \t", "words", "w0rds", "WOR  DS",};
        String sample = "words   ";
        int countResult = countEqualIgnoreCaseAndSpaces(words, sample);
        System.out.println("Result: " + countResult);
        int expectedCount = 2;
        System.out.println("Must be: " + expectedCount);

        System.out.println("Test 2: splitWords");
        String text = "   ,, first, second!!!! third";
        String[] splitResult = splitWords(text);
        System.out.println("Result : " + Arrays.toString(splitResult));
        String[] expectedSplit = new String[]{"first", "second", "third"};
        System.out.println("Must be: " + Arrays.toString(expectedSplit));

        System.out.println("Test 3: convertPath");
        String unixPath = "/some/unix/path";
        String convertResult = convertPath(unixPath, true);
        System.out.println("Result: " + convertResult);
        String expectedWinPath = "C:\\some\\unix\\path";
        System.out.println("Must be: " + expectedWinPath);

        System.out.println("Test 4: joinWords");
        String[] toJoin = new String[]{"go", "with", "the", "", "FLOW"};
        String joinResult = joinWords(toJoin);
        System.out.println("Result: " + joinResult);
        String expectedJoin = "[go, with, the, FLOW]";
        System.out.println("Must be: " + expectedJoin);
    }
}