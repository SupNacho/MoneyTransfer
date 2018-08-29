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
import ru.supernacho.tkb.tz.moneytransfer.view.NewCardView;

public class BeneficiaryRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private MainActivityPresenter presenter;
    private List<Card> cards;

    public BeneficiaryRvAdapter(MainActivityPresenter presenter) {
        this.presenter = presenter;
        this.cards = presenter.getSenderCards();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cached_card_view, parent, false);
        return new NewCardView(view, false);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
