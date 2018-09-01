package ru.supernacho.tkb.tz.moneytransfer.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.supernacho.tkb.tz.moneytransfer.R;
import ru.supernacho.tkb.tz.moneytransfer.model.entity.Card;
import ru.supernacho.tkb.tz.moneytransfer.presenter.MainActivityPresenter;
import ru.supernacho.tkb.tz.moneytransfer.view.CachedCardView;
import ru.supernacho.tkb.tz.moneytransfer.view.NewCardView;

public class BeneficiaryRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private MainActivityPresenter presenter;
    private List<Card> cards;

    public BeneficiaryRvAdapter(MainActivityPresenter presenter) {
        this.presenter = presenter;
        this.cards = presenter.getBeneficiaryCards();
    }

    @Override
    public int getItemViewType(int position) {
        if (cards.get(position).isNewCard()) return ViewType.NEW_CARD;
        else return ViewType.CACHED_CARD;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ViewType.NEW_CARD:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_card_view_holder, parent, false);
                return new NewCardView(view, presenter, true);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cached_card_view, parent, false);
                return new CachedCardView(view, presenter, true);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        Card card = cards.get(position);
        switch (viewType) {
            case ViewType.CACHED_CARD:
                prepareCachedCard((CachedCardView) holder, card);
                break;
            default:
                prepareNewCardData((NewCardView) holder, card);
                break;
        }
    }

    private void prepareNewCardData(@NonNull NewCardView holder, Card card) {
        holder.setCard(card);
        holder.etCardNumber.setText(null);
        holder.etCardNumber.clearFocus();
    }

    private void prepareCachedCard(@NonNull CachedCardView holder, Card card) {
        holder.setCard(card);
        holder.tvCardNumber.setText(card.getNumber());
        holder.tvBanklabel.setText(card.getBankName());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
