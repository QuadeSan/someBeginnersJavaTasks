package com.epam.rd.autocode.startegy.cards;

public class PlayebleCard implements Card {
    String name;

    public PlayebleCard(int name) {
        this.name = "" + name;
    }

    @Override
    public String name() {
        return name;
    }
}
