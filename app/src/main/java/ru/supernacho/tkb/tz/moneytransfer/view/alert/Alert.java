package ru.supernacho.tkb.tz.moneytransfer.view.alert;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
import ru.supernacho.tkb.tz.moneytransfer.presenter.MainActivityPresenter;

public class Alert {

    private static final String DELIMITER = "\n";

    public static void showUserChooser(Context context, MainActivityPresenter presenter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder
                .setTitle("Choose User")
                .setPositiveButton("User with token", (dialog, which) -> presenter.setUser("12234"))
                .setNegativeButton("User without token", ((dialog, which) -> presenter.setUser(null)))
                .setCancelable(true)
                .show();
    }

    public static void showResult(Context context, Card sender, Card beneficiary, String amount) {
        String sb = "Sender info: " +
                sender.getBankName() + DELIMITER +
                sender.getNumber() + DELIMITER +
                sender.getExpire() + DELIMITER +
                sender.getCvv() + DELIMITER +
                "Beneficiary info: " +
                beneficiary.getNumber() + DELIMITER +
                "Amount: " + amount;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder
                .setTitle("Transaction info")
                .setMessage(sb)
                .setPositiveButton("close", (dialog, which) -> dialog.dismiss())
                .setCancelable(true)
                .show();
    }
}
