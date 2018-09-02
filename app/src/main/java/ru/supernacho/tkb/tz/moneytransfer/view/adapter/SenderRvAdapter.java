package ru.supernacho.tkb.tz.moneytransfer.view.adapter;

import android.support.annotation.NonNull;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
import ru.supernacho.tkb.tz.moneytransfer.presenter.MainActivityPresenter;
import ru.supernacho.tkb.tz.moneytransfer.view.CachedCardView;
import ru.supernacho.tkb.tz.moneytransfer.view.NewCardView;

public class SenderRvAdapter extends AbstractRvAdapter {

    public SenderRvAdapter(MainActivityPresenter presenter) {
        super(presenter, false);
    }

    @Override
    void prepareCachedCardData(@NonNull CachedCardView holder, Card card) {
        holder.setCard(card);
        holder.tvCardNumber.setText(card.getNumber());
        holder.tvBankLabel.setText(card.getBankName());
        holder.etCacheCVV.setText(card.getCvv());
        holder.etCacheCVV.clearFocus();
    }

    @Override
    void prepareNewCardData(@NonNull NewCardView holder, Card card) {
        holder.setCard(card);
        holder.etCardNumber.setText(card.getNumber());
        holder.etCardNumber.clearFocus();
        holder.etCVV.setText(card.getCvv());
        holder.etCVV.clearFocus();
        holder.etExpDate.setText(card.getExpire());
        holder.etExpDate.clearFocus();
    }
}
