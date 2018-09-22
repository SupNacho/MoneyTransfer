package ru.supernacho.tkb.tz.moneytransfer.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ru.supernacho.tkb.tz.moneytransfer.R;
import ru.supernacho.tkb.tz.moneytransfer.databinding.CachedCardBinding;
import ru.supernacho.tkb.tz.moneytransfer.databinding.NewCardBinding;
import ru.supernacho.tkb.tz.moneytransfer.view.CachedCardView;
import ru.supernacho.tkb.tz.moneytransfer.view.NewCardView;
import ru.supernacho.tkb.tz.moneytransfer.viewmodel.CardViewModel;
import ru.supernacho.tkb.tz.moneytransfer.viewmodel.MainViewModel;

public abstract class VmAbstractAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<CardViewModel> cards;
    private LayoutInflater layoutInflater;
    private boolean isBeneficiary;
    private MainViewModel mainViewModel;

    public VmAbstractAdapter(List<CardViewModel> cards, MainViewModel mainViewModel, boolean isBeneficiary) {
        this.cards = cards;
        this.isBeneficiary = isBeneficiary;
        this.mainViewModel = mainViewModel;
    }

    @Override
    public int getItemViewType(int position) {
        if (cards.get(position).isNewCard()) return ViewType.NEW_CARD;
        else return ViewType.CACHED_CARD;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ViewType.NEW_CARD:
                NewCardBinding newCardBinding = DataBindingUtil.inflate(layoutInflater, R.layout.new_card_view_holder, parent, false);
                return new NewCardView(newCardBinding, isBeneficiary);
            default:
                CachedCardBinding cachedCardViewBinding = DataBindingUtil.inflate(layoutInflater, R.layout.cached_card_view, parent, false);
                return new CachedCardView(cachedCardViewBinding, isBeneficiary);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        CardViewModel cardViewModel = cards.get(position);
        cardViewModel.setMainViewModel(mainViewModel);
        switch (viewType) {
            case ViewType.NEW_CARD:
                NewCardView newCardView = (NewCardView) holder;
                newCardView.bind(cardViewModel);
                break;
            default:
                CachedCardView cachedCardView = (CachedCardView) holder;
                cachedCardView.bind(cardViewModel);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
