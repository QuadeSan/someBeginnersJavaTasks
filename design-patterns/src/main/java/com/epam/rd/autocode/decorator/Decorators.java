package com.epam.rd.autocode.decorator;

import java.util.ArrayList;
import java.util.List;

public class Decorators {
    public static List<String> evenIndexElementsSubList(List<String> sourceList) {
//        throw new UnsupportedOperationException();
        List<String> res = new ArrayList<>();
        for (String el : sourceList){
            if (sourceList.indexOf(el) % 2 == 0){
                res.add(el);
            }
        }
        return res;
    }


}
