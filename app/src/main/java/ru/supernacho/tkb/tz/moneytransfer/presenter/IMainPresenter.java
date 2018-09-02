package ru.supernacho.tkb.tz.moneytransfer.presenter;

import java.util.List;

import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
import ru.supernacho.tkb.tz.moneytransfer.utils.InputChecker;
import ru.supernacho.tkb.tz.moneytransfer.view.ErrorTypes;

public interface IMainPresenter {
    void setSenderPos(int index);
    void checkSenderPos(int index);
    void setBeneficiaryPos(int index);
    void checkBeneficiaryPos(int index);
    void startTransfer(String amount);
    void getCardsData();
    void setUser(String token);
    List<Card> getSenderCards();
    List<Card> getBeneficiaryCards();
}
