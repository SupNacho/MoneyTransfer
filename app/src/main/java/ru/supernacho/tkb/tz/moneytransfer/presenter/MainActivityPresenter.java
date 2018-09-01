package ru.supernacho.tkb.tz.moneytransfer.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;
import java.util.Stack;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.supernacho.tkb.tz.moneytransfer.model.IPersistenceRepository;
import ru.supernacho.tkb.tz.moneytransfer.model.IUserRepository;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.CardsCollection;
import ru.supernacho.tkb.tz.moneytransfer.utils.InputChecker;
import ru.supernacho.tkb.tz.moneytransfer.view.ErrorTypes;
import ru.supernacho.tkb.tz.moneytransfer.view.MainView;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainView> implements IMainPresenter{
    private Scheduler uiScheduler;
    private Stack<Integer> senderPosHolder;
    private Stack<Integer> beneficiaryPosHolder;
    private CardsCollection cardsCollection;
    private Disposable disposableGetter;

    @Inject
    IPersistenceRepository repository;

    @Inject
    IUserRepository userRepository;

    public MainActivityPresenter(Scheduler uiScheduler) {
        this.uiScheduler = uiScheduler;
        this.senderPosHolder = new Stack<>();
        this.beneficiaryPosHolder = new Stack<>();
        this.cardsCollection = new CardsCollection();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().requestSignIn();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposableGetter != null && !disposableGetter.isDisposed()) disposableGetter.dispose();
    }

    public void setSenderPos(int index){
        senderPosHolder.push(index);
    }

    public void checkSenderPos(int index){
        if (senderPosHolder.peek() == index) senderPosHolder.pop();
    }
    public void setBeneficiaryPos(int index){
        beneficiaryPosHolder.push(index);
    }

    public void checkBeneficiaryPos(int index){
        if (beneficiaryPosHolder.peek() == index) beneficiaryPosHolder.pop();
    }

    public void startTransfer(String amount){
        Card senderCard = cardsCollection.getSenderCards().get(senderPosHolder.peek());
        Card beneficiaryCard = cardsCollection.getBeneficiaryCards().get(beneficiaryPosHolder.peek());
        if (InputChecker.checkSenderReady(senderCard) && InputChecker.checkBeneficiaryReady(beneficiaryCard)
                && InputChecker.checkAmount(amount)) {
            getViewState().viewResult(senderCard, beneficiaryCard, amount);
            addCards(senderCard, beneficiaryCard);
        } else {
            getViewState().viewError(ErrorTypes.CARD_DATA_ERROR);
        }
    }

    private void addCards(Card senderCard, Card beneficiaryCard) {
        if (senderCard.isNewCard() || beneficiaryCard.isNewCard())
            repository.addCards(senderCard, beneficiaryCard, userRepository.getCurrentUser().getToken());
        getCardsData();
    }

    public void setUser(String token){
        userRepository.setUser(token);
        getCardsData();
    }

    public List<Card> getSenderCards(){
        return cardsCollection.getSenderCards();
    }

    public List<Card> getBeneficiaryCards(){
        return cardsCollection.getBeneficiaryCards();
    }

    public void getCardsData(){
        disposableGetter = repository.getCardsData(userRepository.getCurrentUser().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(uiScheduler)
                .subscribe(receivedCardsCollection -> {
                    updateCollection(receivedCardsCollection);
                    getViewState().updateAdapters();
                });
    }

    private void updateCollection(CardsCollection receivedCardsCollection) {
        cardsCollection.addAllToSenders(receivedCardsCollection.getSenderCards());
        cardsCollection.addAllToBeneficiary(receivedCardsCollection.getBeneficiaryCards());
        cardsCollection.addToSenders(new Card(true));
        cardsCollection.addToBeneficiary(new Card(true));
    }
}
