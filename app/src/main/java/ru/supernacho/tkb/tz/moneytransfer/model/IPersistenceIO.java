package ru.supernacho.tkb.tz.moneytransfer.model;

import io.reactivex.Observable;

public interface IPersistenceIO {
    void saveData(String userToken, String senderCards, String beneficiaryCards);
    String loadSenderData(String userToken);
    String loadBeneficiaryData(String userToken);
}
