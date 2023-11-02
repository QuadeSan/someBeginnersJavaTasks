package com.efimchick.ifmo.streams.countwords;


import java.util.*;
import java.util.stream.Collectors;

public class Words {

    public String countWords(List<String> lines) {
        Map<String,Integer> collect = lines.stream()
                .map(l->l.split("\\P{L}+")).flatMap(Arrays::stream)
                .filter(e->e.length() >= 4)
                .collect(Collectors.toMap(String::toLowerCase, w -> 1, Integer::sum));
        LinkedHashMap<String, Integer> countByWordSorted = collect.entrySet()
                .stream()
                .filter(e->e.getValue() >= 10)
                .sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue)
                        .reversed().thenComparing(Map.Entry::getKey))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> {
                            throw new IllegalStateException();
                        },
                        LinkedHashMap::new
                ));
        StringBuilder res = new StringBuilder();
        countByWordSorted.forEach((key, value) -> res.append(key).append(" - ").append(value).append("\n"));
        res.delete(res.lastIndexOf("\n"),res.length());

        return res.toString();
    }
}
