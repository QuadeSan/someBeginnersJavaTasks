package com.epam.rd.autocode.startegy.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoolCardDealingStrategy implements CardDealingStrategy{
    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Deck holdemDeck = deck;
        Map<String, List<Card>> res = new HashMap<>();
        int hand = 6;
        int community = 1;
        for (int i = 0; i < hand; i++) {
            for (int j = 0; j < players; j++) {
                String currentPlayer = "Player " + (j + 1);
                if (!res.containsKey(currentPlayer)) {
                    res.put(currentPlayer, new ArrayList<>());
                }
                res.get(currentPlayer).add(holdemDeck.dealCard());
            }
        }
        for (int i = 0; i < community; i++) {
            String check = "Trump card";
            if (!res.containsKey(check)) {
                res.put(check, new ArrayList<>());
            }
            res.get(check).add(holdemDeck.dealCard());
        }
        res.put("Remaining", holdemDeck.restCards());
        return res;
    }
}
