package ru.supernacho.tkb.tz.moneytransfer.presenter;

import java.util.List;

import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;

public interface IMainPresenter {
    void startTransfer(String amount);
    void getCardsData();
    void setUser(String token);
    List<Card> getSenderCards();
    List<Card> getBeneficiaryCards();
}
