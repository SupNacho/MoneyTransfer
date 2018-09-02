package ru.supernacho.tkb.tz.moneytransfer.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface MainView extends MvpView{
    void updateAdapters();
    @StateStrategyType(value = SkipStrategy.class)
    void requestSignIn();
    @StateStrategyType(value = SkipStrategy.class)
    void viewResult(Card sender, Card beneficiary, String amount);
    void viewError(int errorType);
}
