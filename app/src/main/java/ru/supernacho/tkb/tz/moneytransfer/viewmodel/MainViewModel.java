package ru.supernacho.tkb.tz.moneytransfer.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
import ru.supernacho.tkb.tz.moneytransfer.utils.InputChecker;
import ru.supernacho.tkb.tz.moneytransfer.view.MainView;

public class MainViewModel extends ViewModel {

    public final ObservableField<Boolean> amountAccepted;
    public final ObservableField<String> amountField;
    public final MutableLiveData<List<CardViewModel>> senderCards = new MutableLiveData<>();
    public final MutableLiveData<List<CardViewModel>> receiverCards = new MutableLiveData<>();
    private String userToken;
    private IPersistenceRepository repository;
    private LinearLayoutManager senderLayoutManager;
    private LinearLayoutManager receiverLayoutManager;
    private MainView mainView;

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
        getCards();
    }

    private void getCards() {
        repository.getCardsData(userToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CardsCollection>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CardsCollection cardsCollection) {
                        List<CardViewModel> tmpSenders = Objects.requireNonNull(senderCards.getValue());
                        tmpSenders.clear();
                        for (Card card : cardsCollection.getSenderCards()) {
                            tmpSenders.add(new CardViewModel(card));
                        }
                        tmpSenders.add(new CardViewModel(new Card(true)));
                        senderCards.setValue(tmpSenders);

                        List<CardViewModel> tmpReceivers = Objects.requireNonNull(receiverCards.getValue());
                        tmpReceivers.clear();
                        for (Card card : cardsCollection.getBeneficiaryCards()) {
                            tmpReceivers.add(new CardViewModel(card));
                        }
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
        Log.d("000", "Sender: " + senderLayoutManager.findFirstCompletelyVisibleItemPosition());
        Log.d("000", "Receiver: " + receiverLayoutManager.findFirstCompletelyVisibleItemPosition());

        int senderPos = senderLayoutManager.findFirstCompletelyVisibleItemPosition();
        int receiverPos = receiverLayoutManager.findFirstCompletelyVisibleItemPosition();

        CardViewModel currentSender = senderCards.getValue().get(senderPos);
        CardViewModel currentReceiver = receiverCards.getValue().get(receiverPos);
        currentSender.cardNumber.get();

        String senderCardNumber = currentSender.cardNumber.get();
        String senderCvvNumber = currentSender.cVv.get();
        String receiverCardNumber = currentReceiver.cardNumber.get();
        String senderExpDate = currentSender.expDate.get();

        if (InputChecker.checkCard(senderCardNumber) && InputChecker.checkDate(senderExpDate)
                && InputChecker.checkDate(senderExpDate) && InputChecker.checkCVC(senderCvvNumber)
                && InputChecker.checkCard(receiverCardNumber) && InputChecker.checkAmount(amountField.get())) {
            Card senderCard = new Card(senderCardNumber, senderExpDate, "Bank of Tests");
            Card receiverCard = new Card(receiverCardNumber);
            repository.addCards(senderCard, receiverCard, userToken);
            mainView.viewResult(senderCard, receiverCard, amountField.get());
            getCards();
        }
        else Log.d("000", " input errors");
    }

    public void setManagers(LinearLayoutManager senderManager, LinearLayoutManager receiverManager) {
        this.senderLayoutManager = senderManager;
        this.receiverLayoutManager = receiverManager;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
        getCards();
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
}
