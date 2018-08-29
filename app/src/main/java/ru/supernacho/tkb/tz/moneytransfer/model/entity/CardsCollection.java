package ru.supernacho.tkb.tz.moneytransfer.model.entity;

import java.util.ArrayList;
import java.util.List;

public class CardsCollection {
    private List<List<Card>> collection;
    private  List<Card> senderCards;
    private  List<Card> beneficiaryCards;

    public CardsCollection() {
        this.collection = new ArrayList<>();
        this.senderCards = new ArrayList<>();
        this.beneficiaryCards = new ArrayList<>();
        collection.add(senderCards);
        collection.add(beneficiaryCards);
    }

    public List<List<Card>> getCollection() {
        return collection;
    }

    public List<Card> getSenderCards() {
        return senderCards;
    }

    public List<Card> getBeneficiaryCards() {
        return beneficiaryCards;
    }
}
