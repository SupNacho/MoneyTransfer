package ru.supernacho.tkb.tz.moneytransfer.model;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import ru.supernacho.tkb.tz.moneytransfer.App;

public class PersistenceIO implements IPersistenceIO {

    private static final String CARDS_COLLECTION = "cardsCollection";
    private static final String DEF_COLLECTION = "{\"collection\":{\"receivers\":[],\"senders\":[]}}";

    @Inject
    App app;

    public PersistenceIO(App app) {
        this.app = app;
    }

    @Override
    public void saveData(String userToken, String cardsCollection) {
        if (userToken != null) {
            SharedPreferences.Editor editor = app.
                    getSharedPreferences(userToken, Context.MODE_PRIVATE).edit();
            editor.putString(CARDS_COLLECTION, cardsCollection);
            editor.apply();
        }
    }

    @Override
    public String loadCardsData(String userToken) {
        if (userToken != null) {
            SharedPreferences sharedPreferences = app
                    .getSharedPreferences(userToken, Context.MODE_PRIVATE);
            if (sharedPreferences.contains(CARDS_COLLECTION))
                return sharedPreferences.getString(CARDS_COLLECTION, DEF_COLLECTION);
        }
        return DEF_COLLECTION;
    }
}
