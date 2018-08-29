package ru.supernacho.tkb.tz.moneytransfer.model;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import ru.supernacho.tkb.tz.moneytransfer.App;

public class PersistenceIO implements IPersistenceIO {

    private static final String SENDER_CARDS = "senderCards";
    private static final String BENEFICIARY_CARDS = "beneficiaryCards";

    @Inject
    App app;

    public PersistenceIO(App app) {
        this.app = app;
    }

    @Override
    public void saveData(String userToken, String senderCards, String beneficiaryCards) {
        SharedPreferences.Editor editor = app.
                getSharedPreferences(userToken, Context.MODE_PRIVATE).edit();
        editor.putString(SENDER_CARDS, senderCards);
        editor.putString(BENEFICIARY_CARDS, beneficiaryCards);
        editor.apply();
    }

    @Override
    public String loadSenderData(String userToken) {
        return getStringData(userToken, SENDER_CARDS);
    }

    @Override
    public String loadBeneficiaryData(String userToken) {
        return getStringData(userToken, BENEFICIARY_CARDS);
    }

    private String getStringData(String userToken, String key) {
        SharedPreferences sharedPreferences = app
                .getSharedPreferences(userToken, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(key))
            return sharedPreferences.getString(key, "[]");
        return "[]";
    }
}
