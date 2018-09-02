package ru.supernacho.tkb.tz.moneytransfer.model.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardsCollection {
    private static final String SENDERS = "senders";
    private static final String RECEIVERS = "receivers";
    private Map<String, List<Card>> collection;

    public CardsCollection() {
        this.collection = new HashMap<>();
        collection.put(SENDERS, new ArrayList<>());
        collection.put(RECEIVERS, new ArrayList<>());
    }

    public List<Card> getSenderCards() {
        return collection.get(SENDERS);
    }

    public boolean addToSenders(Card card){
        if (!collection.get(SENDERS).contains(card)){
            collection.get(SENDERS).add(card);
            return true;
        }
        return false;
    }

    public void addAllToSenders(List<Card> list){
        getSenderCards().clear();
        getSenderCards().addAll(list);
    }

    public void addAllToBeneficiary(List<Card> list){
        getBeneficiaryCards().clear();
        getBeneficiaryCards().addAll(list);
    }

    public boolean addToBeneficiary(Card card){
        if (!collection.get(RECEIVERS).contains(card)){
            collection.get(RECEIVERS).add(card);
            return true;
        }
        return false;
    }

    public List<Card> getBeneficiaryCards() {
        return collection.get(RECEIVERS);
    }
}
