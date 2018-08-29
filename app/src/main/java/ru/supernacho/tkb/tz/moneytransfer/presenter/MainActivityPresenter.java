package ru.supernacho.tkb.tz.moneytransfer.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import ru.supernacho.tkb.tz.moneytransfer.model.IPersistenceRepository;
import ru.supernacho.tkb.tz.moneytransfer.model.IUserRepository;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.CardsCollection;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.User;
import ru.supernacho.tkb.tz.moneytransfer.view.MainView;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainView> implements IMainPresenter{
    private Scheduler uiScheduler;
    @Inject
    IPersistenceRepository repository;

    @Inject
    IUserRepository userRepository;

    public MainActivityPresenter(Scheduler uiScheduler) {
        this.uiScheduler = uiScheduler;
    }

    public void startTransfer(int senderPos, int beneficiaryPos){

        repository.addCards(repository.getSenderCards().get(senderPos),
                repository.getBeneficiaryCards().get(beneficiaryPos),
                userRepository.getCurrentUser().getToken());
        getCardsData();
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
