package ru.supernacho.tkb.tz.moneytransfer.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.observable.ObservableReduceMaybe;
import io.reactivex.schedulers.Schedulers;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.CardConstants;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.CardsCollection;

public class PersistenceRepository implements IPersistenceRepository {
    private CardsCollection collection = new CardsCollection();
    private Gson gson;

    @Inject
    IPersistenceIO persistenceIO;

    public PersistenceRepository(IPersistenceIO persistenceIO) {
        this.persistenceIO = persistenceIO;
        this.gson = new Gson();
    }

    @Override
    public List<Card> getSenderCards() {
        return collection.getSenderCards();
    }

    @Override
    public List<Card> getBeneficiaryCards() {
        return collection.getBeneficiaryCards();
    }

    @Override
    public void addCards(Card newSenderCard, Card newBeneficiaryCard, String userToken) {
        collection.getSenderCards().add(newSenderCard);
        collection.getBeneficiaryCards().add(newBeneficiaryCard);
        persistenceIO.saveData(userToken, gson.toJson(collection));
        System.out.println("ss");
    }

    @Override
    public Observable<Boolean> getCardsData(String userToken) {
        return Observable.create( emit -> {
            String result = persistenceIO.loadCardsData(userToken);
            updateCollection(gson.fromJson(result, CardsCollection.class));
            emit.onNext(true);
        });
    }

    private void updateCollection(CardsCollection tempCollection) {
        collection.getSenderCards().clear();
        collection.getSenderCards().addAll(tempCollection.getSenderCards());
        collection.getSenderCards().add(new Card(CardConstants.NEW_CARD));
        collection.getBeneficiaryCards().clear();
        collection.getBeneficiaryCards().addAll(tempCollection.getBeneficiaryCards());
        collection.getBeneficiaryCards().add(new Card(CardConstants.NEW_CARD));
    }
}
