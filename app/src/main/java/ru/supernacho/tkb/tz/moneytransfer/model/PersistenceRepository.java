package ru.supernacho.tkb.tz.moneytransfer.model;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
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
        boolean isNewSender = collection.checkSenderDubles(newSenderCard);
        boolean isNewBeneficiary = collection.checkBeneDubles(newBeneficiaryCard);
//        if (newSenderCard.isNewCard()) isNewSender = collection.addToSenders(newSenderCard);
//        if (newBeneficiaryCard.isNewCard()) isNewbeneficiary = collection.addToBeneficiary(newBeneficiaryCard);
        if (isNewSender || isNewBeneficiary)
            persistenceIO.saveData(userToken, gson.toJson(collection));
    }

    @Override
    public Observable<Boolean> getCardsData(String userToken) {
        return Observable.create(emit -> {
            String result = persistenceIO.loadCardsData(userToken);
            updateCollection(gson.fromJson(result, CardsCollection.class));
            emit.onNext(true);
        });
    }

    private void updateCollection(CardsCollection tempCollection) {
        collection.addAllToSenders(tempCollection.getSenderCards());
        collection.addToSenders(new Card(true));
        collection.addAllToBeneficiary(tempCollection.getBeneficiaryCards());
        collection.addToBeneficiary(new Card(true));
    }
}
