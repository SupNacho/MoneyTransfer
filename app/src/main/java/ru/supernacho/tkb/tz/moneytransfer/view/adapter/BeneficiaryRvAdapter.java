package ru.supernacho.tkb.tz.moneytransfer.view.adapter;

import android.support.annotation.NonNull;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
import ru.supernacho.tkb.tz.moneytransfer.presenter.MainActivityPresenter;
import ru.supernacho.tkb.tz.moneytransfer.view.CachedCardView;
import ru.supernacho.tkb.tz.moneytransfer.view.NewCardView;

public class BeneficiaryRvAdapter extends AbstractRvAdapter {

    public BeneficiaryRvAdapter(MainActivityPresenter presenter) {
        super(presenter, true);
    }

    @Override
    void prepareNewCardData(@NonNull NewCardView holder, Card card) {
        holder.setCard(card);
        holder.etCardNumber.setText(null);
        holder.etCardNumber.clearFocus();
    }

    @Override
    void prepareCachedCardData(@NonNull CachedCardView holder, Card card) {
        holder.setCard(card);
        holder.tvCardNumber.setText(card.getNumber());
        holder.tvBanklabel.setText(card.getBankName());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
