package ru.supernacho.tkb.tz.moneytransfer.model;

import io.reactivex.Observable;

public interface IPersistenceIO {
    void saveData(String userToken, String cardsCollection);
    String loadCardsData(String userToken);
}
