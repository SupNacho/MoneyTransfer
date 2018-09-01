package ru.supernacho.tkb.tz.moneytransfer.model;

public interface IPersistenceIO {
    void saveData(String userToken, String cardsCollection);
    String loadCardsData(String userToken);
}
