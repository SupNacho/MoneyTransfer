package ru.supernacho.tkb.tz.moneytransfer.view;

import android.support.v7.widget.RecyclerView;
import ru.supernacho.tkb.tz.moneytransfer.databinding.CachedCardBinding;
import ru.supernacho.tkb.tz.moneytransfer.viewmodel.CardViewModel;

public class CachedCardView extends RecyclerView.ViewHolder implements IviewHolder{
    private boolean isBeneficiary;
    private CachedCardBinding binding;

    public CachedCardView(CachedCardBinding binding, boolean isBeneficiary) {
        super(binding.getRoot());
        this.binding = binding;
        this.isBeneficiary = isBeneficiary;

    }

    public void bind(CardViewModel cardViewModel){
        binding.setCardModel(cardViewModel);
        binding.executePendingBindings();
    }

}
