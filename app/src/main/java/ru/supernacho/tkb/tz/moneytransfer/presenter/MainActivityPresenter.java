package ru.supernacho.tkb.tz.moneytransfer.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import ru.supernacho.tkb.tz.moneytransfer.model.IPersistenceRepository;
import ru.supernacho.tkb.tz.moneytransfer.view.MainView;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainView> implements IMainPresenter{
    private Scheduler uiScheduler;

    @Inject
    IPersistenceRepository repository;

    public MainActivityPresenter(Scheduler uiScheduler) {
        this.uiScheduler = uiScheduler;
    }
}
