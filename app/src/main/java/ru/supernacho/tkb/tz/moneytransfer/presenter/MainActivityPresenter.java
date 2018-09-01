package ru.supernacho.tkb.tz.moneytransfer.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;
import java.util.Stack;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;
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
        Card senderCard = repository.getSenderCards().get(senderPosHolder.peek());
        Card beneficiaryCard = repository.getBeneficiaryCards().get(beneficiaryPosHolder.peek());
        if (checkSenderReady(senderCard) && checkBeneficiaryReady(beneficiaryCard)) {
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

    private boolean checkSenderReady(Card sender){
        return InputChecker.checkCard(sender.getNumber())
                && InputChecker.checkDate(sender.getExpire())
                && InputChecker.checkCVC(sender.getCvv());
    }

    private boolean checkBeneficiaryReady(Card beneficiary){
        return InputChecker.checkCard(beneficiary.getNumber());
    }

    public void setUser(String token){
        userRepository.setUser(token);
        getCardsData();
    }

    public List<Card> getSenderCards(){
        return repository.getSenderCards();
    }

    public List<Card> getBeneficiaryCards(){
        return repository.getBeneficiaryCards();
    }

    public void getCardsData(){
        repository.getCardsData(userRepository.getCurrentUser().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(uiScheduler)
                .subscribe(new DisposableObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            getViewState().updateAdapters();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        dispose();
                    }
                });
    }
}
