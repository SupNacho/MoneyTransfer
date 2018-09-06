package ru.supernacho.tkb.tz.moneytransfer.model;

import android.databinding.ObservableField;

import io.reactivex.Observable;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.CardsCollection;

public interface IPersistenceRepository {
    void addCards(Card newCard, Card newBeneficiaryCard, String userToken);

    Observable<CardsCollection> getCardsData(String userToken);

    ObservableField<String> getAmountField();

    void click();
}
