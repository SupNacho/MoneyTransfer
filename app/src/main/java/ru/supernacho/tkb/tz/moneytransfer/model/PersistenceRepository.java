package ru.supernacho.tkb.tz.moneytransfer.model;

import java.util.ArrayList;
import java.util.List;

import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;

public class PersisnaceRepository {
    private List<Card> senderCards = new ArrayList<>();
    private List<Card> beneficiaryCards = new ArrayList<>();

    public List<Card> getSenderCards() {
        return senderCards;
    }

    public List<Card> getBeneficiaryCards() {
        return beneficiaryCards;
    }
}
