package ru.supernacho.tkb.tz.moneytransfer.model.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardsCollection {
    public static String SENDERS = "senders";
    public static String RECEIVERS = "receivers";
    private Map<String, List<Card>> collection;

    public CardsCollection() {
        this.collection = new HashMap<>();
        collection.put(SENDERS, new ArrayList<>());
        collection.put(RECEIVERS, new ArrayList<>());
    }

    public List<Card> getSenderCards() {
        return collection.get(SENDERS);
    }

    public void addToSenders(Card card){
        if (!collection.get(SENDERS).contains(card)){
            collection.get(SENDERS).add(card);
        }
    }
    public void addToBeneficiary(Card card){
        if (!collection.get(RECEIVERS).contains(card)){
            collection.get(RECEIVERS).add(card);
        }
    }

    public List<Card> getBeneficiaryCards() {
        return collection.get(RECEIVERS);
    }
}
