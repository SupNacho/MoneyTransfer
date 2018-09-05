package ru.supernacho.tkb.tz.moneytransfer.model;

import android.databinding.ObservableField;
import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.CardsCollection;

public class PersistenceRepository implements IPersistenceRepository {
    private CardsCollection collection = new CardsCollection();
    private final ObservableField<String> amountField = new ObservableField<>();
    private final ObservableField<CardsCollection> cards = new ObservableField<>();
    private Gson gson;

    @Inject
    IPersistenceIO persistenceIO;

    public PersistenceRepository(IPersistenceIO persistenceIO) {
        this.persistenceIO = persistenceIO;
        this.gson = new Gson();
    }

    @Override
    public void addCards(Card newSenderCard, Card newBeneficiaryCard, String userToken) {
        boolean isNewSender = collection.addToSenders(newSenderCard);
        boolean isNewBeneficiary = collection.addToBeneficiary(newBeneficiaryCard);
        if (isNewSender || isNewBeneficiary)
            persistenceIO.saveData(userToken, gson.toJson(collection));
    }

    @Override
    public Observable<CardsCollection> getCardsData(String userToken) {
        return Observable.create(emit -> {
            String result = persistenceIO.loadCardsData(userToken);
            updateCollection(gson.fromJson(result, CardsCollection.class));
            emit.onNext(collection);
        });
    }

    private void updateCollection(CardsCollection tempCollection) {
        collection.addAllToSenders(tempCollection.getSenderCards());
        collection.addAllToBeneficiary(tempCollection.getBeneficiaryCards());
    }

    public ObservableField<String> getAmountField() {
        return amountField;
    }

    public void click(){
        Log.d("++", " Amount in repo: " + amountField.get());
    }
}
