package ru.supernacho.tkb.tz.moneytransfer.view;


import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;

public interface MainView {
    void viewResult(Card sender, Card beneficiary, String amount);
    void showError();
}
