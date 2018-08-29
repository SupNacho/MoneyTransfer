package ru.supernacho.tkb.tz.moneytransfer.model;

import java.util.List;

import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;

public interface IPersistenceRepository {
    List<Card> getSenderCards();
    List<Card> getBeneficiaryCards();
    void addCard(Card newCard, String userToken);
    void getData(String userToken);
}
