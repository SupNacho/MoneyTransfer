package ru.supernacho.tkb.tz.moneytransfer.view.alert;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import ru.supernacho.tkb.tz.moneytransfer.R;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
import ru.supernacho.tkb.tz.moneytransfer.presenter.MainActivityPresenter;

public class Alert {

    private static final String DELIMITER = "\n";
    private static final String TOKEN = "12234";

    public static void showUserChooser(Context context, MainActivityPresenter presenter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder
                .setTitle(R.string.alert_user_title)
                .setPositiveButton(R.string.alert_user_btn_positive, (dialog, which) -> presenter.setUser(TOKEN))
                .setNegativeButton(R.string.alert_user_btn_negative, ((dialog, which) -> presenter.setUser(null)))
                .setCancelable(true)
                .show();
    }

    public static void showResult(Context context, Card sender, Card beneficiary, String amount) {
        String sb = context.getResources().getString(R.string.alert_result_sender_label) +
                sender.getBankName() + DELIMITER +
                sender.getNumber() + DELIMITER +
                sender.getExpire() + DELIMITER +
                sender.getCvv() + DELIMITER +
                context.getResources().getString(R.string.alert_result_beneficiary_label) +
                beneficiary.getNumber() + DELIMITER +
                context.getResources().getString(R.string.alert_result_amount_label) + amount;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder
                .setTitle(R.string.alert_result_title)
                .setMessage(sb)
                .setPositiveButton(R.string.alert_result_btn_positive, (dialog, which) -> dialog.dismiss())
                .setCancelable(true)
                .show();
    }
}
