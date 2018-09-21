package ru.supernacho.tkb.tz.moneytransfer.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import java.util.List;

import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;

public class CardViewModel extends ViewModel {
    public ObservableField<String> cardNumber = new ObservableField<>();
    public ObservableField<String>  expDate = new ObservableField<>();
    public ObservableField<String>  cVv = new ObservableField<>();
    private ObservableField<Boolean>  isNewCard = new ObservableField<>();

    public CardViewModel() {
    }

    public CardViewModel(Card card) {
        this.cardNumber.set(card.getNumber());
        this.expDate.set(card.getExpire());
        this.cVv.set(card.getCvv());
        this.isNewCard.set(card.isNewCard());
    }

    public boolean isNewCard() {
        return isNewCard.get() != null ? isNewCard.get() : false;
    }

    public void setNewCard(boolean newCard) {
        isNewCard.set(newCard);
    }
}
