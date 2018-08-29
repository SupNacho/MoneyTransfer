package ru.supernacho.tkb.tz.moneytransfer.view.alert;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import ru.supernacho.tkb.tz.moneytransfer.presenter.MainActivityPresenter;

public class Alert {
    public static void show(Context context, MainActivityPresenter presenter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder
                .setTitle("Choose User")
                .setPositiveButton("User with token", (dialog, which) -> presenter.setUser("12234"))
                .setNegativeButton("User without token", ((dialog, which) -> presenter.setUser(null)))
                .setCancelable(true)
                .show();
    }
}
