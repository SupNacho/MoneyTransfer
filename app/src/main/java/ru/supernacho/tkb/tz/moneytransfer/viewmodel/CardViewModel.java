package ru.supernacho.tkb.tz.moneytransfer.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;

public class CardViewModel extends ViewModel {
    public String cardNumber;
    public String expDate;
    public String cVv;
    private boolean isNewCard;

    public CardViewModel() {
    }

    public CardViewModel(Card card) {
        this.cardNumber = card.getNumber();
        this.expDate = card.getExpire();
        this.cVv = card.getCvv();
        this.isNewCard = card.isNewCard();
    }

    public boolean isNewCard() {
        return isNewCard;
    }

    public void setNewCard(boolean newCard) {
        isNewCard = newCard;
    }
}
