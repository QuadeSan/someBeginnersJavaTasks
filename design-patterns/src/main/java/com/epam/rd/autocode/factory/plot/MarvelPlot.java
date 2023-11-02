package com.epam.rd.autocode.factory.plot;

import java.util.Arrays;
import java.util.StringJoiner;

public class MarvelPlot implements Plot{
    private final Character[] heroes;
    private final EpicCrisis epicCrisis;
    private final Character villain;

    public MarvelPlot(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        this.heroes = heroes;
        this.epicCrisis = epicCrisis;
        this.villain = villain;
    }

    @Override
    public String toString() {
        StringJoiner heroesSB = new StringJoiner(", ");
        Arrays.stream(heroes).forEach(e->heroesSB.add("brave " + e.name()));
        return epicCrisis.name() + " threatens the world. But "
                +heroesSB + " on guard. So, no way that intrigues of "
                +villain.name() + " overcome the willpower of inflexible heroes";
    }
}
