package ru.supernacho.tkb.tz.moneytransfer.model;

import java.util.List;

import io.reactivex.Observable;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;

public interface IPersistenceRepository {
    List<Card> getSenderCards();
    List<Card> getBeneficiaryCards();
    void addCards(Card newCard, Card newBeneficiaryCard, String userToken);
    Observable<Boolean> getCardsData(String userToken);
}
