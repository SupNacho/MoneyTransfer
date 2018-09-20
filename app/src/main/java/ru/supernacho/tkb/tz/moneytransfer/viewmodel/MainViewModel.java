package ru.supernacho.tkb.tz.moneytransfer.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.ObservableField;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.supernacho.tkb.tz.moneytransfer.App;
import ru.supernacho.tkb.tz.moneytransfer.model.IPersistenceRepository;
import ru.supernacho.tkb.tz.moneytransfer.model.PersistenceIO;
import ru.supernacho.tkb.tz.moneytransfer.model.PersistenceRepository;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.CardsCollection;

public class MainViewModel extends ViewModel {

    public final ObservableField<Boolean> amountAccepted;
    public final ObservableField<String> amountField;
    public final MutableLiveData<List<CardViewModel>> senderCards = new MutableLiveData<>();
    public final MutableLiveData<List<CardViewModel>> receiverCards = new MutableLiveData<>();
    private IPersistenceRepository repository;
    int i = 0;

    @Inject
    public MainViewModel(IPersistenceRepository repository) {
        this.repository = repository;
        this.amountAccepted = new ObservableField<>(false);
        this.amountField = repository.getAmountField();
        this.senderCards.setValue(new ArrayList<>());
        this.receiverCards.setValue(new ArrayList<>());
        amountField.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                String str = amountField.get();
                if (str != null && str.length() > 1) {
                    if (str.charAt(0) == '0' && str.charAt(1) != '.') {
                        str = "" + str.charAt(1);
                        amountField.set(str);
                    }
                }
            }
        });
        getCards(repository);
    }

    private void getCards(IPersistenceRepository repository) {
        repository.getCardsData("000")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CardsCollection>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CardsCollection cardsCollection) {
                        for (Card card : cardsCollection.getSenderCards()) {
                            Objects.requireNonNull(senderCards.getValue()).add(new CardViewModel(card));
                        }
                        List<CardViewModel> tmpSenders = Objects.requireNonNull(senderCards.getValue());
                        tmpSenders.add(new CardViewModel(new Card(true)));
                        senderCards.setValue(tmpSenders);
                        for (Card card : cardsCollection.getBeneficiaryCards()) {
                            Objects.requireNonNull(receiverCards.getValue()).add(new CardViewModel(card));
                        }
                        List<CardViewModel> tmpReceivers = Objects.requireNonNull(receiverCards.getValue());
                        tmpReceivers.add(new CardViewModel(new Card(true)));
                        receiverCards.setValue(tmpReceivers);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void click() {
        repository.click();
        List<CardViewModel> tmpList = senderCards.getValue();
        tmpList.add(new CardViewModel(new Card("000002" + ++i, "12/15", "STB")));
        senderCards.setValue(tmpList);
//        receiverCards.getValue().add(new CardViewModel(new Card("00000" + ++i+i, "12/15", "STB")));

    }
}
