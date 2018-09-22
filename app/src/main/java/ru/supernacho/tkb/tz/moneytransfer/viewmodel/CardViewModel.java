package ru.supernacho.tkb.tz.moneytransfer.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.ObservableField;

import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;

public class CardViewModel extends ViewModel {
    public ObservableField<String> cardNumber = new ObservableField<>();
    public ObservableField<String>  expDate = new ObservableField<>();
    public ObservableField<String>  cVv = new ObservableField<>();
    private ObservableField<Boolean>  isNewCard = new ObservableField<>();
    private MainViewModel mainViewModel;

    public CardViewModel() {
    }

    public CardViewModel(Card card) {
        this.cardNumber.set(card.getNumber());
        this.expDate.set(card.getExpire());
        this.cVv.set(card.getCvv());
        this.isNewCard.set(card.isNewCard());
        cardNumber.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                
            }
        });
    }

    public boolean isNewCard() {
        return isNewCard.get() != null ? isNewCard.get() : false;
    }

    public void setNewCard(boolean newCard) {
        isNewCard.set(newCard);
    }

    public MainViewModel getMainViewModel() {
        return mainViewModel;
    }

    public void setMainViewModel(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }
}
