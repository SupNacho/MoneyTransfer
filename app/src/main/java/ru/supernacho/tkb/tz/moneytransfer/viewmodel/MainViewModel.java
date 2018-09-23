package ru.supernacho.tkb.tz.moneytransfer.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.supernacho.tkb.tz.moneytransfer.model.IPersistenceRepository;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.CardsCollection;
import ru.supernacho.tkb.tz.moneytransfer.utils.InputChecker;
import ru.supernacho.tkb.tz.moneytransfer.view.MainView;

public class MainViewModel extends ViewModel {

    public final ObservableField<Boolean> buttonVisible = new ObservableField<>(false);
    public final ObservableField<Boolean> amountAccepted = new ObservableField<>(false);
    public final ObservableField<Boolean> senderCardAccepted = new ObservableField<>(false);
    public final ObservableField<Boolean> senderDateAccepted = new ObservableField<>(false);
    public final ObservableField<Boolean> senderCvvAccepted = new ObservableField<>(false);
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

        initButtonStateCheckers();
        getCards();
    }

    private void initButtonStateCheckers() {
        Observable.OnPropertyChangedCallback callback = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                buttonVisible.set(checkCredentials());
            }
        };

        senderCardAccepted.addOnPropertyChangedCallback(callback);
        senderCvvAccepted.addOnPropertyChangedCallback(callback);
        senderDateAccepted.addOnPropertyChangedCallback(callback);
        amountAccepted.addOnPropertyChangedCallback(callback);
    }

    private boolean checkCredentials(){
        return senderCardAccepted.get() && senderDateAccepted.get() && senderCvvAccepted.get() && amountAccepted.get();
    }

    private void clearTransferState(){
        amountField.set("");
        senderCardAccepted.set(false);
        senderDateAccepted.set(false);
        senderCvvAccepted.set(false);
        amountAccepted.set(false);
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
                        updateCardList(tmpSenders, cardsCollection.getSenderCards());
                        senderCards.setValue(tmpSenders);

                        List<CardViewModel> tmpReceivers = Objects.requireNonNull(receiverCards.getValue());
                        updateCardList(tmpReceivers, cardsCollection.getBeneficiaryCards());
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

    private void updateCardList(List<CardViewModel> tmpList, List<Card> cardList) {
        tmpList.clear();
        for (Card card : cardList) {
            tmpList.add(new CardViewModel(card));
        }
        tmpList.add(new CardViewModel(new Card(true)));
    }

    public void click() {
        int senderPos = senderLayoutManager.findFirstCompletelyVisibleItemPosition();
        int receiverPos = receiverLayoutManager.findFirstCompletelyVisibleItemPosition();

        CardViewModel currentSender = senderCards.getValue().get(senderPos);
        CardViewModel currentReceiver = receiverCards.getValue().get(receiverPos);

        String senderCardNumber = currentSender.cardNumber.get();
        String senderCvvNumber = currentSender.cVv.get();
        String receiverCardNumber = currentReceiver.cardNumber.get();
        String senderExpDate = currentSender.expDate.get();

        checkSaveAndTransfer(senderCardNumber, senderCvvNumber, receiverCardNumber, senderExpDate);
    }

    private void checkSaveAndTransfer(String senderCardNumber, String senderCvvNumber, String receiverCardNumber, String senderExpDate) {
        if (InputChecker.checkCard(senderCardNumber) && InputChecker.checkDate(senderExpDate)
                && InputChecker.checkDate(senderExpDate) && InputChecker.checkCVC(senderCvvNumber)
                && InputChecker.checkCard(receiverCardNumber) && InputChecker.checkAmount(amountField.get())) {
            Card senderCard = new Card(senderCardNumber, senderExpDate, "Bank of Tests");
            Card receiverCard = new Card(receiverCardNumber);
            repository.addCards(senderCard, receiverCard, userToken);
            mainView.viewResult(senderCard, receiverCard, amountField.get());
            getCards();
            clearTransferState();
        }
        else mainView.showError();
    }

    public void setManagers(LinearLayoutManager senderManager, LinearLayoutManager receiverManager) {
        this.senderLayoutManager = senderManager;
        this.receiverLayoutManager = receiverManager;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
        getCards();
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
}
