package com.epam.rd.autocode.startegy.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BridgeCardDealingStrategy implements CardDealingStrategy {
    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Deck holdemDeck = deck;
        Map<String, List<Card>> res = new HashMap<>();
        int hand = 13;
        for (int i = 0; i < hand; i++) {
            for (int j = 0; j < players; j++) {
                String currentPlayer = "Player " + (j + 1);
                if (!res.containsKey(currentPlayer)) {
                    res.put(currentPlayer, new ArrayList<>());
                }
                res.get(currentPlayer).add(holdemDeck.dealCard());
            }
        }
        if (holdemDeck.size() > 0) {
            res.put("Remaining", holdemDeck.restCards());
        }
        return res;
    }
}
