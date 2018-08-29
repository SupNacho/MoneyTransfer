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

    public void updateCollection(CardsCollection tempCollection){
        collection.clear();
        collection.addAll(tempCollection.getCollection());
        System.out.println("ss");
    }

    public List<Card> getSenderCards() {
        return collection.get(0);
    }

    public List<Card> getBeneficiaryCards() {
        return collection.get(1);
    }
}
