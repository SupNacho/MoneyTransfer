package ru.supernacho.tkb.tz.moneytransfer.model.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CardsCollection {
    private static String SENDERS = "senders";
    private static String RECEIVERS = "receivers";
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

    public boolean checkSenderDubles(Card card){
        return !getSenderCards().subList(0, getSenderCards().size()-2).contains(card);
    }
    public boolean checkBeneDubles(Card card){
        return !getBeneficiaryCards().subList(0, getBeneficiaryCards().size()-2).contains(card);
    }

    public void addAllToSenders(List<Card> list){
        getSenderCards().clear();
        getSenderCards().addAll(new HashSet<>(list));
    }

    public void addAllToBeneficiary(List<Card> list){
        getBeneficiaryCards().clear();
        getBeneficiaryCards().addAll(new HashSet<>(list));
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
