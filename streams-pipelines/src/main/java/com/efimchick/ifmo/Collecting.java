package com.efimchick.ifmo;

import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.MyContainer;
import com.efimchick.ifmo.util.Person;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.*;

public class Collecting {
    public int sum(IntStream stream) {
        return stream.sum();
    }

    public int production(IntStream stream) {
        return stream.reduce((a, b) -> a * b).getAsInt();
    }

    public int oddSum(IntStream stream) {
        return stream.filter(v -> Math.abs(v % 2) == 1).sum();
    }

    public Map<Integer, Integer> sumByRemainder(int divisor, IntStream stream) {
        return stream.boxed()
                .collect(Collectors.groupingBy(v -> v % divisor, Collectors.summingInt(x -> x)));
    }

    public Map<Person, Double> totalScores(Stream<CourseResult> stream) {
        List<CourseResult> myList = stream.collect(Collectors.toList());
        final long courseCount = myList.stream()
                .map(CourseResult::getTaskResults)
                .flatMap(e -> e.keySet().stream())
                .distinct()
                .count();
        return myList.stream().collect(Collectors.
                toMap(CourseResult::getPerson,
                        s -> s.getTaskResults().values()
                                .stream()
                                .mapToDouble(Integer::doubleValue)
                                .sum() / courseCount));
    }

    public double averageTotalScore(Stream<CourseResult> stream) {
        Map<Person, Double> myMap = totalScores(stream);
        double totalAvg = myMap.values().stream()
                .mapToDouble(v -> v).sum();
        long persons = myMap.size();
        return totalAvg / persons;
    }

    public Map<String, Double> averageScoresPerTask(Stream<CourseResult> stream) {
        List<CourseResult> myList = stream.collect(Collectors.toList());
        Supplier<Stream<CourseResult>> supp = myList::stream;
        Map<String, Integer> myMap = supp.get().map(CourseResult::getTaskResults)
                .flatMap(e -> e.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::sum));
        int count = (int) supp.get().count();
        return myMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().doubleValue() / count
                ));
    }

    public Map<Person, String> defineMarks(Stream<CourseResult> stream) {
        Map<Person, Double> scoreMap = totalScores(stream);
        Map<Person, String> fin =
        scoreMap.entrySet().stream().collect(Collectors.toMap(k->k.getKey(),
                k->getLetter(k.getValue())));
        return fin;
    }

    public static String getLetter(double value) {
        if (value > 90) {
            return "A";
        }
        if (value >= 83) {
            return "B";
        }
        if (value >= 75) {
            return "C";
        }
        if (value >= 68) {
            return "D";
        }
        if (value >= 60) {
            return "E";
        }
            return "F";
    }

    public String easiestTask(Stream<CourseResult> stream) {
        Map<String, Double> myMap = averageScoresPerTask(stream);
        Optional<Map.Entry<String, Double>> max = myMap.entrySet()
                .stream().max(Comparator.comparingDouble(Map.Entry::getValue));
        return max.get().getKey();
    }

    public Collector printableStringCollector() {
        return Collector.of(
                MyContainer::new,
                (l, r) -> l.add((CourseResult) r),
                MyContainer::merge,
                MyContainer::print
        );
    }
}
