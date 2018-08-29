package ru.supernacho.tkb.tz.moneytransfer.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.CardsCollection;

public class PersistenceRepository implements IPersistenceRepository {
    private List<Card> senderCards = new ArrayList<>();
    private List<Card> beneficiaryCards = new ArrayList<>();
    private Gson gson;

    @Inject
    IPersistenceIO persistenceIO;

    public PersistenceRepository(IPersistenceIO persistenceIO) {
        this.persistenceIO = persistenceIO;
        this.gson = new Gson();
    }

    @Override
    public List<Card> getSenderCards() {
        return senderCards;
    }

    @Override
    public List<Card> getBeneficiaryCards() {
        return beneficiaryCards;
    }

    @Override
    public void addCard(Card newSenderCard, Card newBeneficiaryCard, String userToken) {
        CardsCollection collection = new CardsCollection();
        collection.getSenderCards().add(newSenderCard);
        collection.getBeneficiaryCards().add(newBeneficiaryCard);
        persistenceIO.saveData(userToken, gson.toJson(collection));
    }

    @Override
    public void getData(String userToken) {
        // TODO: 28.08.2018 переделать без RX из IO
        Disposable disposeSender = persistenceIO.loadSenderData(userToken)
                .subscribeOn(Schedulers.io())
                .subscribe(s -> {
                    senderCards.clear();
                    senderCards.addAll(Arrays.asList(gson.fromJson(s, Card[].class)));
                });

        Disposable disposeBeneficiary = persistenceIO.loadBeneficiaryData(userToken)
                .subscribeOn(Schedulers.io())
                .subscribe(s ->{
                    beneficiaryCards.clear();
                    beneficiaryCards.addAll(Arrays.asList(gson.fromJson(s, Card[].class)));
                });
    }
}
