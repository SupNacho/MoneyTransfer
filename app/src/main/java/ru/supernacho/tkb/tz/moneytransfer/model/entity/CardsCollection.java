package ru.supernacho.tkb.tz.moneytransfer.model.entity;

import java.util.ArrayList;
import java.util.List;

public class CardsCollection {
    private List<List<Card>> collection;

    public CardsCollection() {
        this.collection = new ArrayList<>();
        collection.add(new ArrayList<>());
        collection.add(new ArrayList<>());
    }

    public List<List<Card>> getCollection() {
        return collection;
    }

    public List<Card> getSenderCards() {
        return collection.get(0);
    }

    public void addToSenders(Card card){
        if (!collection.get(0).contains(card)){
            collection.get(0).add(card);
        }
    }
    public void addToBeneficiary(Card card){
        if (!collection.get(1).contains(card)){
            collection.get(1).add(card);
        }
    }

    public List<Card> getBeneficiaryCards() {
        return collection.get(1);
    }
}
