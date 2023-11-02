package com.efimchick.ifmo.util;

import com.efimchick.ifmo.Collecting;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyContainer implements Supplier<CourseResult> {
    ArrayList<CourseResult> myList = new ArrayList<>();
    boolean leftJustifiedRows = false;


    public MyContainer add(CourseResult el) {
        myList.add(el);
        return this;
    }

    public int calcRows() {
        long persons = this.myList.stream().map(CourseResult::getPerson).count();
        return (int) (persons + 2);
    }

    public int calcColumns() {
        long tasks = this.myList.stream().map(CourseResult::getTaskResults)
                .flatMap(e -> e.keySet().stream())
                .distinct()
                .count();
        return (int) (tasks + 3);
    }

    public String[] getTasks(ArrayList<CourseResult> list) {
        ArrayList<String> result = (ArrayList<String>) list.stream()
                .flatMap(e -> e.getTaskResults().keySet().stream())
                .distinct().collect(Collectors.toList());
        String[] res = new String[result.size()];
        res = result.toArray(res);
        Arrays.sort(res);
        return res;
    }

    public String[] getStudents(ArrayList<CourseResult> list) {
        ArrayList<Person> result = (ArrayList<Person>) list.stream()
                .map(e -> e.getPerson())
                .distinct().collect(Collectors.toList());
        String[] res = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i).getLastName() + " " + result.get(i).getFirstName();
        }
        result.sort(Comparator.comparing(o -> (o.getLastName() + " " + o.getFirstName())));
        Arrays.sort(res);
        return res;
    }

    public String print() {
        int rows = calcRows();
        int columns = calcColumns();
        String[][] table = new String[rows][columns];
        String[] tasks = getTasks(myList);
        String[] students = getStudents(myList);
        table[0][0] = "Student";
        table[0][columns - 2] = "Total";
        table[0][columns - 1] = "Mark";
        for (int i = 1, j = 0; i < table[0].length - 2; i++, j++) {
            table[0][i] = tasks[j];
        }
        //
        //
        table[rows - 1][0] = "Average";
        for (int i = 1, j = 0; i < table.length - 1; i++, j++) {
            table[i][0] = students[j];
        }
        Map<String, Double> avgPerTask = new Collecting().averageScoresPerTask(myList.stream());
        for (int i = 0; i < table[0].length; i++) {
            String current = table[0][i];
            if (avgPerTask.containsKey(current)) {
                table[rows - 1][i] = String.format(Locale.US, "%.2f", avgPerTask.get(current));
            }
        }
        table[rows - 1][columns - 2] = String.format
                (Locale.US, "%.2f", new Collecting().averageTotalScore(myList.stream()));
        //
        //
        table[rows - 1][columns - 1] = Collecting.getLetter(Double.parseDouble(table[rows - 1][columns - 2]));
        //
        //
        ArrayList<CourseResult> sortedList = myList;
        sortedList.sort(Comparator.comparing(o -> (o.getPerson().getLastName() + " " + o.getPerson().getFirstName())));
        ArrayList<Map<String, Integer>> tasksList = new ArrayList<>();
        for (CourseResult courseResult : sortedList) {
            tasksList.add(courseResult.getTaskResults());
        }
        Map<Person, Double> studentsTotals = new Collecting().totalScores(sortedList.stream());
        ArrayList<Map.Entry<Person, Double>> entryArray = (ArrayList<Map.Entry<Person, Double>>) studentsTotals.entrySet().stream().collect(Collectors.toList());
        entryArray.sort(Comparator.comparing(o -> (o.getKey().getLastName() + " " + o.getKey().getFirstName())));
        ArrayList<Double> totalsList = new ArrayList<>();
        for (int i = 0; i < entryArray.size(); i++) {
            totalsList.add(entryArray.get(i).getValue());
        }
        for (int i = 1, c = 0, t = 0; i < table.length - 1; i++, c++) {
            Map<String, Integer> current = tasksList.get(c);
            for (int j = 1; j < table[0].length; j++) {
                if (j < table[0].length - 2) {
                    String curKey = table[0][j];
                    if (current.containsKey(curKey)) {
                        table[i][j] = current.get(curKey).toString();
                    } else {
                        table[i][j] = "0";
                    }
                }
                if (j == table[0].length - 2) {
                    table[i][j] = String.format
                            (Locale.US, "%.2f", totalsList.get(t));
                    t++;
                }
                if (j == table[0].length - 1) {
                    table[i][j] = Collecting.getLetter(Double.parseDouble(table[i][j - 1]));
                }
            }
        }
        Map<Integer, Integer> columnLengths = new HashMap<>();
        Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            if (columnLengths.get(i) == null) {
                columnLengths.put(i, 0);
            }
            if (columnLengths.get(i) < a[i].length()) {
                columnLengths.put(i, a[i].length());
            }
        }));
        final StringBuilder formatString = new StringBuilder("");
        String flag = leftJustifiedRows ? "-" : "";
//        columnLengths.values().forEach(e -> formatString.append(" %" + flag + e + "s |"));
        List<Integer> formatList = new ArrayList<>();
        for (Integer integer : columnLengths.values()) {
            formatList.add(integer);
        }
        for (int i = 0; i < formatList.size(); i++) {
            if (i%columns!=0){
                formatString.append(" %" + "" + formatList.get(i) + "s |");
            } else {
                formatString.append("%" + "-" + formatList.get(i) + "s |");
            }
        }
        formatString.append("\n");

        StringBuilder print = new StringBuilder();

        Stream.iterate(0, (i -> i < table.length), (i -> ++i))
                .forEach(a -> print.append(String.format(formatString.toString(), table[a])));
        print.delete(print.lastIndexOf("\n"),print.length());
        return print.toString();
    }

    public MyContainer merge(MyContainer other) {
        return this.add(other.get());
    }

    @Override
    public CourseResult get() {
        return myList.get(0);
    }
}
